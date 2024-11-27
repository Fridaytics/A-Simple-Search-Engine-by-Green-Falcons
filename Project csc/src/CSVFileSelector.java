import javax.swing.*;
import java.awt.*;
import java.io.File;

public class CSVFileSelector extends JDialog {
    private JButton selectButton;
    private JLabel fileLabel;
    private File selectedFile;

    public CSVFileSelector(JFrame parent) {
        super(parent, "CSV File Selector", true); // Make this a modal dialog (as in the code does not run until the use)
        setSize(400, 200);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // UI Components
        selectButton = new JButton("Select CSV File");
        fileLabel = new JLabel("No file selected", SwingConstants.CENTER);

        // Add Components
        add(selectButton, BorderLayout.NORTH);
        add(fileLabel, BorderLayout.CENTER);

        // Button Action
        selectButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setDialogTitle("Select a CSV File");

            // Accept only .csv files
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("CSV Files", "csv"));

            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                fileLabel.setText("Selected File: " + selectedFile.getName());
                dispose(); // Close the dialog
            }
        });

        // Center the dialog on the screen
        setLocationRelativeTo(parent);
    }

    public File getSelectedFile() {
        return selectedFile;
    }
}


