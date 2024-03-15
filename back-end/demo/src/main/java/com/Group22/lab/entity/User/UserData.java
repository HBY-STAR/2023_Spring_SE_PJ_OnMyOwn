package com.Group22.lab.entity.User;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
  @TableName("user_data")
@ApiModel(value = "UserData对象")
public class UserData implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("用户序列号")
        @TableId(value = "uid", type = IdType.AUTO)
      private Long uid;

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

      @ApiModelProperty("用户身份证号")
      private String idNumber;

      @ApiModelProperty("头像地址")
      private String avatarUrl;

}
