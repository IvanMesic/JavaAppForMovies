/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.meske.dal.sql;

import hr.meske.dal.GenreRepository;
import hr.meske.dal.models.Genre;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author 505wL
 */
public class SqlGenreRepository implements GenreRepository {

    private static final String ID_GENRE = "IdGenre";
    private static final String GENRE_NAME = "GenreName";
    private static final String MOVIE_ID_STRING = "MovieId";
    private static final String CREATE_GENRE_WITH_MOVIE = "{ CALL CreateGenreWithMovie (?, ?, ?) }";
    private static final String CREATE_GENRE = "{ CALL CreateGenre (?) }";
    private static final String READ_GENRE = "{ CALL ReadGenre (?) }";
    private static final String READ_ALL_GENRES = "{ CALL ReadAllGenres }";
    private static final String EDIT_GENRE = "{ CALL EditGenre (?, ?) }";
    private static final String DELETE_GENRE = "{ CALL DeleteGenre (?) }";

    DataSource dataSource = DataSourceSingleton.getInstance();

    @Override
    public int createGenre(String genreName) throws Exception {
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_GENRE)) {
            stmt.setString(GENRE_NAME, genreName);
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
            return -1;
        }
    }

    @Override
    public void updateGenre(int id, String genreName) throws Exception {
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(EDIT_GENRE)) {
            stmt.setInt(ID_GENRE, id);
            stmt.setString(GENRE_NAME, genreName);
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteGenre(String genreName) {
        String storedProcedure = DELETE_GENRE;
        try (Connection connection = dataSource.getConnection(); CallableStatement callableStatement = connection.prepareCall(storedProcedure)) {
            callableStatement.setString(GENRE_NAME, genreName);
            callableStatement.executeUpdate();
        } catch (SQLException e) {
        }
    }

    @Override
    public Genre getGenreById(int id) throws Exception {
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(READ_GENRE)) {
            stmt.setInt(ID_GENRE, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Genre(rs.getInt(ID_GENRE), rs.getString(GENRE_NAME));
                }
            }
        }
        return null;
    }

    @Override
    public List<Genre> getAllGenres() throws Exception {
        List<Genre> genres = new ArrayList<>();
        DataSource ds = DataSourceSingleton.getInstance();
        try (Connection con = ds.getConnection(); CallableStatement stmt = con.prepareCall(READ_ALL_GENRES); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Genre genre = new Genre(rs.getInt(ID_GENRE), rs.getString(GENRE_NAME));
                genres.add(genre);
            }
        }
        return genres;
    }

    @Override
    public int createGenreWithMovie(String genreName, int movieId) {
        DataSource ds = DataSourceSingleton.getInstance();
        try (Connection con = ds.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_GENRE_WITH_MOVIE)) {
            stmt.setString(GENRE_NAME, genreName);
            stmt.setInt(MOVIE_ID_STRING, movieId);
            stmt.registerOutParameter(ID_GENRE, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(ID_GENRE); // Return the generated ID
        } catch (SQLException e) {
        }
        return 0; // Return 0 in case of an error
    }

}
