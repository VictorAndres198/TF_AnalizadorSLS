package tf.analizador;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.file.Files;

import javax.swing.JScrollPane;

public class FrmPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	protected JTextArea txtAnalizar;
	protected JTextArea txtResultado;
	
	private final static String nuevaLinea= "\n";
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPrincipal frame = new FrmPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FrmPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 519);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 43, 414, 165);
        contentPane.add(scrollPane);
        
        txtAnalizar = new JTextArea();
        scrollPane.setViewportView(txtAnalizar);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(10, 253, 414, 216);
        contentPane.add(scrollPane_1);
        
        txtResultado = new JTextArea();
        scrollPane_1.setViewportView(txtResultado);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                JFileChooser choser = new JFileChooser();
                choser.showOpenDialog(null);
                File archivo = new File(choser.getSelectedFile().getAbsolutePath());
                
                try {
                    String cadena = new String(Files.readAllBytes(archivo.toPath()));
                    txtAnalizar.setText(cadena);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
		});
		btnBuscar.setBounds(10, 11, 414, 23);
		contentPane.add(btnBuscar);
		
		JButton btnAnalizar = new JButton("Analizar");
		btnAnalizar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            int contador = 1;
		            String expresion = txtAnalizar.getText(); 
		            Lexer lexer = new Lexer(new StringReader(expresion));                    
		            String resultado = "PALABRA RESERVADA" + "\tSIMBOLO" + nuevaLinea + " ";                     
		            while (true) {
		                Tokens token = lexer.yylex();
		                if (token == null) { 
		                      txtResultado.setText(resultado);
		                      return; 
		                  } 
		                switch (token) { 
		                case Linea:
		                    contador++;
		                    resultado += " ";
		                    break;
		                case Let:
		                    resultado += "Let\t\t" + " " + lexer.lexeme + nuevaLinea;
		                    break;
		                case In:
		                    resultado += "In\t\t" + " " + lexer.lexeme + nuevaLinea;
		                    break;
		                case Where:
		                    resultado += "Where\t\t" + " " + lexer.lexeme + nuevaLinea;
		                    break;
		                case If:
		                    resultado += "If\t\t" + " " + lexer.lexeme + nuevaLinea;
		                    break;
		                case Then:
		                    resultado += "Then\t\t" + " " + lexer.lexeme + nuevaLinea;
		                    break;
		                case Else:
		                    resultado += "Else\t\t" + " " + lexer.lexeme + nuevaLinea;
		                    break;
		                case Case:
		                    resultado += "Case\t\t" + " " + lexer.lexeme + nuevaLinea;
		                    break;
		                case Of:
		                    resultado += "Of\t\t" + " " + lexer.lexeme + nuevaLinea;
		                    break;
		                case Data:
		                    resultado += "Data\t\t" + " " + lexer.lexeme + nuevaLinea;
		                    break;
		                case Type:
		                    resultado += "Type\t\t" + " " + lexer.lexeme + nuevaLinea;
		                    break;
		                case Int:
		                    resultado += "Int\t\t" + " " + lexer.lexeme + nuevaLinea;
		                    break;
		                case Double:
		                    resultado += "Double\t\t" + " " + lexer.lexeme + nuevaLinea;
		                    break;
		                case Char:
		                    resultado += "Char\t\t" + " " + lexer.lexeme + nuevaLinea;
		                    break;
		                case Bool:
		                    resultado += "Bool\t\t" + " " + lexer.lexeme + nuevaLinea;
		                    break;
		                case Igual:
		                    resultado += "Igual\t\t" + " " + lexer.lexeme + nuevaLinea;
		                    break;
		                case Suma:
		                    resultado += "Suma\t\t" + " " + lexer.lexeme + nuevaLinea;
		                    break;
		                case Resta:
		                    resultado += "Resta\t\t" + " " + lexer.lexeme + nuevaLinea;
		                    break;
		                case Multiplicacion:
		                    resultado += "Multiplicacion\t\t" + " " + lexer.lexeme + nuevaLinea;
		                    break;
		                case Division:
		                    resultado += "Division\t\t" + " " + lexer.lexeme + nuevaLinea;
		                    break;
		                case Modulo:
		                    resultado += "Modulo\t\t" + " " + lexer.lexeme + nuevaLinea;
		                    break;
		                case Potencia:
		                    resultado += "Potencia\t\t" + " " + lexer.lexeme + nuevaLinea;
		                    break;
		                case Identificador:
		                    resultado += "Identificador\t\t" + " " + lexer.lexeme + nuevaLinea;
		                    break;
		                case Numero:
		                    resultado += "Numero\t\t" + " " + lexer.lexeme + nuevaLinea;
		                    break;
		                case ERROR:
		                    resultado += "ERROR\t\t" + " " + lexer.lexeme + nuevaLinea;
		                    break;
		                default:
		                    resultado += token + " " + lexer.lexeme + nuevaLinea;
		                    break;
		            }
		            }
		            } catch (IOException e1) {
		                    e1.printStackTrace();
		                } 
		          }
		});
		btnAnalizar.setBounds(10, 219, 414, 23);
		contentPane.add(btnAnalizar);
	}
}