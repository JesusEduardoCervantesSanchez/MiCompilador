
package compilador;

import java.util.logging.Level;
import java.util.logging.Logger;
import jflex.exceptions.SilentExit;

public class GALexico {
    
    public static void main(String[] args) {
        String archivoLexer = System.getProperty("user.dir")+"\\src\\compilador\\ALexico.flex";
        generararchivoLexer(archivoLexer);
    }
    
    public static void generararchivoLexer(String ruta){
        try {
            jflex.Main.generate(new String[] {ruta});
        } catch (SilentExit ex) {
            Logger.getLogger(GALexico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
