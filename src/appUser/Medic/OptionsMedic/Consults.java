package appUser.Medic.OptionsMedic;

import Service.ScanEntry;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import appUser.Medic.TelaMedic;
import model.Consultas;
import dao.daoControlls;
public class Consults {
    public void inform(){
        Consultas cons = new Consultas();
        System.out.println("Informe seu Id: ");
        int id_Medic = ScanEntry.sc.nextInt();
        cons.setMedico(id_Medic);
        System.out.println("Informe o id do paciente: ");
        int idPaciente = ScanEntry.sc.nextInt();
        cons.setPaciente(idPaciente);

        System.out.println("Informe sua data de nascimento(dd/MM/yyyy): ");
        String dataRealizada = ScanEntry.sc.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(dataRealizada, formatter);
        cons.setData_realizada(date);
        System.out.println("Relátorio: ");
        String relatorio = ScanEntry.sc.nextLine();
        cons.setRelatorio(relatorio);
        new daoControlls().consultasRealizadas(cons);
        new TelaMedic().telaMedic();
    }
}
