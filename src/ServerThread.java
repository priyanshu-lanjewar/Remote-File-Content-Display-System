import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class ServerThread extends Thread{
    public static ServerSocket ss;
    public static Boolean isServerRunning;
    public static String port;
    public static String location;
    public ServerThread(String port, Boolean isServerRunning, String location) {
        ServerThread.isServerRunning = isServerRunning;
        ServerThread.port = port;
        ServerThread.location = location;
    }
    public void run(){
        try {
            ss = new ServerSocket(Integer.parseInt(port));
            ss.setSoTimeout(100);
            while (isServerRunning) {
                nowServe(ss);
                if (Thread.interrupted()) {
                    ss.close();
                    return;
                }
            }
        } catch (Exception ex) {
            System.out.println("ts");
        }
    }
    private void nowServe(ServerSocket ssc) {

        final Socket[] s = new Socket[1];
        try {


            try {
                s[0] = ssc.accept();
            } catch (SocketTimeoutException e) {
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }

            Scanner SC = new Scanner(s[0].getInputStream());
            String fileLoc=location+"/"+SC.next()+".txt";
            System.out.println(fileLoc);
            File file = new File(fileLoc);
            Scanner sn = new Scanner(file);
            PrintStream PS = new PrintStream(s[0].getOutputStream());
            StringBuilder result= new StringBuilder();
            while (sn.hasNext()){
                result.append(sn.nextLine()).append("\n");


            }
            System.out.println(result);
            PS.println(result);


        } catch (IOException ignored) {

        }
    }
    }
