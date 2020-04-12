package net.togogo.mapper;

import java.util.List;
import net.togogo.entity.Purchasingline;
import net.togogo.entity.PurchasinglineExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface PurchasinglineMapper {
    long countByExample(PurchasinglineExample example);

    int deleteByExample(PurchasinglineExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Purchasingline record);

    int insertSelective(Purchasingline record);

    List<Purchasingline> selectByExample(PurchasinglineExample example);

    Purchasingline selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Purchasingline record, @Param("example") PurchasinglineExample example);

    int updateByExample(@Param("record") Purchasingline record, @Param("example") PurchasinglineExample example);

    int updateByPrimaryKeySelective(Purchasingline record);

    int updateByPrimaryKey(Purchasingline record);
}