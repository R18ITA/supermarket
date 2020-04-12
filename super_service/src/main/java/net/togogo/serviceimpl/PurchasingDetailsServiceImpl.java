package net.togogo.serviceimpl;

import net.togogo.entity.Purchasingdetail;
import net.togogo.entity.PurchasingdetailExample;
import net.togogo.mapper.PurchasingdetailMapper;
import net.togogo.service.PurchasingDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PurchasingDetailsServiceImpl implements PurchasingDetailsService {

    @Resource
    PurchasingdetailMapper purchasingdetailMapper;

    @Override
    public int addPurchasingDetail(Purchasingdetail purchasingdetail) {
        return purchasingdetailMapper.insert(purchasingdetail);
    }

    @Override
    public int deletePurchasingDetailByExample(PurchasingdetailExample purchasingdetailExample) {
        return purchasingdetailMapper.deleteByExample(purchasingdetailExample);
    }

    @Override
    public List<Purchasingdetail> selectPurchasingDetailsByExample(PurchasingdetailExample purchasingdetailExample) {
        return purchasingdetailMapper.selectByExample(purchasingdetailExample);
    }

    @Override
    public int updatePurchasingDetailsByExample(Purchasingdetail purchasingdetail,PurchasingdetailExample purchasingdetailExample) {
        return purchasingdetailMapper.updateByExample(purchasingdetail,purchasingdetailExample);
    }
}
