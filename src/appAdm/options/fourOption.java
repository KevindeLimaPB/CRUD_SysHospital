package appAdm.options;

import Service.ScanEntry;
import dao.modifications.daoRemove;

public class fourOption {
    public void removerUser(){

        System.out.println("****REMOVER USER****");

        System.out.println("Informe o ID para remover: ");
        int id  = ScanEntry.sc.nextInt();
        new daoRemove().removeUser(id);
    }
}
