package com.Group22.lab.mapper;

import com.Group22.lab.entity.User.UserAccount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 个人账户 Mapper 接口
 * </p>
 *
 * @author HouBinyang
 * @since 2023-04-06
 */
@Mapper
public interface UserAccountMapper extends BaseMapper<UserAccount> {

}
