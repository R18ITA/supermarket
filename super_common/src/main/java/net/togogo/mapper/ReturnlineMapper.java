package net.togogo.mapper;

import java.util.List;
import net.togogo.entity.Returnline;
import net.togogo.entity.ReturnlineExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface ReturnlineMapper {
    long countByExample(ReturnlineExample example);

    int deleteByExample(ReturnlineExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Returnline record);

    int insertSelective(Returnline record);

    List<Returnline> selectByExample(ReturnlineExample example);

    Returnline selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Returnline record, @Param("example") ReturnlineExample example);

    int updateByExample(@Param("record") Returnline record, @Param("example") ReturnlineExample example);

    int updateByPrimaryKeySelective(Returnline record);

    int updateByPrimaryKey(Returnline record);
}