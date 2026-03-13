package Logs;

import Service.ScanEntry;
import Service.classService.UserEntry;
import dao.UserControlls;

public class createLog {
    public void criarLog(){
        System.out.println("*----CRIAR CONTA----*");

        System.out.println("Informe seu nome: ");
        String nome = ScanEntry.sc.nextLine();
        UserEntry.userEntry.setNome(nome);

        System.out.println("Informe seu email: ");
        String email = ScanEntry.sc.nextLine();
        UserEntry.userEntry.setEmail(email);

        System.out.println("Senha: ");
        String senha = ScanEntry.sc.nextLine();
        UserEntry.userEntry.setSenha(senha);

        System.out.println("Informe sua senha novamente: ");
        String validar_senha = ScanEntry.sc.nextLine();

        if (senha.equals(validar_senha)){
            UserEntry.userEntry.setPerfil("User");
            new UserControlls().criandoConta(UserEntry.userEntry);
        }else {
            System.out.println("Senha errada!");
        }
    }
}
