
/*
 * Author Of Code : Priyanshu Lanjewar
 * Registration Number : 1941012603
 * Branch / Sec / Sem : CSE - F - 3rd Sem
 * Institute : Institute of Technical Education and Research
 * University : Siksha O Anusandhana Deemed To Be University.
 * Class Description :
   Class Provides GUI to choose and upload a file to server.
 */

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

public class UploadFIle extends JFrame{
    String ip;
    int port;
    private JTextField file;
    private JButton upload;
    private JTextField status;
    private JLabel fileLoc;
    private JPanel fileUploader;
    private JButton cancel;
    private JButton chooseFileButton;
    JFileChooser chooser;
    public  String  location;
    ImageIcon icon = new ImageIcon(this.getClass().getResource("upp.png"));


    public UploadFIle(String ip,int port){
        this.ip=ip;
        this.port=port;
        add(fileUploader);
        setResizable(false);
        setIconImage(icon.getImage());
        setLocationRelativeTo(null);
        setSize(600,200);
        setTitle("File Uploader");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cancel.addActionListener(e -> {
            UploadFIle.this.dispose();
            SearchFileApp sf = new SearchFileApp();
            sf.setVisible(true);
        });
        chooseFileButton.addActionListener(e -> {
            chooser = new JFileChooser();
            chooser.setDialogTitle("Choose File");

          //  chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            chooser.setAcceptAllFileFilterUsed(false);
            chooser.addChoosableFileFilter(new FileFilter() {

                @Override
                public boolean accept(File f) {
                    return f.isDirectory() || f.getName().toLowerCase().endsWith(".txt");
                }

                @Override
                public String getDescription() {
                    return "Text Files (*.txt)";
                }
            });
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                location=  chooser.getSelectedFile().toString();
                file.setDisabledTextColor(Color.BLUE);
                file.setText(location);

            }
            else {
                file.setDisabledTextColor(Color.RED);
                file.setText("Not Chosen Any File !!");

            }

        });
        AtomicBoolean isFirstClick= new AtomicBoolean(true);
        upload.addActionListener(e -> {
            if(file.getText().isEmpty() || file.getText().equals("Not Chosen Any File !!")){
                JOptionPane.showMessageDialog(this,"Please Choose a valid Text File","No File Selected",JOptionPane.ERROR_MESSAGE,icon);
            }else {
                ClientThread up = new ClientThread(ip, port, location, "upload");
                up.start();
                status.setText(ClientThread.op);
                if (isFirstClick.get()) {
                    isFirstClick.set(false);

                    upload.doClick();
                } else {
                    if(status.getText().equals("File Uploaded Successfully!")){
                        status.setDisabledTextColor(Color.GREEN);
                       JOptionPane.showMessageDialog(this, status, "File Uploader", JOptionPane.QUESTION_MESSAGE,icon);
                       this.dispose();
                       System.exit(0);

                    }
                    else status.setDisabledTextColor(Color.RED);
                }
                isFirstClick.set(true);
            }
        });

    }

    
}
