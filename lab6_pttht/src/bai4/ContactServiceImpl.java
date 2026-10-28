package bai4;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class ContactServiceImpl extends UnicastRemoteObject implements ContactService {
    private Map<String, String> contacts;

    public ContactServiceImpl() throws RemoteException {
        super();
        contacts = new HashMap<>();
    }

    @Override
    public synchronized void addContact(String name, String phone) throws RemoteException {
        contacts.put(name, phone);
        System.out.println("Đã thêm/cập nhật: " + name + " - " + phone);
    }

    @Override
    public synchronized String findContact(String name) throws RemoteException {
        return contacts.getOrDefault(name, null);
    }

    @Override
    public synchronized boolean deleteContact(String name) throws RemoteException {
        if (contacts.containsKey(name)) {
            contacts.remove(name);
            System.out.println("Đã xóa liên hệ: " + name);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        try {
            ContactService service = new ContactServiceImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("ContactService", service);
            System.out.println("Server Quản Lý Danh Bạ đang chạy...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}