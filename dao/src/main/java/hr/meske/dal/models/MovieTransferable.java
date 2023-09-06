/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.meske.dal.models;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 *
 * @author 505wL
 */
public class MovieTransferable implements Transferable {

    public static final DataFlavor MOVIE_FLAVOR = new DataFlavor(Movie.class, "Movie");
    private static final DataFlavor[] SUPPORTED_FLAVORS = {MOVIE_FLAVOR};

    private final Movie movie;

    public MovieTransferable(Movie movie) {
        this.movie = movie;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return SUPPORTED_FLAVORS;
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor.equals(MOVIE_FLAVOR);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (isDataFlavorSupported(flavor)) {
            return movie;
        }
        throw new UnsupportedFlavorException(flavor);
    }
}
