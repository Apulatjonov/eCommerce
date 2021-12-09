package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product extends BaseModel{
    private UUID categoryId;
    private UUID shopId;
    private int quantity;
    private double price;
    private double discount;
}
