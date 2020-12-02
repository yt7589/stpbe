package com.zhuanjingkj.stpbe.mgqs.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.vo.VehicleCxtzVo;
import com.zhuanjingkj.stpbe.mgqs.mgq.MgqEngine;
import com.zhuanjingkj.stpbe.mgqs.service.IMgqService;
import org.apache.commons.io.FileUtils;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class MgqService implements IMgqService {
    @Autowired
    private MgqEngine mgqEngine;
    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;
    private final static Logger logger = LoggerFactory.getLogger(MgqService.class);
    private AtomicInteger successImages = new AtomicInteger(0);
    private AtomicInteger errorImages = new AtomicInteger(0);

    @Override
    public ResultDTO<BaseDTO> importDclFds() {
        //runExcemple();
        Thread thd = new Thread(()->{
            runImportDclFdsThread();
        });
        thd.start();
        ResultDTO<BaseDTO> dto = new ResultDTO<>();
        dto.setCode(0);
        dto.setMsg("开始建库");
        return dto;
    }

    public void runImportDclFdsThread() {
        System.out.println("开始导入DCL全量数据集到Milvus中......");
        List<File> dsFiles = getFgvcDs();
        System.out.println("总文件数：" + dsFiles.size() + "!");
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(200);
        connectionManager.setDefaultMaxPerRoute(20);
        httpclient = HttpClients.custom().setConnectionManager(connectionManager).build();
        String result = processImageFile(dsFiles.get(0));
        if (result.equals(ERROR_RESPONSE)) {
            System.out.println("识别图片失败");
        } else {
            System.out.println("识别结果：" + result + "!");
        }
    }

    private final static String ERROR_RESPONSE = "ERROR";
    public String processImageFile(File f) {
        System.out.println("处理文件：" + f);
        boolean sendName = true;
        String type = "file";
        String url = "http://192.168.2.68:6666/image/function/recognition";
        Map<String, Object> map = new HashMap<>();
        map.put("GCXH", "111111");
        map.put("MRHPT", "test");
        map.put("HPHM", "test");
        map.put("MRHPT", "test");
        map.put("cameraId", "101");
        if (sendName == true) {
            map.put("TPMC", f.getName());
        }

        String response = null;
        try {
            if ("file".equals(type)) {
                map.put("TPXX", f);
                map.put("TPLX", "1");
                response = postFile(url, map);//postFile(url, map);
            } else {
                map.put("TPLX", "2");
                map.put("TPXX", Base64.getEncoder().encodeToString(FileUtils.readFileToByteArray(f)));
                response = postString(url, map);
            }
            map.clear();
            map = null;
        } catch (IOException ex) {
            return ERROR_RESPONSE;
        }

        if (isSuccessRequest(response)) {
            successImages.incrementAndGet();
            return response;
        } else {
            errorImages.incrementAndGet();
            System.out.println("error image:" + f.getName());
            return ERROR_RESPONSE;
        }
    }


    private boolean isSuccessRequest(String response) {
        try {
            JSONObject json = JSONObject.parseObject(response); //JSONUtil.parseObj(response);
            Integer code = json.getIntValue("CODE"); //json.getInt("CODE");
            if (Integer.valueOf(1).equals(code)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    private CloseableHttpClient httpclient = null;
    private String postFile(String url, Map<String, Object> data) throws IOException {
        HttpPost post = new HttpPost(url);
        try {
//            post.addHeader("Connection", "close");
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            for (Map.Entry<String, Object> entry : data.entrySet()) {
                if (entry.getValue() instanceof File) {
                    builder.addBinaryBody(entry.getKey(), (File) entry.getValue());
                } else {
                    builder.addTextBody(entry.getKey(), String.valueOf(entry.getValue()));
                }
            }
            post.setEntity(builder.build());
            CloseableHttpResponse response = httpclient.execute(post);
            return EntityUtils.toString(response.getEntity());
        } finally {
//            post.releaseConnection();
        }
    }

    private String postString(String url, Map<String, Object> data) throws IOException {
        HttpPost post = new HttpPost(url);
        try {
//            post.addHeader("Connection", "close");
            List<BasicNameValuePair> pair = new ArrayList<>();
            for (Map.Entry<String, Object> entry : data.entrySet()) {
                pair.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
            }
            post.setEntity(new UrlEncodedFormEntity(pair));
            CloseableHttpResponse response = httpclient.execute(post);
            return EntityUtils.toString(response.getEntity());
        } finally {
//            post.releaseConnection();
        }
    }



    private List<File> getFgvcDs() {
        List<File> fs = new ArrayList<>();
        String dsFn = "/media/ps/0A9AD66165F33762/yantao/dcl/datasets/CUB_200_2011/anno/sfds_train_ds_20201020.txt";
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
        System.out.println("Begin running in new thread ......");
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
                rstVo.getPpxhms() + "; id=" + rstVo.getTzxlId() + "!");
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
        mgqEngine.insertRecord(partitionTag, vos, embeddings);
        return tzxl;
    }

    private List<Float> createRandomTzxl() {
        List<Float> tzxl = new ArrayList<>();
        Random random = new Random();
        for (int i=0; i<MgqEngine.REID_DIM; i++) {
            tzxl.add(random.nextFloat());
        }
        return tzxl;
    }
}
