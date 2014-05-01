import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JFileChooser;


/**
 *
 * @author cirwin000
 */
public class FileChooser implements ActionListener 
{
    public static File [] files;
    public static String fileName, filenames;

    public void actionPerformed(ActionEvent event) {

        final JFileChooser fileDialog = new JFileChooser();
        String filenames = "";
        String fileName = "";
        String fileSelection = "fileSelection.txt";
        File file = new File(fileSelection);
        
        fileDialog.setMultiSelectionEnabled(true);
        int option = fileDialog.showOpenDialog(null);
        
        try {
            PrintWriter pw = new PrintWriter(file);
            if (option == JFileChooser.APPROVE_OPTION) {

                if (fileDialog.isMultiSelectionEnabled()) {
                    files = fileDialog.getSelectedFiles();
                    if (files != null && files.length > 0) {
                        for (int i = 0; i < files.length; i++) {
                            filenames = files[i].getPath();
                            System.out.println(filenames);
                            pw.println(filenames);
                        }

                    }
                }
                pw.close();
            } else if (option == JFileChooser.CANCEL_OPTION) {
                System.out.println(" User cancelled operation. No file was choosen");
            }
            
        } catch (IOException e) {
            System.out.println("Unable to process file");
            System.exit(90);
        }
    }

    public static void main(String[] args) {
        
        FileChooser fl = new FileChooser();
        fl.actionPerformed(null);
        
    }

   
}
