package com.Group22.lab.service;

import com.Group22.lab.dto.BySeller.OrderPass;
import com.Group22.lab.entity.AllTradeRecord;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 所有交易记录 服务类
 * </p>
 *
 * @author HouBinyang
 * @since 2023-04-06
 */
public interface IAllTradeRecordService extends IService<AllTradeRecord> {

    List<AllTradeRecord> findOrderByShopId(Long shopId);

    boolean checkOrderRequest(OrderPass orderPass);

    List<AllTradeRecord> findCheckedOrderByShopId(Long shopId);

    List<AllTradeRecord> findNotCheckedOrder();

    List<AllTradeRecord> findAllTradeRecord();
}
