package at.dto.response;

import lombok.Data;
import java.util.List;

@Data
public class AddToCartResponse {
    private String cartMessage;
    private boolean success;
    private List<CartItem> items;
}
