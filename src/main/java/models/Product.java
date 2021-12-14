package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product extends BaseModel{
    private String name;
    private double price;
    private double netPrice;
    private int quantity;
    private double discount;
    private String photo;
    private UUID categoryId;
    private UUID shopId;
}
