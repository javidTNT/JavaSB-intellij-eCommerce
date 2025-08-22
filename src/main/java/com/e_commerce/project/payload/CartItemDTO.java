package com.e_commerce.project.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {
    private Long CartItemId;
    private CartDTO cart;
    private ProductDTO product;
    private Integer quantity;
    private Double productprice;
    private Double discount;
}
