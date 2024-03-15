package com.Group22.lab.controller.UserController;

import com.Group22.lab.common.Result;
import com.Group22.lab.dto.ByUser.RechargeUserAccount;
import com.Group22.lab.entity.User.UserData;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.math.BigInteger;

import com.Group22.lab.service.IUserService.IUserAccountService;
import com.Group22.lab.entity.User.UserAccount;


/**
 * <p>
 * 个人账户 前端控制器
 * </p>
 *
 * @author HouBinyang
 * @since 2023-04-06
 */
@RestController
@RequestMapping("/user-account")
public class UserAccountController {

    @Resource
    private IUserAccountService userAccountService;

    @PostMapping
    public Boolean save(@RequestBody UserAccount userAccount) {
        return userAccountService.saveOrUpdate(userAccount);
    }

    @PostMapping("/recharge_account")
    public Result recharge_account(@RequestBody RechargeUserAccount rechargeUserAccount) {
        UserAccount userAccount = userAccountService.rechargeAccount(rechargeUserAccount);
        return Result.success(userAccount);
    }

    @PostMapping("/add_to_shopping_cart_{uid}")
    public Result addToShoppingCart(@PathVariable Long uid, @RequestBody Long productId) {
        UserAccount userAccount = userAccountService.addToShoppingCart(uid, productId);
        return Result.success(userAccount);
    }

    @DeleteMapping("/delete_{id}")
    public Boolean delete(@PathVariable BigInteger id) {
        return userAccountService.removeById(id);
    }

    @PostMapping("/delete_shop_cart_by_{uid}")
    public Result deleteShopCartByUid(@PathVariable Long uid,@RequestParam(value = "productIds[]") Long[] productIds){
        UserAccount userAccount = userAccountService.deleteShopCartByUid(uid,productIds);
        return Result.success(userAccount);
    }

    @PostMapping("/pay_shop_cart_by_{uid}")
    public Result payShopCartByUid(@PathVariable Long uid,@RequestParam(value = "productIds[]") Long[] productIds){
        UserAccount userAccount = userAccountService.payShopCartByUid(uid,productIds);
        return Result.success(userAccount);
    }

    @GetMapping("/find_{id}")
    public Result findOne(@PathVariable BigInteger id) {
        UserAccount userAccount = userAccountService.getById(id);
        return Result.success(userAccount);
    }

    @GetMapping("/find_all_user_account")
    public Result findAll(){
        List<UserAccount> userAccountList = userAccountService.findAll();
        return Result.success(userAccountList);
    }
}
