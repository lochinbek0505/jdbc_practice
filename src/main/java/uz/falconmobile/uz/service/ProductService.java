package uz.falconmobile.uz.service;

import uz.falconmobile.uz.connection.DbConnection;
import uz.falconmobile.uz.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class ProductService {

    public void addProduct(Product product) {
        String sql = "INSERT INTO products(name, category_id, price, quantity, unit) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DbConnection.connect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, product.getName());
            ps.setInt(2, product.getCategoryId());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getQuantity());
            ps.setString(5, product.getUnit());
            ps.executeUpdate();
            System.out.println("✅ Mahsulot qo‘shildi!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addProductFromInput(Scanner sc) {
        sc.nextLine(); // consume newline
        System.out.print("Mahsulot nomi: ");
        String name = sc.nextLine();
        System.out.print("Kategoriya ID: ");
        int catId = sc.nextInt();
        System.out.print("Narxi: ");
        double price = sc.nextDouble();
        System.out.print("Miqdori: ");
        int qty = sc.nextInt();
        sc.nextLine();
        System.out.print("O‘lchov birligi (kg, dona, litr): ");
        String unit = sc.nextLine();

        Product p = new Product(name, catId, price, qty, unit);
        addProduct(p);
    }

    public void listProducts() {
        String sql = "SELECT * FROM products ORDER BY id";
        try (Connection conn = DbConnection.connect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            System.out.println("\n== Mahsulotlar ==");
            while (rs.next()) {
                System.out.printf("ID: %d | %s | %.2f | %d %s | CatID: %d\n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getString("unit"),
                        rs.getInt("category_id")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateProductFromInput(Scanner sc) {
        System.out.print("O‘zgartiriladigan mahsulot ID: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline

        System.out.print("Yangi nom: ");
        String name = sc.nextLine();
        System.out.print("Yangi kategoriya ID: ");
        int catId = sc.nextInt();
        System.out.print("Yangi narxi: ");
        double price = sc.nextDouble();
        System.out.print("Yangi miqdor: ");
        int qty = sc.nextInt();
        sc.nextLine();
        System.out.print("Yangi o‘lchov birligi: ");
        String unit = sc.nextLine();

        String sql = "UPDATE products SET name = ?, category_id = ?, price = ?, quantity = ?, unit = ? WHERE id = ?";
        try (Connection conn = DbConnection.connect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setInt(2, catId);
            ps.setDouble(3, price);
            ps.setInt(4, qty);
            ps.setString(5, unit);
            ps.setInt(6, id);
            int updated = ps.executeUpdate();
            if (updated > 0) {
                System.out.println("✅ Mahsulot yangilandi.");
            } else {
                System.out.println("❌ Bunday ID topilmadi.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteProductById(Scanner sc) {
        System.out.print("O‘chiriladigan mahsulot ID: ");
        int id = sc.nextInt();

        String sql = "DELETE FROM products WHERE id = ?";
        try (Connection conn = DbConnection.connect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int deleted = ps.executeUpdate();
            if (deleted > 0) {
                System.out.println("✅ Mahsulot o‘chirildi.");
            } else {
                System.out.println("❌ Bunday ID topilmadi.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
