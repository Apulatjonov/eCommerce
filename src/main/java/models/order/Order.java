package models.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.BaseModel;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order extends BaseModel {

    private String name = "";
    private double price;
    private int quantity;
    private String photo = "";
    private UUID productId;
    private UUID categoryId;
    private UUID shopId;
    private UUID userId;
    private Status status;


}
