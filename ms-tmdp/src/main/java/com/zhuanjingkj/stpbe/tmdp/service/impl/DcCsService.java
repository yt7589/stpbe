package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.dc.DcCsDTO;
import com.zhuanjingkj.stpbe.tmdp.service.IDcCsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DcCsService implements IDcCsService {
    @Override
    public ResultDTO<DbQrsDTO> queryVehicleByGraph(String cltzxl, String psfx,
                                                   String cllxfl, String cllxzfl,
                                                   String startDate, String endDate,
                                                   String startTime, String endTime) {
        ResultDTO<DbQrsDTO> dto = new ResultDTO<>();
        DbQrsDTO data = new DbQrsDTO(100,20,0,20,0,null);
        List<DcCsDTO> recs = new ArrayList<>();
        recs.add(new DcCsDTO(102,"v1.北京市海淀区西二旗","2020-12-01 15:11:26","http://222.128.117.234:8090/www/images/ytsc_001.jpg"));
        recs.add(new DcCsDTO(103,"v1.北京市海淀区上地","2020-12-02 15:11:26","http://222.128.117.234:8090/www/images/ytsc_002.jpg"));
        recs.add(new DcCsDTO(104,"v1.北京市海淀区西直门","2020-12-03 15:11:26","http://222.128.117.234:8090/www/images/ytsc_003.jpg"));
        recs.add(new DcCsDTO(105,"v1.北京市海淀区知春路","2020-12-04 15:11:26","http://222.128.117.234:8090/www/images/ytsc_004.jpg"));
        recs.add(new DcCsDTO(106,"v1.北京市朝阳区东湖区","2020-12-05 15:11:26","http://222.128.117.234:8090/www/images/ytsc_005.jpg"));
        recs.add(new DcCsDTO(107,"v1.北京市昌平区北七家","2020-12-06 15:11:26","http://222.128.117.234:8090/www/images/ytsc_006.jpg"));
        recs.add(new DcCsDTO(108,"v1.北京市望京","2020-12-07 15:11:26","http://222.128.117.234:8090/www/images/ytsc_007.jpg"));
        recs.add(new DcCsDTO(109,"v1.北京市海淀区回龙观","2020-12-08 15:11:26","http://222.128.117.234:8090/www/images/ytsc_008.jpg"));
        recs.add(new DcCsDTO(110,"v1.北京市海淀区龙泽","2020-12-09 15:11:26","http://222.128.117.234:8090/www/images/ytsc_009.jpg"));
        recs.add(new DcCsDTO(111,"v1.北京市海淀区西北旺","2020-12-10 15:11:26","http://222.128.117.234:8090/www/images/ytsc_010.jpg"));
        recs.add(new DcCsDTO(112,"v1.北京市海淀区霍营","2020-12-12 15:11:26","http://222.128.117.234:8090/www/images/ytsc_011.jpg"));
        recs.add(new DcCsDTO(113,"v1.北京市海淀区魏公村","2020-12-11 15:11:26","http://222.128.117.234:8090/www/images/ytsc_012.jpg"));
        data.setRecs(recs);
        dto.setData(data);
        return dto;
    }
}
