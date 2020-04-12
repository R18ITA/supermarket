package net.togogo.mapper;

import java.util.List;
import net.togogo.entity.Returndetail;
import net.togogo.entity.ReturndetailExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface ReturndetailMapper {
    long countByExample(ReturndetailExample example);

    int deleteByExample(ReturndetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Returndetail record);

    int insertSelective(Returndetail record);

    List<Returndetail> selectByExample(ReturndetailExample example);

    Returndetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Returndetail record, @Param("example") ReturndetailExample example);

    int updateByExample(@Param("record") Returndetail record, @Param("example") ReturndetailExample example);

    int updateByPrimaryKeySelective(Returndetail record);

    int updateByPrimaryKey(Returndetail record);
}