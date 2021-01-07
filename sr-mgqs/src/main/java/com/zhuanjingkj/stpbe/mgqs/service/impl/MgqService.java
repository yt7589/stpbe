package com.zhuanjingkj.stpbe.mgqs.service.impl;

import com.zhuanjingkj.stpbe.common.BmyDao;
import com.zhuanjingkj.stpbe.common.mgq.MgqEngine;
import com.zhuanjingkj.stpbe.common.tvis.TvisUtil;
import com.zhuanjingkj.stpbe.data.dto.*;
import com.zhuanjingkj.stpbe.data.vo.VehicleCltzxlVo;
import com.zhuanjingkj.stpbe.data.vo.VehicleCxtzVo;
import com.zhuanjingkj.stpbe.data.vo.VehicleVo;
import com.zhuanjingkj.stpbe.data.vo.VehicleWztzVo;
import com.zhuanjingkj.stpbe.mgqs.service.IMgqService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import sun.awt.windows.ThemeReader;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class MgqService implements IMgqService {
    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;
    @Autowired
    private MongoTemplate mongoTemplate;
    private final static Logger logger = LoggerFactory.getLogger(MgqService.class);
    private AtomicInteger successImages = new AtomicInteger(0);
    private AtomicInteger errorImages = new AtomicInteger(0);

    @Override
    public ResultDTO<BaseDTO> importDclFds() {
        Thread thd = new Thread(()->{
            runImportDclFdsThread();
        });
        thd.start();
        ResultDTO<BaseDTO> dto = new ResultDTO<>();
        dto.setCode(0);
        dto.setMsg("开始建库 v0.0.2");
        return dto;
    }

    public void runImportDclFdsThread() {
        System.out.println("开始导入DCL全量数据集到Milvus中......");
        List<File> dsFiles = getFgvcDs();
        System.out.println("总文件数：" + dsFiles.size() + "!");
        List<Thread> thds = new ArrayList<>();
        Thread thd = null;
        for (int i = 0; i < 5; i++) {
            final List<File> dsf = dsFiles.subList(i * 1000000, (i + 1) * 1000000);
            thd = new Thread(() -> {
                processBatchDclFdsFiles(dsf);
            });
            thd.start();
            thds.add(thd);
        }
        for (Thread t : thds) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("^_^ OK ^_^");
    }

    private static int numFiles = 0;
    private static int errorNum = 0;
    private static int savedNum = 0;
    public void processBatchDclFdsFiles(List<File> dsFiles) {
        long cameraId = 101;
        String result = null;
        List<VehicleVo> vos = null;
        VehicleWztzVo vehicleWztzVo = null;
        VehicleCxtzVo vehicleCxtzVo = null;
        VehicleCltzxlVo vehicleCltzxlVo = null;
        String partitionTag = null;
        BrandDTO brandDTO = null;
        ModelDTO modelDTO = null;
        BmyDTO bmyDTO = null;
        int clpp = 0;
        int ppcx = 0;
        int cxnk = 0;
        int ppxhms = 0;
        for (File f : dsFiles) {
            Map<String, Object> map = new HashMap<>();
            map.put("GCXH", "111111");
            map.put("MRHPT", "test");
            map.put("HPHM", "test");
            map.put("MRHPT", "test");
            map.put("cameraId", "" + cameraId);
            map.put("TPMC", f.getName());
            result = TvisUtil.recognizeImageFile(map, f);
            if (result.equals(TvisUtil.ERROR_RESPONSE)) {
                //System.out.println("识别图片失败");
                errorNum++;
            } else {
                vos = TvisUtil.parseTvisJson(cameraId, result);
                for (VehicleVo vo : vos) {
                    vehicleWztzVo = vo.getVehicleWztzVo();
                    vehicleCxtzVo = vo.getVehicleCxtzVo();
                    vehicleCltzxlVo = vo.getVehicleCltzxlVo();
                    partitionTag = MgqEngine.getPartitionTag(
                            vehicleWztzVo.getPsfx(),
                            vehicleCxtzVo.getCllxflCode(),
                            vehicleCxtzVo.getCllxzflCode()
                    );
                    if (!vehicleWztzVo.getPsfx().equals("1")) {
                        System.out.println("############ pn=" + partitionTag + "! ########");
                        continue;
                    }
                    if (vehicleCxtzVo != null) {
                        brandDTO = BmyDao.getBrandDTOByCode(mongoTemplate, vehicleCxtzVo.getClppCode());
                        if (null == brandDTO) {
                            continue;
                        }
                        vehicleCxtzVo.setClpp(brandDTO.getBrandId());
                        modelDTO = BmyDao.getModelDTOByCode(mongoTemplate, vehicleCxtzVo.getPpcxCode());
                        if (null == modelDTO) {
                            continue;
                        }
                        vehicleCxtzVo.setPpcx(modelDTO.getModelId());
                        bmyDTO = BmyDao.getBmyDTOByCode(mongoTemplate, vehicleCxtzVo.getCxnkCode());
                        if (null == bmyDTO) {
                            continue;
                        }
                        vehicleCxtzVo.setCxnk(bmyDTO.getBmyId());
                        vehicleCxtzVo.setPpxhms(bmyDTO.getBmyId());
                        MgqEngine.insertRecord(redisTemplate, partitionTag, vo);
                        savedNum++;
                    }
                }
            }
            numFiles++;
            if (numFiles % 100 == 0) {
                System.out.println("已经处理完成" + numFiles + "个文件：识别错误：" + errorNum + "; 入库：" + savedNum + "！");
            }
        }
    }








    private List<Float> generateTzxl(String vecStr) {
        List<Float> tzxl = new ArrayList<>();
        String[] arrs = vecStr.split(",");
        System.out.println("arrs.length=" + arrs.length + "!!!!!!!!!!!!!!!!!");
        for (String item : arrs) {
            tzxl.add(Float.parseFloat(item));
        }
        System.out.println("tzxl.size=" + tzxl.size() + "!!!!!!!!!!!!!!!!!!");
        return tzxl;
    }






    private List<File> getFgvcDs() {
        List<File> fs = new ArrayList<>();
        String dsFn = "/media/ps/0A9AD66165F33762/yantao/dcl/datasets/CUB_200_2011/anno/sfds_train_ds_20201020.txt";
        //String dsFn = "d:/awork/work/sfds_train_ds_20201020.txt";
        try {
            FileInputStream fis = new FileInputStream(new File(dsFn));
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            String[] arrs = null;
            int loop = 0;
            while ((line = br.readLine()) != null) {
                arrs = line.split("\\*");
                fs.add(new File(arrs[0]));
                loop++;
                if (loop % 1000000 == 0) {
                    logger.info("已处理：" + loop + "条记录！");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fs;
    }




    public void runExcemple() {
        /*System.out.println("Begin running in new thread ......");
        String partitionTag = MgqEngine.PN_HEAD_TRUCK;
        int seq = 1;
        List<Float> tzxl_1 = insertOneReIdVec(partitionTag, seq++);
        System.out.println("插入第1条记录......");
        List<Float> tzxl_2 = insertOneReIdVec(partitionTag, seq++);
        System.out.println("插入第2条记录......");
        List<Float> tzxl_3 = insertOneReIdVec(partitionTag, seq++);
        System.out.println("插入第3条记录......");
        List<Float> tzxl_4 = insertOneReIdVec(partitionTag, seq++);
        System.out.println("插入第4条记录......");
        List<Float> tzxl_5 = insertOneReIdVec(partitionTag, seq++);
        System.out.println("插入第5条记录......");

        // 查询结果
        //MgqEngine mgqEngine = new MgqEngine();
        List<List<Float>> queryEmbedding = new ArrayList<>();
        queryEmbedding.add(tzxl_2);
        VehicleCxtzVo rstVo = mgqEngine.findTopK(partitionTag, queryEmbedding, 1);
        System.out.println("查询结果：" + rstVo.getPpcx() + "; " +
                rstVo.getPpxhms() + "; id=" + rstVo.getTzxlId() + "!");*/
    }

    private Map<String, Object> getReIDInfo(int seq) {
        Map<String, Object> infos = new HashMap<>();
        List<List<Float>> embeddings = new ArrayList<>();
        List<VehicleCxtzVo> vos = new ArrayList<>();
        // 定义车型特征值对象
        VehicleCxtzVo vo = new VehicleCxtzVo();
        vo.setCllxfl(seq);
        vo.setCllxflName("类型" + seq);
        vo.setCllxzfl(1000 + seq);
        vo.setCllxzflName("子类型" + seq);
        vo.setCsys(2000 + seq);
        vo.setCsysName("颜色" + seq);
        vo.setClpp(100000 + seq);
        vo.setClppName("品牌" + seq);
        vo.setPpcx(210000 + seq);
        vo.setPpcxName("车型" + seq);
        vo.setCxnk(220000 + seq);
        vo.setCxnkName("年款" + seq);
        vo.setPpxhms(300000 + seq);
        vo.setPpxhmsName("名称" + seq);
        infos.put("vo", vo);
        //
        List<Float> tzxl = createRandomTzxl();
        embeddings.add(tzxl);
        infos.put("tzxl", tzxl);
        return infos;
    }

    private List<Float> insertOneReIdVec(String partitionTag, int seq) {
        Map<String, Object> infos = null;
        //
        List<List<Float>> embeddings = new ArrayList<>();
        List<VehicleCxtzVo> vos = new ArrayList<>();
        List<Float> tzxl;
        VehicleCxtzVo vo = null;
        //
        infos = getReIDInfo(seq);
        vo = (VehicleCxtzVo)infos.get("vo");
        vos.add(vo);
        tzxl = (List<Float>)infos.get("tzxl");
        embeddings.add(tzxl);
        //MgqEngine mgqEngine = new MgqEngine();
        //mgqEngine.insertRecord(partitionTag, vo);
        return tzxl;
    }

    private List<Float> createRandomTzxl() {
        /*List<Float> tzxl = new ArrayList<>();
        Random random = new Random();
        for (int i=0; i<MgqEngine.REID_DIM; i++) {
            tzxl.add(random.nextFloat());
        }
        return tzxl;*/
        return null;
    }
}
