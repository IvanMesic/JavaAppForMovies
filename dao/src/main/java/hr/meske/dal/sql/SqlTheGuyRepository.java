/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.meske.dal.sql;

import hr.meske.dal.ITheGuyRepository;
import hr.meske.dal.models.TheGuy;
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
public class SqlTheGuyRepository implements ITheGuyRepository {

    private static final String ID_THE_GUY = "IdTheGuy";
    private static final String USERNAME = "Username";
    private static final String PASSWORD = "Password";
    private static final String ROLE = "Role";
    private static final String IS_ADMIN = "IsAdmin";
    
    private static final String CHECK_IF_ADMIN = "{ CALL CheckIfAdmin (?, ?, ?) }";
    private static final String CREATE_THE_GUY = "{ CALL CreateTheGuy (?,?) }";
    private static final String EDIT_THE_GUY = "{ CALL EditTheGuy (?,?,?) }";
    private static final String DELETE_THE_GUY = "{ CALL DeleteTheGuy (?) }";
    private static final String READ_THE_GUY = "{ CALL ReadTheGuy (?) }";
    private static final String READ_ALL_GUYS = "{ CALL ReadAllGuys }";
    private static final String CHECK_PASSWORD_FOR_USERNAME = "{ CALL CheckPasswordForUsername (?, ?, ?) }";
    private static final String SEARCH_THE_GUY_BY_USERNAME = "{ CALL SearchTheGuyByUsername (?, ?) }";
    DataSource dataSource = DataSourceSingleton.getInstance();

    public SqlTheGuyRepository() {
    }

    @Override
    public void createTheGuy(String username, String password) {
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_THE_GUY)) {
            stmt.setString(USERNAME, username);
            stmt.setString(PASSWORD, password);
            stmt.executeUpdate();
        } catch (SQLException e) {
        }
    }

    @Override
    public void editTheGuy(String username, String password) {
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(EDIT_THE_GUY)) {
            stmt.setString(USERNAME, username);
            stmt.setString(PASSWORD, password);
            stmt.executeUpdate();
        } catch (SQLException e) {
        }
    }

    @Override
    public void deleteTheGuy(int id) {
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_THE_GUY)) {
            stmt.setInt(ID_THE_GUY, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
        }
    }

    @Override
    public Optional<TheGuy> getTheGuy(int id) {
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(READ_THE_GUY)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int theGuyId = rs.getInt(ID_THE_GUY);
                    String username = rs.getString(USERNAME);
                    String password = rs.getString(PASSWORD);
                    int role = rs.getInt(ROLE);
                    TheGuy theGuy = new TheGuy(theGuyId, username, password, role);
                    return Optional.of(theGuy);
                }
            }
        } catch (SQLException e) {
        }
        return Optional.empty();
    }

    @Override
    public List<TheGuy> getAllGuys() {
        List<TheGuy> guys = new ArrayList<>();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(READ_ALL_GUYS); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int theGuyId = rs.getInt(ID_THE_GUY);
                String username = rs.getString(USERNAME);
                String password = rs.getString(PASSWORD);
                int role = rs.getInt(ROLE);
                TheGuy theGuy = new TheGuy(theGuyId, username, password, role);
                guys.add(theGuy);
            }
        } catch (SQLException e) {
        }
        return guys;
    }

    @Override
    public boolean isPasswordMatch(String username, String password) {
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CHECK_PASSWORD_FOR_USERNAME)) {
            stmt.setString(USERNAME, username);
            stmt.setString(PASSWORD, password);
            stmt.registerOutParameter(3, Types.BOOLEAN);
            stmt.execute();
            return stmt.getBoolean(3);
        } catch (SQLException e) {
        }
        return false;
    }

    @Override
    public boolean SearchTheGuyByUsername(String username) {
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SEARCH_THE_GUY_BY_USERNAME)) {
            stmt.setString(1, username);
            stmt.registerOutParameter(2, Types.BOOLEAN);
            stmt.execute();
            return stmt.getBoolean(2);
        } catch (SQLException e) {
        }
        return false;
    }

    @Override
    public Boolean isAdmin(String username, String password) {
        Boolean isAdmin = null;
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CHECK_IF_ADMIN)) {
            stmt.setString(USERNAME, username);
            stmt.setString(PASSWORD , password);
            stmt.registerOutParameter(3, Types.TINYINT);
            stmt.execute();
            int result = stmt.getInt(3);
            if (stmt.wasNull()) {
                isAdmin = null; // Indicates invalid username or password
            } else {
                isAdmin = result == 1;
            }
        } catch (SQLException e) {
        }
        return isAdmin;
    }

    @Override
    public boolean registerTheGuy(String username, String password) {
        boolean isRegistered = false;
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall("{ CALL RegisterTheGuy (?, ?, ?) }")) {
            stmt.setString(USERNAME, username);
            stmt.setString(USERNAME, password);
            stmt.execute();
            isRegistered = stmt.getInt(3) == 1;
        } catch (SQLException e) {
        }
        return isRegistered;
    }

}
