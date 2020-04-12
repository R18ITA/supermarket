package net.togogo.serviceimpl;

import net.togogo.entity.Suppliers;
import net.togogo.entity.SuppliersExample;
import net.togogo.mapper.SuppliersMapper;
import net.togogo.service.SuppliersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SuppliersServiceImpl implements SuppliersService {

    @Resource
    SuppliersMapper suppliersMapper;

    @Override
    public int addSupplier(Suppliers supplier) {
        return suppliersMapper.insert(supplier);
    }

    @Override
    public int deleteSupplierByPK(Integer id) {
        return suppliersMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Suppliers> selectSupplierByExample(SuppliersExample suppliersExample) {
        return suppliersMapper.selectByExample(suppliersExample);
    }

    @Override
    public int updateSupplier(Suppliers supplier) {
        SuppliersExample suppliersExample = new SuppliersExample();
        SuppliersExample.Criteria cri = suppliersExample.createCriteria();
        cri.andIdEqualTo(supplier.getId());
        return suppliersMapper.updateByExample(supplier,suppliersExample);
    }
}
