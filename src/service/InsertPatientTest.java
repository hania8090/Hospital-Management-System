import java.sql.Connection;
import java.sql.PreparedStatement;

public class InsertPatientTest {
    public static void main(String[] args) {
        String sql = "INSERT INTO patients(name, age, gender, phone) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "Ali");
            ps.setInt(2, 25);
            ps.setString(3, "Male");
            ps.setString(4, "03001234567");

            int rows = ps.executeUpdate();
            System.out.println("✅ Inserted rows: " + rows);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}import java.sql.Connection;
import java.sql.PreparedStatement;

public class InsertPatientTest {
    public static void main(String[] args) {
        String sql = "INSERT INTO patients(name, age, gender, phone) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "Ali");
            ps.setInt(2, 25);
            ps.setString(3, "Male");
            ps.setString(4, "03001234567");

            int rows = ps.executeUpdate();
            System.out.println("✅ Inserted rows: " + rows);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}