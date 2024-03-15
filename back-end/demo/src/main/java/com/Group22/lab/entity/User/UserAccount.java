package com.Group22.lab.entity.User;

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
 * 个人账户
 * </p>
 *
 * @author HouBinyang
 * @since 2023-04-06
 */
@Getter
@Setter
  @TableName("user_account")
@ApiModel(value = "UserAccount对象", description = "个人账户")
@NoArgsConstructor
@AllArgsConstructor
public class UserAccount implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("用户ID")
      @TableId
        private Long uid;

      @ApiModelProperty("账户状态")
      private String accountState;

      @ApiModelProperty("账户余额")
      private Double balance;

      @ApiModelProperty("购物车")
      private String shopCart;


}
