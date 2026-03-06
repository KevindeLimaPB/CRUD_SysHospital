package Service.classService;
import model.Usuario;
import java.util.HashMap;

public class UserEntry {

    public static Usuario userEntry = new Usuario();

    public HashMap<Integer, Usuario> hashbusc(){
        HashMap<Integer, Usuario> userHash = new HashMap<>();
        userHash.put(userEntry.getId(), userEntry);

        return userHash;
    }


}
