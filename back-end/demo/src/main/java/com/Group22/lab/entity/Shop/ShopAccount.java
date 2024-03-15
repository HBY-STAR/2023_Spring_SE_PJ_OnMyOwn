package com.Group22.lab.entity.Shop;

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
 * 商店账户
 * </p>
 *
 * @author HouBinyang
 * @since 2023-04-06
 */
@Getter
@Setter
  @TableName("shop_account")
@ApiModel(value = "ShopAccount对象", description = "商店账户")
@NoArgsConstructor
@AllArgsConstructor
public class ShopAccount implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("商店ID")
      @TableId
        private Long shopId;
      @ApiModelProperty("店主UID")
      private Long sellerUid;
      @ApiModelProperty("账户状态")
      private String accountState;

      @ApiModelProperty("商品种类")
      private String productType;

      @ApiModelProperty("商店余额")
      private Double balance;


}
