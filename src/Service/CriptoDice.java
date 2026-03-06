package Service;

import java.security.MessageDigest;

public class CriptoDice {
    public String criptografia(String criptografia){
        StringBuilder builder = new StringBuilder();
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashbyte = digest.digest(criptografia.getBytes());
            for (byte c : hashbyte){
                builder.append(String.format("%02x", c));
            }

        }catch (Exception e){
            e.printStackTrace();

        }

        return builder.toString();
    }

}
