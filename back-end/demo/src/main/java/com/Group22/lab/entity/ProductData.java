package com.Group22.lab.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 * 商品信息
 * </p>
 *
 * @author HouBinyang
 * @since 2023-04-06
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
  @TableName("product_data")
@ApiModel(value = "ProductData对象", description = "商品信息")
public class ProductData implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("商品ID")
        @TableId(value = "product_id", type = IdType.AUTO)
      private Long productId;

      @ApiModelProperty("商品名")
      private String productName;

      @ApiModelProperty("商品图片")
      private String productImage;

      @ApiModelProperty("商品价格")
      private Double productPrice;

      @ApiModelProperty("商品描述")
      private String productInformation;

      @ApiModelProperty("商品状态")
      private String productState;

      @ApiModelProperty("所属商店Id")
      private Long shopId;


}
