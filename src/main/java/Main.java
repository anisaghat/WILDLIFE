import app.launchApplication;
import javax.swing.*;
import com.formdev.flatlaf.FlatDarculaLaf;
import model.dao.BiomeDAO;
import model.entities.Biome;


public class Main {
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel( new FlatDarculaLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        SwingUtilities.invokeLater(() -> new launchApplication().start());

    }
}
