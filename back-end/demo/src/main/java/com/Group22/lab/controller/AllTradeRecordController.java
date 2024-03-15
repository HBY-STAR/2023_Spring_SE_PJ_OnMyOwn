package com.Group22.lab.controller;

import com.Group22.lab.common.Result;
import com.Group22.lab.dto.BySeller.OrderPass;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.math.BigInteger;

import com.Group22.lab.service.IAllTradeRecordService;
import com.Group22.lab.entity.AllTradeRecord;


/**
 * <p>
 * 所有交易记录 前端控制器
 * </p>
 *
 * @author HouBinyang
 * @since 2023-04-06
 */
@RestController
@RequestMapping("/all-trade-record")
public class AllTradeRecordController {

    @Resource
    private IAllTradeRecordService allTradeRecordService;

    @PostMapping
    public Boolean save(@RequestBody AllTradeRecord allTradeRecord) {
        return allTradeRecordService.saveOrUpdate(allTradeRecord);
    }

    @DeleteMapping("/delete_{id}")
    public Boolean delete(@PathVariable BigInteger id) {
        return allTradeRecordService.removeById(id);
    }

    @GetMapping
    public List<AllTradeRecord> findAll() {
        return allTradeRecordService.list();
    }

    @GetMapping("/find_order_by_{shop_id}")
    public Result findOrderByShopId(@PathVariable Long shop_id) {
        List<AllTradeRecord> allTradeRecords = allTradeRecordService.findOrderByShopId(shop_id);
        return Result.success(allTradeRecords);
    }

    @GetMapping("/find_checked_order_by_{shop_id}")
    public Result findCheckedOrderByShopId(@PathVariable Long shop_id) {
        List<AllTradeRecord> allTradeRecords = allTradeRecordService.findCheckedOrderByShopId(shop_id);
        return Result.success(allTradeRecords);
    }

    @GetMapping("/find_all_trade_record")
    public Result findAllTradeRecord() {
        List<AllTradeRecord> allTradeRecords = allTradeRecordService.findAllTradeRecord();
        return Result.success(allTradeRecords);
    }

    @GetMapping("/find_not_checked_order")
    public Result findNotCheckedOrder() {
        List<AllTradeRecord> allTradeRecords = allTradeRecordService.findNotCheckedOrder();
        return Result.success(allTradeRecords);
    }

    @PostMapping("/check_order_request")
    public Result checkOrderRequest(@RequestBody OrderPass orderPass) {
        boolean success = allTradeRecordService.checkOrderRequest(orderPass);
        return Result.success(success);
    }

    @GetMapping("/find_{id}")
    public AllTradeRecord findOne(@PathVariable BigInteger id) {
        return allTradeRecordService.getById(id);
    }
}
