import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashSet;

import static java.io.Console.*;

public class Main {
    private InetAddress adresa;
    private Socket socket;
    private static BufferedReader vstup;
    private static PrintWriter vystup;
    private static HashSet prikazy;

    public static void main(String[] args){
        try {
            InetAddress adresa = InetAddress.getByName("localhost");
            Socket socket = new Socket(adresa, 8000);
            vstup = new BufferedReader(new InputStreamReader((socket.getInputStream()),"UTF-8"));
            vystup = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"),true);
            System.out.println("Klient běží");
            Main.komunikuj();
        } catch(UnknownHostException e){
            System.out.println(e);
        } catch(IOException i){
            System.out.println(i);
        }

    }
    public static void komunikuj() {
        BufferedReader konzole = new BufferedReader(new InputStreamReader(System.in));
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
