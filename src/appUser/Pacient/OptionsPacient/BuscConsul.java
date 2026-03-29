package appUser.Pacient.OptionsPacient;

import Service.ScanEntry;
import Service.classService.UserEntry;
import appUser.Pacient.TelaPacient;
import dao.modifications.daoSelect;

public class BuscConsul {
    public void consulPacient(){
        System.out.println("Informe o ID: ");
        int id = ScanEntry.sc.nextInt();
        UserEntry.userEntry.setId(id);

        new daoSelect().buscarConsulta(UserEntry.userEntry);
        new TelaPacient().TelaPaciente();

    }
}
