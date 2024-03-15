package com.Group22.lab.dto.BySeller;

import lombok.Data;

@Data
public class ChangeProduct {
    Long productId;
    String productName;
    Double productPrice;
    String productInformation;
}
