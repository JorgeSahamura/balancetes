package conexao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import org.eclipse.jdt.internal.compiler.batch.Main;

public class FabricaConexao {
	private static Properties config = new Properties();
	private static String arquivo;
	
 public static Connection getconnection() throws ClassNotFoundException, SQLException {
	Connection conexao=null;
	try {
		String diretorio = new File(".").getCanonicalPath();
		arquivo=diretorio + "//config.ini";
	} catch (IOException e) {
		e.printStackTrace();
	}
	String servidor;
	String usuario;
	String senha;
	String porta;
    try {
        config.load(new FileInputStream(arquivo));
        servidor=config.getProperty("SERVIDOR");
        usuario=config.getProperty("USUARIO"); 
        senha=config.getProperty("SENHA");
        porta=config.getProperty("PORTA");
    	String url = "jdbc:sqlserver://" + servidor + ":" + porta + ";databaseName=ConsistGem";
    	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    	conexao = DriverManager.getConnection(url,usuario,senha);
} catch (IOException ex) {
	JOptionPane.showMessageDialog(null,"Problema ao ler arquivo de configuração config.ini");
//        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
}
	
//	String usuario = "";
//	String senha = "";
//	String server ="localhost";
//	String port = "1433";
//    String url = "jdbc:sqlserver://.\\SQLEXPRESS;databaseName=ConsistGem; Integrated Security = SSPI ";
	return conexao;
	}
}
