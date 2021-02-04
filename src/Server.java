import javax.swing.*;

public class Server extends JFrame{
    private JPanel mainpanel;
    String port="7575";
    String location="";

    public Server(){
        add(mainpanel);

        setSize(500,450);
        setResizable(false);
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
}
