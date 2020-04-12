package net.togogo.mapper;

import java.util.List;
import net.togogo.entity.Suppliers;
import net.togogo.entity.SuppliersExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface SuppliersMapper {
    long countByExample(SuppliersExample example);

    int deleteByExample(SuppliersExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Suppliers record);

    int insertSelective(Suppliers record);

    List<Suppliers> selectByExample(SuppliersExample example);

    Suppliers selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Suppliers record, @Param("example") SuppliersExample example);

    int updateByExample(@Param("record") Suppliers record, @Param("example") SuppliersExample example);

    int updateByPrimaryKeySelective(Suppliers record);

    int updateByPrimaryKey(Suppliers record);
}