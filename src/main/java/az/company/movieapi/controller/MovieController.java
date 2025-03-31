package az.company.movieapi.controller;

import az.company.movieapi.entity.MovieEntity;
import az.company.movieapi.exception.MovieNotFoundException;
import az.company.movieapi.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieEntity> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{movieId}")
    public MovieEntity getMovie(@PathVariable Integer movieId) {
        MovieEntity movie = movieService.getMovieById(movieId);
        if (movie != null) {
            return movie;
        } else {
            throw new MovieNotFoundException("Movie with id:" + movieId + " does not exist");
        }
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createMovie(@RequestBody MovieEntity movie) {
        movieService.createMovieEntity(movie);
    }

    @DeleteMapping("/delete/{movieId}")
    public void deleteMovie(@PathVariable Integer movieId) {
        if(movieService.movieExists(movieId)){
            movieService.deleteMovie(movieId);
        }else {
            throw new MovieNotFoundException("Movie with id:" + movieId + " does not exist");
        }
    }

    @PutMapping("/edit/{movieId}")
    public void updateMovie(@PathVariable Integer movieId, @RequestBody MovieEntity updatedMovie) {
        if(movieService.movieExists(movieId)){
            movieService.updateUser(movieId, updatedMovie);
        }else {
            throw new MovieNotFoundException("Movie with id:" + movieId + " does not exist");
        }
    }
}
