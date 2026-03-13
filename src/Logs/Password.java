package Logs;

import Service.ScanEntry;
import Service.classService.UserEntry;
import dao.modifications.daoUp;
public class Password {
    public void passSenha(){
        System.out.println("*---MUDAR SENHA---*");
        System.out.println("Informe sua nova senha: ");
        String senha = ScanEntry.sc.nextLine();
        UserEntry.userEntry.setSenha(senha);
        new daoUp().esqueciSenha(UserEntry.userEntry);

        new maxTentatvs();
    }
}
