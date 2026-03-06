package app.SecurtyJv;

import app.inserts.inserir;
import Config.config;
import app.telas.*;

public class maxTentatvs {

    inserir inser = new inserir();
    static boolean lock = false;
    int tentativas = 0;

    public void tentativsCh() {

        //ADMIN_EMAIL=devHospital@gmail.com
        //ADMIN_SENHA=hospital1234

        while (true) {

            String email = inser.insertDiceEmail();
            String pass = inser.insertDicePassword();

            if (!lock && email.equals(config.getAdmEmail()) && pass.equals(config.getAdmSenha())) {
                new telaAdm().exibirAdmin();
                return;
            } else {
                System.err.println("❎Login or Password incorretos!");
                tentativas++;
            }

            if (tentativas == 5) {
                tentativas = 0;
                lock = true;
                System.out.println("🔒Usuário Bloqueado após muitas tentativas!");
            }
        }
    }
}
