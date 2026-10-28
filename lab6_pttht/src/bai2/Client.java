package bai2;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            ComputeService service = (ComputeService) registry.lookup("ComputeService");

            Scanner scanner = new Scanner(System.in);
            System.out.print("Nhập số a: ");
            int a = scanner.nextInt();
            System.out.print("Nhập số b: ");
            int b = scanner.nextInt();

            int result = service.add(a, b);
            System.out.println("Tổng a + b = " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}