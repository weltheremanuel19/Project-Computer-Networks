import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientSocketFrame extends JFrame implements ActionListener {
    JLabel lblBP;
    static JTextField txtBP;
    JButton btnProcess;
    JButton btnSetBreak;
    JButton btnContinue;

    static JTextArea txtS;
    static JTextArea txtResult;

    static String breakPoints="";
    static String codeWithLineNumbers = "";
    static String code = "";

    static DataOutputStream out;

    public ClientSocketFrame() {

        this.setTitle("Client");
        this.setSize(820, 640);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        lblBP = new JLabel("Break point: ");
        lblBP.setBounds(10, 10, 90, 21);
        add(lblBP);

        txtBP = new JTextField();
        txtBP.setBounds(105, 10, 90, 21);
        add(txtBP);

        btnProcess = new JButton("Process");
        btnProcess.setBounds(200, 40, 90, 21);
        btnProcess.addActionListener(this);
        add(btnProcess);

        btnSetBreak = new JButton("SetBreak");
        btnSetBreak.setBounds(300, 40, 90, 21);
        btnSetBreak.addActionListener(this);
        add(btnSetBreak);

        btnContinue = new JButton("Continue");
        btnContinue.setBounds(400, 40, 90, 21);
        btnContinue.addActionListener(this);
        add(btnContinue);

        txtS = new JTextArea();
        txtS.setBounds(10, 85, 390, 320);
        add(txtS);

        txtResult = new JTextArea();
        txtResult.setBounds(450, 85, 390, 320);
        add(txtResult);

        this.setVisible(true);
    }
    static DataInputStream in;
    public static void main(String[] args) throws IOException {

        new ClientSocketFrame();

        Socket s = new Socket("localhost", 5013);

        in = new DataInputStream(s.getInputStream());
        out = new DataOutputStream(s.getOutputStream());
        // Here we read the details from server
        BufferedReader response = new BufferedReader(new InputStreamReader(
                s.getInputStream()));

        code = in.readUTF();

        codeWithLineNumbers = "";
        int i = 0;
        int lineNumber = 0;
        lineNumber++;
        codeWithLineNumbers += lineNumber + "   ";

        while (true) {

            if (i >= code.length()) {
                break;
            }

            if (code.charAt(i) == '\n') {
                lineNumber++;
                codeWithLineNumbers += code.charAt(i);
                codeWithLineNumbers += lineNumber + "   ";
                i++;
            } else
            {
                codeWithLineNumbers += code.charAt(i);
                i++;
            }
        }
        txtS.setText(codeWithLineNumbers);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnContinue)) {
            try {
                out.writeUTF("CONTINUE");
                String result = in.readUTF();
                txtResult.setText(txtResult.getText() + result + "\n");

            } catch (UnknownHostException e1)
            {

            } catch (IOException e1) {

            }
        }
        if (e.getSource().equals(btnSetBreak)) {
            breakPoints = txtBP.getText();
            try {
                out.writeUTF(breakPoints);
            }
            catch (IOException ex) {

            }
        }
    }

    public void processInformation() throws IOException {
    }

}