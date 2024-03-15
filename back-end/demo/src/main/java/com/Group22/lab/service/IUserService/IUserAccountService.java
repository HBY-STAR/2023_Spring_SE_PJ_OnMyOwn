package com.Group22.lab.service.IUserService;

import com.Group22.lab.dto.ByUser.RechargeUserAccount;
import com.Group22.lab.entity.User.UserAccount;
import com.Group22.lab.entity.User.UserData;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 个人账户 服务类
 * </p>
 *
 * @author HouBinyang
 * @since 2023-04-06
 */
public interface IUserAccountService extends IService<UserAccount> {

    UserAccount rechargeAccount(RechargeUserAccount rechargeUserAccount);

    UserAccount addToShoppingCart(Long uid, Long productId);

    UserAccount deleteShopCartByUid(Long uid, Long[] productIds);

    UserAccount payShopCartByUid(Long uid, Long[] productIds);

    List<UserAccount> findAll();
}
