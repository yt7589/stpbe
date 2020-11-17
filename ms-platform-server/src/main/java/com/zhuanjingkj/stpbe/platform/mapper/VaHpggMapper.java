package com.zhuanjingkj.stpbe.platform.mapper;

import com.zhuanjingkj.stpbe.platform.domain.VaHpgg;
import com.zhuanjingkj.stpbe.platform.domain.VaHpggExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VaHpggMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_va_hpgg
     *
     * @mbg.generated Tue Nov 17 19:02:08 CST 2020
     */
    long countByExample(VaHpggExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_va_hpgg
     *
     * @mbg.generated Tue Nov 17 19:02:08 CST 2020
     */
    int deleteByExample(VaHpggExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_va_hpgg
     *
     * @mbg.generated Tue Nov 17 19:02:08 CST 2020
     */
    int deleteByPrimaryKey(Integer vaHpggId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_va_hpgg
     *
     * @mbg.generated Tue Nov 17 19:02:08 CST 2020
     */
    int insert(VaHpgg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_va_hpgg
     *
     * @mbg.generated Tue Nov 17 19:02:08 CST 2020
     */
    int insertSelective(VaHpgg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_va_hpgg
     *
     * @mbg.generated Tue Nov 17 19:02:08 CST 2020
     */
    List<VaHpgg> selectByExample(VaHpggExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_va_hpgg
     *
     * @mbg.generated Tue Nov 17 19:02:08 CST 2020
     */
    VaHpgg selectByPrimaryKey(Integer vaHpggId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_va_hpgg
     *
     * @mbg.generated Tue Nov 17 19:02:08 CST 2020
     */
    int updateByExampleSelective(@Param("record") VaHpgg record, @Param("example") VaHpggExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_va_hpgg
     *
     * @mbg.generated Tue Nov 17 19:02:08 CST 2020
     */
    int updateByExample(@Param("record") VaHpgg record, @Param("example") VaHpggExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_va_hpgg
     *
     * @mbg.generated Tue Nov 17 19:02:08 CST 2020
     */
    int updateByPrimaryKeySelective(VaHpgg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_va_hpgg
     *
     * @mbg.generated Tue Nov 17 19:02:08 CST 2020
     */
    int updateByPrimaryKey(VaHpgg record);
}