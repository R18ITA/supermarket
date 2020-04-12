package net.togogo.service;

import net.togogo.entity.Returnline;
import net.togogo.entity.ReturnlineExample;

import java.util.List;

public interface ReturnLineService {

    //增加退货单
    int addReturnLine(Returnline returnline);

    //删除退货单
    int deleteReturnLineByPK(Integer id);

    //查询退货单
    List<Returnline> selectReturnLineByExamole(ReturnlineExample returnlineExample);

    //修改退货单
    int updateReturnLineByExample(Returnline returnline);
}
