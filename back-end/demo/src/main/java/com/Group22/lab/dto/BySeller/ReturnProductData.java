package com.Group22.lab.dto.BySeller;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnProductData {
    @ApiModelProperty("商品ID")
    private Long productId;

    @ApiModelProperty("商品名")
    private String productName;

    @ApiModelProperty("商品图片")
    private List<String> productImage;

    @ApiModelProperty("商品价格")
    private Double productPrice;

    @ApiModelProperty("商品描述")
    private String productInformation;

    @ApiModelProperty("商品状态")
    private String productState;

    @ApiModelProperty("所属商店Id")
    private Long shopId;
}
