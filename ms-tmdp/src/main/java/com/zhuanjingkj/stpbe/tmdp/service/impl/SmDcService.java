package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.common.mapper.DcStMapper;
import com.zhuanjingkj.stpbe.common.mapper.SmDcMapper;
import com.zhuanjingkj.stpbe.data.dto.*;
import com.zhuanjingkj.stpbe.data.rto.sm.AddUserToSmRTO;
import com.zhuanjingkj.stpbe.data.rto.sm.DeleteUserFromSmRTO;
import com.zhuanjingkj.stpbe.data.dto.SmRoleDTO;
import com.zhuanjingkj.stpbe.data.rto.sm.UpdateUserInfoRTO;
import com.zhuanjingkj.stpbe.tmdp.service.ISmDcService;
import com.zhuanjingkj.stpbe.tmdp.util.DateUtil;
import com.zhuanjingkj.stpbe.tmdp.util.SHA1;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
public class SmDcService implements ISmDcService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private DcStMapper dcStMapper;

    @Autowired
    private SmDcMapper smDcMapper;

    @Override
    public ResultDTO<DbQrsDTO> getUsers_exp(Integer startIndex, Integer amount, Integer direction,
                                            String loginName, String userName, String phone) {
        ResultDTO dto = new ResultDTO();
        if(direction == 0) {
            startIndex = (startIndex - amount * 2) < 0 ? 0 : (startIndex - amount * 2);
        }
        List<SmUserDTO> recs = smDcMapper.getUsers(startIndex, amount, loginName, userName, phone);
        Integer count = smDcMapper.getUserCount();
        DbQrsDTO data = new DbQrsDTO(count,recs.size(),startIndex,amount,direction,recs);
        dto.setData(data);
        return dto;
    }

    @Override
    public ResultDTO<DbDeleteResultDTO> delUser_exp(DeleteUserFromSmRTO rto) {
        ResultDTO dto = new ResultDTO();
        Integer affectedRows = smDcMapper.delUser(rto.getUserId());
        DbDeleteResultDTO data = new DbDeleteResultDTO(affectedRows);
        dto.setData(data);
        return dto;
    }

    @Override
    public ResultDTO<DbInsertResultDTO> addUser_exp(AddUserToSmRTO rto) {
        ResultDTO dto = new ResultDTO();
        rto.setPwd(SHA1.getStr2Sha1(rto.getPwd()));
        Integer affectedRows = smDcMapper.addUser(rto);
        DbInsertResultDTO data = new DbInsertResultDTO(rto.getUserId(),affectedRows);
        dto.setData(data);
        return dto;
    }

    @Override
    public ResultDTO<DbDeleteResultDTO> uptUserInfo(UpdateUserInfoRTO rto) {
        ResultDTO<DbDeleteResultDTO> dto = new ResultDTO<>();
        if(StringUtils.isNotBlank(rto.getPwd())) {
            rto.setPwd(SHA1.getStr2Sha1(rto.getPwd()));
        }
        Integer affectedRows = smDcMapper.uptUserInfo(rto);
        DbDeleteResultDTO data = new DbDeleteResultDTO(affectedRows);
        dto.setData(data);
        return dto;
    }

    @Override
    public ResultDTO<DbQrsDTO> getRoles_exp(Integer startIndex, Integer amount, Integer direction) {
        ResultDTO dto = new ResultDTO();
        if(direction == 0) {
            startIndex = (startIndex - amount * 2) < 0 ? 0 : (startIndex - amount * 2);
        }
        List<SmRoleDTO> recs = smDcMapper.getRoles(startIndex, amount);
        Integer count = smDcMapper.getRoleCount();
        DbQrsDTO data = new DbQrsDTO(count,recs.size(),startIndex,amount,direction,recs);
        dto.setData(data);
        return dto;
    }

    @Override
    public ResultDTO<SmUserDTO> getUserInfo_exp(long userId) {
        ResultDTO dto = new ResultDTO();
        SmUserDTO smUserDTO = smDcMapper.getUserInfo(userId);
        dto.setData(smUserDTO);
        return dto;
    }

    @Override
    public ResultDTO<SmSysInfoDTO> getSysInfo_exp() {
        ResultDTO dto = new ResultDTO();
        SmSysInfoDTO sysInfoDTO = smDcMapper.getSysInfo();
        dto.setData(sysInfoDTO);
        return dto;
    }

    @Override
    public ResultDTO<DbInsertResultDTO> uptSysInfo_exp(MultipartFile file, String qyName, String sysName,
                                                       String qyIcp, String ownership) {
        String qyImgUrl = "";
        if(file == null  ||  file.isEmpty()) {
            System.out.println("文件为空");
            System.out.println("file >>>>" + file);
        } else {
            String fileName = "sys_" + System.currentTimeMillis() +".jpg"; //文件名
//            String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
            String filePath = "/home/ps/yantao/stp/imgs/"; // 上传后的路径
//            String filePath = "D://"; // 上传后的路径
//            fileName = UUID.randomUUID() + suffixName; // 新文件名
            File dest = new File(filePath + fileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
            qyImgUrl = "http://222.128.117.234:9003/imgs/" + fileName;
        }
        ResultDTO dto = new ResultDTO();
        Integer affectedRows =  smDcMapper.uptSysInfo(qyName, qyImgUrl, sysName, qyIcp, ownership);
        DbInsertResultDTO data = new DbInsertResultDTO(0,affectedRows);
        dto.setData(data);
        return dto;
    }

    /**
     * 每日0点需要清除的数据
     */
    @Override
    public void trkc() {
        redisTemplate.opsForValue().set("ks_svs_car", 0); //轿车
        redisTemplate.opsForValue().set("ks_svs_suv", 0); //SUV
        redisTemplate.opsForValue().set("ks_svs_mpv", 0); //MPV
        redisTemplate.opsForValue().set("ks_svs_van", 0); //面包车
        redisTemplate.opsForValue().set("ks_svs_tank_truck", 0); //罐式货车
        redisTemplate.opsForValue().set("ks_svs_normal_truck", 0); //普通货车
        redisTemplate.opsForValue().set("ks_svs_van_truck", 0); //箱式货车
        redisTemplate.opsForValue().set("ks_svs_slab_truck", 0); //栏板式货车
        redisTemplate.opsForValue().set("ks_svs_flat_truck", 0); //平板式货车
        redisTemplate.opsForValue().set("ks_svs_grate_truck", 0); //仓栅式货车
        redisTemplate.opsForValue().set("ks_svs_others", 0); //其他

        if(redisTemplate.hasKey("ks_svs_area")) { //大货车点位统计
            redisTemplate.delete("ks_svs_area");
        }

        if(redisTemplate.hasKey("ks_ksvrp_site")) { //大货车点位统计
            redisTemplate.delete("ks_ksvrp_site");
        }

        if(redisTemplate.hasKey("ks_ksvrp_vehicle")) {
            redisTemplate.delete("ks_ksvrp_vehicle");
            redisTemplate.opsForList().rightPushAll("ks_ksvrp_vehicle", 0,0,0,0,0,0,0,0,0,0,0,0);
        }

        if(redisTemplate.hasKey("ks_svs_area")){
            redisTemplate.delete("ks_svs_area");
        }

        if(redisTemplate.hasKey("ks_ksvrp_site")){
            redisTemplate.delete("ks_ksvrp_site");
        }

        if(redisTemplate.hasKey("ks_ksvrp_truck")) {
            redisTemplate.delete("ks_ksvrp_truck");
            redisTemplate.opsForList().rightPushAll("ks_ksvrp_truck", 0,0,0,0,0,0,0,0,0,0,0,0);
        }
        /**
         * 1.本日过车量清空前统计到本周过车量
         * 2.如果本周过车量列表大于6，要从右侧删除一个
         * 3.本周过车量 = 前6天过车量 + 本日过车量
         * 4.重置本日过车量
         */
        Integer vToday = (int)(redisTemplate.opsForValue().get("dk_htfs_today") == null ? 0 : redisTemplate.opsForValue().get("dk_htfs_today"));
        redisTemplate.opsForList().leftPush("dk_htfs_week", vToday);
        if(redisTemplate.opsForList().size("dk_htfs_week") > 6) {
            redisTemplate.opsForList().rightPop("dk_htfs_week");
        }
        redisTemplate.opsForValue().set("dk_htfs_today", 0); //首页本日过车量

        String tnVsTrend = "tn_vs_trend_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        if(!redisTemplate.hasKey("tn_vs_trend_" + tnVsTrend)) {
            redisTemplate.opsForList().rightPushAll(tnVsTrend, 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
        }
    }

    /**
     * 每月1号需要清除的数据
     */
    @Override
    public void mrkc() {
        redisTemplate.opsForValue().set("dcst_key_vehicle", 0); //重点监管车辆数量统计
        redisTemplate.opsForValue().set("dcst_key_truck", 0); //大货车数量统计
        redisTemplate.opsForValue().set("ks_lps_wp", 0); //无牌照
        redisTemplate.opsForValue().set("ks_lps_tp", 0); //套牌照
        redisTemplate.opsForValue().set("ks_lps_jp", 0); //假牌照
        redisTemplate.opsForValue().set("ks_lps_hpzd", 0); //号牌遮挡
        redisTemplate.opsForValue().set("dk_vt_car", 0); //轿车
        redisTemplate.opsForValue().set("dk_vt_suv", 0); //SUV
        redisTemplate.opsForValue().set("dk_vt_mpv", 0); //MPV
        redisTemplate.opsForValue().set("dk_vt_van", 0); //面包车
        redisTemplate.opsForValue().set("dk_vt_tank_truck", 0); //罐式货车
        redisTemplate.opsForValue().set("dk_vt_normal_truck", 0); //普通货车
        redisTemplate.opsForValue().set("dk_vt_others", 0); //其他
        redisTemplate.opsForValue().set("dkInternalNum", 0); //本市
        redisTemplate.opsForValue().set("dkExternalNum", 0); //外埠
        redisTemplate.opsForValue().set("dk_titf_2020123004", 0); //2020年12月30日4时过车量 需要按月批量删除所有key dk_titf_202012
        redisTemplate.opsForValue().set("dcst_vehicle_alerts", 0); //布控车辆出现报警次数
        redisTemplate.opsForValue().set("dcst_vehicle_total", 0); //数据中心车辆识别统计
        redisTemplate.opsForValue().set("dcst_category_0_total", 0); //本市车辆统计次数
        redisTemplate.opsForValue().set("dcst_category_1_total", 0); //外埠车辆统计次数
        redisTemplate.opsForValue().set("dk_htfs_month", 0); //首页本月过车量
        redisTemplate.opsForValue().set("dchp_vehicle_identification", 0); //车辆识别量
        redisTemplate.opsForValue().set("dchp_vehicle_violation", 0); //车辆违章数量
        redisTemplate.opsForValue().set("dchp_vehicle_0_violation", 0); //本市车辆违章数量
        redisTemplate.opsForValue().set("dchp_vehicle_1_violation", 0); //外埠车辆违章数量

        /**
         * list 类型的需要删除，然后重新初始化
         */

        if(redisTemplate.hasKey("dk_htfs_week")) {
            redisTemplate.delete("dk_htfs_week");
            redisTemplate.opsForList().rightPushAll("dk_htfs_week",0,0,0,0,0,0);
        }

        if(redisTemplate.hasKey("ks_rss_lsvs_list")) {  //路段监管监控动态列表
            redisTemplate.delete("ks_rss_lsvs_list");
            redisTemplate.opsForList().rightPushAll("ks_rss_lsvs_list","0");
        }

        if(redisTemplate.hasKey("ks_lps_time")) { // 车牌异常分时段统计
            redisTemplate.delete("ks_lps_time");
            redisTemplate.opsForList().rightPushAll("ks_lps_time", 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
        }

        if(redisTemplate.hasKey("ks_as_lsvs_list")) { //区域监管监控动态列表
            redisTemplate.delete("ks_as_lsvs_list");
            redisTemplate.opsForList().rightPushAll("ks_as_lsvs_list","0");
        }

        if(redisTemplate.hasKey("dk_vttf_lcar")) { //车辆类型流量(大型车)
            redisTemplate.delete("dk_vttf_lcar");
            redisTemplate.opsForList().rightPushAll("dk_vttf_lcar", 0,0,0,0,0,0,0,0,0,0,0,0);
        }

        if(redisTemplate.hasKey("dk_vttf_car")) { //车辆类型流量(中型车)
            redisTemplate.delete("dk_vttf_car");
            redisTemplate.opsForList().rightPushAll("dk_vttf_car", 0,0,0,0,0,0,0,0,0,0,0,0);
        }

        if(redisTemplate.hasKey("dk_vttf_scar")) { //车辆类型流量(小型车)
            redisTemplate.delete("dk_vttf_scar");
            redisTemplate.opsForList().rightPushAll("dk_vttf_scar", 0,0,0,0,0,0,0,0,0,0,0,0);
        }

        if(redisTemplate.hasKey("dk_rtvr_violation")) { //统计时段违章
            redisTemplate.delete("dk_rtvr_violation");
            redisTemplate.opsForList().rightPushAll("dk_rtvr_violation", 0,0,0,0,0,0,0,0,0,0,0,0);
        }

        if(redisTemplate.hasKey("ks_vs_dyn_list")) {
            redisTemplate.delete("ks_vs_dyn_list"); //车辆布控动态列表删除
            redisTemplate.opsForList().rightPushAll("ks_vs_dyn_list", 0 + "|" + 0);
        }

        if(redisTemplate.hasKey("ks_vs_ill_list")) {
            redisTemplate.delete("ks_vs_ill_list"); //车辆报警列表删除
            redisTemplate.opsForList().rightPushAll("ks_vs_ill_list", 0 + "|" + 0);
        }

        //hash
        if(redisTemplate.hasKey("ks_rss_lsvs_total")) { //路段监管动态车辆通过次数
            redisTemplate.delete("ks_rss_lsvs_total");
        }

        if(redisTemplate.hasKey("ks_lps_area")) { //车牌异常分区域统计
            redisTemplate.delete("ks_lps_area");
        }

        if(redisTemplate.hasKey("ks_as_lsvs_total")) { //区域监管动态车辆通过次数
            redisTemplate.delete("ks_as_lsvs_total");
        }

        if(redisTemplate.hasKey("ks_as_lsvs_time")) { //区域监管动态车辆最后一次通过时间
            redisTemplate.delete("ks_as_lsvs_time");
        }

        if(redisTemplate.hasKey("dk_tjrs_road")) { //首页拥堵路段过车统计
            redisTemplate.delete("dk_tjrs_road");
        }

        if(redisTemplate.hasKey("dk_dctf_area")) { //首页区县过车量
            redisTemplate.delete("dk_dctf_area");
        }

        if(redisTemplate.hasKey("ks_rss_lsvs_time")) {  //路段监管动态车辆最后一次通过时间
            redisTemplate.delete("ks_rss_lsvs_time");
        }

        if(redisTemplate.hasKey("ks_vs_dyn_total")) {
            redisTemplate.delete("ks_vs_dyn_total"); //车辆布控动态次数删除
        }

        if(redisTemplate.hasKey("ks_vs_dyn_time")) {
            redisTemplate.delete("ks_vs_dyn_time"); //车辆布控动态时间删除
        }

        if(redisTemplate.hasKey("ks_vs_ill_time")) {
            redisTemplate.delete("ks_vs_ill_time"); //车辆报警时间删除
        }

        if(redisTemplate.hasKey("ks_vs_ill_total")) {
            redisTemplate.delete("ks_vs_ill_total"); //车辆报警次数删除
        }


        //zset
        String date = DateUtil.countMonthYm1(-1);
        if(redisTemplate.hasKey("dcst_top7_site_" + date)) { //重点车辆点位排名TOP7
            redisTemplate.delete("dcst_top7_site_" + date);
        }

        if(redisTemplate.hasKey("dc_st_truck")) { //大货车点位统计
            redisTemplate.delete("dc_st_truck");
        }
        dcStMapper.deleteTifData();
    }

}
