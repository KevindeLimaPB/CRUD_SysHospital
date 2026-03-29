package appUser.Pacient.OptionsPacient;

import InitMain.Main;
import Service.ScanEntry;
import dao.modifications.daoRemove;

public class RemovPacient {
    public void deletPacient(){
        System.out.println("Informe o ID:");
        int id = ScanEntry.sc.nextInt();

        new daoRemove().removeUser(id);
        new Main().inicio();
    }
}
