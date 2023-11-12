import java.sql.*;
import java.util.Scanner;

public class ProjectOne {

            public static void main(String[] args) {
                ProjectOne project = new ProjectOne();
                project.createTable();
                project.populateTable();
            }
            public void createTable() {

                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_one", "root", "");
                     Statement statement = connection.createStatement()) {

                    statement.execute("CREATE TABLE IF NOT EXISTS users(name Text NOT NULL, email Text, age Int, location Text, designation Text)");
                } catch (SQLException exception) {
                    System.out.println(exception.getMessage());
                }
            }
            public int populateTable() {

                int count = 0;
                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_one", "root", "");
                     Statement statement = connection.createStatement()) {

                    PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO users(name, email, age, location, designation) VALUES(?, ?, ?, ?, ?)");

                    Scanner scanner = new Scanner(System.in);

                    for (int i = 0; i<10; i++) {

                        System.out.println("Please enter your name: ");
                        String name = scanner.nextLine();
                        System.out.println("Please enter your email: ");
                        String email = scanner.nextLine();
                        System.out.println("Please enter your age: ");
                        int age = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("PLease enter your location: ");
                        String location = scanner.nextLine();
                        System.out.println("PLease enter your designation: ");
                        String designation = scanner.nextLine();

                        insertStatement.setString(1, name);
                        insertStatement.setString(2, email);
                        insertStatement.setInt(3, age);
                        insertStatement.setString(4, location);
                        insertStatement.setString(5, designation);

                        insertStatement.execute();
                        count++;
                    }

                } catch (SQLException exception) {
                    System.out.println(exception.getMessage());
                }
                return count;
            }
        }



