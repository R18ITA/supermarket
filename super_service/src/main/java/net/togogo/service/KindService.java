package net.togogo.service;


import net.togogo.entity.Kind;
import net.togogo.entity.KindExample;

import java.util.List;

public interface KindService {

    //增加种类
    int addKind(Kind kind);

    //删除种类
    int deleteKindByPK(Integer id);

    //查询种类
    List<Kind> selectKindByExample(KindExample kindExample);

    //修改种类
    int updateKindByExample(Kind kind);
}
