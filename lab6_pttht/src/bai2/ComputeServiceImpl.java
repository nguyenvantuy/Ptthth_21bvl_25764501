package bai2;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ComputeServiceImpl extends UnicastRemoteObject implements ComputeService {
    public ComputeServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }

    public static void main(String[] args) {
        try {
            ComputeService service = new ComputeServiceImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("ComputeService", service);
            System.out.println("Server Bài 2 đang chạy...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}