package com.Group22.lab.dto.BySeller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderPass {
    Long orderId;
    String isPass;
}
