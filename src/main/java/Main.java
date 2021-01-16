import javax.swing.*;
import java.awt.*;

public class Main {

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI(Database database) {
        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        //Create and set up the window.
        JFrame frame = new JFrame("TabbedPaneDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        //Create and set up the content pane.
        frame.getContentPane().add(new GUI.TabbedPane(database),
                BorderLayout.CENTER);
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Database database = Database.getInstance();

        javax.swing.SwingUtilities.invokeLater(() -> createAndShowGUI(database));
  }
}
