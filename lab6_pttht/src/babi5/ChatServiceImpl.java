package babi5;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class ChatServiceImpl extends UnicastRemoteObject implements ChatService {
    private Scanner scanner;

    public ChatServiceImpl() throws RemoteException {
        super();
        scanner = new Scanner(System.in);
    }

    @Override
    public synchronized String sendMessage(String message) throws RemoteException {
        System.out.println("\n[Client gửi]: " + message);
        System.out.print("[Server trả lời]: ");
        String reply = scanner.nextLine();
        return reply;
    }

    public static void main(String[] args) {
        try {
            ChatService service = new ChatServiceImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("ChatService", service);
            System.out.println("Server Chat 1-1 đã mở! Chờ tin nhắn từ client...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}