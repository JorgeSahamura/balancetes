package Telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Relatorio.Balancete;
import Relatorio.RazaoAnalitico;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;

public class Client extends JFrame {

	private JPanel contentPane;
	private JTextField fco;
	private JTextField fyr;
	private JTextField per;
	private ButtonGroup rb = new ButtonGroup();
	private String tipo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client frame = new Client();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Client() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 254);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JButton btnGerarRelatorio = new JButton("Gerar Relatorio");
		btnGerarRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tipo!="razao") {
				Balancete bal = new Balancete();
		 		bal.gerabalancete(fco.getText(),Integer.parseInt(fyr.getText()), Integer.parseInt(per.getText()), tipo);	
				}
				else
				{
					RazaoAnalitico razaoanalitico=new RazaoAnalitico();
					razaoanalitico.gerarazao(fco.getText(), Integer.parseInt(fyr.getText()), Integer.parseInt(per.getText()));
				}
			}
		});
		contentPane.add(btnGerarRelatorio, BorderLayout.SOUTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JRadioButton rbsintetico = new JRadioButton("Balancete Sint\u00E9tico");
		rbsintetico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipo="sintetico";
			}
		});
		panel.add(rbsintetico);
		rbsintetico.setSelected(true);
		JRadioButton rbanalitico = new JRadioButton("Balancete Anal\u00EDtico");
		rbanalitico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipo="analitico";
			}
		});
		panel.add(rbanalitico);
		
		JRadioButton rbrazao = new JRadioButton("Raz\u00E3o Anal\u00EDtico");
		rbrazao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipo="razao";
			}
		});
		panel.add(rbrazao);
		rb.add(rbsintetico);
		rb.add(rbanalitico);
		rb.add(rbrazao);
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JLabel lblPerodo = new JLabel("Per\u00EDodo");
		lblPerodo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPerodo.setBounds(106, 72, 71, 14);
		panel_1.add(lblPerodo);
		
		JLabel lblExerccio = new JLabel("Exerc\u00EDcio");
		lblExerccio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblExerccio.setBounds(90, 36, 87, 14);
		panel_1.add(lblExerccio);
		
		JLabel lblCompanhia = new JLabel("Companhia");
		lblCompanhia.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCompanhia.setBounds(90, 11, 87, 14);
		panel_1.add(lblCompanhia);
		
		fco = new JTextField();
		fco.setBounds(187, 5, 86, 20);
		panel_1.add(fco);
		fco.setColumns(10);
		fco.setText("SER");
		fyr = new JTextField();
		fyr.setText("1998");
		fyr.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c=e.getKeyChar();
				if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE) || c==KeyEvent.VK_DELETE)){
			        e.consume();
			    }  
			}
		});
		fyr.setBounds(187, 33, 86, 20);
		panel_1.add(fyr);
		fyr.setColumns(10);
		
		per = new JTextField();
		per.setText("1");
		per.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c=e.getKeyChar();
				if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE) || c==KeyEvent.VK_DELETE)){
			        e.consume();
			}
			}
		});
		per.setBounds(186, 66, 86, 20);
		panel_1.add(per);
		per.setColumns(10);
	}
}
