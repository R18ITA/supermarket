package net.togogo.serviceimpl;

import net.togogo.entity.Orderline;
import net.togogo.entity.OrderlineExample;
import net.togogo.mapper.OrderlineMapper;
import net.togogo.service.OrderLineService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderLineServiceImpl implements OrderLineService {
    @Resource
    OrderlineMapper orderlineMapper;

    @Override
    public int addOrderLine(Orderline orderline) {
        return orderlineMapper.insert(orderline);
    }

    @Override
    public int deleteOrderLineByPK(Integer id) {
        return orderlineMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Orderline> selectOrderLineByExample(OrderlineExample orderlineExample) {
        return orderlineMapper.selectByExample(orderlineExample);
    }
}
