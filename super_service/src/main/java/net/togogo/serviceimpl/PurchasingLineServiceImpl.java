package net.togogo.serviceimpl;

import net.togogo.entity.Purchasingline;
import net.togogo.entity.PurchasinglineExample;
import net.togogo.mapper.PurchasinglineMapper;
import net.togogo.service.PurchasingLineService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PurchasingLineServiceImpl implements PurchasingLineService {

    @Resource
    PurchasinglineMapper purchasinglineMapper;


    @Override
    public int addPurchasingLine(Purchasingline purchasingline) {
        return purchasinglineMapper.insert(purchasingline);
    }

    @Override
    public int deletePurchasingLineByPK(Integer id) {
        return purchasinglineMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Purchasingline> selectPurchasinglineByExample(PurchasinglineExample purchasinglineExample) {
        return purchasinglineMapper.selectByExample(purchasinglineExample);
    }

    @Override
    public int updatePurchasinglineByExample(Purchasingline purchasingline) {
        PurchasinglineExample purchasinglineExample = new PurchasinglineExample();
        PurchasinglineExample.Criteria cri = purchasinglineExample.createCriteria();
        cri.andIdEqualTo(purchasingline.getId());
        return purchasinglineMapper.updateByExample(purchasingline,purchasinglineExample);
    }
}
