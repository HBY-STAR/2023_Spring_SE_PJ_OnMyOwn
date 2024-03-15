package com.Group22.lab.controller.ShopController;

import com.Group22.lab.common.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.math.BigInteger;

import com.Group22.lab.service.IShopService.IShopAccountService;
import com.Group22.lab.entity.Shop.ShopAccount;


/**
 * <p>
 * 商店账户 前端控制器
 * </p>
 *
 * @author HouBinyang
 * @since 2023-04-06
 */
@RestController
@RequestMapping("/shop-account")
public class ShopAccountController {

    @Resource
    private IShopAccountService shopAccountService;

    @PostMapping
    public Boolean save(@RequestBody ShopAccount shopAccount) {
        return shopAccountService.saveOrUpdate(shopAccount);
    }

    @PostMapping("/recharge_shop_account_{shop_id}")
    public Result rechargeShopAccount(@PathVariable Long shop_id,@RequestBody Double number){
        ShopAccount shopAccount = shopAccountService.rechargeShopAccount(shop_id,number);
        return Result.success(shopAccount);
    }

    @DeleteMapping("/delete_{id}")
    public Boolean delete(@PathVariable BigInteger id) {
        return shopAccountService.removeById(id);
    }

    @GetMapping("find_all_shop_account")
    public Result findAll() {
        List<ShopAccount> shopAccountList = shopAccountService.findAll();
        return Result.success(shopAccountList);
    }

    @GetMapping("/find_{id}")
    public Result findOne(@PathVariable BigInteger id) {
        return Result.success(shopAccountService.getById(id));
    }

    @GetMapping("/find_by_uid_{uid}")
    public Result findByUid(@PathVariable BigInteger uid) {
        ShopAccount shopAccount = shopAccountService.findByUid(uid);
        return Result.success(shopAccount);
    }

}
