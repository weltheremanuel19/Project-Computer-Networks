import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server1 {

    static final int PORT = 5013;

    static String breakPoints;

    // Read the file in
    private static String readFile ( String file )
            throws IOException
    {
        BufferedReader br;
        StringBuilder data = new StringBuilder();

        if ( file != null )
        {
            br = new BufferedReader(new FileReader(file));
            String line;
            // read in a line at a time, and tack on a \n for delimiting!
            while ( (line = br.readLine()) != null)
                data.append(line + "\n");
        }

        return data.toString();
    }

    public static void main(String[] argv) throws Exception {
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                System.out.println("Server pornit");
                System.out.println("Asteptam un client ...");

                socket = serverSocket.accept();
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
            // new thread for a client
            new ServerFirExecutie(socket).start();
        }
        }
        //t.close();
        //out.close();
        //t.close();
    }