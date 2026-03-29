package appUser.User;

import Service.ScanEntry;
import appUser.User.OptionsUser.*;
import InitMain.Main;

public class TelaUser {
    public void exibirUser(){
        int opcao;
        do {
            System.out.println("*--- TELA USUÁRIO ---*");
            System.out.println("1 - Mudar email");
            System.out.println("2 - Exclui conta");
            System.out.println("3 - Sair da conta");
            System.out.println("*--- ----------- ---*");
            opcao = ScanEntry.sc.nextInt();
            ScanEntry.sc.nextLine();

            if (opcao == 1) {
                new GetData().MudarEmail();
            }

            if (opcao == 2) {
                new home().excluirConta();
            }

            if (opcao == 3){
                new Main().inicio();
            }

        }while (opcao != 3);
    }
}
