package appUser.Pacient.OptionsPacient;

import Service.ScanEntry;
import Service.classService.PacientEntry;
import appUser.Pacient.TelaPacient;
import dao.modifications.daoUp;

public class AttCpf {
    public void upCpf(){
        System.out.println("Informe o novo cpf: ");
        String cpf = ScanEntry.sc.nextLine();
        PacientEntry.pacientEntry.setCpf(cpf);

        System.out.println("Informe o ID: ");
        int id = ScanEntry.sc.nextInt();
        PacientEntry.pacientEntry.setId(id);

        new daoUp().pacient(PacientEntry.pacientEntry);
        new TelaPacient().TelaPaciente();
    }
}
