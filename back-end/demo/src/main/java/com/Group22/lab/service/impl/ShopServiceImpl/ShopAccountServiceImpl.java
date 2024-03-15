package com.Group22.lab.service.impl.ShopServiceImpl;

import com.Group22.lab.common.Constants;
import com.Group22.lab.entity.Shop.ShopAccount;
import com.Group22.lab.entity.User.UserAccount;
import com.Group22.lab.exception.ServiceException;
import com.Group22.lab.mapper.ShopAccountMapper;
import com.Group22.lab.mapper.UserAccountMapper;
import com.Group22.lab.service.IShopService.IShopAccountService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

/**
 * <p>
 * 商店账户 服务实现类
 * </p>
 *
 * @author HouBinyang
 * @since 2023-04-06
 */
@Service
public class ShopAccountServiceImpl extends ServiceImpl<ShopAccountMapper, ShopAccount> implements IShopAccountService {
    @Resource
    private UserAccountMapper userAccountMapper;

    @Override
    public ShopAccount findByUid(BigInteger uid) {
        QueryWrapper<ShopAccount> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("seller_uid",uid);
        ShopAccount one = getOne(queryWrapper);
        if(one!=null) {
            return one;
        }
        else{
            throw new ServiceException(Constants.CODE_404, "未找到该UID对应的商店账户");
        }
    }

    @Override
    public ShopAccount rechargeShopAccount(Long shopId, Double number) {
        QueryWrapper<ShopAccount> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("shop_id",shopId);
        ShopAccount shopAccount = getOne(queryWrapper);
        UserAccount userAccount = userAccountMapper.selectById(shopAccount.getSellerUid());
        if(userAccount.getBalance() < number){
            throw new ServiceException(Constants.CODE_404, "个人账户余额不足，请先充值个人账户");
        }
        else{
            shopAccount.setBalance(shopAccount.getBalance()+number);
            userAccount.setBalance(userAccount.getBalance()-number);
            saveOrUpdate(shopAccount);
            userAccountMapper.updateById(userAccount);
            return shopAccount;
        }
    }

    @Override
    public List<ShopAccount> findAll() {
        return list();
    }
}
