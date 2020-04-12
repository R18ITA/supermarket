package net.togogo.service;

import net.togogo.entity.Suppliers;
import net.togogo.entity.SuppliersExample;

import java.util.List;

public interface SuppliersService {

    //添加供应商
    int addSupplier(Suppliers supplier);

    //删除供应商
    int deleteSupplierByPK(Integer id);

    //查询供应商
    List<Suppliers> selectSupplierByExample(SuppliersExample suppliersExample);

    //修改供应商信息
    int updateSupplier(Suppliers supplier);
}
