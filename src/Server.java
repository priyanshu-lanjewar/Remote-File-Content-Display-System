
/*
 * Author Of Code : Priyanshu Lanjewar
 * Registration Number : 1941012603
 * Branch / Sec / Sem : CSE - F - 3rd Sem
 * Institute : Institute of Technical Education and Research
 * University : Siksha O Anusandhana Deemed To Be University.
 * Class Description :
   Execution of Programme Begins here. Class Provides GUI to user to Start / Stop the server, also provides an option to configure it.
 */

import javax.swing.*;
import java.awt.*;

public class Server extends JFrame{
    private JPanel mainpanel;
    private JTextField address;
    private JTextField locationTxt;
    private JButton startServerButton;
    private JButton stopServerButton;
    private JTextField status;
    private JButton conf;
    public static String addr = "127.0.0.1:";
    public static String port="7575";
    //If you are executing code from any IDE where src directory is available then please comment below 2 lines(String loc... anf String k...) and uncomment 3rd line below(String k ="")...
    //public static String loc = Server.class.getResource("server.png").getPath();//If You are using JARs, default directory is made in folder named Jars this line and line below will extract location of default directory.
    //static String k = loc.replace("%20"," ").replace("server.png","").replace("!","").replace("/RFCDS Server.jar","").replace("file:","");
    static String k = "";
    //IF you want to change default directory by own, then please paste location below.
    public static String location=k+"defaultDirctory/textFiles";
    public static ServerThread serverThread;

    public Server(){
        add(mainpanel);
        setSize(500,450);
        setResizable(false);
        ImageIcon icn = new ImageIcon(this.getClass().getResource("server.png"));
        setTitle("Remote File Content Display System");
        setIconImage(icn.getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        locationTxt.setText(location);
        address.setText(addr+port);

        startServerButton.addActionListener(e -> {
            int pno;
            try {
               pno= Integer.parseInt(port);
               if(pno<0) throw new NumberFormatException();
                serverThread = new ServerThread(port,location);
                serverThread.start();
                status.setText("Status : Starting Server....");
                for(int i=0;i<1000;i++) {
                    status.setDisabledTextColor(Color.RED);
                        status.setText(ServerThread.status);
                }
                if(ServerThread.isServerRunning){
                    startServerButton.setEnabled(Boolean.FALSE);
                    stopServerButton.setEnabled(Boolean.TRUE);
                    status.setDisabledTextColor(Color.BLUE);
                }
            } catch (Exception e1){
                status.setDisabledTextColor(Color.RED);
                status.setText("Error : Invalid Port number");
            }





        });

        stopServerButton.addActionListener(e -> {
            try {
                serverThread.interrupt();
                ServerThread.isServerRunning=Boolean.FALSE;
                startServerButton.setEnabled(Boolean.TRUE);
                stopServerButton.setEnabled(Boolean.FALSE);
                status.setText("Status : Server is Stopped");
            } catch (Exception ioException) {
                status.setText("Error : "+ioException);
            }
        });

        conf.addActionListener(e -> {
            if(serverThread != null && ServerThread.isServerRunning){
                int opt = JOptionPane.showConfirmDialog(mainpanel,"To configure Server it should be stopped first.\nAre you ready to continue ?","Want to Stop Server",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if(opt==0) {
                    stopServerButton.doClick();
                    Server.this.dispose();
                    ServerConfiguration cnf = new ServerConfiguration();
                    cnf.setVisible(true);
                }
            }
            else {
                Server.this.dispose();
                ServerConfiguration cnf = new ServerConfiguration();
                cnf.setVisible(true);
            }
        });
    }



    public Server(String addr,String port,String location){
        this();
        Server.port =port;
        Server.location =location;
        Server.addr =addr;
        locationTxt.setText(location);
        address.setText(addr+port);


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
            ConfigSetting m = new ConfigSetting();
            m.setVisible(true);
        });
    }
}
