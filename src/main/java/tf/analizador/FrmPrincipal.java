package tf.analizador;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java_cup.runtime.Symbol;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.File;

import java.io.IOException;
import java.awt.event.ActionEvent;
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
        setBounds(100, 100, 450, 749);
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
		
		JButton btnAnalizarLexico = new JButton("Analizar Lexico");
		btnAnalizarLexico.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            int contador = 1;
		            String expresion = txtAnalizar.getText(); 
		            Lexer lexer = new Lexer(new StringReader(expresion));                    
		            String resultado = "Linea" + contador + "\t\tSIMBOLO" + nuevaLinea;                   
		            while (true) {
		            	Tokens token = lexer.yylex();
		                if (token == null) { 
		                	txtResultado.setText(resultado);
		                	return; 
		                } 
		                switch (token) { 
			                case Linea ->
			                    {contador++;
			                    resultado += " ";
			                    }
			                case Let ->
			                    {resultado += "Let\t\t" + " " + lexer.lexeme + nuevaLinea;}
			                case In ->
			                    {resultado += "In\t\t" + " " + lexer.lexeme + nuevaLinea;}
			                   
			                case Where ->
			                	{resultado += "Where\t\t" + " " + lexer.lexeme + nuevaLinea;}
			                    
			                case If ->
			                    {resultado += "If\t\t" + " " + lexer.lexeme + nuevaLinea;}
			                    
			                case Then ->
			                    {resultado += "Then\t\t" + " " + lexer.lexeme + nuevaLinea;}
			                    
			                case Else ->
			                    {resultado += "Else\t\t" + " " + lexer.lexeme + nuevaLinea;}
			                    
			                case Case ->
			                    {resultado += "Case\t\t" + " " + lexer.lexeme + nuevaLinea;}
			                    
			                case Of ->
			                    {resultado += "Of\t\t" + " " + lexer.lexeme + nuevaLinea;}
			                    
			                case Data ->
			                    {resultado += "Data\t\t" + " " + lexer.lexeme + nuevaLinea;}
			                case Type ->
			                    {resultado += "Type\t\t" + " " + lexer.lexeme + nuevaLinea;}

			                case Int ->
			                    {resultado += "Int\t\t" + " " + lexer.lexeme + nuevaLinea;}

			                case Double ->
			                    {resultado += "Double\t\t" + " " + lexer.lexeme + nuevaLinea;}

			                case Char ->
			                    {resultado += "Char\t\t" + " " + lexer.lexeme + nuevaLinea;}

			                case Bool ->
			                    {resultado += "Bool\t\t" + " " + lexer.lexeme + nuevaLinea;}

			                case Igual ->
			                    {resultado += "Igual\t\t" + " " + lexer.lexeme + nuevaLinea;}

			                case Suma ->
			                    {resultado += "Suma\t\t" + " " + lexer.lexeme + nuevaLinea;}

			                case Resta ->
			                    {resultado += "Resta\t\t" + " " + lexer.lexeme + nuevaLinea;}

			                case Multiplicacion ->
			                    {resultado += "Multiplicacion\t\t" + " " + lexer.lexeme + nuevaLinea;}
			                    
			                case Division ->
			                    {resultado += "Division\t\t" + " " + lexer.lexeme + nuevaLinea;}
			                    
			                case Modulo ->
			                    {resultado += "Modulo\t\t" + " " + lexer.lexeme + nuevaLinea;}
			                    
			                case Potencia ->
			                    {resultado += "Potencia\t\t" + " " + lexer.lexeme + nuevaLinea;}
			                    
			                case Identificador ->
			                    {resultado += "Identificador\t\t" + " " + lexer.lexeme + nuevaLinea;}
			                    
			                case Numero ->
			                    {resultado += "Numero\t\t" + " " + lexer.lexeme + nuevaLinea;}			                    
			                    
			                case Flecha_D ->
			                	{resultado += "Flecha Derecha\t\t" + " " + lexer.lexeme + nuevaLinea;}			                
			                	
			                case Flecha_I ->
			                	{resultado += "Flecha izquierda\t\t" + " " + lexer.lexeme + nuevaLinea;}
			                
			                case DosPuntos_Doble ->
			                	{resultado += "Dos puntos doble\t\t" + " " + lexer.lexeme + nuevaLinea;}

			                case Coma ->
			                	{resultado += "Coma\t\t" + " " + lexer.lexeme + nuevaLinea;}
			                
			                case Mayor_que ->
			                	{resultado += "Mayor que\t\t" + " " + lexer.lexeme + nuevaLinea;}              
			                
			                case Mayor_igual_que ->
			                	{resultado += "Mayor Igual que\t\t" + " " + lexer.lexeme + nuevaLinea;}
			                			                
			                case Menor_que ->
			                	{resultado += "Menor que\t\t" + " " + lexer.lexeme + nuevaLinea;}
			                
			                case Menor_igual_que ->
			                	{resultado += "Menor Igual que\t\t" + " " + lexer.lexeme + nuevaLinea;}
			                			                
			                case Igual_a ->
			                	{resultado += "Comparacion igualdad\t\t" + " " + lexer.lexeme + nuevaLinea;}
			                
			                case And ->
			                	{resultado += "Op. And\t\t" + " " + lexer.lexeme + nuevaLinea;}
			                			                
			                case Or ->
			                	{resultado += "Operador Or\t\t" + " " + lexer.lexeme + nuevaLinea;}
			                
			                case Guarda ->
			                	{resultado += "Op. Guarda\t\t" + " " + lexer.lexeme + nuevaLinea;}
			                			                
			                case Dolar ->
			                	{resultado += "Dolar\t\t" + " " + lexer.lexeme + nuevaLinea;}
			                
			                case Lambda ->
			                	{resultado += "Op. funcion lambda\t\t" + " " + lexer.lexeme + nuevaLinea;}
			                			                
			                case Parentesis_a ->
			                	{resultado += "Parentesis apertura\t\t" + " " + lexer.lexeme + nuevaLinea;}
			                
			                case Parentesis_c ->
			                	{resultado += "Parentesis cierre\t\t" + " " + lexer.lexeme + nuevaLinea;}
			                
			                case Llave_a ->
			                	{resultado += "Llave apertura\t\t" + " " + lexer.lexeme + nuevaLinea;}
			                
			                case Llave_c ->
			                	{resultado += "Llave cierre\t\t" + " " + lexer.lexeme + nuevaLinea;}
			                
			                case Corchete_a ->
			                	{resultado += "Corchete apertura\t\t" + " " + lexer.lexeme + nuevaLinea;}
			                
			                case Corchete_c ->
			                	{resultado += "Corchete cierre\t\t" + " " + lexer.lexeme + nuevaLinea;}
			                
			                case ERROR ->
			                    {resultado += "ERROR\t\t" + " " + lexer.lexeme + nuevaLinea;}
			                    
			                default ->
			                    {resultado += token + " " + lexer.lexeme + nuevaLinea;}
			                    
			            }
		            }
		        } catch (IOException e1) { e1.printStackTrace();} 
		    }
		});
		btnAnalizarLexico.setBounds(10, 219, 414, 23);
		contentPane.add(btnAnalizarLexico);
		
		JTextArea txtResultado_Sintactico = new JTextArea();
		txtResultado_Sintactico.setBounds(12, 512, 412, 190);
		contentPane.add(txtResultado_Sintactico);
		
		JButton btnAnalizarSintactico = new JButton("Analizar - Sintactico");
		btnAnalizarSintactico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String ST = txtAnalizar.getText();
				Sintax s = new Sintax(new tf.analizador.LexerCup(new StringReader(ST)));

				try {
					s.parse();
					txtResultado_Sintactico.setText("Analisis realizado correctamente");
					txtResultado_Sintactico.setForeground(new Color(25, 111, 61));
				} catch (Exception ex) {
					Symbol sym = s.getS();
					txtResultado_Sintactico.setText("Error de sintaxis. Linea: " + (sym.right + 1) + " Columna: "
							+ (sym.left + 1) + ", Texto: \"" + sym.value + "\"");
					txtResultado_Sintactico.setForeground(Color.red);
				}
				 			
			}
		});
		btnAnalizarSintactico.setBounds(10, 479, 414, 23);
		contentPane.add(btnAnalizarSintactico);		
	}
}