package com.Group22.lab.controller;

import com.Group22.lab.common.Result;
import com.Group22.lab.dto.ByAdmin.ProductApplyPass;
import com.Group22.lab.dto.BySeller.ApplyForProduct;
import com.Group22.lab.dto.BySeller.ChangeProduct;
import com.Group22.lab.dto.BySeller.ReturnProductData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.math.BigInteger;

import com.Group22.lab.service.IProductDataService;
import com.Group22.lab.entity.ProductData;
import org.springframework.web.multipart.MultipartFile;


/**
 * <p>
 * 商品信息 前端控制器
 * </p>
 *
 * @author HouBinyang
 * @since 2023-04-06
 */
@RestController
@RequestMapping("/product-data")
public class ProductDataController {

    @Resource
    private IProductDataService productDataService;
    @Value("${file.upload-path}")
    private String avatar_url;

    @PostMapping("/apply_for_product_by_{shopId}")
    public Result apply_for_product(@RequestBody ApplyForProduct applyForProduct, @PathVariable Long shopId) {
        ProductData productData = productDataService.applyForProduct(applyForProduct, shopId);
        return Result.success(productData);
    }

    @PostMapping("/change_product")
    public Result changeProduct(@RequestBody ChangeProduct changeProduct) {
        ProductData productData = productDataService.changeProduct(changeProduct);
        return Result.success(productData);
    }

    @PostMapping
    public Boolean save(@RequestBody ProductData productData) {
        return productDataService.saveOrUpdate(productData);
    }

    @DeleteMapping("/delete_{id}")
    public Boolean delete(@PathVariable Long id) {
        return productDataService.removeById(id);
    }

    @DeleteMapping("/delete_image_{id}")
    public Boolean deleteImage(@PathVariable Long id) {
        return productDataService.deleteImage(id);
    }

    @GetMapping
    public List<ProductData> findAll() {
        return productDataService.list();
    }

    @GetMapping("/find_by_{shopId}")
    public Result findByShopId(@PathVariable Long shopId) {
        List<ReturnProductData> returnProductDataList = productDataService.findByShopId(shopId);
        return Result.success(returnProductDataList);
    }

    @GetMapping("/find_request_product")
    public Result findRequestProduct() {
        List<ReturnProductData> returnProductDataList = productDataService.findRequestProduct();
        return Result.success(returnProductDataList);
    }

    @GetMapping("/find_all_product_data")
    public Result findAllProduct() {
        List<ReturnProductData> returnProductDataList = productDataService.findAllProductData();
        return Result.success(returnProductDataList);
    }

    @GetMapping("/find_all_sell_product_data")
    public Result findAllSellProduct() {
        List<ReturnProductData> returnProductDataList = productDataService.findAllSellProductData();
        return Result.success(returnProductDataList);
    }

    @GetMapping("/find_shop_cart_by_{uid}")
    public Result findShopCartByUid(@PathVariable Long uid) {
        List<ReturnProductData> returnProductDataList = productDataService.findShopCartByUid(uid);
        return Result.success(returnProductDataList);
    }

    @PostMapping("/upload_image_{id}")
    public Result setImg(@PathVariable("id") Long id, @RequestParam MultipartFile file) throws IOException {
        List<String> returnProductDataList = productDataService.upload_image(id, file, avatar_url);
        return Result.success(returnProductDataList);
    }

    @PostMapping("/check_product_request")
    public Result check_product_request(@RequestBody ProductApplyPass productApplyPass) {
        ProductData productData = productDataService.check_product_request(productApplyPass);
        return Result.success(productData);
    }

    @GetMapping("/find_{id}")
    public ProductData findOne(@PathVariable BigInteger id) {
        return productDataService.getById(id);
    }
}
