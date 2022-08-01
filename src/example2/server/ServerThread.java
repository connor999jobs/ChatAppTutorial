package example2.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;


public class ServerThread extends Thread {
    private Socket socket;
    private ArrayList<ServerThread> threadList;
    private PrintWriter output;

    public ServerThread(Socket socket, ArrayList<ServerThread> threads) {
        this.socket = socket;
        this.threadList = threads;
    }

    @Override
    public void run() {
        try {
            //Чтение нового пользователя
            BufferedReader input = new BufferedReader( new InputStreamReader(socket.getInputStream()));

            //вывод клиента:
            output = new PrintWriter(socket.getOutputStream(),true);



            while(true) {
                String outputString = input.readLine();
                if(outputString.equals("exit")) {
                    break;
                }
                printToALlClients(outputString);
                System.out.println("Server received " + outputString);

            }


        } catch (Exception e) {
         e.getStackTrace();
        }
    }

    private void printToALlClients(String outputString) {
        for( ServerThread sT: threadList) {
            sT.output.println(outputString);
        }

    }
}