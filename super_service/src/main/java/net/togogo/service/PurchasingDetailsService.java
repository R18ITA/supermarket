package net.togogo.service;

import net.togogo.entity.Purchasingdetail;
import net.togogo.entity.PurchasingdetailExample;

import java.util.List;

public interface PurchasingDetailsService {

    int addPurchasingDetail(Purchasingdetail purchasingdetail);

    int deletePurchasingDetailByExample(PurchasingdetailExample purchasingdetailExample);

    List<Purchasingdetail> selectPurchasingDetailsByExample(PurchasingdetailExample purchasingdetailExample);

    int updatePurchasingDetailsByExample(Purchasingdetail purchasingdetail,PurchasingdetailExample purchasingdetailExample);


}
