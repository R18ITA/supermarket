package net.togogo.service;

import net.togogo.entity.Orderdetails;
import net.togogo.entity.OrderdetailsExample;

import java.util.List;

public interface OrderDetailsService {

    //增加订单详情记录
    int addOrderdetails(Orderdetails orderdetails);

    //删除订单记录
    int deleteOrderdetailsByExample(OrderdetailsExample orderdetailsExample);

    //查询订单记录
    List<Orderdetails> selectOrderdetailsByExample(OrderdetailsExample orderdetailsExample);
}
