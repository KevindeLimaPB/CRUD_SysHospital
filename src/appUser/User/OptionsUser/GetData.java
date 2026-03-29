package appUser.User.OptionsUser;

import Service.ScanEntry;
import Service.classService.UserEntry;
import appUser.User.TelaUser;
import dao.modifications.daoUp;

public class GetData {

    public void MudarEmail(){
        System.out.println("Informe seu novo email: ");
        String email = ScanEntry.sc.nextLine();
        UserEntry.userEntry.setEmail(email);

        System.out.println("Informe seu ID para alterar: ");
        int id = ScanEntry.sc.nextInt();
        UserEntry.userEntry.setId(id);

        new daoUp().alteraEmail(UserEntry.userEntry);
        new TelaUser().exibirUser();

    }
}
