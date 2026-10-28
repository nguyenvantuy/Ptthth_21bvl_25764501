package bai3;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class PrimeServiceImpl extends UnicastRemoteObject implements PrimeService {
    public PrimeServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public boolean isPrime(int n) throws RemoteException {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        try {
            PrimeService service = new PrimeServiceImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("PrimeService", service);
            System.out.println("Server Bài 3 đang chạy...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}