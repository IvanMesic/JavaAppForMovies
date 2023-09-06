/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.meske.dal.sql;

import hr.meske.dal.MovieRepository;
import hr.meske.dal.models.Actor;
import hr.meske.dal.models.Director;
import hr.meske.dal.models.Genre;
import hr.meske.dal.models.Movie;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author 505wL
 */
public class SqlMovieRepository implements MovieRepository {

    private static final String CREATE_MOVIE = "{ CALL CreateMovie (?, ?, ?, ?, ?, ?)}";
    private static final String DELETE_ALL_FROM_MOVIEDIRECTOR = "{ CALL DeleteAllDataInMovieDirector(?)}";
    private static final String DELETE_ALL_FROM_MOVIEGENRE = "{ CALL DeleteAllDataInMovieDirector(?)}";
    private static final String UPDATE_MOVIE = "{ CALL EditMovie (?, ?, ?, ?, ?, ?) }";
    private static final String DELETE_MOVIE = "{ CALL DeleteMovie (?) }";
    private static final String GET_MOVIE_BY_ID = "{ CALL getMovieById (?) }";
    private static final String GET_ALL_MOVIES = "{ CALL GetAllMovies}";
    private static final String GET_ACTORS_FOR_MOVIE = "{ CALL GetActorsForMovie (?) }";
    private static final String GET_DIRECTORS_FOR_MOVIE = "{ CALL GetDirectorsForMovie (?) }";
    private static final String GET_GENRES_FOR_MOVIE = "{ CALL GetGenresForMovie (?) }";
    private static final String CHECK_MOVIE_EXIST = "{call CheckIfMovieExists(?)}";

    private static final String MOVIE_ID = "MovieId";
    private static final String ID_MOVIE = "IdMovie";
    private static final String MOVIE_FILE_PATH = "ImagePath";
    private static final String MOVIE_NAME_STRING = "MovieName";
    private static final String MOVIE_DESC = "MovieDescription";
    private static final String IMG_URL = "ImageUrl";
    private static final String SHOWING_DATE_STRING = "showingDate";
    private static final String ID_DIRECTOR_STRING = "IdDirector";
    private static final String ID_GENRE_STRING = "IdGenre";
    private static final String ID_ACTOR_STRING = "IdActor";
    private static final String ACTOR_NAME_STRING = "ActorName";
    private static final String GENRE_NAME_STRING = "GenreName";
    private static final String DIRECTOR_NAME_STRING = "DirectorName";

    private final DataSource dataSource = DataSourceSingleton.getInstance();

    @Override
    public int createMovie(Movie movie) {
        int generatedId = 0;
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_MOVIE)) {
            stmt.setString(MOVIE_NAME_STRING, movie.getMovieName());
            stmt.setString(MOVIE_DESC, movie.getMovieDescription());
            stmt.setString(IMG_URL, movie.getImageUrl());
            stmt.setString(MOVIE_FILE_PATH, movie.getMoviePictureFilePath());
            stmt.setDate(SHOWING_DATE_STRING, new java.sql.Date(movie.getShowingDate().getTime())); // Use java.sql.Date constructor
            stmt.registerOutParameter(ID_MOVIE, Types.INTEGER);
            stmt.executeUpdate();
            generatedId = stmt.getInt(ID_MOVIE);
        } catch (SQLException e) {
        }
        return generatedId;
    }

    @Override
    public void updateMovie(Movie movie) {
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(UPDATE_MOVIE)) {
            stmt.setInt(ID_MOVIE, movie.getId());
            stmt.setString(MOVIE_NAME_STRING, movie.getMovieName());
            stmt.setString(MOVIE_DESC, movie.getMovieDescription());
            stmt.setString(MOVIE_FILE_PATH, movie.getMoviePictureFilePath());
            stmt.setString(IMG_URL, movie.getImageUrl());
            stmt.setDate(SHOWING_DATE_STRING, new java.sql.Date(movie.getShowingDate().getTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
        }
    }

    @Override
    public void deleteMovie(int id) {
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_MOVIE)) {
            stmt.setInt(MOVIE_ID, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
        }
    }

    @Override
    public Movie getMovieById(int id) {
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(GET_MOVIE_BY_ID)) {
            stmt.setInt(MOVIE_ID, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int movieId = rs.getInt(ID_MOVIE);
                    Movie movie = mapResultSetToMovie(rs);
                    movie.setActors(getActorsForMovie(movieId));
                    movie.setDirectors(getDirectorsForMovie(movieId));
                    movie.setGenres(getGenresForMovie(movieId));
                    return movie;
                }
            }
        } catch (SQLException e) {
        }
        return null;
    }

    @Override
    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(GET_ALL_MOVIES)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int movieId = rs.getInt(ID_MOVIE);
                    Movie movie = mapResultSetToMovie(rs);
                    movie.setActors(getActorsForMovie(movieId));
                    movie.setDirectors(getDirectorsForMovie(movieId));
                    movie.setGenres(getGenresForMovie(movieId));
                    movies.add(movie);
                }
            }
        } catch (SQLException e) {
        }
        return movies;
    }

    private Movie mapResultSetToMovie(ResultSet rs) throws SQLException {
        return new Movie(
                rs.getInt(ID_MOVIE),
                rs.getString(MOVIE_NAME_STRING),
                rs.getString(MOVIE_DESC),
                rs.getString(IMG_URL),
                rs.getDate(SHOWING_DATE_STRING),
                rs.getString(MOVIE_FILE_PATH),
                null, // You can populate the List of Actor, Director, Genre from another source or use a JOIN in the SQL
                null,
                null
        );
    }

    @Override
    public List<Actor> getActorsForMovie(int movieId) {
        List<Actor> actors = new ArrayList<>();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(GET_ACTORS_FOR_MOVIE)) {
            stmt.setInt(MOVIE_ID, movieId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    actors.add(new Actor(rs.getInt(ID_ACTOR_STRING), rs.getString(ACTOR_NAME_STRING)));
                }
            }
        } catch (SQLException e) {
        }
        return actors;
    }

    @Override
    public List<Director> getDirectorsForMovie(int movieId) {
        List<Director> directors = new ArrayList<>();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(GET_DIRECTORS_FOR_MOVIE)) {
            stmt.setInt(MOVIE_ID, movieId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    directors.add(new Director(rs.getInt(ID_DIRECTOR_STRING), rs.getString(DIRECTOR_NAME_STRING)));
                }
            }
        } catch (SQLException e) {
        }
        return directors;
    }

    @Override
    public List<Genre> getGenresForMovie(int movieId) {
        List<Genre> genres = new ArrayList<>();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(GET_GENRES_FOR_MOVIE)) {
            stmt.setInt(MOVIE_ID, movieId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    genres.add(new Genre(rs.getInt(ID_GENRE_STRING), rs.getString(GENRE_NAME_STRING)));
                }
            }
        } catch (SQLException e) {
        }
        return genres;
    }

    @Override
    public void deleteAllDataFromMovieDirector(int movieId) {
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_ALL_FROM_MOVIEDIRECTOR)) {
            stmt.setInt(MOVIE_ID, movieId);
            stmt.executeUpdate();

        } catch (SQLException e) {
        }
    }

    @Override
    public void deleteAllDataFromMovieActor(int movieId) {
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_ALL_FROM_MOVIEDIRECTOR)) {
            stmt.setInt(MOVIE_ID, movieId);
            stmt.executeUpdate();

        } catch (SQLException e) {
        }
    }

    @Override
    public void deleteAllDataFromMovieGenre(int movieId) {
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_ALL_FROM_MOVIEGENRE)) {
            stmt.setInt(MOVIE_ID, movieId);
            stmt.executeUpdate();

        } catch (SQLException e) {
        }

    }

    @Override
    public boolean checkIfMovieExists(String movieName) {

        try (Connection con = dataSource.getConnection()) {
            CallableStatement stmt = con.prepareCall(CHECK_MOVIE_EXIST);
            stmt.setString(MOVIE_NAME_STRING, movieName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("IsExists") == 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SqlMovieRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public void deleteAllData() {
        try (Connection con = dataSource.getConnection()) {

            if (!con.getAutoCommit()) {
                con.setAutoCommit(true);
            }

            try (CallableStatement stmt = con.prepareCall("EXEC DeleteAllData")) {
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
        
            
        }
    }

}
