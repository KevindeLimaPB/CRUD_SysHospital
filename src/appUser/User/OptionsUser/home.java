package appUser.User.OptionsUser;

import Service.ScanEntry;
import dao.modifications.daoRemove;
import InitMain.Main;

public class home {
    public void excluirConta(){
        System.out.println("Informe o id: ");
        int id = ScanEntry.sc.nextInt();

        new daoRemove().removeUser(id);
        new Main().inicio();

    }
}
