/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.meske.dal;

import hr.meske.dal.models.Actor;
import hr.meske.dal.models.Director;
import hr.meske.dal.models.Genre;
import hr.meske.dal.models.Movie;
import java.util.List;

/**
 *
 * @author 505wL
 */
public interface MovieRepository {

    int createMovie(Movie movie);

    void updateMovie(Movie movie);

    void deleteMovie(int id);

    Movie getMovieById(int id);

    List<Movie> getAllMovies();

    public List<Actor> getActorsForMovie(int movieId);

    public List<Director> getDirectorsForMovie(int movieId);

    public List<Genre> getGenresForMovie(int movieId);

    public void deleteAllDataFromMovieDirector(int movieId);

    public void deleteAllDataFromMovieActor(int movieId);

    public void deleteAllDataFromMovieGenre(int movieId);

    public boolean checkIfMovieExists(String movieName);
    public void deleteAllData();
}
