/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.meske.dal.models;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author 505wL
 */
public class MovieTableModel extends AbstractTableModel {

    // private static final String[] COLUMN_NAMES = {"Id", "MovieName", "Description", "ShowingDate","MoviePicturePath","Actors", "Directors", "Genres"};
    private static final String[] COLUMN_NAMES = {"Id", "MovieName", "ShowingDate",};

    private List<Movie> movies;

    public MovieTableModel(List<Movie> movies) {
        this.movies = movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return movies.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public String getColumnName(int column) {

        return COLUMN_NAMES[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
        }
        return super.getColumnClass(columnIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return movies.get(rowIndex).getId();
            case 1:
                return movies.get(rowIndex).getMovieName();
            case 2:
                return movies.get(rowIndex).getMovieDescription();
            case 3:
                return movies.get(rowIndex).getShowingDate();
            case 4:
                return movies.get(rowIndex).getMoviePictureFilePath();
            case 5:
                return "string";
            case 6:
                return "otherString";
            case 7:
                return "GenreString";
            default:
                throw new RuntimeException("No such column");
        }
    }

    public Movie getClass(int selectedRow) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
