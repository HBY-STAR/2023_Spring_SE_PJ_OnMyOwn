package com.Group22.lab.service.impl;

import com.Group22.lab.dto.BySeller.OrderPass;
import com.Group22.lab.entity.AllTradeRecord;
import com.Group22.lab.entity.Shop.ShopAccount;
import com.Group22.lab.entity.Shop.ShopData;
import com.Group22.lab.entity.User.UserAccount;
import com.Group22.lab.mapper.AllTradeRecordMapper;
import com.Group22.lab.mapper.ShopAccountMapper;
import com.Group22.lab.mapper.UserAccountMapper;
import com.Group22.lab.service.IAllTradeRecordService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 所有交易记录 服务实现类
 * </p>
 *
 * @author HouBinyang
 * @since 2023-04-06
 */
@Service
public class AllTradeRecordServiceImpl extends ServiceImpl<AllTradeRecordMapper, AllTradeRecord> implements IAllTradeRecordService {
    @Resource
    private ShopAccountMapper shopAccountMapper;

    @Resource
    private UserAccountMapper userAccountMapper;

    @Override
    public List<AllTradeRecord> findOrderByShopId(Long shopId) {
        QueryWrapper<AllTradeRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("shop_id",shopId);
        List<AllTradeRecord> allTradeRecords = list(queryWrapper);
        allTradeRecords.removeIf(allTradeRecord -> !allTradeRecord.getState().equals("未处理订单"));
        return allTradeRecords;
    }

    @Override
    public boolean checkOrderRequest(OrderPass orderPass) {
        QueryWrapper<AllTradeRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("record_id",orderPass.getOrderId());
        AllTradeRecord allTradeRecord = getOne(queryWrapper);
        if(orderPass.getIsPass().equals("通过")){
            ShopAccount shopAccount = shopAccountMapper.selectById(allTradeRecord.getShopId());
            shopAccount.setBalance(shopAccount.getBalance()+allTradeRecord.getPrice());
            shopAccountMapper.updateById(shopAccount);
            allTradeRecord.setState("订单已完成");
        }
        else{
            UserAccount userAccount = userAccountMapper.selectById(allTradeRecord.getUid());
            userAccount.setBalance(userAccount.getBalance()+allTradeRecord.getPrice());
            userAccountMapper.updateById(userAccount);
            allTradeRecord.setState("商家拒绝订单，用户资金已退还");
        }
        saveOrUpdate(allTradeRecord);
        return true;
    }

    @Override
    public List<AllTradeRecord> findCheckedOrderByShopId(Long shopId) {
        QueryWrapper<AllTradeRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("shop_id",shopId);
        List<AllTradeRecord> allTradeRecords = list(queryWrapper);
        allTradeRecords.removeIf(allTradeRecord -> allTradeRecord.getState().equals("未处理订单"));
        return allTradeRecords;
    }

    @Override
    public List<AllTradeRecord> findNotCheckedOrder() {
        QueryWrapper<AllTradeRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("state","未处理订单");
        return list(queryWrapper);
    }

    @Override
    public List<AllTradeRecord> findAllTradeRecord() {
        return list();
    }
}
