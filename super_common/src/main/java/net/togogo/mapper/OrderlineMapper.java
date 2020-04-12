package net.togogo.mapper;

import java.util.List;
import net.togogo.entity.Orderline;
import net.togogo.entity.OrderlineExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface OrderlineMapper {
    long countByExample(OrderlineExample example);

    int deleteByExample(OrderlineExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Orderline record);

    int insertSelective(Orderline record);

    List<Orderline> selectByExample(OrderlineExample example);

    Orderline selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Orderline record, @Param("example") OrderlineExample example);

    int updateByExample(@Param("record") Orderline record, @Param("example") OrderlineExample example);

    int updateByPrimaryKeySelective(Orderline record);

    int updateByPrimaryKey(Orderline record);
}