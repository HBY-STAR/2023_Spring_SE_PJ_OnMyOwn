package com.Group22.lab.service.IShopService;

import com.Group22.lab.entity.Shop.ShopAccount;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigInteger;
import java.util.List;

/**
 * <p>
 * 商店账户 服务类
 * </p>
 *
 * @author HouBinyang
 * @since 2023-04-06
 */
public interface IShopAccountService extends IService<ShopAccount> {

    ShopAccount findByUid(BigInteger uid);

    ShopAccount rechargeShopAccount(Long shopId, Double number);

    List<ShopAccount> findAll();
}
