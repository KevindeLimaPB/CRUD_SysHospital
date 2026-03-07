package app.options.secundaryOptions.selectedOption;

import Service.ScanEntry;
import Service.classService.PacientEntry;
import dao.AdminControlls;
import app.telas.telaAdm;
import Service.classService.UserEntry;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class pacientOption {

    public void pacienteCadastro(){
        System.out.println("*****CADASTRO DE PACIENTE*****");

        UserEntry.userEntry.setPerfil("Paciente");

        System.out.println("Informe o ID: ");
        int id = ScanEntry.sc.nextInt();
        PacientEntry.pacientEntry.setId_usuario(id);

        ScanEntry.sc.nextLine();
        System.out.println("Informe seu CPF: ");
        String cpf = ScanEntry.sc.nextLine();


        if (cpf.matches("\\d{11}")){
            PacientEntry.pacientEntry.setCpf(cpf);
            System.out.println("Válido");

            System.out.println("Informe sua data de nascimento(dd/MM/yyyy): ");
            String dataNascimento = ScanEntry.sc.nextLine();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(dataNascimento, formatter);
            PacientEntry.pacientEntry.setData_nascimento(date);
        }else {
            System.out.println("Inválido, Tem que ter 11 dígitos!");
        }


        new AdminControlls().cadastrarPacient(PacientEntry.pacientEntry);
        new telaAdm().exibirAdmin();
    }
}
