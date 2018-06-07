package co.com.appmimas.appmimas.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class conexionDB {
    
    
    public static Connection getConexion() {
        String url = "jdbc:mysql://localhost:3306/mimascota";
        String usuario = "root";
        String clave = "";
        String driver = "com.mysql.jdbc.Driver";
//    	  String url = "jdbc:mysql://sql10.freesqldatabase.com:3306/id6092333_appmimas";
//    	  String usuario = "sql10241187";
//    	  String clave = "SrbCPgXXsc";
//    	  String driver = "com.mysql.jdbc.Driver";
        return getConexion(url, usuario, clave, driver);
    }

    public static Connection getConexion(String url, String usuario,
            String clave, String driver) {
        Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, usuario, clave);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return con;
    }
    

}
