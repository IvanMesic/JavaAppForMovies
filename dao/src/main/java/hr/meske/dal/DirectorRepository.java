/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.meske.dal;

import hr.meske.dal.models.Director;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author 505wL
 */
public interface DirectorRepository {

    int createDirector(String director) throws Exception;

    void updateDirector(int id, String director) throws Exception;

    void deleteDirector(String directorName) throws Exception;

    Optional<Director> getDirectorById(int id) throws Exception;

    List<Director> getAllDirectors() throws Exception;

    int createDirectorWithMovie(String directorName, int movieId);
}
