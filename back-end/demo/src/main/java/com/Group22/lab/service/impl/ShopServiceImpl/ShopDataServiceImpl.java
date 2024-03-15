package com.Group22.lab.service.impl.ShopServiceImpl;

import com.Group22.lab.common.Constants;
import com.Group22.lab.entity.AllTradeRecord;
import com.Group22.lab.entity.ProductData;
import com.Group22.lab.entity.Shop.ShopAccount;
import com.Group22.lab.entity.User.UserAccount;
import com.Group22.lab.exception.ServiceException;
import com.Group22.lab.dto.BySeller.ApplyForShop;
import com.Group22.lab.dto.ByAdmin.ShopApplyPass;
import com.Group22.lab.entity.Shop.ShopData;
import com.Group22.lab.entity.User.UserData;
import com.Group22.lab.mapper.*;
import com.Group22.lab.service.IShopService.IShopDataService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author HouBinyang
 * @since 2023-03-14
 */
@Service
public class ShopDataServiceImpl extends ServiceImpl<ShopDataMapper, ShopData> implements IShopDataService {
    @Resource
    private UserDataMapper userDataMapper;
    @Resource
    private UserAccountMapper userAccountMapper;
    @Resource
    private ShopAccountMapper shopAccountMapper;
    @Resource
    private AllTradeRecordMapper allTradeRecordMapper;
    @Resource
    private ProductDataMapper productDataMapper;
    @Override
    public ShopData apply(ApplyForShop applyForShop) {
        QueryWrapper<ShopData> queryWrapper1 = new QueryWrapper<>();
        QueryWrapper<ShopData> queryWrapper2 = new QueryWrapper<>();
        queryWrapper1.eq("shop_name", applyForShop.getShop_name());
        ShopData one = getOne(queryWrapper1);
        if(one != null) {
            throw new ServiceException(Constants.CODE_300,"店名已存在");
        }
        queryWrapper2.eq("seller_id_number", applyForShop.getSeller_id_number());
        ShopData two = getOne(queryWrapper2);
        if(two!=null){
            throw new ServiceException(Constants.CODE_300,"该身份证号已注册过一个商店");
        }
        else{
            QueryWrapper<UserData> queryWrapperUserData = new QueryWrapper<>();
            queryWrapperUserData.eq("id_number", applyForShop.getSeller_id_number());
            UserData temp = userDataMapper.selectOne(queryWrapperUserData);
            if(temp == null){
                throw new ServiceException(Constants.CODE_300,"该身份证号对应的用户不存在");
            }
            else{
                UserAccount userAccount = userAccountMapper.selectById(temp.getUid());
                if(!temp.getUserType().equals("seller"))
                {
                    throw new ServiceException(Constants.CODE_300,"只有商户类型的用户才可申请开店");
                }
                else if(userAccount.getBalance()<applyForShop.getShop_register_cost()){
                    throw new ServiceException(Constants.CODE_404,"个人账户资金不足，请先充值个人账户");
                }
                else{
                    AllTradeRecord sys_user = allTradeRecordMapper.selectById(10000);
                    sys_user.setPrice(sys_user.getPrice()+applyForShop.getShop_register_cost());
                    allTradeRecordMapper.updateById(sys_user);
                    userAccount.setBalance(userAccount.getBalance()-applyForShop.getShop_register_cost());
                    userAccountMapper.updateById(userAccount);
                    ShopData addShop = new ShopData();
                    addShop.setShopName(applyForShop.getShop_name());
                    addShop.setProductType(applyForShop.getProduct_type());
                    addShop.setShopResume(applyForShop.getShop_resume());
                    addShop.setShopAddress(applyForShop.getShop_address());
                    addShop.setShopRegisterCost(applyForShop.getShop_register_cost());
                    addShop.setShopRegisterTime(applyForShop.getShopRegisterTime());
                    addShop.setSellerUid(temp.getUid());
                    addShop.setSellerIdNumber(temp.getIdNumber());
                    addShop.setShopState("审核中");
                    boolean success = save(addShop);
                    if(success){
                        QueryWrapper<ShopData> getShopId = new QueryWrapper<>();
                        getShopId.eq("shop_name", applyForShop.getShop_name());
                        ShopData addAccount = getOne(getShopId);
                        ShopAccount add = new ShopAccount(addAccount.getShopId(),addAccount.getSellerUid(),"正常使用中",addAccount.getProductType(),0.0);
                        shopAccountMapper.insert(add);
                        return addAccount;
                    }
                    else{
                        throw new ServiceException(Constants.CODE_500,"后端错误，申请失败");
                    }
                }
            }
        }
    }

    @Override
    public List<ShopData> findRequestShop() {
        QueryWrapper<ShopData> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("shop_state","审核中");
        return list(queryWrapper);
    }

    @Override
    public List<ShopData> findOpenShop() {
        QueryWrapper<ShopData> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("shop_state","营业中");
        return list(queryWrapper);
    }

    @Override
    public ShopData check_shop_request(ShopApplyPass shopApplyPass) {
        QueryWrapper<ShopData> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("shop_id",shopApplyPass.getShop_id());
        ShopData check_shop = getOne(queryWrapper);
        if (check_shop != null) {
            if(shopApplyPass.getIs_pass().equals("通过")){
                check_shop.setShopState("营业中");
                updateById(check_shop);
            }
            else{
                check_shop.setShopState("未通过审核，注册资金已退还");
                AllTradeRecord sys_user = allTradeRecordMapper.selectById(10000);
                sys_user.setPrice(sys_user.getPrice()-check_shop.getShopRegisterCost());
                allTradeRecordMapper.updateById(sys_user);
                UserAccount userAccount = userAccountMapper.selectById(check_shop.getSellerUid());
                userAccount.setBalance(userAccount.getBalance()+check_shop.getShopRegisterCost());
                userAccountMapper.updateById(userAccount);
                updateById(check_shop);
            }
            return check_shop;
        } else {
            throw new ServiceException(Constants.CODE_301, "该商店ID不存在，请输入正确的商店ID");
        }
    }

    @Override
    public ShopData findByUid(BigInteger uid) {
        QueryWrapper<ShopData>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("seller_uid",uid);
        ShopData one = getOne(queryWrapper);
        if(one!=null) {
            return one;
        }
        else{
            throw new ServiceException(Constants.CODE_404, "未找到该UID对应的商店");
        }
    }

    @Override
    public List<ShopData> findDeleteShop() {
        QueryWrapper<ShopData> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("shop_state","注销请求审核中");
        return list(queryWrapper);
    }

    @Override
    public ShopData check_shop_delete(Long shopId, String isPass) {
        QueryWrapper<ShopData> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("shop_id",shopId);
        ShopData shopData = getOne(queryWrapper);
        if(isPass.equals("\"通过\"")){
            UserAccount userAccount = userAccountMapper.selectById(shopData.getSellerUid());
            ShopAccount shopAccount = shopAccountMapper.selectById(shopData.getShopId());
            userAccount.setBalance(userAccount.getBalance()+shopAccount.getBalance());
            userAccountMapper.updateById(userAccount);
            shopAccountMapper.deleteById(shopAccount);
            removeById(shopData);
            return null;
        }
        else{
            shopData.setShopState("注销请求未通过");
            saveOrUpdate(shopData);
            return shopData;
        }


    }

    @Override
    public boolean deleteShop(BigInteger shopId) {
        QueryWrapper<ShopData> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("shop_id",shopId);
        ShopData shopData = getOne(queryWrapper);
        QueryWrapper<AllTradeRecord> allTradeRecordQueryWrapper = new QueryWrapper<>();
        allTradeRecordQueryWrapper.eq("shop_id",shopId);
        List<AllTradeRecord> allTradeRecordList = allTradeRecordMapper.selectList(allTradeRecordQueryWrapper);
        allTradeRecordList.removeIf(allTradeRecord -> !allTradeRecord.getState().equals("未处理订单"));
        if(allTradeRecordList.size()>0){
            throw new ServiceException(Constants.CODE_404, "存在未处理的订单，注销请求提交失败");
        }
        else{
            QueryWrapper<ProductData> productDataQueryWrapper = new QueryWrapper<>();
            productDataQueryWrapper.eq("shop_id",shopId);
            productDataMapper.delete(productDataQueryWrapper);
            shopData.setShopState("注销请求审核中");
            saveOrUpdate(shopData);
            return  true;
        }
    }
}
