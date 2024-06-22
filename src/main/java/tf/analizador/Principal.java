package tf.analizador;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import jflex.Main;
import java_cup.*;
import jflex.*;

public class Principal {

    public static void main(String[] args) {
    	String pathLexer = "C:/Users/Alejo/eclipse-workspace/TF_AnalizadorSLS/src/main/java/tf/analizador/Lexer.flex";
    	String pathLexerCup = "C:/Users/Alejo/eclipse-workspace/TF_AnalizadorSLS/src/main/java/tf/analizador/LexerCup.flex";
    	String pathSyntaxCup = "C:/Users/Alejo/eclipse-workspace/TF_AnalizadorSLS/src/main/java/tf/analizador/Sintax.cup";
        String[] rutaS = {"-parser", "Sintax", pathSyntaxCup};
        try {
			generar(pathLexer, pathLexerCup, rutaS);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
	public static void generar(String pathLexer, String pathLexerCup, String[] rutaS) throws IOException, Exception {
        File archivo;
        archivo = new File(pathLexer);
        jflex.Main.generate(archivo);
        archivo = new File(pathLexerCup);
        jflex.Main.generate(archivo);
        java_cup.Main.main(rutaS);
        
        String pathSym = "C:/Users/Alejo/eclipse-workspace/TF_AnalizadorSLS/src/main/java/tf/analizador/sym.java";
        Path rutaSym = Paths.get(pathSym);
        if (Files.exists(rutaSym)) {
            Files.delete(rutaSym);
        }
        
        Files.move(
                Paths.get("C:/Users/Alejo/eclipse-workspace/TF_AnalizadorSLS/sym.java"), 
                Paths.get(pathSym)
        );
        
        
        String pathSintax = "C:/Users/Alejo/eclipse-workspace/TF_AnalizadorSLS/src/main/java/tf/analizador/Sintax.java";
        Path rutaSin = Paths.get(pathSintax);
        if (Files.exists(rutaSin)) {
            Files.delete(rutaSin);
        }
        Files.move(
                Paths.get("C:/Users/Alejo/eclipse-workspace/TF_AnalizadorSLS/Sintax.java"), 
                Paths.get(pathSintax)
        );
    }
    
    

}
