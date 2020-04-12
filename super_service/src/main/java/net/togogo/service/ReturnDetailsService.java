package net.togogo.service;

import net.togogo.entity.Returndetail;
import net.togogo.entity.ReturndetailExample;

import java.util.List;

public interface ReturnDetailsService {

    //增加退货单详情
    int addReturnDetails(Returndetail returndetail);

    //删除退货单详情
    int deleteReturnDetailsByExample(ReturndetailExample returndetailExample);

    //查询退货单详情
    List<Returndetail> selectReturnDetailsByExample(ReturndetailExample returndetailExample);
}
