import javax.swing.*;

public class Server extends JFrame{
    private JPanel mainpanel;
    String port="7575";
    String location="";

    public Server(){
        add(mainpanel);

        setSize(500,450);
        setResizable(false);
        ImageIcon icn = new ImageIcon("src/icons/server.png");
        setTitle("Remote File Content Display System");
        setIconImage(icn.getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

    }
    public Server(String port,String location){
        add(mainpanel);
        setSize(500,450);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.port=port;
        this.location=location;
        System.out.println("\n"+port+"\n"+location);
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
