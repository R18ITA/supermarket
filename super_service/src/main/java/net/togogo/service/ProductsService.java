package net.togogo.service;



import net.togogo.entity.Products;
import net.togogo.entity.ProductsExample;

import java.util.List;

public interface ProductsService {

    //添加商品
    int addProduct(Products products);

    //删除商品
    int deleteProductByPK(Integer id);

    //按条件搜索商品
    List<Products> selectByExample(ProductsExample productsExample);

    //修改
    int updateProduct(Products products);

}
