package net.togogo.service;

import net.togogo.entity.Purchasingline;
import net.togogo.entity.PurchasinglineExample;

import java.util.List;

public interface PurchasingLineService {

    //增加进货单
    int addPurchasingLine(Purchasingline purchasingline);

    //删除进货单
    int deletePurchasingLineByPK(Integer id);

    //查询进货单
    List<Purchasingline> selectPurchasinglineByExample(PurchasinglineExample purchasinglineExample);

    //修改进货单
    int updatePurchasinglineByExample(Purchasingline purchasingline);
}
