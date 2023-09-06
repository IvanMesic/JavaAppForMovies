/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.meske.parser;

import factories.UrlConnectionFactory;
import hr.meske.dal.models.Actor;
import hr.meske.dal.models.Director;
import hr.meske.dal.models.Genre;
import hr.meske.dal.models.Movie;
import hr.meske.utilities.FileUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author 505wL
 */
public class MyRssParser {

    private static final String RSS_URL = "https://www.blitz-cinestar-bh.ba/rss.aspx?id=2682";
    public static final String TIMESTAMP_PATTERN = "dd.mm.yyyy";
    private static final String EXT = ".jpg";
    private static final String DIR = "data";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

    private MyRssParser() {
    }

    public static List<Movie> parse() throws IOException, XMLStreamException, ParseException {
        List<Movie> movies = new ArrayList<>();
        HttpURLConnection con = UrlConnectionFactory.getHttpUrlConnection(RSS_URL);
        try (InputStream is = con.getInputStream()) {
            XMLEventReader reader = ParserFactory.createStaxParser(is);

            Optional<TagType> tagType = Optional.empty();
            Movie movie = null;
            StartElement startElement = null;
            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                switch (event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT -> {
                        startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();
                        tagType = TagType.from(qName);
                        if (tagType.isPresent() && tagType.get().equals(TagType.ITEM)) {
                            movie = new Movie();
                            movies.add(movie);
                        }
                    }

                    case XMLStreamConstants.CHARACTERS -> {
                        if (tagType.isPresent()) {
                            Characters characters = event.asCharacters();
                            String data = characters.getData().trim();
                            switch (tagType.get()) {
                                case ITEM -> {
                                    movie = new Movie();
                                    movies.add(movie);
                                }
                                case TITLE -> {
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setMovieName(data);
                                    }
                                }
                                case DESCRIPTION -> {
                                    if (!data.isEmpty() && movie != null) {
                                        String html = data;
                                        String result = html.replaceAll("<[^>]*>", "");

                                        movie.setMovieDescription(result);
                                    }
                                }
                                case DIRECTOR -> {
                                    if (movie != null && !data.isEmpty()) {
                                        List<String> people = parseCsv(data);
                                        movie.setDirectors(objectifyDirectors(people));
                                    }
                                }
                                case ACTORS -> {
                                    if (movie != null && !data.isEmpty()) {
                                        List<String> people = parseCsv(data);
                                        movie.setActors(objectifyActors(people));
                                    }
                                }

                                case GENRE -> {
                                    if (movie != null && !data.isEmpty()) {
                                        List<String> genres = parseCsv(data);
                                        movie.setGenres(objectifyGenres(genres));

                                    }
                                }
                                case POSTER -> {
                                    if (movie != null && !data.isEmpty()) {
                                        handlePicture(movie, data);
                                        movie.setImageUrl(data);
                                    }
                                }
                                case SHOWING_DATE -> {
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setShowingDate(DATE_FORMAT.parse(data));
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }
        return movies;

    }

    private static List<String> parseCsv(String data) {
        List<String> people = new ArrayList<>();
        String[] peopleArray = data.split(",");
        for (String person : peopleArray) {
            people.add(person.trim());
        }
        return people;
    }

    private static List<Actor> objectifyActors(List<String> people) {
        List<Actor> actors = new ArrayList<>();
        Actor actor;
        for (String name : people) {
            actor = new Actor(name);
            actors.add(actor);
        }
        return actors;
    }

    private static List<Director> objectifyDirectors(List<String> people) {
        List<Director> directors = new ArrayList<>();
        Director director;
        for (String name : people) {
            director = new Director(name);
            directors.add(director);
        }
        return directors;
    }

    private static List<Genre> objectifyGenres(List<String> myGenres) {
        List<Genre> genres = new ArrayList<>();
        Genre genre;
        for (String name : myGenres) {
            genre = new Genre(name);
            genres.add(genre);
        }
        return genres;
    }

    public static void handlePicture(Movie movie, String pictureUrl) {
        try {
            String ext = pictureUrl.substring(pictureUrl.lastIndexOf("."));
            if (ext.length() > 4) {
                ext = EXT;
            }
            String pictureName = UUID.randomUUID() + ext;
            String localPicturePath = DIR + File.separator + pictureName;

            File directory = new File(DIR);
            if (!directory.exists()) {
                directory.mkdirs(); // Creates the directory and any missing parent directories
            }

            try {
                FileUtils.copyFromUrl(pictureUrl, localPicturePath);
                movie.setMoviePictureFilePath(localPicturePath);
            } catch (SocketTimeoutException e) {
                // Handle the timeout exception
                System.err.println("Connection timed out while downloading the picture. Please try again later.");
            }
        } catch (IOException ex) {
            Logger.getLogger(MyRssParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private enum TagType {

        ITEM("item"),
        TITLE("orignaziv"),
        DESCRIPTION("description"),
        DIRECTOR("redatelj"),
        ACTORS("glumci"),
        LENGTH("trajanje"),
        GENRE("zanr"),
        LINK("link"),
        POSTER("plakat"),
        SHOWING_DATE("datumprikazivanja");

        private final String name;

        private TagType(String name) {
            this.name = name;
        }

        private static Optional<TagType> from(String name) {
            for (TagType value : values()) {
                if (value.name.equals(name)) {
                    return Optional.of(value);
                }
            }
            return Optional.empty();
        }
    }
}
