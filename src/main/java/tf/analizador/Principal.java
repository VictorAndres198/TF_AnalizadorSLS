package tf.analizador;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Principal {

    public static void main(String[] args) {
        // Obtener la ruta del proyecto
        String projectPath = System.getProperty("user.dir").replace("\\", "/");

        // Construir las rutas basadas en la ruta del proyecto
        String pathLexer = projectPath + "/src/main/java/tf/analizador/Lexer.flex";
        String pathLexerCup = projectPath + "/src/main/java/tf/analizador/LexerCup.flex";
        String pathSyntaxCup = projectPath + "/src/main/java/tf/analizador/Sintax.cup";
        String[] rutaS = {"-parser", "Sintax", pathSyntaxCup};

        try {
            generar(pathLexer, pathLexerCup, rutaS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generar(String pathLexer, String pathLexerCup, String[] rutaS) throws IOException, Exception {
        File archivo;

        // Generar Lexer.flex
        archivo = new File(pathLexer);
        jflex.Main.generate(archivo);

        // Generar LexerCup.flex
        archivo = new File(pathLexerCup);
        jflex.Main.generate(archivo);

        // Ejecutar JavaCUP
        java_cup.Main.main(rutaS);

        // Mover sym.java
        String projectPath = System.getProperty("user.dir").replace("\\", "/");
        String pathSym = projectPath + "/src/main/java/tf/analizador/sym.java";
        Path rutaSym = Paths.get(pathSym);
        if (Files.exists(rutaSym)) {
            Files.delete(rutaSym);
        }
        Files.move(
                Paths.get(projectPath + "/sym.java"),
                Paths.get(pathSym)
        );

        // Mover Sintax.java
        String pathSintax = projectPath + "/src/main/java/tf/analizador/Sintax.java";
        Path rutaSin = Paths.get(pathSintax);
        if (Files.exists(rutaSin)) {
            Files.delete(rutaSin);
        }
        Files.move(
                Paths.get(projectPath + "/Sintax.java"),
                Paths.get(pathSintax)
        );
    }
}
