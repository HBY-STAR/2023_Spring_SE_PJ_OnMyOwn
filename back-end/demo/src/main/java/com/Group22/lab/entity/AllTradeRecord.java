package com.Group22.lab.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 所有交易记录
 * </p>
 *
 * @author HouBinyang
 * @since 2023-04-06
 */
  @TableName("all_trade_record")
@ApiModel(value = "AllTradeRecord对象", description = "所有交易记录")
  @Data
  @AllArgsConstructor
  @NoArgsConstructor
public class AllTradeRecord implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("交易记录ID")
        @TableId(value = "record_id", type = IdType.AUTO)
      private Long recordId;

      @ApiModelProperty("卖家ID")
      private Long shopId;

      @ApiModelProperty("买家ID")
      private Long uid;

      @ApiModelProperty("商品ID")
      private Long productId;

      @ApiModelProperty("交易金额")
      private Double price;

      @ApiModelProperty("订单状态")
      private String state;

      @ApiModelProperty("创建时间")
      private LocalDateTime time;

}
