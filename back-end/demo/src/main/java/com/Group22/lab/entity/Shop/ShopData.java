package com.Group22.lab.entity.Shop;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author HouBinyang
 * @since 2023-03-14
 */
@Getter
@Setter
  @TableName("shop_data")
@ApiModel(value = "ShopData对象", description = "")
public class ShopData implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("商店序列号")
        @TableId(value = "shop_id", type = IdType.AUTO)
      private Long shopId;

      @ApiModelProperty("商店名")
      private String shopName;

      @ApiModelProperty("商品种类")
      private String productType;

      @ApiModelProperty("商店简历")
      private String shopResume;

      @ApiModelProperty("商店备案地址")
      private String shopAddress;

      @ApiModelProperty("注册资金,要求大于1000.")
      private Double shopRegisterCost;

      @ApiModelProperty("注册时间")
      private LocalDateTime shopRegisterTime;

      @ApiModelProperty("商店分数")
      private Integer shopScore;

      @ApiModelProperty("店主UID")
      private Long sellerUid;

      @ApiModelProperty("店主身份证号")
      private String sellerIdNumber;

      @ApiModelProperty("商店状态")
      private String shopState;
}
