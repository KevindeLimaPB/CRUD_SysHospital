package appAdm.options.treeOptions.ListyOptions;
import dao.modifications.daoSelect;
import model.Paciente;

public class listyPacient {
    public void listaPaciente(){
        daoSelect dao = new daoSelect();

        for (Paciente p : dao.listaPacient()){
            System.out.println("ID_user: " + p.getId_usuario()
            + " cpf: " + p.getCpf() + " data de Nascimento: " + p.getData_nascimento());
        }
    }
}
