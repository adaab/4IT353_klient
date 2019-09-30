import java.io.*;
import java.net.Socket;
import java.util.HashSet;

public class Klient {
    public Socket socket;
    public BufferedReader vstup;
    public PrintWriter vystup;

    public Klient(Socket socket){
        try {
            vstup = new BufferedReader(new InputStreamReader((socket.getInputStream()), "UTF-8"));
            vystup = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
            System.out.println("Klient běží");
            komunikuj();
        } catch(IOException e){
            System.out.println(e);
        }
    }

    public void komunikuj() {
        BufferedReader konzole = new BufferedReader(new InputStreamReader(System.in));
        //vystup.println("NEW CLIENT");
        HashSet<String> prikazy = new HashSet<>();
        prikazy.add("USER");
        prikazy.add("MESSAGE");
        prikazy.add("QUIT");
        while (true) {
            try {
                String text = konzole.readLine();
                String textParse[] = text.split(" ", 2);
                String prikaz = textParse[0];
                boolean existuje = false;
                for (Object hledane : prikazy) {
                    if (hledane.equals(prikaz)) {
                        existuje = true;
                        break;
                    }
                }
                if(existuje) {
                    vystup.println(text);
                    System.out.println(vstup.readLine());
                } else {
                    System.out.println("neplatný příkaz");
                }
            } catch (IOException e) {
                System.out.println(e);
            }

        }
    }




}
