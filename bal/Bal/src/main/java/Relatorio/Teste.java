package Relatorio;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import org.eclipse.jdt.internal.compiler.batch.Main;

import Tabelas.Fcodf;
import dao.FcodfDao;
import java.io.File;
import java.io.IOException;

public class Teste {
	private static Properties config = new Properties();
	private static String arquivo;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
				try {
					String diretorio = new File(".").getCanonicalPath();
					arquivo=diretorio + "//config.ini";
				} catch (IOException e) {
					e.printStackTrace();
				}
        try {
            config.load(new FileInputStream(arquivo));
           
            System.out.println("Iniciando processo de leitura de configurações: ");
           
            System.out.println();
           
            System.out.println(config.getProperty("SERVIDOR"));
            System.out.println(config.getProperty("USUARIO"));
            System.out.println(config.getProperty("SENHA"));
            System.out.println(config.getProperty("PORTA"));
           
            System.out.println();
           
            System.out.println("Finalizando leitura!");
    } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }
	}

}


