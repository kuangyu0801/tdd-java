import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class MovieStoreTest {
    MovieStore movieStore = new MovieStore();
    private final Movie harryPotter = new Movie("Harry Potter");
    private final Movie startwars = new Movie("Star Wars");
    private final Movie startrek = new Movie("STAR Trek");
    private final Movie hobbit = new Movie("Hobbit");

    @Before
    public void setUp() throws Exception {
        movieStore.add(harryPotter);
        movieStore.add(startwars);
        movieStore.add(startrek);
        movieStore.add(hobbit);
    }

    @Test
    public void returnNoResultWhenNoTitle() throws Exception {
        List<Movie> results = movieStore.findByPartialTitle("abc");
        assertThat(results.size(), is(0));
    }

    @Test
    public void findAMovieWhenTitleMatches() throws Exception {
        List<Movie> results = movieStore.findByPartialTitle("arry");
        assertThat(results.size(), is(1));
        assertThat(results, contains(harryPotter));
    }

    @Test
    public void findMoviesWhenTitleMatches() throws Exception {

        List<Movie> results = movieStore.findByPartialTitle("tar");
        assertThat(results.size(), is(2));
        assertThat(results, containsInAnyOrder(startrek, startwars));
    }
}
