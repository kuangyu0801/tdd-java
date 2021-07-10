import java.util.LinkedList;
import java.util.List;

public class MovieStore {
    interface Predicate {
        boolean matches(Movie movie);
    }

    List<Movie> movies = new LinkedList<Movie>();
    public List<Movie> findByPartialTitle(final String partialTitle) {
        List<Movie> result = new LinkedList<Movie>();
        /*
        Predicate predicate = new Predicate() {
            public boolean matches(Movie movie) {
                return movie.title().toUpperCase().contains(partialTitle.toUpperCase());
            }
        };
        */
        // Using Lambda expression to implement predicate interface
        return getMovies(result, (Movie movie)-> movie.title().toUpperCase().contains(partialTitle.toUpperCase()));
        // return getMovies(result, predicate);
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
        return getMovies(result, (Movie movie) -> movie.director().toUpperCase().contains(partialDirector.toUpperCase()));
    }

    public List<Movie> findByReleaseYear(final int from, final int to) {
        List<Movie> result = new LinkedList<Movie>();
        Predicate predicate = new Predicate() {
            public boolean matches(Movie movie) {
                return movie.releaseYear() >= from && movie.releaseYear() <= to;
            }
        };

        return getMovies(result, (Movie movie) -> movie.releaseYear() >= from && movie.releaseYear() <= to);
    }


}
