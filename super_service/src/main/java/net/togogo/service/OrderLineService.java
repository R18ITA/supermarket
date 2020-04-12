package net.togogo.service;

import net.togogo.entity.Orderline;
import net.togogo.entity.OrderlineExample;

import java.util.List;

public interface OrderLineService {

    //添加订单
    int addOrderLine(Orderline orderline);

    //删除订单
    int deleteOrderLineByPK(Integer id);

    //查询订单
    List<Orderline> selectOrderLineByExample(OrderlineExample orderlineExample);

}
