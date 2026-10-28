package babi5;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            ChatService service = (ChatService) registry.lookup("ChatService");
            Scanner scanner = new Scanner(System.in);

            System.out.println("=== BẮT ĐẦU CHAT VỚI SERVER (Gõ 'exit' để thoát) ===");
            while (true) {
                System.out.print("[Bạn]: ");
                String msg = scanner.nextLine();
                if (msg.equalsIgnoreCase("exit")) break;

                String reply = service.sendMessage(msg);
                System.out.println("[Server]: " + reply);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}