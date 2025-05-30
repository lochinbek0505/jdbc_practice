package uz.falconmobile.uz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private int id;
    private String name;
    private int categoryId;
    private double price;
    private int quantity;
    private String unit;

    public Product(String name, int categoryId, double price, int quantity, String unit) {
        this.name = name;
        this.categoryId = categoryId;
        this.price = price;
        this.quantity = quantity;
        this.unit = unit;
    }

}
