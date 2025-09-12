import java.sql.*;
import java.util.Scanner;

public class HabitTrackerApp {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Daily Habit Tracker ===");
            System.out.println("1. Add Habit");
            System.out.println("2. View Habits");
            System.out.println("3. Mark Habit as Complete");
            System.out.println("4. Delete Habit");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addHabit();
                case 2 -> viewHabits();
                case 3 -> markHabitComplete();
                case 4 -> deleteHabit();
                case 5 -> {
                    System.out.println("👋 Goodbye!");
                    return;
                }
                default -> System.out.println("❌ Invalid choice!");
            }
        }
    }

    private static void addHabit() {
        System.out.print("Enter habit name: ");
        String name = sc.nextLine();
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO habits (habit_name, is_complete) VALUES (?, false)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.executeUpdate();
            System.out.println("✅ Habit added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void viewHabits() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM habits");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + ". " + rs.getString("habit_name") +
                        " [Complete: " + rs.getBoolean("is_complete") + "]");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void markHabitComplete() {
        System.out.print("Enter habit ID to mark complete: ");
        int id = sc.nextInt();
        sc.nextLine();
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "UPDATE habits SET is_complete = true WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) System.out.println("✅ Habit marked complete!");
            else System.out.println("❌ Habit not found!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void deleteHabit() {
        System.out.print("Enter habit ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine();
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM habits WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) System.out.println("✅ Habit deleted!");
            else System.out.println("❌ Habit not found!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
