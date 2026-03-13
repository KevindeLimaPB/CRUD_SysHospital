package appAdm.options.secundaryOptions;
//parte onde o admin escolhe ser o usuario será paciente ou médico.
import Service.ScanEntry;
import appAdm.options.secundaryOptions.selectedOption.*;
import appAdm.telas.telaAdm;

public class choiceOptions {
    public void choice(){
        int opcao;
        do {
            System.out.println("1 - Paciente");
            System.out.println("2 - Médico");
            System.out.println("3 - Voltar ao Início");
            opcao = ScanEntry.sc.nextInt();
            ScanEntry.sc.nextLine();

            if (opcao == 1){
                new pacientOption().pacienteCadastro();
            }

            if(opcao == 2){
                new medicOption().medicoCadastro();
            }

            if (opcao == 3){
                new telaAdm().exibirAdmin();
            }
        }while (opcao != 3);
    }
}
