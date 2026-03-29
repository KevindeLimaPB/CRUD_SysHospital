package Logs;

import Service.ScanEntry;
import appAdm.telas.*;
import appUser.Medic.TelaMedic;
import appUser.Pacient.TelaPacient;
import dao.daoControlls;
import appUser.User.TelaUser;
import model.Usuario;

public class entrada {
    daoControlls user = new daoControlls();

    public void entrad(){
        //ADMIN_EMAIL=devHospital@gmail.com
        //ADMIN_SENHA=hospital1234


        while (true) {

            System.out.println("Informe seu email: ");
            String email = ScanEntry.sc.nextLine();

            System.out.println("Informe sua senha: ");
            String senha = ScanEntry.sc.nextLine();
            user.validacaoLog(email, senha);

            Usuario u = user.validacaoLog(email,senha);
            //NOVO, PODE COLOCA O VALOR NUMA CLASSE COM INICIO DE VARIAVEL

            if (u != null){

                if (u.getPerfil().equalsIgnoreCase("admin")){
                    System.out.println("Entrando na tela Admin");
                    new telaAdm().exibirAdmin();
                }

                if (u.getPerfil().equalsIgnoreCase("user")){
                    System.out.println("Entrando na tela Usuário");
                    new TelaUser().exibirUser();
                }

                if (u.getPerfil().equalsIgnoreCase("paciente")){
                    System.out.println("Entrando na tela Paciente");

                    new TelaPacient().TelaPaciente();
                }

                if (u.getPerfil().equalsIgnoreCase("Médico")){
                    System.out.println("Entrando na tela Médico");
                    new TelaMedic().telaMedic();
                }

            }
        }
    }
}
