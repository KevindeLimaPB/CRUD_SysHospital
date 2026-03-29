package appAdm.options.secundaryOptions.selectedOption;

import Service.ScanEntry;
import Service.classService.UserEntry;
import Service.classService.medicEntry;
import dao.AdminControlls;
import appAdm.telas.telaAdm;
import dao.modifications.daoUp;
public class medicOption {

    public void medicoCadastro(){
        System.out.println("*****CADASTRO MÉDICO*****");

        String perfil = "Médico";
        UserEntry.userEntry.setPerfil(perfil);

        System.out.println("Informe o ID: ");
        int id = ScanEntry.sc.nextInt();
        medicEntry.medico.setIdMedico(id);
        UserEntry.userEntry.setId(id);
        ScanEntry.sc.nextLine();
        System.out.println("Sua Profissão: ");
        String profissao = ScanEntry.sc.nextLine();
        medicEntry.medico.setProfissao(profissao);

        System.out.println("Sua especialidade: ");
        String especialidade = ScanEntry.sc.nextLine();
        medicEntry.medico.setEspecialidade(especialidade);

        new AdminControlls().cadastrarMedic(medicEntry.medico);
        new daoUp().atualizarPerfil(UserEntry.userEntry);
        new telaAdm().exibirAdmin();
    }
}
