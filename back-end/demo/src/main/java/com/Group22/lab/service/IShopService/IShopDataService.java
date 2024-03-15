package com.Group22.lab.service.IShopService;

import com.Group22.lab.dto.BySeller.ApplyForShop;
import com.Group22.lab.dto.ByAdmin.ShopApplyPass;
import com.Group22.lab.entity.Shop.ShopData;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigInteger;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author HouBinyang
 * @since 2023-03-14
 */
public interface IShopDataService extends IService<ShopData> {

    ShopData apply(ApplyForShop applyForShop);

    List<ShopData> findRequestShop();

    List<ShopData> findOpenShop();

    ShopData check_shop_request(ShopApplyPass shopApplyPass);

    ShopData findByUid(BigInteger uid);

    List<ShopData> findDeleteShop();

    ShopData check_shop_delete(Long shopId, String isPass);

    boolean deleteShop(BigInteger shopId);
}
