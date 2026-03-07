package app.telas;

import Service.*;
import app.options.*;
import app.options.secundaryOptions.choiceOptions;

public class telaAdm {
    public void exibirAdmin() {
        int opcao;

        do {
            System.out.println("1 - Cadastrar usuário");
            System.out.println("2 - informa o perfil(P/M)");
            System.out.println("3 - Atualizar dados");
            System.out.println("4 - Listas do Hospital");
            System.out.println("5 - Consultas Realizadas");
            System.out.println("6 - Remove Usuário");
            System.out.println("7 - Sair do Sistema");
            opcao = ScanEntry.sc.nextInt();
            ScanEntry.sc.nextLine();

            if (opcao == 1) {
                new primeryOptions().primeiraOpcao();
            }

            if (opcao == 2) {
                new choiceOptions().choice();
            }

        } while (opcao != 7);
    }
}
