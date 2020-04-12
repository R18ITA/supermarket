package net.togogo.serviceimpl;

import net.togogo.entity.Orderline;
import net.togogo.entity.OrderlineExample;
import net.togogo.entity.Returnline;
import net.togogo.entity.ReturnlineExample;
import net.togogo.mapper.ReturnlineMapper;
import net.togogo.service.OrderLineService;
import net.togogo.service.ReturnLineService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ReturnLineServiceImpl implements ReturnLineService {

    @Resource
    ReturnlineMapper returnlinemapper;


    @Override
    public int addReturnLine(Returnline returnline) {
        return returnlinemapper.insert(returnline);
    }

    @Override
    public int deleteReturnLineByPK(Integer id) {
        return returnlinemapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Returnline> selectReturnLineByExamole(ReturnlineExample returnlineExample) {
        return returnlinemapper.selectByExample(returnlineExample);
    }

    @Override
    public int updateReturnLineByExample(Returnline returnline) {

        ReturnlineExample returnlineExample = new ReturnlineExample();
        ReturnlineExample.Criteria cri = returnlineExample.createCriteria();
        cri.andIdEqualTo(returnline.getId());
        return returnlinemapper.updateByExample(returnline,returnlineExample);
    }
}
