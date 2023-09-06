package hr.meske.moviemanager;

import hr.meske.dal.ActorRepository;
import hr.meske.dal.DirectorRepository;
import hr.meske.dal.GenreRepository;
import hr.meske.dal.MovieRepository;
import hr.meske.dal.RepoFactory;
import hr.meske.dal.models.Actor;
import hr.meske.dal.models.Director;
import hr.meske.dal.models.Genre;
import hr.meske.dal.models.Movie;
import hr.meske.dal.models.MovieTableModel;
import hr.meske.dal.models.Movies;
import hr.meske.utilities.IconUtils;
import hr.meske.utilities.MessageUtils;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class UserForm extends javax.swing.JFrame {

    private static final String EXT = ".jpg";
    private static final String DIR = "data";
    private static final String XML_DIR = "XmlData";

    private static final MovieRepository movieRepository = RepoFactory.getMovieRepository();
    private static final ActorRepository actorRepo = RepoFactory.getActorRepository();
    private static final GenreRepository genreRepo = RepoFactory.getGenreRepository();
    private static final DirectorRepository directorRepo = RepoFactory.getDirectorRepository();

    private List<Movie> moviesList = new ArrayList<>();

    private JPopupMenu popupMenu;

    private final DefaultListModel<String> listModelActors = new DefaultListModel<>();
    private final DefaultListModel<String> listModelDirectors = new DefaultListModel<>();
    private final DefaultListModel<String> listModelGenres = new DefaultListModel<>();

    public UserForm() {
        initComponents();
        init();
        initGenres();
        initActors();
        initDirectors();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jpMoviePanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbMovieTable = new javax.swing.JTable();
        tfShowingDate = new javax.swing.JTextField();
        btnEditMovie = new javax.swing.JButton();
        lbDesc = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        lsActors = new javax.swing.JList<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        lsDirectors = new javax.swing.JList<>();
        lbMovieName = new javax.swing.JLabel();
        lbDirectors = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        taDesc = new javax.swing.JTextArea();
        lbShowingDate = new javax.swing.JLabel();
        lbPoster = new javax.swing.JLabel();
        tfMovieName = new javax.swing.JTextField();
        lbActors = new javax.swing.JLabel();
        btnDeleteMovie = new javax.swing.JButton();
        btnNewMovie = new javax.swing.JButton();
        jpGenreForm = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        lsSoloGenres = new javax.swing.JList<>();
        btnCreateNewGenre = new javax.swing.JButton();
        tfGenreName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jpActor = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        lsSoloActors = new javax.swing.JList<>();
        tfActorName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnCreateNewActor = new javax.swing.JButton();
        jpDirector = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        lsSoloDirectors = new javax.swing.JList<>();
        tfDirectorName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnCreateNewDirector = new javax.swing.JButton();
        jmbMyMenu = new javax.swing.JMenuBar();
        jmExportToXml = new javax.swing.JMenu();
        jmiCallDatabase = new javax.swing.JMenuItem();
        jmiLogout = new javax.swing.JMenuItem();
        jmiXmlExport = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jpMoviePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbMovieTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbMovieTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMovieTableMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbMoviesTableKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbMovieTable);

        jpMoviePanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 570, 190));
        jpMoviePanel.add(tfShowingDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 230, 40));

        btnEditMovie.setText("EditMovie");
        btnEditMovie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditMovieActionPerformed(evt);
            }
        });
        jpMoviePanel.add(btnEditMovie, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 120, 53));

        lbDesc.setText("Description");
        jpMoviePanel.add(lbDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 410, -1, -1));

        jScrollPane6.setViewportView(lsActors);

        jpMoviePanel.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 180, 160, 150));

        jScrollPane5.setViewportView(lsDirectors);

        jpMoviePanel.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 180, 160, 150));

        lbMovieName.setText("MovieName");
        jpMoviePanel.add(lbMovieName, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        lbDirectors.setText("Directors");
        jpMoviePanel.add(lbDirectors, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 160, 65, -1));

        taDesc.setColumns(20);
        taDesc.setLineWrap(true);
        taDesc.setRows(5);
        jScrollPane2.setViewportView(taDesc);

        jpMoviePanel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 430, 230, 110));

        lbShowingDate.setText("Showing Date");
        jpMoviePanel.add(lbShowingDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 93, -1));

        lbPoster.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Cimer i Ja.jpg"))); // NOI18N
        lbPoster.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbPosterMouseClicked(evt);
            }
        });
        jpMoviePanel.add(lbPoster, new org.netbeans.lib.awtextra.AbsoluteConstraints(603, 106, 235, 287));
        jpMoviePanel.add(tfMovieName, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 230, 41));

        lbActors.setText("Actors");
        jpMoviePanel.add(lbActors, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, 37, -1));

        btnDeleteMovie.setText("DeleteMovie");
        btnDeleteMovie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteMovieActionPerformed(evt);
            }
        });
        jpMoviePanel.add(btnDeleteMovie, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 60, 120, 53));

        btnNewMovie.setText("NewMovie");
        btnNewMovie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewMovieActionPerformed(evt);
            }
        });
        jpMoviePanel.add(btnNewMovie, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 120, 53));

        jTabbedPane1.addTab("Movie", jpMoviePanel);

        lsSoloGenres.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane7.setViewportView(lsSoloGenres);

        btnCreateNewGenre.setText("Create");
        btnCreateNewGenre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateNewGenreActionPerformed(evt);
            }
        });

        jLabel5.setText("Name");

        javax.swing.GroupLayout jpGenreFormLayout = new javax.swing.GroupLayout(jpGenreForm);
        jpGenreForm.setLayout(jpGenreFormLayout);
        jpGenreFormLayout.setHorizontalGroup(
            jpGenreFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpGenreFormLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpGenreFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfGenreName, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCreateNewGenre, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(371, Short.MAX_VALUE))
        );
        jpGenreFormLayout.setVerticalGroup(
            jpGenreFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpGenreFormLayout.createSequentialGroup()
                .addGroup(jpGenreFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpGenreFormLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfGenreName, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCreateNewGenre, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpGenreFormLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(212, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Genre", jpGenreForm);

        lsSoloActors.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(lsSoloActors);

        jLabel1.setText("Name");

        btnCreateNewActor.setText("Create");
        btnCreateNewActor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateNewActorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpActorLayout = new javax.swing.GroupLayout(jpActor);
        jpActor.setLayout(jpActorLayout);
        jpActorLayout.setHorizontalGroup(
            jpActorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpActorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpActorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfActorName, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCreateNewActor, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(371, Short.MAX_VALUE))
        );
        jpActorLayout.setVerticalGroup(
            jpActorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpActorLayout.createSequentialGroup()
                .addGroup(jpActorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpActorLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfActorName, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCreateNewActor, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpActorLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(212, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Actor", jpActor);

        lsSoloDirectors.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane4.setViewportView(lsSoloDirectors);

        jLabel2.setText("Name");

        btnCreateNewDirector.setText("Create");
        btnCreateNewDirector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateNewDirectorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpDirectorLayout = new javax.swing.GroupLayout(jpDirector);
        jpDirector.setLayout(jpDirectorLayout);
        jpDirectorLayout.setHorizontalGroup(
            jpDirectorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDirectorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpDirectorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCreateNewDirector, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfDirectorName, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(371, Short.MAX_VALUE))
        );
        jpDirectorLayout.setVerticalGroup(
            jpDirectorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDirectorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpDirectorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpDirectorLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfDirectorName, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCreateNewDirector, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(212, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Director", jpDirector);

        jmExportToXml.setText("File");

        jmiCallDatabase.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jmiCallDatabase.setText("Call Database ");
        jmiCallDatabase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiCallDatabaseActionPerformed(evt);
            }
        });
        jmExportToXml.add(jmiCallDatabase);

        jmiLogout.setText("Logout");
        jmiLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiLogoutActionPerformed(evt);
            }
        });
        jmExportToXml.add(jmiLogout);

        jmiXmlExport.setText("Export Movie to XML");
        jmiXmlExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiXmlExportActionPerformed(evt);
            }
        });
        jmExportToXml.add(jmiXmlExport);

        jmbMyMenu.add(jmExportToXml);

        jMenu2.setText("Edit");
        jmbMyMenu.add(jMenu2);

        setJMenuBar(jmbMyMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 853, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmiCallDatabaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiCallDatabaseActionPerformed

        new Thread(() -> {
            getDataFromDatabase();
        }).start();
    }//GEN-LAST:event_jmiCallDatabaseActionPerformed

    private void jmiXmlExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiXmlExportActionPerformed

        try {
            File directory = new File(XML_DIR);
            if (!directory.exists()) {
                directory.mkdir();
            }

            File file = new File(XML_DIR + "/XmlMovies.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Movies.class);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            Movies moviesWrapper = new Movies();

            moviesWrapper.setMovies(moviesList);
            jaxbMarshaller.marshal(moviesWrapper, file);

        } catch (JAXBException ex) {
            Logger.getLogger(UserForm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jmiXmlExportActionPerformed

    private void jmiLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiLogoutActionPerformed

        this.dispose();
        new LoginForm().setVisible(true);
    }//GEN-LAST:event_jmiLogoutActionPerformed

    private void btnCreateNewGenreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateNewGenreActionPerformed

        String newGenreName = tfGenreName.getText().trim();
        try {
            genreRepo.createGenre(newGenreName);
        } catch (Exception ex) {
            Logger.getLogger(UserForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        initGenres();
    }//GEN-LAST:event_btnCreateNewGenreActionPerformed

    private void btnNewMovieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewMovieActionPerformed

        try {

            Movie newMovie = new Movie();

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            Date showingDate = formatter.parse("2023-01-01");
            newMovie.setShowingDate(showingDate);

            EditMovieForm editMovieForm = new EditMovieForm(newMovie, this);
            editMovieForm.setVisible(true);

        } catch (ParseException ex) {

            Logger.getLogger(UserForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        new Thread(() -> {
            getDataFromDatabase();
        }).start();
    }//GEN-LAST:event_btnNewMovieActionPerformed

    private void btnDeleteMovieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteMovieActionPerformed

        try {

            Movie selectedMovie = selectMovie();

            movieRepository.deleteMovie(selectedMovie.getId());
            tbMovieTable.clearSelection();

            MessageUtils.showInformationMessage("Success", "The movie was successfully deleted.");
        } catch (Exception e) {
        }

        getDataFromDatabase();
    }//GEN-LAST:event_btnDeleteMovieActionPerformed

    private void lbPosterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbPosterMouseClicked

        if (SwingUtilities.isRightMouseButton(evt)) {
            popupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_lbPosterMouseClicked

    private void btnEditMovieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditMovieActionPerformed

        if (tbMovieTable.getSelectedRow() == -1) {
            return;
        }
        EditMovieForm editMovieForm = new EditMovieForm(selectMovie(), this);
        editMovieForm.setVisible(true);

        editMovieForm.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosed(WindowEvent e) {

                new Thread(() -> {
                    getDataFromDatabase();
                }).start();
            }
        });
    }//GEN-LAST:event_btnEditMovieActionPerformed

    private void tbMoviesTableKeyReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMoviesTableKeyReleased
        showMovie();
    }//GEN-LAST:event_tbMoviesTableKeyReleased

    private void tbMovieTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMovieTableMouseClicked

        showMovie();
    }//GEN-LAST:event_tbMovieTableMouseClicked

    private void btnCreateNewActorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateNewActorActionPerformed
        String newActorName = tfActorName.getText().trim();
        try {
            actorRepo.createActor(newActorName);
        } catch (Exception ex) {
            Logger.getLogger(UserForm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnCreateNewActorActionPerformed

    private void btnCreateNewDirectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateNewDirectorActionPerformed

        String newDirectorName = tfDirectorName.getText().trim();
        try {
            directorRepo.createDirector(newDirectorName);

        } catch (Exception ex) {
            Logger.getLogger(UserForm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnCreateNewDirectorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreateNewActor;
    private javax.swing.JButton btnCreateNewDirector;
    private javax.swing.JButton btnCreateNewGenre;
    private javax.swing.JButton btnDeleteMovie;
    private javax.swing.JButton btnEditMovie;
    private javax.swing.JButton btnNewMovie;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JMenu jmExportToXml;
    private javax.swing.JMenuBar jmbMyMenu;
    private javax.swing.JMenuItem jmiCallDatabase;
    private javax.swing.JMenuItem jmiLogout;
    private javax.swing.JMenuItem jmiXmlExport;
    private javax.swing.JPanel jpActor;
    private javax.swing.JPanel jpDirector;
    private javax.swing.JPanel jpGenreForm;
    private javax.swing.JPanel jpMoviePanel;
    private javax.swing.JLabel lbActors;
    private javax.swing.JLabel lbDesc;
    private javax.swing.JLabel lbDirectors;
    private javax.swing.JLabel lbMovieName;
    private javax.swing.JLabel lbPoster;
    private javax.swing.JLabel lbShowingDate;
    private javax.swing.JList<String> lsActors;
    private javax.swing.JList<String> lsDirectors;
    private javax.swing.JList<String> lsSoloActors;
    private javax.swing.JList<String> lsSoloDirectors;
    private javax.swing.JList<String> lsSoloGenres;
    private javax.swing.JTextArea taDesc;
    private javax.swing.JTable tbMovieTable;
    private javax.swing.JTextField tfActorName;
    private javax.swing.JTextField tfDirectorName;
    private javax.swing.JTextField tfGenreName;
    private javax.swing.JTextField tfMovieName;
    private javax.swing.JTextField tfShowingDate;
    // End of variables declaration//GEN-END:variables

    private void init() {

        try {

            tbMovieTable.removeAll();
            lbPoster.setIcon(IconUtils.createIcon(new File("PlaceHolderPic/placeholder.jpg"), lbPoster.getWidth(), lbPoster.getHeight()));

        } catch (IOException ex) {
            Logger.getLogger(UserForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        initPicEdit();

    }

    private void initTable() {

        tbMovieTable.removeAll();

        tbMovieTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tbMovieTable.setAutoCreateRowSorter(true);

        tbMovieTable.setRowHeight(25);

        MovieTableModel mtm = new MovieTableModel(movieRepository.getAllMovies());

        tbMovieTable.setModel(mtm);

    }

    private Movie selectMovie() {

        int selectedRow = tbMovieTable.getSelectedRow();

        // Check if there is a valid selection
        if (selectedRow >= 0 && selectedRow < tbMovieTable.getRowCount()) {
            int rowIndex = tbMovieTable.convertRowIndexToModel(selectedRow);
            int selectedMoviesId = (int) tbMovieTable.getValueAt(rowIndex, 0);

            Movie selectedMovie = movieRepository.getMovieById(selectedMoviesId);
            return selectedMovie;
        } else {
            // No selection, or an invalid selection
            return null;
        }

    }

    private void showMovie() {

        try {
            fillForm(selectMovie());
        } catch (IOException ex) {
            Logger.getLogger(UserForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fillForm(Movie movie) throws IOException {

        listModelActors.clear();
        listModelDirectors.clear();
        listModelGenres.clear();

        movie.getActors().forEach(a -> listModelActors.addElement(a.getActorName()));
        movie.getDirectors().forEach(b -> listModelDirectors.addElement(b.getDirectorName()));
        movie.getGenres().forEach(c -> listModelGenres.addElement(c.getGenreName()));

        if (movie.getMoviePictureFilePath() != null && Files.exists(Paths.get(movie.getMoviePictureFilePath()))) {
            setIcon(lbPoster, new File(movie.getMoviePictureFilePath()));
        } else {
            lbPoster.setIcon(IconUtils.createIcon(new File("PlaceHolderPic/placeholder.jpg"), lbPoster.getWidth(), lbPoster.getHeight()));
        }

        lsDirectors.setModel(listModelDirectors);
        lsActors.setModel(listModelActors);
        taDesc.setText(movie.getMovieDescription());
        tfShowingDate.setText(movie.getShowingDate().toString());
        tfMovieName.setText(movie.getMovieName());

    }

    private void setIcon(JLabel label, File file) throws IOException {

        label.setIcon(IconUtils.createIcon(file.getAbsoluteFile(), label.getWidth(), label.getHeight()));
    }

    public void getDataFromDatabase() {

        tbMovieTable.removeAll();
        moviesList.removeAll(moviesList);

        moviesList = movieRepository.getAllMovies();

        initTable();
    }

    private void initPicEdit() {

        popupMenu = new JPopupMenu();

        JMenuItem menuItem = new JMenuItem("Change picture");
        menuItem.addActionListener((ActionEvent e) -> {

            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG Images", "jpg");
            fileChooser.setFileFilter(filter);

            int returnValue = fileChooser.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {

                File selectedFile = fileChooser.getSelectedFile();
                try {

                    String ext = selectedFile.getName().substring(selectedFile.getName().lastIndexOf("."));
                    if (!ext.equalsIgnoreCase(EXT)) {

                        JOptionPane.showMessageDialog(null, "Invalid file extension. Please select a file with the extension '" + EXT + "'.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    lbPoster.setIcon(IconUtils.createIcon(selectedFile, lbPoster.getWidth(), lbPoster.getHeight()));

                    String pictureName = UUID.randomUUID() + EXT;
                    String localPicturePath = DIR + File.separator + pictureName;

                    Files.copy(selectedFile.toPath(), Paths.get(localPicturePath), StandardCopyOption.REPLACE_EXISTING);

                    Movie selectedMovie = selectMovie();
                    selectedMovie.setMoviePictureFilePath(localPicturePath);

                    movieRepository.updateMovie(selectedMovie);

                } catch (IOException ex) {

                    Logger.getLogger(UserForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        popupMenu.add(menuItem);
    }

    private void initGenres() {

        try {
            List<Genre> allGenres = genreRepo.getAllGenres();

            DefaultListModel<String> listModel = new DefaultListModel<>();

            allGenres.forEach(genre -> {
                listModel.addElement(genre.getGenreName());
            });
            lsSoloGenres.setModel(listModel);
            lsSoloGenres.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent evt) {

                    if (evt.getClickCount() == 2) {
                        int index = lsSoloGenres.locationToIndex(evt.getPoint());
                        String item = listModel.getElementAt(index);
                        int dialogResult = JOptionPane.showConfirmDialog(null, "Would you like to delete " + item + "?");
                        if (dialogResult == JOptionPane.YES_OPTION) {
                            try {
                                genreRepo.deleteGenre(item);
                            } catch (Exception ex) {
                                Logger.getLogger(UserForm.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            listModel.remove(index);
                        }
                    }
                }
            });
        } catch (Exception ex) {
            Logger.getLogger(UserForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initActors() {
        try {
            List<Actor> allActors = actorRepo.getAllActors();
            DefaultListModel<String> listModel = new DefaultListModel<>();
            allActors.forEach(actor -> {
                listModel.addElement(actor.getActorName());
            });
            lsSoloActors.setModel(listModel);
            lsSoloActors.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent evt) {
                    if (evt.getClickCount() == 2) {
                        int index = lsSoloActors.locationToIndex(evt.getPoint());
                        String item = listModel.getElementAt(index);
                        int dialogResult = JOptionPane.showConfirmDialog(null, "Would you like to delete " + item + "?");
                        if (dialogResult == JOptionPane.YES_OPTION) {
                            try {
                                actorRepo.deleteActor(item);
                            } catch (Exception ex) {
                                Logger.getLogger(UserForm.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            listModel.remove(index);
                        }
                    }
                }
            });
        } catch (Exception ex) {
            Logger.getLogger(UserForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initDirectors() {
        try {

            List<Director> allDirectors = directorRepo.getAllDirectors();
            DefaultListModel<String> listModel = new DefaultListModel<>();

            allDirectors.forEach(director -> {
                listModel.addElement(director.getDirectorName());
            });

            lsSoloDirectors.setModel(listModel);
            lsSoloDirectors.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent evt) {
                    if (evt.getClickCount() == 2) {
                        int index = lsSoloDirectors.locationToIndex(evt.getPoint());
                        String item = listModel.getElementAt(index);
                        int dialogResult = JOptionPane.showConfirmDialog(null, "Would you like to delete " + item + "?");
                        if (dialogResult == JOptionPane.YES_OPTION) {
                            try {
                                directorRepo.deleteDirector(item);
                            } catch (Exception ex) {
                                Logger.getLogger(UserForm.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            listModel.remove(index);
                        }
                    }
                }
            });
        } catch (Exception ex) {
            Logger.getLogger(UserForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
