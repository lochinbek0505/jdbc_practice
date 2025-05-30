package uz.falconmobile.uz;

import uz.falconmobile.uz.service.ProductService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductService service = new ProductService();

        while (true) {
            System.out.println("""
                \n--- XO‘JALIK MOLLARI DO‘KONI ---
                1. Mahsulot qo‘shish
                2. Mahsulotlar ro‘yxati
                3. Mahsulotni tahrirlash
                4. Mahsulotni o‘chirish
                0. Chiqish
                Tanlang: 
                """);

            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> service.addProductFromInput(sc);
                case 2 -> service.listProducts();
                case 3 -> service.updateProductFromInput(sc);
                case 4 -> service.deleteProductById(sc);
                case 0 -> {
                    System.out.println("Dastur yakunlandi.");
                    return;
                }
                default -> System.out.println("❌ Noto‘g‘ri tanlov.");
            }
        }
    }
}
