package app.options;

import Service.ScanEntry;
import Service.classService.UserEntry;
import dao.AdminControlls;
import app.telas.telaAdm;

public class primeryOptions {

    String name;
    String email;
    String password;
    String perfil;

    public void primeiraOpcao() {

        System.out.println("*****CADASTRO DE USUÁRIO****");

        System.out.println("Informe o nome: ");
        name = ScanEntry.sc.nextLine();
        UserEntry.userEntry.setNome(name);

        System.out.println("Informe o email: ");
        email = ScanEntry.sc.nextLine();
        UserEntry.userEntry.setEmail(email);

        System.out.println("Informe a senha: ");
        password = ScanEntry.sc.nextLine();
        UserEntry.userEntry.setSenha(password);

        perfil = "User";
        UserEntry.userEntry.setPerfil(perfil);

        new AdminControlls().cadastrarUser(UserEntry.userEntry);

        new telaAdm().exibirAdmin();

    }
}
