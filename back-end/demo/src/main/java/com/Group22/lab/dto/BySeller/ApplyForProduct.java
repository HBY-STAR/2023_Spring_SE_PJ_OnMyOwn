package com.Group22.lab.dto.BySeller;

import com.Group22.lab.entity.ProductData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplyForProduct {
    String productName;
    Double productPrice;
    String productInformation;
}
