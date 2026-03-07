package app.options.secundaryOptions;
//parte onde o admin escolhe ser o usuario será paciente ou médico.
import Service.ScanEntry;
import app.options.secundaryOptions.selectedOption.*;

public class choiceOptions {
    public void choice(){
        int opcao;
        do {
            System.out.println("1 - Paciente");
            System.out.println("2 - Médico");
            opcao = ScanEntry.sc.nextInt();
            ScanEntry.sc.nextLine();

            if (opcao == 1){
                new pacientOption().pacienteCadastro();
            }
        }while (opcao != 3);
    }
}
