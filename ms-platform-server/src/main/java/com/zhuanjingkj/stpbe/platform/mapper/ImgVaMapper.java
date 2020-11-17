package com.zhuanjingkj.stpbe.platform.mapper;

import com.zhuanjingkj.stpbe.platform.domain.ImgVa;
import com.zhuanjingkj.stpbe.platform.domain.ImgVaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ImgVaMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_img_va
     *
     * @mbg.generated Tue Nov 17 19:02:08 CST 2020
     */
    long countByExample(ImgVaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_img_va
     *
     * @mbg.generated Tue Nov 17 19:02:08 CST 2020
     */
    int deleteByExample(ImgVaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_img_va
     *
     * @mbg.generated Tue Nov 17 19:02:08 CST 2020
     */
    int deleteByPrimaryKey(Long imgVaId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_img_va
     *
     * @mbg.generated Tue Nov 17 19:02:08 CST 2020
     */
    int insert(ImgVa record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_img_va
     *
     * @mbg.generated Tue Nov 17 19:02:08 CST 2020
     */
    int insertSelective(ImgVa record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_img_va
     *
     * @mbg.generated Tue Nov 17 19:02:08 CST 2020
     */
    List<ImgVa> selectByExample(ImgVaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_img_va
     *
     * @mbg.generated Tue Nov 17 19:02:08 CST 2020
     */
    ImgVa selectByPrimaryKey(Long imgVaId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_img_va
     *
     * @mbg.generated Tue Nov 17 19:02:08 CST 2020
     */
    int updateByExampleSelective(@Param("record") ImgVa record, @Param("example") ImgVaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_img_va
     *
     * @mbg.generated Tue Nov 17 19:02:08 CST 2020
     */
    int updateByExample(@Param("record") ImgVa record, @Param("example") ImgVaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_img_va
     *
     * @mbg.generated Tue Nov 17 19:02:08 CST 2020
     */
    int updateByPrimaryKeySelective(ImgVa record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_img_va
     *
     * @mbg.generated Tue Nov 17 19:02:08 CST 2020
     */
    int updateByPrimaryKey(ImgVa record);
}