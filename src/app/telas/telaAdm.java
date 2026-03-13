package app.telas;

import Service.*;
import app.options.*;
import app.options.secundaryOptions.choiceOptions;
import app.options.treeOptions.choiceOptionListy;

public class telaAdm {
    public void exibirAdmin() {
        int opcao;

        do {

            System.out.println("------BEM-VINDO ADMIN------");
            System.out.println("1 - Cadastrar usuário");
            System.out.println("2 - informa o perfil(P/M)");
            System.out.println("3 - Listas do Hospital");
            System.out.println("4 - Remove Usuário");//fazer amanhã
            System.out.println("5 - Sair do Sistema");
            System.out.println("---------------------------");

            opcao = ScanEntry.sc.nextInt();
            ScanEntry.sc.nextLine();

            if (opcao == 1) {
                new primeryOptions().primeiraOpcao();
            }

            if (opcao == 2) {
                new choiceOptions().choice();
            }

            if (opcao == 3) {
                new choiceOptionListy().choiceOption();
            }

        } while (opcao != 5);
    }
}
