package Service;

import java.security.MessageDigest;

public class Servicos {
    public String criptografia(String criptografia){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashbyte = digest.digest(criptografia.getBytes());
            StringBuilder builder = new StringBuilder();
            for (byte c : hashbyte){
                builder.append(String.format("0%2x", c));
            }
            return builder.toString();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
