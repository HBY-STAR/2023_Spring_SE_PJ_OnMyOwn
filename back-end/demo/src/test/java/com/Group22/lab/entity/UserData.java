package com.Group22.lab.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
 * @since 2023-03-13
 */
@Getter
@Setter
  @TableName("user_data")
@ApiModel(value = "UserData对象", description = "")
public class UserData implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("身份证号")
        private Long id;

      @ApiModelProperty("用户名")
      private String username;

      @ApiModelProperty("密码")
      private String password;

      @ApiModelProperty("邮箱")
      private String email;

      @ApiModelProperty("电话")
      private String phone;

      @ApiModelProperty("用户类型")
      private String userType;


}
