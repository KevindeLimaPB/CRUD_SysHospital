package Config;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class config {
    private static String URL;
    private static String USER;
    private static String PASSWORD;
    private  static  Properties prop = new Properties();;
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try(FileInputStream file = new FileInputStream("config.properties")) {
                prop.load(file);

                URL = prop.getProperty("db_url");
                USER = prop.getProperty("db_user");
                PASSWORD = prop.getProperty("db_password");
            }

        }catch (ClassNotFoundException e){
            System.err.println("❎Driver não encontrado" + e);
        }catch (IOException e){
            System.err.println("❎Arquivo não encontrado");
            e.printStackTrace();
        }
    }

    public static String getAdmEmail(){
        return prop.getProperty("ADMIN_EMAIL");
    }

    public static  String getAdmSenha(){
        return prop.getProperty("ADMIN_SENHA");
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }

    public static boolean testeConnection(){
        try(Connection conn = getConnection()) {
            return true;
        }catch (SQLException e){
            System.err.println("❎ERRO");
            return false;
        }
    }
}
