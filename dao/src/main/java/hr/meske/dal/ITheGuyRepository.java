package hr.meske.dal;

import hr.meske.dal.models.TheGuy;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author 505wL
 */
public interface ITheGuyRepository {

    void createTheGuy(String username, String password);

    void editTheGuy(String username, String password);

    void deleteTheGuy(int id);

    Optional<TheGuy> getTheGuy(int id);

    List<TheGuy> getAllGuys();

    public boolean SearchTheGuyByUsername(String username);

    public boolean isPasswordMatch(String username, String password);

    public Boolean isAdmin(String username, String password);

    public boolean registerTheGuy(String username, String password);
}
