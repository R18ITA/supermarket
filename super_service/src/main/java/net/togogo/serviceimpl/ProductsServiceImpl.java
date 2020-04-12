package net.togogo.serviceimpl;



import net.togogo.entity.Products;
import net.togogo.entity.ProductsExample;
import net.togogo.mapper.ProductsMapper;
import net.togogo.service.ProductsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductsServiceImpl implements ProductsService {

    @Resource
    ProductsMapper productsMapper;


    @Override
    public int addProduct(Products products) {
        return productsMapper.insert(products);
    }

    @Override
    public int deleteProductByPK(Integer id) {
        return productsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Products> selectByExample(ProductsExample productsExample) {
        List<Products> products = productsMapper.selectByExample(productsExample);
        return products;
    }

    @Override
    public int updateProduct(Products products) {
        ProductsExample productsExample = new ProductsExample();
        ProductsExample.Criteria cri = productsExample.createCriteria();
        cri.andIdEqualTo(products.getId());
        return productsMapper.updateByExample(products,productsExample);
    }
}
