import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;

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

    @Test
    public void findMoviesWhenTitleMatches() throws Exception {
        MovieStore movieStore = new MovieStore();
        Movie harryPotter = new Movie("Harry Potter");
        movieStore.add(harryPotter);
        Movie startwars = new Movie("Star Wars");
        movieStore.add(startwars);
        Movie startrek = new Movie("STAR Trek");
        movieStore.add(startrek);
        movieStore.add(new Movie("Hobbit"));
        List<Movie> results = movieStore.findByPartialTitle("tar");
        assertThat(results.size(), is(2));
        assertThat(results, containsInAnyOrder(startrek, startwars));
    }
}
