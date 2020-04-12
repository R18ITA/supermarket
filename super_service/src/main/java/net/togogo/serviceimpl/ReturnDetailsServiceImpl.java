package net.togogo.serviceimpl;

import net.togogo.entity.Returndetail;
import net.togogo.entity.ReturndetailExample;
import net.togogo.mapper.ReturndetailMapper;
import net.togogo.service.ReturnDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ReturnDetailsServiceImpl implements ReturnDetailsService {

    @Resource
    ReturndetailMapper returndetailMapper;


    @Override
    public int addReturnDetails(Returndetail returndetail) {
        return returndetailMapper.insert(returndetail);
    }

    @Override
    public int deleteReturnDetailsByExample(ReturndetailExample returndetailExample) {
        return returndetailMapper.deleteByExample(returndetailExample);
    }

    @Override
    public List<Returndetail> selectReturnDetailsByExample(ReturndetailExample returndetailExample) {
        return returndetailMapper.selectByExample(returndetailExample);
    }
}
