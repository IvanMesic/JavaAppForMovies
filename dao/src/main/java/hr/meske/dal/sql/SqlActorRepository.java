/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.meske.dal.sql;

import hr.meske.dal.ActorRepository;
import hr.meske.dal.models.Actor;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;

/**
 *
 * @author 505wL
 */
public class SqlActorRepository implements ActorRepository {

    private static final String ID_ACTOR = "IdActor";
    private static final String ACTOR_NAME = "ActorName";
    private static final String MOVIE_ID_STRING = "MovieId";
    private static final String CREATE_ACTOR = "{ CALL CreateActor(?) }";
    private static final String UPDATE_ACTOR = "{ CALL EditActor(?, ?) }";
    private static final String DELETE_ACTOR = "{ CALL DeleteActor(?) }";
    private static final String SELECT_ACTOR = "{ CALL ReadActor(?) }";
    private static final String SELECT_ALL_ACTORS = "{ CALL ReadAllActors }";
    private static final String CREATE_ACTOR_WITH_MOVIE = "{ CALL CreateActorWithMovie (?, ?, ?) }";

    DataSource dataSource = DataSourceSingleton.getInstance();

    public SqlActorRepository() {
    }

    @Override
    public int createActor(String actor) throws Exception {
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_ACTOR)) {
            stmt.setString(ACTOR_NAME, actor);
            stmt.executeUpdate();
            return stmt.getInt(ID_ACTOR);
        }
    }

    @Override
    public void updateActor(int id, Actor actor) throws Exception {
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(UPDATE_ACTOR)) {
            stmt.setInt(ID_ACTOR, id);
            stmt.setString(ACTOR_NAME, actor.getActorName());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteActor(String actorName) {
        String storedProcedure = DELETE_ACTOR;
        try (Connection connection = dataSource.getConnection();
             CallableStatement callableStatement = connection.prepareCall(storedProcedure)) {
            callableStatement.setString(1, actorName);
            callableStatement.executeUpdate();
        } catch (SQLException e) {
        }
    }

    @Override
    public Optional<Actor> getActorById(int id) throws Exception {
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_ACTOR)) {
            stmt.setInt(ID_ACTOR, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Actor actor = new Actor(rs.getInt(ID_ACTOR), rs.getString(ACTOR_NAME));
                    return Optional.of(actor);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Actor> getAllActors() throws Exception {
        List<Actor> actors = new ArrayList<>();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_ALL_ACTORS); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Actor actor = new Actor(rs.getInt(ID_ACTOR), rs.getString(ACTOR_NAME));
                actors.add(actor);
            }
        }
        return actors;
    }

    @Override
    public int createActorWithMovie(String actorName, int movieId) {
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_ACTOR_WITH_MOVIE)) {
            stmt.setString(ACTOR_NAME, actorName);
            stmt.setInt(MOVIE_ID_STRING, movieId);
            stmt.registerOutParameter(ID_ACTOR, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(ID_ACTOR); // Return the generated ID
        } catch (SQLException e) {
        }
        return 0; // Return 0 in case of an error
    }

}
