package tf.analizador;
import java.io.File;
import jflex.Main;
import jflex.exceptions.SilentExit;

public class Principal {

    public static void main(String[] args) {
        String ruta = "C:/Users/Victor/eclipse-workspace/Eclipse-Java-Enterprise/analizador/src/main/java/tf/analizador/Lexer.flex";
        generarLexer(ruta);
    }
    
    public static void generarLexer(String ruta){
        // Construir los argumentos para JFlex como un arreglo de String
        String[] flexOptions = { ruta };
        try {
            Main.generate(flexOptions);
        } catch (SilentExit e) {
            System.err.println("Error al generar el lexer: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
