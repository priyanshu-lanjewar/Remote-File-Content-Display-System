import javax.swing.*;
import java.awt.*;

public class ServerConfiguration extends JFrame{
    private JPanel serverConfigPanel;
    private JTextField location_txt;
    private JButton clickToChooseLocationButton;
    private JSpinner portNo;
    private JButton saveButton;
    private JButton cancelButton;

    JFileChooser chooser;
    String choosertitle;
    String location;

    public ServerConfiguration(){
        add(serverConfigPanel);
        setSize(500,250);
        setResizable(false);
        ImageIcon icn = new ImageIcon("src/icons/serverConf.png");
        setIconImage(icn.getImage());
        setTitle("Remote File Content Display System");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        clickToChooseLocationButton.addActionListener(e -> {
            chooser = new JFileChooser();
            chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setDialogTitle(choosertitle);
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);
            Color c = Color.DARK_GRAY;
            if (chooser.showOpenDialog(ServerConfiguration.this) == JFileChooser.APPROVE_OPTION) {
                location=  chooser.getSelectedFile().toString();
                location_txt.setDisabledTextColor(c);
                location_txt.setText(location);

            }
            else {
                location_txt.setText("Not Choosen Any Directory !!");
                location_txt.setDisabledTextColor(Color.RED);
            }
        });
        cancelButton.addActionListener(e -> SwingUtilities.invokeLater(() -> {
            ServerConfiguration.this.setVisible(false);
            Server f = new Server();
            f.setVisible(true);
        }));
        saveButton.addActionListener(e -> SwingUtilities.invokeLater(() -> {
            if(location==null || location_txt.toString().equals("Not Choosen Any Directory !!")){
                JOptionPane.showMessageDialog(this,"Please Select Valid Location of Directory !","Error : Invalid Address",JOptionPane.ERROR_MESSAGE,null);
            } else {
                ServerConfiguration.this.setVisible(false);
                Server f = new Server(portNo.getValue().toString(), location);
                f.setVisible(true);
            }
        }));
    }

    private void createUIComponents() {
        SpinnerModel sm = new SpinnerNumberModel(8331,1000,9999,1);
       portNo = new JSpinner(sm);

    }
}

