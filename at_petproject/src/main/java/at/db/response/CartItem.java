package at.db.response;

import lombok.Data;

@Data
public class CartItem {
    private int productId;
    private String name;
    private int quantity;
}
