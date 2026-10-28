package jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // B1. Ket noi SQLite
            Class.forName("org.sqlite.JDBC");

            String url = "jdbc:sqlite:nhanvien.db";
            conn = DriverManager.getConnection(url);

            System.out.println("Ket noi SQLite thanh cong!");

            // B2. Tao Statement
            stmt = conn.createStatement();

            // Su dung transaction
            conn.setAutoCommit(false);

            // B3.1 Tao bang NhanVien
            String sql_create_table =
                "CREATE TABLE IF NOT EXISTS NhanVien (" +
                "id INTEGER PRIMARY KEY, " +
                "ten TEXT NOT NULL, " +
                "chuc_vu TEXT)";

            stmt.executeUpdate(sql_create_table);
            System.out.println("Tao bang NhanVien thanh cong!");

            // B3.2 Them du lieu
            // Xoa du lieu cu de moi lan chay chi co 3 nhan vien
            stmt.executeUpdate("DELETE FROM NhanVien");

            String sql_insert1 =
                "INSERT INTO NhanVien VALUES (1, 'Nguyen An', 'Nhan vien')";

            String sql_insert2 =
                "INSERT INTO NhanVien VALUES (2, 'Tran Binh', 'Ke toan')";

            String sql_insert3 =
                "INSERT INTO NhanVien VALUES (3, 'Le Cuong', 'Quan ly')";

            stmt.executeUpdate(sql_insert1);
            stmt.executeUpdate(sql_insert2);
            stmt.executeUpdate(sql_insert3);

            // Luu cac thay doi
            conn.commit();
            System.out.println("Them 3 nhan vien thanh cong!");

            // B4. Truy van du lieu
            String sql_select =
                "SELECT id, ten, chuc_vu FROM NhanVien";

            rs = stmt.executeQuery(sql_select);

            System.out.println("\nDANH SACH NHAN VIEN");
            System.out.println("-------------------------------------");

            while (rs.next()) {
                int id = rs.getInt("id");
                String ten = rs.getString("ten");
                String chucVu = rs.getString("chuc_vu");

                System.out.println(
                    id + " | " + ten + " | " + chucVu
                );
            }

        } catch (Exception e) {
            System.out.println("Co loi: " + e.getMessage());

            // Neu co loi thi huy thay doi chua commit
            if (conn != null) {
                try {
                    conn.rollback();
                    System.out.println("Da rollback giao dich.");
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }

        } finally {
            // B5. Dong tai nguyen theo thu tu nguoc lai
            try {
                if (rs != null) {
                    rs.close();
                }

                if (stmt != null) {
                    stmt.close();
                }

                if (conn != null) {
                    conn.close();
                }

                System.out.println("Da dong ket noi SQLite.");

            } catch (SQLException e) {
                System.out.println("Loi dong ket noi: " + e.getMessage());
            }
        }
    }
}
