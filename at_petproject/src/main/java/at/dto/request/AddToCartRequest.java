package at.dto.request;

import lombok.Data;

@Data
public class AddToCartRequest {
    private String productId;
    private int quantity;
}
