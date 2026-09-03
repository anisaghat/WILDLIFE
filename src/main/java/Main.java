import app.launchApplication;
import javax.swing.*;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;
import model.dao.BiomeDAO;
import model.entities.Biome;


public class Main {
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel( new FlatLightLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        SwingUtilities.invokeLater(() -> new launchApplication().start());

    }
}
