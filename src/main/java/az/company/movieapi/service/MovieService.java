package az.company.movieapi.service;

import az.company.movieapi.entity.MovieEntity;
import az.company.movieapi.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieEntity> getAllMovies() {
        return movieRepository.findAll();
    }

    public MovieEntity getMovieById(Integer movieId) {
        return movieRepository.findByMovieId(movieId);
    }

    public void createMovieEntity(MovieEntity movie) {
        movieRepository.save(movie);
    }

    public void deleteMovie(Integer movieId) {
        movieRepository.deleteById(movieId);
    }

    public void updateUser(Integer movieId, MovieEntity updatedMovie) {
        MovieEntity existingMovie = movieRepository.findByMovieId(movieId);
        existingMovie.setDirector(updatedMovie.getDirector());
        existingMovie.setGenre(updatedMovie.getGenre());
        existingMovie.setTitle(updatedMovie.getTitle());
        existingMovie.setImdbRating(updatedMovie.getImdbRating());
        existingMovie.setReleaseYear(updatedMovie.getReleaseYear());
        movieRepository.save(existingMovie);
    }

    public boolean movieExists(Integer movieId) {
        if (movieRepository.findById(movieId).isPresent()) {
            return true;
        } else {
            return false;
        }
    }
}
