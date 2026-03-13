package Logs;

import Config.config;
import Service.ScanEntry;
import appAdm.telas.*;
import dao.UserControlls;
import appUser.User.TelaUser;

public class maxTentatvs {

    //MANUTENÇÃO NESSA PARTE


    UserControlls user = new UserControlls();
    static boolean lock = false;

    int tentativas_Admin = 0, tentativas_User = 0;

    public void tentativsCh() {
        //ADMIN_EMAIL=devHospital@gmail.com
        //ADMIN_SENHA=hospital1234
        while (true) {

            System.out.println("informe o Email: ");
            String email = ScanEntry.sc.nextLine();

            System.out.println("Informe a senha: ");
            String senha = ScanEntry.sc.nextLine();

            String perfil = user.validacaoLog(email,senha);

            if (perfil != null && perfil.equals("User")) {
                System.out.println("Login de Usuário realizado!");
                new TelaUser().d();
                return;
            } else {
                System.out.println("ERRADO");
                tentativas_User++;
            }

            if (!lock && email.equals(config.getAdmEmail()) && senha.equals(config.getAdmSenha())){
                new telaAdm().exibirAdmin();
            }else {
                tentativas_Admin++;
            }

            if (tentativas_Admin == 5){
                lock = true;
                System.out.println("Usuário admin bloqueado!");
            }

            if (tentativas_User == 3){
                lock = true;
                System.out.println("Tente novamente mais tarde!");
            }
        }
    }
}
