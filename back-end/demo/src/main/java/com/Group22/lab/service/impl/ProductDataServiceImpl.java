package com.Group22.lab.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.Group22.lab.common.Constants;
import com.Group22.lab.dto.ByAdmin.ProductApplyPass;
import com.Group22.lab.dto.BySeller.ApplyForProduct;
import com.Group22.lab.dto.BySeller.ChangeProduct;
import com.Group22.lab.dto.BySeller.ReturnProductData;
import com.Group22.lab.entity.ProductData;
import com.Group22.lab.entity.Shop.ShopData;
import com.Group22.lab.entity.User.UserAccount;
import com.Group22.lab.exception.ServiceException;
import com.Group22.lab.mapper.ProductDataMapper;
import com.Group22.lab.mapper.ShopDataMapper;
import com.Group22.lab.mapper.UserAccountMapper;
import com.Group22.lab.service.IProductDataService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author HouBinyang
 * @since 2023-04-06
 */
@Service
public class ProductDataServiceImpl extends ServiceImpl<ProductDataMapper, ProductData> implements IProductDataService {
    @Resource
    private UserAccountMapper userAccountMapper;
    @Resource
    private ShopDataMapper shopDataMapper;
    @Override
    public ProductData applyForProduct(ApplyForProduct applyForProduct, Long shopId) {
        if(!shopDataMapper.selectById(shopId).getShopState().equals("营业中")){
            throw new ServiceException(Constants.CODE_404, "商店暂未营业，不可申请上架商品");
        }
        else {
            ProductData productData = new ProductData();
            productData.setProductName(applyForProduct.getProductName());
            productData.setProductPrice(applyForProduct.getProductPrice());
            productData.setProductInformation(applyForProduct.getProductInformation());
            productData.setProductState("审核中");
            productData.setShopId(shopId);
            save(productData);
            return productData;
        }
    }

    @Override
    public ProductData changeProduct(ChangeProduct changeProduct) {
        QueryWrapper<ProductData> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id",changeProduct.getProductId());
        ProductData productData=getOne(queryWrapper);
        productData.setProductName(changeProduct.getProductName());
        productData.setProductPrice(changeProduct.getProductPrice());
        productData.setProductInformation(changeProduct.getProductInformation());
        productData.setProductState("审核中");
        saveOrUpdate(productData);
        return productData;
    }

    @Override
    public List<ReturnProductData> findByShopId(Long shopId) {
        QueryWrapper<ProductData> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("shop_id",shopId);
        return getReturnProductData(queryWrapper);
    }

    @Override
    public ProductData check_product_request(ProductApplyPass productApplyPass) {
        QueryWrapper<ProductData> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id",productApplyPass.getProductId());
        ProductData check_product = getOne(queryWrapper);
        if (check_product != null) {
            if(productApplyPass.getIsPass().equals("通过")){
                check_product.setProductState("上架中");
                updateById(check_product);
            }
            else{
                check_product.setProductState("未通过审核");
                updateById(check_product);
            }
            return check_product;
        } else {
            throw new ServiceException(Constants.CODE_404, "该商品ID不存在，请输入正确的商品ID");
        }
    }

    @Override
    public List<ReturnProductData> findRequestProduct() {
        QueryWrapper<ProductData> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_state","审核中");
        return getReturnProductData(queryWrapper);

    }

    @Override
    public List<ReturnProductData> findAllProductData() {
        return getReturnProductData(null);
    }

    @Override
    public List<String> upload_image(Long id, MultipartFile file, String avatarUrl) throws IOException {
        String fileName = file.getOriginalFilename();
        String type = FileUtil.extName(fileName);
        // 定义一个文件唯一的标识码
        String fileUUID = IdUtil.fastSimpleUUID() + StrUtil.DOT + type;
        File uploadFile = new File(avatarUrl + fileUUID);
        // 判断配置的文件目录是否存在，若不存在则创建一个新的文件目录
        // 数据库若不存在重复文件，则不删除刚才上传的文件
        String url = "http://localhost:8080/image/" +fileUUID;
        QueryWrapper<ProductData> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("product_id",id);
        ProductData productData = getOne(queryWrapper);
        String allUrl=productData.getProductImage();
        if(allUrl==null||allUrl.equals(" ")){
            allUrl=url.replaceAll(" ","");
            // 上传文件到磁盘
            file.transferTo(uploadFile);
        }
        else{
            if((allUrl.length()-allUrl.replaceAll("\n","").length())>=4){
                throw new ServiceException(Constants.CODE_405, "最多上传5张图片");
            }else{
                allUrl=allUrl+"\n"+url;
                // 上传文件到磁盘
                file.transferTo(uploadFile);
            }
        }
        productData.setProductImage(allUrl);
        productData.setProductState("审核中");
        saveOrUpdate(productData);
        return Arrays.asList(allUrl.split("\n"));
    }

    @Override
    public Boolean deleteImage(Long id) {
        QueryWrapper<ProductData> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id",id);
        ProductData productData = getOne(queryWrapper);
        if(productData.getProductImage()==null||productData.getProductImage().equals(" ")||productData.getProductImage().equals("")){
            return false;
        }else {
            String url=productData.getProductImage().replaceAll(" ","");
            List<String> oldUrlList = Arrays.asList(url.split("\n"));
            for (String s : oldUrlList) {
                File deleteFile = new File("D:/2023_SE_lab_file/image/" + s.substring(28));
                if (deleteFile.exists()) {
                    deleteFile.delete();
                }
            }
            productData.setProductImage(" ");
            updateById(productData);
            return true;

        }
    }

    @Override
    public List<ReturnProductData> findAllSellProductData() {
        QueryWrapper<ProductData> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_state","上架中");
        return getReturnProductData(queryWrapper);
    }

    @Override
    public List<ReturnProductData> findShopCartByUid(Long uid) {
        UserAccount userAccount = userAccountMapper.selectById(uid);
        List<ReturnProductData> allProduct= new ArrayList<>();
        if(userAccount.getShopCart()==null||userAccount.getShopCart().equals(" ")||userAccount.getShopCart().equals("")){
            return null;
        }
        String [] allProductString = userAccount.getShopCart().replaceAll(" ","").split("\n");
        for (String s : allProductString) {
            if(s!=null&& !s.equals("") && !s.equals(" ")){
                if(getById(Long.parseLong(s.replaceAll(" ","")))!=null)
                {
                    allProduct.add(ProductToReturnProduct(getById(Long.parseLong(s.replaceAll(" ","")))));
                }
            }
        }
        return allProduct;
    }

    private List<ReturnProductData> getReturnProductData(QueryWrapper<ProductData> queryWrapper) {
        List<ReturnProductData> returnProductData = new ArrayList<>();
        List<ProductData> productDataList = list(queryWrapper);
        for (ProductData productData : productDataList) {
            returnProductData.add(ProductToReturnProduct(productData));
        }
        return returnProductData;
    }

    private ReturnProductData ProductToReturnProduct(ProductData productData){
        ReturnProductData temp = new ReturnProductData(productData.getProductId(), productData.getProductName(),null,
                productData.getProductPrice(), productData.getProductInformation(), productData.getProductState(), productData.getShopId());
        if(productData.getProductImage()!=null){
            temp.setProductImage(Arrays.asList(productData.getProductImage().replaceAll(" ","").split("\n")));
        }
        return temp;
    }
}
