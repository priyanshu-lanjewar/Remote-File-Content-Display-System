/*
 * Author Of Code : Priyanshu Lanjewar
 * Registration Number : 1941012603
 * Branch / Sec / Sem : CSE - F - 3rd Sem
 * Institute : Institute of Technical Education and Research
 * University : Siksha O Anusandhana Deemed To Be University.
 * Class Description :
   Class Server Thread will create a new socket and accept Clients request and perform read and write operation.
   A new thread is required to establish connection because Some methods like accept() are blocking method. If will share single thread with GUI, it will block
   Functioning of GUI.
 */

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ServerThread extends Thread{
    public static ServerSocket ss;
    public static Boolean isServerRunning;
    public static String port; //port no to connect
    public static String location; //file location
    public static String status; //status of request
    public ServerThread(String port, String location) {
        ServerThread.isServerRunning = false;
        ServerThread.port = port;
        ServerThread.location = location;
    }
    public void run(){
        try {
            ss = new ServerSocket(Integer.parseInt(port));
            ss.setSoTimeout(100);
            isServerRunning = true;
            while (isServerRunning) {
                nowServe(ss);
                if (Thread.interrupted()) {
                    ss.close();
                    return;
                }

            }
        } catch (Exception ex) {
            status = "Error  : Port Already in use....";

        }
    }
    private void nowServe(ServerSocket ssc) {

        final Socket[] s = new Socket[1];
        try {


            try {
                status = "Status : Server Running at Port no "+port;
                s[0] = ssc.accept();
            } catch (SocketTimeoutException e) {
                status = "Error : "+e;
                return;
            } catch (Exception e) {
                status = "Error : "+e;
            }

            try {

            Scanner SC = new Scanner(s[0].getInputStream());
            String in = SC.nextLine();
            if(in.equals("read")) {
                String fileLoc = location + "/" + SC.nextLine() + ".txt";
                File file = new File(fileLoc);
                Scanner sn = new Scanner(file);
                PrintStream PS = new PrintStream(s[0].getOutputStream());
                String result;
                while (sn.hasNextLine()) {
                    result = sn.nextLine();
                    PS.println(result);
                }

            }
            if(in.equals("upload")){
                String name=SC.nextLine();
                FileWriter writer = null;
                PrintStream PS = new PrintStream(s[0].getOutputStream());


                try {
                    File file = new File(location+"/"+name);

                    writer = new FileWriter(file,StandardCharsets.UTF_8);

                    PrintWriter pw=new PrintWriter(writer);
                    PS.println("File Uploaded Successfully!");
                    while (SC.hasNextLine()){
                        pw.println(SC.nextLine());
                    }
                    pw.flush();
                    pw.close();

                } catch (Exception ignored) {
                } finally {
                    try {
                        assert writer != null;

                        writer.flush();
                        writer.close();
                    } catch (Exception ignored) {}
                }
            }

            }catch ( FileNotFoundException E){
                PrintStream PS = new PrintStream(s[0].getOutputStream());
                PS.println("File is Not Present at Server.");
            }

        } catch (IOException ignored) {

        }
    }
    }
