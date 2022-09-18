package proyecto1_compiladores1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * @author Luciano Xiquín
 */
public class Proyecto1_Compiladores1 {

    public static void main(String[] args) throws Exception {
        Interface window = new Interface();
        window.setVisible(true);
        String one =  "C:\\Users\\Luciano Xiquín\\Documents\\NetBeansProjects\\Proyecto1_Compiladores1\\src\\analizadores\\Lexico";
        String two = "C:\\Users\\Luciano Xiquín\\Documents\\NetBeansProjects\\Proyecto1_Compiladores1\\src\\analizadores\\Sintactico";
        String[] rutaS = {"-parser", "Sintax", "C:\\Users\\Luciano Xiquín\\Documents\\NetBeansProjects\\Proyecto1_Compiladores1\\src\\analizadores\\Sintactico.cup"};
        generar(one,two, rutaS);
    }
    
     public static void generar(String ruta1, String ruta2, String[] rutaS) throws IOException, Exception{
        File archivo;
        archivo = new File(ruta2);
        JFlex.Main.generate(archivo);
        java_cup.Main.main(rutaS);
    }
}