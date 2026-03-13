package app.options.treeOptions.ListyOptions;
import dao.modifications.daoSelect;
import model.Medico;

public class listyMedic {
    public void listaMedic(){
        daoSelect dao = new daoSelect();
        for (Medico m : dao.listaMedic()){
            System.out.println("Id: " + m.getIdMedico() + " Profissão: " + m.getProfissao()
            + "Especialidade: " + m.getEspecialidade());
        }
    }
}
