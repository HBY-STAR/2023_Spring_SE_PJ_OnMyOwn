package com.Group22.lab.service.impl.UserServiceImpl;

import com.Group22.lab.common.Constants;
import com.Group22.lab.dto.ByUser.RechargeUserAccount;
import com.Group22.lab.entity.AllTradeRecord;
import com.Group22.lab.entity.User.UserAccount;
import com.Group22.lab.entity.User.UserData;
import com.Group22.lab.exception.ServiceException;
import com.Group22.lab.mapper.AllTradeRecordMapper;
import com.Group22.lab.mapper.ProductDataMapper;
import com.Group22.lab.mapper.UserAccountMapper;
import com.Group22.lab.service.IUserService.IUserAccountService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 个人账户 服务实现类
 * </p>
 *
 * @author HouBinyang
 * @since 2023-04-06
 */
@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements IUserAccountService {
    @Resource
    private ProductDataMapper productDataMapper;
    @Resource
    private AllTradeRecordMapper allTradeRecordMapper;

    @Override
    public UserAccount rechargeAccount(RechargeUserAccount rechargeUserAccount) {
        QueryWrapper<UserAccount> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", rechargeUserAccount.getUid());
        UserAccount one = getOne(queryWrapper);
        if(one!=null){
            one.setBalance(one.getBalance()+ rechargeUserAccount.getNumber());
            saveOrUpdate(one);
            return one;
        }else{
            throw new ServiceException(Constants.CODE_404, "充值失败，未找到对应用户");
        }
    }

    @Override
    public UserAccount addToShoppingCart(Long uid, Long productId) {
        QueryWrapper<UserAccount> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid",uid);
        UserAccount userAccount= getOne(queryWrapper);
        if(userAccount.getShopCart()==null||userAccount.getShopCart().equals("")||userAccount.getShopCart().equals(" ")){
            userAccount.setShopCart(productId.toString());
        }
        else{
            userAccount.setShopCart(userAccount.getShopCart()+"\n"+productId.toString());
        }
        userAccount.setShopCart(userAccount.getShopCart().replaceAll(" ",""));
        saveOrUpdate(userAccount);
        return userAccount;
    }

    @Override
    public UserAccount deleteShopCartByUid(Long uid, Long[] productIds) {
        QueryWrapper<UserAccount> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid",uid);
        UserAccount userAccount= getOne(queryWrapper);
        if(userAccount.getShopCart()==null||userAccount.getShopCart().equals("")||userAccount.getShopCart().equals(" ")){
            throw new ServiceException(Constants.CODE_404, "删除失败，购物车为空");
        }
        else{
            return deleteShopCart(productIds, userAccount);
        }
    }

    @Override
    public UserAccount payShopCartByUid(Long uid, Long[] productIds) {
        QueryWrapper<UserAccount> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid",uid);
        UserAccount userAccount= getOne(queryWrapper);
        Double pay = 0.0;
        for(Long product : productIds){
            pay+=productDataMapper.selectById(product).getProductPrice();
        }
        if(userAccount.getShopCart()==null||userAccount.getShopCart().equals("")||userAccount.getShopCart().equals(" ")){
            throw new ServiceException(Constants.CODE_404, "购买失败，购物车为空");
        }
        else if(userAccount.getBalance()<pay){
            throw new ServiceException(Constants.CODE_404, "购买失败，余额不足");
        }
        else{
            userAccount.setBalance(userAccount.getBalance()-pay);
            saveOrUpdate(userAccount);
            return deleteShopCart(productIds, userAccount);
        }
    }

    @Override
    public List<UserAccount> findAll() {
        return list();
    }

    private UserAccount deleteShopCart(Long[] productIds, UserAccount userAccount) {
        String shopCart=userAccount.getShopCart();
        for(Long productId : productIds){
            shopCart=shopCart.replaceFirst(productId.toString(),"");
            shopCart=shopCart.replaceAll("\n\n","\n");

            AllTradeRecord allTradeRecord = new AllTradeRecord();
            allTradeRecord.setUid(userAccount.getUid());
            allTradeRecord.setShopId(productDataMapper.selectById(productId).getShopId());
            allTradeRecord.setProductId(productId);
            allTradeRecord.setPrice(productDataMapper.selectById(productId).getProductPrice());
            allTradeRecord.setState("未处理订单");
            allTradeRecordMapper.insert(allTradeRecord);
        }
        if(shopCart.charAt(0)=='\n'){
            shopCart=shopCart.substring(1);
        }
        userAccount.setShopCart(shopCart);
        saveOrUpdate(userAccount);
        return userAccount;
    }

}
