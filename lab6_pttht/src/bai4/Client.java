package bai4;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            ContactService service = (ContactService) registry.lookup("ContactService");
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("\n--- CHƯƠNG TRÌNH QUẢN LÝ DANH BẠ (RMI) ---");
                System.out.println("1. Thêm liên hệ");
                System.out.println("2. Tìm kiếm liên hệ");
                System.out.println("3. Xóa liên hệ");
                System.out.println("4. Thoát");
                System.out.print("Chọn chức năng (1-4): ");
                
                String choiceStr = sc.nextLine();
                int choice;
                try {
                    choice = Integer.parseInt(choiceStr);
                } catch (NumberFormatException e) {
                    System.out.println("Vui lòng nhập số hợp lệ!");
                    continue;
                }

                if (choice == 1) {
                    System.out.print("Nhập tên liên hệ: ");
                    String name = sc.nextLine().trim();
                    System.out.print("Nhập số điện thoại: ");
                    String phone = sc.nextLine().trim();
                    
                    service.addContact(name, phone);
                    System.out.println("-> Thêm liên hệ thành công!");

                } else if (choice == 2) {
                    System.out.print("Nhập tên cần tìm: ");
                    String name = sc.nextLine().trim();
                    
                    String phone = service.findContact(name);
                    if (phone != null) {
                        System.out.println("-> Kết quả: Tên: " + name + " | SĐT: " + phone);
                    } else {
                        System.out.println("-> Không tìm thấy liên hệ có tên: " + name);
                    }

                } else if (choice == 3) {
                    System.out.print("Nhập tên cần xóa: ");
                    String name = sc.nextLine().trim();
                    
                    boolean deleted = service.deleteContact(name);
                    if (deleted) {
                        System.out.println("-> Xóa thành công!");
                    } else {
                        System.out.println("-> Không tìm thấy tên này để xóa.");
                    }

                } else if (choice == 4) {
                    System.out.println("Đang thoát chương trình...");
                    break;
                } else {
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại từ 1-4.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}