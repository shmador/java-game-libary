import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.ResultSetMetaData;

public class SQLConnection {
	private static final String USER = "root";
	private static final String PASSWORD = "root";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/";
	private static final String DB = "rental_db";
	private static final String TABLE_CUSTOMERS = "Customers";
	private static final String TABLE_PRODUCTS = "Products";
	private static final String RENTAL_PRODUCTS = "Rentals";

	public static void main(String[] args) {
		if (Connect()) {
			CreateDatabase(DB);
			String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_CUSTOMERS + " (" + "id INT PRIMARY KEY AUTO_INCREMENT, "
					+ "name VARCHAR(100), " + "email VARCHAR(100), " + "dob DATE);";
			CreateTable(sql);
			sql = "CREATE TABLE IF NOT EXISTS " + TABLE_PRODUCTS + " (" + "id INT PRIMARY KEY AUTO_INCREMENT, "
					+ "name VARCHAR(100), " + "price FLOAT);";
			CreateTable(sql);
			sql = "CREATE TABLE IF NOT EXISTS " + RENTAL_PRODUCTS + " (" + "id INT PRIMARY KEY AUTO_INCREMENT, "
					+ "customer_id INT, " + "product_id INT, " + "rental_date DATETIME, " + "return_date DATETIME, "
					+ "FOREIGN KEY (customer_id) REFERENCES " + TABLE_CUSTOMERS + "(id), "
					+ "FOREIGN KEY (product_id) REFERENCES " + TABLE_PRODUCTS + "(id));";
			CreateTable(sql);

			sql = "INSERT INTO `Customers` (name, email, dob) VALUES "
					+ "('John Doe', 'johndoe@example.com', '1985-01-01'), "
					+ "('Jane Smith', 'janesmith@example.com', '1990-05-15'), "
					+ "('Michael Johnson', 'michaelj@example.com', '1982-11-23'), "
					+ "('Emily Davis', 'emilydavis@example.com', '1995-02-10'), "
					+ "('William Brown', 'williambrown@example.com', '1988-07-22');";

			Insert(sql);

			sql = "INSERT INTO `Products` (name, price) VALUES" + "('Laptop', 999.99)," + "('Smartphone', 499.99),"
					+ "('Headphones', 79.99)," + "('Smartwatch', 149.99)," + "('Tablet', 249.99);";
			Insert(sql);

			sql = "INSERT INTO `Rentals` (customer_id, product_id, rental_date, return_date) VALUES"
					+ "(1, 2, '2024-12-01 10:00:00', '2024-12-07 10:00:00'), "
					+ "(2, 3, '2024-12-02 14:30:00', '2024-12-10 14:30:00'), "
					+ "(3, 5, '2024-12-03 09:00:00', '2024-12-12 09:00:00'), "
					+ "(3, 1, '2024-12-04 12:00:00', '2024-12-11 12:00:00'), "
					+ "(5, 4, '2024-12-05 16:00:00', '2024-12-14 16:00:00');";
			Insert(sql);

			while (true) {
				System.out.println("Menu");
				System.out.println("1: insert into customers (name, email, dob)");
				System.out.println("2: insert into products (name, price)");
				System.out.println("3: insert into rentals (customer_id, product_id, rental_date, return_date)");
				System.out.println("4: select * from Customers");
				System.out.println("5: select * from Products");
				System.out.println("6: select * from Rentals");
				System.out.println("7: delete from Customers");
				System.out.println("8: delete from Products");
				System.out.println("9: delete from Rentals");
				System.out.println("10: join Customers and Rentals");
				System.out.println("11: join Products and Rentals");
				System.out.println("12: update Customers (id, new_name, new_email)");
				System.out.println("13: update Products (id ,new_name, new_price)");
				System.out.println("14: update Rentals (product_id, new_rental_date, new_return_date)");
				System.out.println("15: sum (table, column))");

				int choice = -1;
				Scanner s = new Scanner(System.in);
				choice = Integer.parseInt(s.nextLine());

				switch (choice) {
				case 1:
					InsertIntoCustomers();
					break;
				case 2:
					InsertIntoProducts();
					break;
				case 3:
					InsertIntoRentals();
					break;
				case 4:
					select("Select * from " + TABLE_CUSTOMERS);
					break;
				case 5:
					select("Select * from Products " + TABLE_PRODUCTS);
					break;
				case 6:
					select("Select * from Rentals " + RENTAL_PRODUCTS);
					break;
				case 7:
					delete(TABLE_CUSTOMERS);
					break;
				case 8:
					delete(TABLE_PRODUCTS);
					break;
				case 9:
					delete(RENTAL_PRODUCTS);
					break;
				case 10:
					join(TABLE_CUSTOMERS, RENTAL_PRODUCTS);
					break;
				case 11:
					join(TABLE_PRODUCTS, RENTAL_PRODUCTS);
					break;
				case 12:
					updateCustomer();
					break;
				case 13:
					updateProduct();
					break;
				case 14:
					updateRentalDate();
					break;
				case 15:
					sumColumn();
					break;
				default:
					return;
				}

			}

		}

	}

	private static void InsertIntoProducts() {
		try (Connection conn = DriverManager.getConnection(URL + DB, USER, PASSWORD);
				Statement stmt = conn.createStatement();) {
			Scanner s = new Scanner(System.in);
			System.out.println("Enter product name:");
			String name = s.nextLine();
			System.out.println("Enter product price:");
			String price = s.nextLine();
			String sql = "INSERT INTO `Products` (name, price) VALUES " + "('" + name + "', '" + price + "');";
			stmt.executeUpdate(sql);
			System.out.println("Insertion complete!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void InsertIntoCustomers() {
		try (Connection conn = DriverManager.getConnection(URL + DB, USER, PASSWORD);
				Statement stmt = conn.createStatement();) {
			Scanner s = new Scanner(System.in);
			System.out.println("Enter customer name:");
			String cName = s.nextLine();
			System.out.println("Enter customer mail:");
			String cMail = s.nextLine();
			System.out.println("Enter customer birthday:");
			String cBday = s.nextLine();
			String sql = "INSERT INTO `Customers` (name, email, dob) VALUES " + "('" + cName + "', '" + cMail + "', '"
					+ cBday + "');";
			stmt.executeUpdate(sql);
			System.out.println("Insertion complete!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void InsertIntoRentals() {
		try (Connection conn = DriverManager.getConnection(URL + DB, USER, PASSWORD);
				Statement stmt = conn.createStatement();) {
			Scanner s = new Scanner(System.in);
			System.out.println("Enter customer id:");
			String cId = s.nextLine();
			System.out.println("Enter product id :");
			String pId = s.nextLine();
			System.out.println("Enter rental date:");
			String rentDate = s.nextLine();
			System.out.println("Enter return date:");
			String returnDate = s.nextLine();
			String sql = "INSERT INTO `Rentals` (customer_id, product_id, rental_date, return_date) VALUES " + "('"
					+ cId + "', '" + pId + "', '" + rentDate + "', '" + returnDate + "');";
			stmt.executeUpdate(sql);
			System.out.println("Insertion complete!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static boolean Connect() {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
			System.out.println("Connected");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	private static void CreateDatabase(String Table) {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement stmt = conn.createStatement();) {
			String sql = "CREATE DATABASE if not exists " + Table + ";";
			stmt.executeUpdate(sql);
			System.out.println("Database created");
			sql = "USE " + Table + ";";
			stmt.executeUpdate(sql);
			System.out.println("Database !");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void CreateTable(String sql) {
		try (Connection conn = DriverManager.getConnection(URL + DB, USER, PASSWORD);
				Statement stmt = conn.createStatement();) {
			stmt.executeUpdate(sql);
			System.out.println("Created");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void Insert(String sql) {
		try (Connection conn = DriverManager.getConnection(URL + DB, USER, PASSWORD);
				Statement stmt = conn.createStatement();) {
			stmt.executeUpdate(sql);
			System.out.println("Inserted Data");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void select(String sql) {
		try (Connection conn = DriverManager.getConnection(URL + DB, USER, PASSWORD);
				Statement stmt = conn.createStatement();) {
			System.out.println("Select: output");
			ResultSet r = stmt.executeQuery(sql);
			ResultSetMetaData metaData = r.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (r.next()) {
				for (int i = 1; i <= columnCount; i++) {
					System.out.print(r.getString(i) + "\t");
				}
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void delete(String table) {
		try (Connection conn = DriverManager.getConnection(URL + DB, USER, PASSWORD);
				Statement stmt = conn.createStatement();) {
			if (table == TABLE_CUSTOMERS) {
				String sql = "DELETE FROM " + RENTAL_PRODUCTS + " WHERE customer_id IN (" + "SELECT id FROM "
						+ TABLE_CUSTOMERS + ");";
				stmt.executeUpdate(sql);
			}
			if (table == TABLE_PRODUCTS) {
				String sql = "DELETE FROM " + RENTAL_PRODUCTS + " WHERE product_id IN (" + "SELECT id FROM "
						+ TABLE_PRODUCTS + ");";
				stmt.executeUpdate(sql);
			}
			String sql = "Delete from " + table + ";";
			stmt.executeUpdate(sql);
			System.out.println("Deleted");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void join(String table1, String table2) {
		String sql = "SELECT " + table1 + ".*, " + table2 + ".*\r\n" + "FROM " + table1 + "\r\n" + "INNER JOIN "
				+ table2 + " ON " + table1 + ".id = " + table2 + ".customer_id;";

		try (Connection conn = DriverManager.getConnection(URL + DB, USER, PASSWORD);
				Statement stmt = conn.createStatement();
				ResultSet r = stmt.executeQuery(sql)) {

			ResultSetMetaData metaData = r.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (r.next()) {
				for (int i = 1; i <= columnCount; i++) {
					System.out.print(r.getString(i) + "\t");
				}
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void updateCustomer() {

		Scanner s = new Scanner(System.in);
		System.out.println("Enter customer id");
		String customerId = s.nextLine();
		System.out.println("Enter new name");
		String newName = s.nextLine();
		System.out.println("Enter new email");
		String newEmail = s.nextLine();
		System.out.println("Enter new name");

		String sql = "UPDATE `Customers` " + "SET name = '" + newName + "', email = '" + newEmail + "' " + "WHERE id = "
				+ customerId + ";";

		try (Connection conn = DriverManager.getConnection(URL + DB, USER, PASSWORD);
				Statement stmt = conn.createStatement()) {

			int rowsUpdated = stmt.executeUpdate(sql);

			if (rowsUpdated > 0) {
				System.out.println("Updated");
			} else {
				System.out.println("No updates");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void updateProduct() {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter product id");
		String productId = s.nextLine();
		System.out.println("Enter new name");
		String newName = s.nextLine();
		System.out.println("Enter new price");
		String newPrice = s.nextLine();

		String sql = "UPDATE `Products` " + "SET name = '" + newName + "', price = " + newPrice + " " + "WHERE id = "
				+ productId + ";";

		try (Connection conn = DriverManager.getConnection(URL + DB, USER, PASSWORD);
				Statement stmt = conn.createStatement()) {

			int rowsUpdated = stmt.executeUpdate(sql);

			if (rowsUpdated > 0) {
				System.out.println("Updated");
			} else {
				System.out.println("No updates");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void updateRentalDate() {

		Scanner s = new Scanner(System.in);
		System.out.println("Enter product id");
		String productId = s.nextLine();
		System.out.println("Enter new rental date");
		String newRentalDate = s.nextLine();
		System.out.println("Enter new return date");
		String newReturnDate = s.nextLine();

		String sql = "UPDATE `Rentals` " + "SET rental_date = '" + newRentalDate + "', return_date = '" + newReturnDate
				+ "' " + "WHERE product_id = " + productId + ";";

		try (Connection conn = DriverManager.getConnection(URL + DB, USER, PASSWORD);
				Statement stmt = conn.createStatement()) {

			int rowsUpdated = stmt.executeUpdate(sql);

			if (rowsUpdated > 0) {
				System.out.println("Updated");
			} else {
				System.out.println("No updates");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void sumColumn() {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter table name");
		String table = s.nextLine();
		System.out.println("Enter column name");
		String column = s.nextLine();
		String sql = "SELECT SUM(" + column + ") AS total_sum FROM " + table + ";";

		try (Connection conn = DriverManager.getConnection(URL + DB, USER, PASSWORD);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			if (rs.next()) {
				double totalSum = rs.getDouble("total_sum");
				System.out.println("The sum of the column " + column + " in table " + table + " is: " + totalSum);
			} else {
				System.out.println("No data found or column is empty.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}