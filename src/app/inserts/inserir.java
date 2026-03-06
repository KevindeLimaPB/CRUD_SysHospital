package app.inserts;

import Service.ScanEntry;

public class inserir {

    public String insertDiceEmail() {
        System.out.println("Digite o email: ");
        String email;
        return email = ScanEntry.sc.nextLine();
    }

    public String insertDicePassword() {
        System.out.println("Digite a senha: ");
        String password;
        return password = ScanEntry.sc.nextLine();
    }
}
