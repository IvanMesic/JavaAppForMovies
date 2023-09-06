/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.meske.dal.models;

import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 505wL
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Movie {

    private int id;
    private String movieName;
    private String movieDescription;
    private String imageUrl;
    private Date showingDate;

    @XmlElementWrapper(name = "actors")
    @XmlElement(name = "actor")
    private List<Actor> actors;

    @XmlElementWrapper(name = "directors")
    @XmlElement(name = "director")
    private List<Director> directors;

    @XmlElementWrapper(name = "genres")
    @XmlElement(name = "genre")
    private List<Genre> genres;

    private String pictureFilePath;

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public Movie(String movieName, String movieDescription, String imageUrl, Date showingDate, String pictureFilePath,
            List<Actor> actors, List<Director> directors, List<Genre> genres) {

        this.movieName = movieName;
        this.movieDescription = movieDescription;
        this.imageUrl = imageUrl;
        this.pictureFilePath = pictureFilePath;
        this.showingDate = showingDate;
        this.actors = actors;
        this.directors = directors;
        this.genres = genres;
    }

    public Movie(int id, String movieName, String movieDescription, String imageUrl, Date showingDate, String pictureFilePath,
            List<Actor> actors, List<Director> directors, List<Genre> genres) {
        this.id = id;
        this.movieName = movieName;
        this.movieDescription = movieDescription;
        this.imageUrl = imageUrl;
        this.pictureFilePath = pictureFilePath;
        this.showingDate = showingDate;
        this.actors = actors;
        this.directors = directors;
        this.genres = genres;
    }

    public Movie() {
    }

    public int getId() {
        return id;
    }

    public String getMoviePictureFilePath() {
        return pictureFilePath;
    }

    public void setMoviePictureFilePath(String path) {
        pictureFilePath = path;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Date getShowingDate() {
        return showingDate;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public List<Director> getDirectors() {
        return directors;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setShowingDate(Date showingDate) {
        this.showingDate = showingDate;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public void setDirectors(List<Director> directors) {
        this.directors = directors;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Movie movie = (Movie) o;
        return id == movie.id && Objects.equals(movieName, movie.movieName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, movieName);
    }

    public void setMovies(List<Movie> movies) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
