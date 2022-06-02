import java.io.*;
import java.net.Socket;

public class ServerFirExecutie extends Thread {

    String breakPoints = "";
    String textProgram = "";

    protected Socket socket;
    DataInputStream in = null;
    DataOutputStream out = null;

    // Read the file in
    private static String readFile(String file)
            throws IOException {
        BufferedReader br;
        StringBuilder data = new StringBuilder();

        if (file != null) {
            br = new BufferedReader(new FileReader(file));
            String line;
            // read in a line at a time, and tack on a \n for delimiting!
            while ((line = br.readLine()) != null)
                data.append(line + "\n");
        }

        return data.toString();
    }

    // constructor
    public ServerFirExecutie(Socket clientSocket) {
        this.socket = clientSocket;
    }

    public void run() {
        System.out.println("Server started");

        try {
            DataOutputStream out = null;
            out = new DataOutputStream(socket.getOutputStream());
            textProgram = readFile("tests/fib.n");
            out.writeUTF(textProgram);

            TheScan scanner = new TheScan(textProgram, breakPoints);;
            String readLine = "";

            while (true) {
                //System.out.println("server connected");
                DataInputStream in = null;
                in = new DataInputStream(socket.getInputStream());
                // clientul trimite date
                readLine = in.readUTF();

                if (readLine.contains("CONTINUE")) {
                    scanner.parser.executeProgram();
                    String result = scanner.parser.executionOutput;
                    out.writeUTF(result);
                } else {
                    if (!readLine.contains("CONTINUE")) {
                        //if (breakPoints==""){
                            breakPoints = readLine;
                        //}
                        //else
                        //{
                        //    breakPoints = breakPoints + "," + readLine;
                        //}
                        scanner = new TheScan(textProgram, breakPoints);
                        scanner.interpret();
                    }
                    System.out.println("Am setat breakpoints pe server:" + breakPoints);
                }
            }
        } catch (Exception e){

        }
    }
}
