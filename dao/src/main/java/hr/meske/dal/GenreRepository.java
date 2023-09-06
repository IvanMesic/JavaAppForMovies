
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.meske.dal;

import hr.meske.dal.models.Genre;
import java.util.List;

/**
 *
 * @author 505wL
 */
public interface GenreRepository {

    int createGenre(String genre) throws Exception;

    void updateGenre(int id, String genre) throws Exception;

    void deleteGenre(String genreName) throws Exception;

    Genre getGenreById(int id) throws Exception;

    List<Genre> getAllGenres() throws Exception;

    public int createGenreWithMovie(String genreName, int movieId);
}
