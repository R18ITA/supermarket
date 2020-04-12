package net.togogo.mapper;

import java.util.List;
import net.togogo.entity.Purchasingdetail;
import net.togogo.entity.PurchasingdetailExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface PurchasingdetailMapper {
    long countByExample(PurchasingdetailExample example);

    int deleteByExample(PurchasingdetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Purchasingdetail record);

    int insertSelective(Purchasingdetail record);

    List<Purchasingdetail> selectByExample(PurchasingdetailExample example);

    Purchasingdetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Purchasingdetail record, @Param("example") PurchasingdetailExample example);

    int updateByExample(@Param("record") Purchasingdetail record, @Param("example") PurchasingdetailExample example);

    int updateByPrimaryKeySelective(Purchasingdetail record);

    int updateByPrimaryKey(Purchasingdetail record);
}