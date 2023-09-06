package hr.meske.dal;

import hr.meske.dal.sql.DataSourceSingleton;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RepoFactory {

    private static final String PATH = "/config/repository.properties";
    private static final Properties properties = new Properties();

    private static final String CLASS_THE_GUY = "CLASS_THE_GUY";
    private static final String CLASS_MOVIE = "CLASS_MOVIE";
    private static final String CLASS_GENRE = "CLASS_GENRE";
    private static final String CLASS_DIRECTOR = "CLASS_DIRECTOR";
    private static final String CLASS_ACTOR = "CLASS_ACTOR";

    private static ITheGuyRepository theGuyRepository;
    private static MovieRepository movieRepository;
    private static GenreRepository genreRepository;
    private static DirectorRepository directorRepository;
    private static ActorRepository actorRepository;

    static {
        try (InputStream is = DataSourceSingleton.class.getResourceAsStream(PATH)) {
            properties.load(is);
            theGuyRepository = createInstance(properties.getProperty(CLASS_THE_GUY));
            movieRepository = createInstance(properties.getProperty(CLASS_MOVIE));
            genreRepository = createInstance(properties.getProperty(CLASS_GENRE));
            directorRepository = createInstance(properties.getProperty(CLASS_DIRECTOR));
            actorRepository = createInstance(properties.getProperty(CLASS_ACTOR));
        } catch (Exception ex) {
            Logger.getLogger(DataSourceSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private RepoFactory() {
    }

    private static <T> T createInstance(String className) {
        try {
            return (T) Class.forName(className).getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException ex) {
            Logger.getLogger(DataSourceSingleton.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static ITheGuyRepository getTheGuyRepository() {
        return theGuyRepository;
    }

    public static MovieRepository getMovieRepository() {
        return movieRepository;
    }

    public static GenreRepository getGenreRepository() {
        return genreRepository;
    }

    public static DirectorRepository getDirectorRepository() {
        return directorRepository;
    }

    public static ActorRepository getActorRepository() {
        return actorRepository;
    }
}
