package at.db.request;

import lombok.Data;

@Data
public class AddToCartRequest {
    private String productId;
    private int quantity;
}
