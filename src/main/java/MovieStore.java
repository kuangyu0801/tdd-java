import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;

public class MovieStore {
    List<Movie> movies = new LinkedList<Movie>();
    public List<Movie> findByPartialTitle(final String partialTitle) {
        List<Movie> result = new LinkedList<Movie>();
        Predicate predicate = new Predicate() {
            public boolean matches(Movie movie) {
                return movie.title().toUpperCase().contains(partialTitle.toUpperCase());
            }
        };
        return getMovies(result, predicate);
    }

    private List<Movie> getMovies(List<Movie> result, Predicate predicate) {
        for (Movie movie : movies) {
            if (predicate.matches(movie)) {
                result.add(movie);
            }
        }
        return result;
    }

    public void add(Movie movie) {
        movies.add(movie);
    }

    public List<Movie> findByPartialDirector(final String partialDirector) {
        List<Movie> result = new LinkedList<Movie>();
        Predicate predicate =  new Predicate() {
            public boolean matches(Movie movie) {
                return movie.director().toUpperCase().contains(partialDirector.toUpperCase());
            }
        };
        return getMovies(result, predicate);
    }

    public List<Movie> findByReleaseYear(final int from, final int to) {
        List<Movie> result = new LinkedList<Movie>();
        Predicate predicate = new Predicate() {
            public boolean matches(Movie movie) {
                return movie.releaseYear() >= from && movie.releaseYear() <= to;
            }
        };

        return getMovies(result, predicate);
    }

    interface Predicate {
        boolean matches(Movie movie);
    }
}
