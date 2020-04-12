package net.togogo.serviceimpl;

import net.togogo.entity.Kind;
import net.togogo.entity.KindExample;
import net.togogo.mapper.KindMapper;
import net.togogo.service.KindService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class KindServiceImpl implements KindService {

    @Resource
    KindMapper kindMapper;

    @Override
    public int addKind(Kind kind) {
        return kindMapper.insert(kind);
    }

    @Override
    public int deleteKindByPK(Integer id) {
        return kindMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Kind> selectKindByExample(KindExample kindExample) {
        List<Kind> kinds = kindMapper.selectByExample(kindExample);
        return kinds;
    }

    @Override
    public int updateKindByExample(Kind kind) {
        KindExample kindExample = new KindExample();
        KindExample.Criteria cri = kindExample.createCriteria();
        cri.andIdEqualTo(kind.getId());
        return kindMapper.updateByExample(kind,kindExample);
    }
}
