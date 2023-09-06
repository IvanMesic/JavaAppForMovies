/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.meske.dal;

import hr.meske.dal.models.Actor;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author 505wL
 */
public interface ActorRepository {

    int createActor(String actor) throws Exception;

    void updateActor(int id, Actor actor) throws Exception;

    void deleteActor(String actorName) throws Exception;

    Optional<Actor> getActorById(int id) throws Exception;

    List<Actor> getAllActors() throws Exception;

    public int createActorWithMovie(String actorName, int movieId);
}
