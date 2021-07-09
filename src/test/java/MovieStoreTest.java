import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class MovieStoreTest {

    @Test
    public void returnNoResultWhenNoTitle() throws Exception {
        MovieStore movieStore = new MovieStore();
        List<Movie> results = movieStore.findByPartialTitle("abc");
        assertThat(results.size(), is(0));
    }

    @Test
    public void findAMovieWhenTitleMatches() throws Exception {
        MovieStore movieStore = new MovieStore();
        Movie harryPotter = new Movie("Harry Potter");
        movieStore.add(harryPotter);
        movieStore.add(new Movie("Star Wars"));
        movieStore.add(new Movie("Star Trek"));
        movieStore.add(new Movie("Hobbit"));
        List<Movie> results = movieStore.findByPartialTitle("arry");
        assertThat(results.size(), is(1));
        assertThat(results, contains(harryPotter));
    }
}
