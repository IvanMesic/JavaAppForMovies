/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.meske.dal.sql;

import hr.meske.dal.DirectorRepository;
import hr.meske.dal.models.Director;
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
public class SqlDirectorRepository implements DirectorRepository {

    private static final String ID_DIRECTOR = "IdDirector";
    private static final String DIRECTOR_NAME = "DirectorName";
    private static final String MOVIE_ID = "MovieId";

    private static final String CREATE_DIRECTOR_WITH_MOVIE = "{ CALL CreateDirectorWithMovie (?, ?, ?) }";
    private static final String CREATE_DIRECTOR = "{ CALL CreateDirector (?) }";
    private static final String UPDATE_DIRECTOR = "{ CALL EditDirector (?, ?) }";
    private static final String DELETE_DIRECTOR = "{ CALL DeleteDirector (?) }";
    private static final String SELECT_DIRECTOR = "{ CALL ReadDirector (?) }";
    private static final String SELECT_ALL_DIRECTORS = "{ CALL ReadAllDirectors }";

    DataSource dataSource = DataSourceSingleton.getInstance();

    @Override
    public int createDirector(String directorName) throws Exception {
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_DIRECTOR)) {
            stmt.setString(DIRECTOR_NAME,directorName);
            stmt.executeUpdate();
            return stmt.getInt(ID_DIRECTOR);
        }
    }

    @Override
    public void updateDirector(int id, String directorName) throws Exception {

        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(UPDATE_DIRECTOR)) {
            stmt.setInt(ID_DIRECTOR, id);
            stmt.setString(DIRECTOR_NAME, directorName);
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteDirector(String directorName) {
        String storedProcedure = DELETE_DIRECTOR;
        try (Connection connection = dataSource.getConnection(); CallableStatement callableStatement = connection.prepareCall(storedProcedure)) {
            callableStatement.setString(DELETE_DIRECTOR, directorName);
            callableStatement.executeUpdate();
        } catch (SQLException e) {
        }
    }

    @Override
    public Optional<Director> getDirectorById(int id) throws Exception {
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_DIRECTOR)) {
            stmt.setInt(ID_DIRECTOR, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Director director = new Director(rs.getInt(ID_DIRECTOR),
                            rs.getString(DIRECTOR_NAME));
                    return Optional.of(director);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Director> getAllDirectors() throws Exception {
        List<Director> directors = new ArrayList<>();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_ALL_DIRECTORS); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Director director = new Director(rs.getInt(ID_DIRECTOR),
                        rs.getString(DIRECTOR_NAME));
                directors.add(director);
            }
        }

        return directors;
    }

    @Override
    public int createDirectorWithMovie(String directorName, int movieId) {
        DataSource ds = DataSourceSingleton.getInstance();
        try (Connection con = ds.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_DIRECTOR_WITH_MOVIE)) {
            stmt.setString(DIRECTOR_NAME, directorName);
            stmt.setInt(MOVIE_ID, movieId);
            stmt.registerOutParameter(ID_DIRECTOR, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(ID_DIRECTOR); 
        } catch (SQLException e) {
        }
        return 0;
    }
}
