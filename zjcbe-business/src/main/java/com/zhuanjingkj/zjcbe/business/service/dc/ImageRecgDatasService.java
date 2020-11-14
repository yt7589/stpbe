package com.zhuanjingkj.zjcbe.business.service.dc;

import com.zhuanjingkj.zjcbe.business.dto.dc.QueryImageRecgDatasDTO;
import com.zhuanjingkj.zjcbe.business.dto.dc.QueryImageRecgDatasItemDTO;
import com.zhuanjingkj.zjcbe.domain.po.VehicleAnalysisWztzPO;
import com.zhuanjingkj.zjcbe.domain.po.VehicleImagesPO;
import com.zhuanjingkj.zjcbe.repository.account.AccountAuthTokenRepository;
import com.zhuanjingkj.zjcbe.repository.analysis.VehicleAnalysisWztzRepository;
import com.zhuanjingkj.zjcbe.repository.analysis.VehicleImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageRecgDatasService implements IImageRecgDatasService {
    @Autowired
    AccountAuthTokenRepository accountAuthTokenRepository;
    @Autowired
    VehicleImagesRepository vehicleImagesRepository;
    @Autowired
    VehicleAnalysisWztzRepository vehicleAnalysisWztzRepository;

    @Override
    public QueryImageRecgDatasDTO queryImageRecgDatas(
            String accessToken, String startTime, String endTime,
            int startIdx, int amount, int direction
    ) {
        long accountId = getAccountIdByAccessToken(accessToken);
        int totalNum = getImageRecgTotalNum(accountId, startTime, endTime);
        List<QueryImageRecgDatasItemDTO> items = getImageRecgDatas(
                accountId, startTime, endTime,
                startIdx, amount, direction
        );
        QueryImageRecgDatasDTO dto = new QueryImageRecgDatasDTO();
        dto.setTotalNum(totalNum);
        dto.setDueNum(amount);
        dto.setRealNum(items.size());
        dto.setItems(items);
        return dto;
    }

    private long getAccountIdByAccessToken(String accessToken) {
        return (long)accountAuthTokenRepository.getAccountAuthToken(accessToken).getAccountId();
    }

    private int getImageRecgTotalNum(long accountId, String startTime, String endTime) {
        return vehicleImagesRepository.getImageRecgTotalNum(accountId, startTime, endTime);
    }

    private List<QueryImageRecgDatasItemDTO> getImageRecgDatas(long accountId, String startTime,
                                  String endTime, int startIdx, int amount, int direction)
    {
        List<VehicleImagesPO> recs = vehicleImagesRepository.getImageRecgDatas(accountId, startTime, endTime, startIdx, amount, direction);
        System.out.println("recs: " + recs + "!");
        List<QueryImageRecgDatasItemDTO> items = new ArrayList<>();
        QueryImageRecgDatasItemDTO item = null;
        for (VehicleImagesPO rec: recs) {
            item = new QueryImageRecgDatasItemDTO();
            item.setImageId(rec.getImageId());
            item.setImageUrl(rec.getImageUrl());
            item.setUploadTime(rec.getUploadTime());
            VehicleAnalysisWztzPO po = null;
            try {
                po = vehicleAnalysisWztzRepository.getVehicleNumInImage(rec.getImageId());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            int vehicleNum = 0;
            if (po != null) {
                vehicleNum = po.getVehicleNum();
            }
            item.setVehicleNum(vehicleNum);
            items.add(item);
        }
        return items;
    }

    private QueryImageRecgDatasItemDTO temp1(int idx) {
        QueryImageRecgDatasItemDTO item =  new QueryImageRecgDatasItemDTO();
        item.setImageId("i00" + idx);
        item.setImageUrl("http://server/i00" + idx);
        item.setUploadTime("2199-10-09 15:35");
        item.setVehicleNum(1);
        return item;
    }
}
