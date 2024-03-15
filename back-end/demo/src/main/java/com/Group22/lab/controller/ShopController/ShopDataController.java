package com.Group22.lab.controller.ShopController;

import com.Group22.lab.common.Result;
import com.Group22.lab.dto.BySeller.ApplyForShop;
import com.Group22.lab.dto.ByAdmin.ShopApplyPass;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.math.BigInteger;

import com.Group22.lab.service.IShopService.IShopDataService;
import com.Group22.lab.entity.Shop.ShopData;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author HouBinyang
 * @since 2023-03-14
 */
@RestController
@RequestMapping("/shop-data")
public class ShopDataController {

    @Resource
    private IShopDataService shopDataService;

    @PostMapping("/apply_for_shop")
    public Result apply(@RequestBody ApplyForShop applyForShop) {
        ShopData shopData = shopDataService.apply(applyForShop);
        return Result.success(shopData);
    }

    @PostMapping("/check_shop_request")
    public Result check_shop_request(@RequestBody ShopApplyPass shopApplyPass) {
        ShopData shopData = shopDataService.check_shop_request(shopApplyPass);
        return Result.success(shopData);
    }

    @PostMapping("/check_shop_delete_{shopId}")
    public Result check_shop_delete(@PathVariable Long shopId,@RequestBody String isPass) {
        ShopData shopData = shopDataService.check_shop_delete(shopId,isPass);
        return Result.success(shopData);
    }

    @PostMapping
    public Boolean save(@RequestBody ShopData shopData) {
        return shopDataService.saveOrUpdate(shopData);
    }

    @DeleteMapping("/delete_{id}")
    public Boolean delete(@PathVariable BigInteger id) {
        return shopDataService.removeById(id);
    }

    @DeleteMapping("/delete_shop_{shopId}")
    public Result deleteShop(@PathVariable BigInteger shopId) {
        boolean success = shopDataService.deleteShop(shopId);
        return Result.success(success);
    }

    @GetMapping("/find_all_shop_data")
    public Result findAll() {
        List<ShopData> list = shopDataService.list();
        return Result.success(list);
    }

    @GetMapping("/find_delete_shop")
    public Result findDeleteShop() {
        List<ShopData> list = shopDataService.findDeleteShop();
        return Result.success(list);
    }

    @GetMapping("/find_request_shop")
    public Result findRequestShop() {
        List<ShopData> list = shopDataService.findRequestShop();
        return Result.success(list);
    }

    @GetMapping("/find_open_shop")
    public Result findOpenShop() {
        List<ShopData> list = shopDataService.findOpenShop();
        return Result.success(list);
    }

    @GetMapping("/find_{id}")
    public ShopData findOne(@PathVariable BigInteger id) {
        return shopDataService.getById(id);
    }

    @GetMapping("/find_by_uid_{uid}")
    public Result findByUid(@PathVariable BigInteger uid) {
        ShopData shopData = shopDataService.findByUid(uid);
        return Result.success(shopData);
    }

}
