package com.Group22.lab.dto.BySeller;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ApplyForShop {
    private String shop_name;
    private String product_type;
    private String seller_id_number;
    private String shop_resume;
    private String shop_address;
    private Double shop_register_cost;
    private LocalDateTime shopRegisterTime;
}
