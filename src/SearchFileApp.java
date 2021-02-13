
/*
 * Author Of Code : Priyanshu Lanjewar
 * Registration Number : 1941012603
 * Branch / Sec / Sem : CSE - F - 3rd Sem
 * Institute : Institute of Technical Education and Research
 * University : Siksha O Anusandhana Deemed To Be University.
 * Class Description :
   Execution of User/Client End Programme Begins Here. An GUI where Client can request for a file or from where can upload a file to server.
 */

import javax.swing.*;
import java.util.regex.Pattern;

public class SearchFileApp extends JFrame {
    private JPanel searchpanel;
    private JTextField input;
    private JButton submit;
    private JButton upload;
    boolean isIpValid = Boolean.FALSE;
    boolean isFirstClick = true;
    public String filename;
    public static FileViewer fw;
    public String out="";
    public String ip;
    Integer portNo;
    ImageIcon icn = new ImageIcon(this.getClass().getResource("serverConf.png"));
    ImageIcon icon = new ImageIcon(this.getClass().getResource("search.png"));

    public SearchFileApp(){
        add(searchpanel);
        setResizable(false);
        setIconImage(icon.getImage());
        setSize(600,250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Remote File Content Display");
        serverDetailsSetup();



        submit.addActionListener(e -> {
            if(input.getText().isEmpty()) JOptionPane.showMessageDialog(this,"Please Input Valid File Name.","No file name Inputted",JOptionPane.ERROR_MESSAGE,icon);
            else {
                load();
               if(out.equals("File not Found") || out.equals("File is Not Present at Server.\n") ){
                    JOptionPane.showMessageDialog(this,"File is Not Present at Server!\n(Sometimes due to connectivity issues file may not be parsed by Parser.\nWe will suggest you to check again.","File Not Found",JOptionPane.ERROR_MESSAGE,icon);
                }else if(out.equals("Issue While Connecting With Server !")){
                   JOptionPane.showMessageDialog(this,out,"Server Connection Error",JOptionPane.ERROR_MESSAGE,icon);

               }
               else{
                    if(!isFirstClick){
                        SwingUtilities.invokeLater(() -> {
                            if (fw == null)
                                fw = new FileViewer();
                            fw.openFile(filename, out);
                            if (!fw.isVisible()) fw.setVisible(true);
                        });
                    }
                }

                    ClientThread.op ="";
                if(isFirstClick) {
                    isFirstClick=false;
                    submit.doClick();
                }
                isFirstClick=true;

        }});

        upload.addActionListener(e -> {
            SearchFileApp.this.dispose();
            UploadFIle fm = new UploadFIle(ip,portNo);
            fm.setVisible(true);
        });
    }

    private void load() {
        out="";
        filename = input.getText();

        ClientThread clientThread = new ClientThread(ip, portNo, filename,"read");
        clientThread.start();
        out = ClientThread.op;


    }

    private void serverDetailsSetup() {
        while (true) {
            ip = (String) JOptionPane.showInputDialog(this, "Please Enter a Valid IP of Server below.(Ex. 192.168.1.2)\nOr directly press ok to continue with localhost", "Server IP", JOptionPane.QUESTION_MESSAGE, icn, null, "localhost");
            if(ip!=null){
                if(ip.equals("localhost")) ip = "127.0.0.1";
                isIpValid= Pattern.compile("^((25[0-5]|(2[0-4]|1[0-9]|[1-9]|)[0-9])(\\.(?!$)|$)){4}$").matcher(ip).matches();
            }
            if (!isIpValid) JOptionPane.showMessageDialog(this,"Please Enter a Valid IP of Server again.","Invalid IP",JOptionPane.ERROR_MESSAGE,icn);
            else break;
        }

        while (true){
            try {
                portNo = Integer.parseInt((String) JOptionPane.showInputDialog(this,"Enter Port Number (Default Port No: 7575): ","Server Port",JOptionPane.QUESTION_MESSAGE,icn,null,"7575"));
                if(portNo>=0)  break;
            } catch (Exception e){
                JOptionPane.showMessageDialog(this,"Please Enter a Valid Port of Server again.","Invalid IP",JOptionPane.ERROR_MESSAGE,icn);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (Exception ignored) {

            }
            SearchFileApp m = new SearchFileApp();
            m.setVisible(true);
        });
    }


    private void createUIComponents() {


    }
}
