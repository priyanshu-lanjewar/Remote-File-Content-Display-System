import javax.swing.*;
import java.awt.*;

public class Server extends JFrame{
    private JPanel mainpanel;
    private JTextField address;
    private JTextField locationTxt;
    private JButton startServerButton;
    private JButton stopServerButton;
    private JTextField status;
    public static String addr = "127.0.0.1:";
    public static String port="7575";
    public static String location="defaultDirctory/textFiles";
    public static Boolean isServerRunning = Boolean.FALSE;
    public static ServerThread serverThread;

    public Server(){
        add(mainpanel);
        setSize(500,450);
        setResizable(false);
        ImageIcon icn = new ImageIcon("src/icons/server.png");
        setTitle("Remote File Content Display System");
        setIconImage(icn.getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        locationTxt.setText(location);
        address.setText(addr+port);

        startServerButton.addActionListener(e -> {
            isServerRunning=Boolean.TRUE;
            startServerButton.setEnabled(Boolean.FALSE);
            startServerButton.setText("Started..");
            stopServerButton.setEnabled(Boolean.TRUE);
            status.setDisabledTextColor(Color.BLUE);
            status.setText("Status : Server is Running on port number  "+port);
            serverThread = new ServerThread(port,isServerRunning,location);
            serverThread.start();

        });
        stopServerButton.addActionListener(e -> {
            try {
                serverThread.interrupt();
                isServerRunning = Boolean.FALSE;
                startServerButton.setEnabled(Boolean.TRUE);
                stopServerButton.setEnabled(Boolean.FALSE);
                status.setText("Status : Server is Stopped");
            } catch (Exception ioException) {
                status.setText("Status : Error => "+ioException);
            }
        });
    }



    public Server(String addr,String port,String location){
        add(mainpanel);
        setSize(500,450);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
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
