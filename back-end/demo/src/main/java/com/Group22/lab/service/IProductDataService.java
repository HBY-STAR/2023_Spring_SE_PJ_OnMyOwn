package com.Group22.lab.service;

import com.Group22.lab.dto.ByAdmin.ProductApplyPass;
import com.Group22.lab.dto.BySeller.ApplyForProduct;
import com.Group22.lab.dto.BySeller.ChangeProduct;
import com.Group22.lab.dto.BySeller.ReturnProductData;
import com.Group22.lab.entity.ProductData;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 商品信息 服务类
 * </p>
 *
 * @author HouBinyang
 * @since 2023-04-06
 */
public interface IProductDataService extends IService<ProductData> {


    ProductData applyForProduct(ApplyForProduct applyForProduct, Long shopId);


    ProductData changeProduct(ChangeProduct changeProduct);

    List<ReturnProductData> findByShopId(Long shopId);

//    List<ProductData> findRequestProduct();

    ProductData check_product_request(ProductApplyPass productApplyPass);

    List<ReturnProductData> findRequestProduct();

    List<ReturnProductData> findAllProductData();

    List<String> upload_image(Long id, MultipartFile file, String avatarUrl) throws IOException;

    Boolean deleteImage(Long id);

    List<ReturnProductData> findAllSellProductData();

    List<ReturnProductData> findShopCartByUid(Long uid);
}
