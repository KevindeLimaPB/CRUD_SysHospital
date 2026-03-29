package appUser.Medic.OptionsMedic;
import InitMain.Main;
import Service.ScanEntry;
import dao.modifications.daoRemove;
public class DeleteMedic {
    public void deletarMedic(){
        System.out.println("Informe o ID:");
        int id = ScanEntry.sc.nextInt();

        new daoRemove().removeUser(id);
        new Main().inicio();
    }
}
