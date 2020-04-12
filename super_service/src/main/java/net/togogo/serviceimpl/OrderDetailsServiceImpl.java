package net.togogo.serviceimpl;

import net.togogo.entity.Orderdetails;
import net.togogo.entity.OrderdetailsExample;
import net.togogo.mapper.OrderdetailsMapper;
import net.togogo.service.OrderDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

    @Resource
    OrderdetailsMapper orderdetailsMapper;

    @Override
    public int addOrderdetails(Orderdetails orderdetails) {
        return orderdetailsMapper.insert(orderdetails);
    }

    @Override
    public int deleteOrderdetailsByExample(OrderdetailsExample orderdetailsExample) {
        return orderdetailsMapper.deleteByExample(orderdetailsExample);
    }

    @Override
    public List<Orderdetails> selectOrderdetailsByExample(OrderdetailsExample orderdetailsExample) {
        return orderdetailsMapper.selectByExample(orderdetailsExample);
    }
}
