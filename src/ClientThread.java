/*
 * Author Of Code : Priyanshu Lanjewar
 * Registration Number : 1941012603
 * Branch / Sec / Sem : CSE - F - 3rd Sem
 * Institute : Institute of Technical Education and Research
 * University : Siksha O Anusandhana Deemed To Be University.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientThread extends Thread{
    public static Integer portNo; //port number for connection
    public static String HostIP; //host ip of server for connection
    //location...
    public  static String file; //File name / location depending on input condition
    public File fileToUpload; //File to be uploaded
    public static String op=""; //output String/messege
    public static boolean dataRecieved=false; //status of data to be recieved from server
    String operation; //Operation to be performed -> read or upload
    String data=""; //data read from file and to be sent to server for uploading

    public ClientThread(String HostIP, Integer portNo, String file, String operation){
        ClientThread.HostIP =HostIP;
        ClientThread.portNo =portNo;
        ClientThread.file = file;
        this.operation=operation;

    }

    public void run() {
        if (operation.equals("read")) {
            try {
                Socket S = new Socket(HostIP, portNo);
                PrintStream PC = new PrintStream(S.getOutputStream());
                PC.println("read");
                PC.println(file); // here file is just a name of .txt file without an extension
                Scanner SC1 = new Scanner(S.getInputStream());

                while (true) {
                    if (SC1.hasNextLine()) {
                        String chk=SC1.nextLine();
                        if (chk.equals("File is Not Present at Server.")) {
                            throw new Exception("File not Found");
                        } else op = op + chk + "\n";

                    } else {
                        dataRecieved = true;
                        break;
                    }
                }


            } catch (IOException ioException) {
                op="Issue While Connecting With Server !";

            } catch (Exception eee) {
                op = "File not Found";
            }
        }
        if (operation.equals("upload")){
            fileToUpload = new File(file); //here file is location of particular .txt file

            try {
               Scanner sc = new Scanner(fileToUpload);
            while(sc.hasNextLine()){
                data = data + (sc.nextLine() + "\n");
            }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            try {
                Socket S = new Socket(HostIP,portNo);
                PrintStream PC = new PrintStream(S.getOutputStream());
                PC.println("upload");
                PC.println(fileToUpload.getName());
                PC.println(data);
                Scanner SC1 = new Scanner(S.getInputStream());
                op=SC1.nextLine();
            } catch (IOException e) {
                op = "Issue While Connecting With Server !";
            }
        }
    }

}
