package com.zhuanjingkj.stpbe.tvis_client;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
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
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SrTvisClientApplication {
    @Value("${app.output.mode}")
    private String appOutputMode;

    private final static Logger logger = LoggerFactory.getLogger(SrTvisClientApplication.class);
    private AtomicInteger errorImages = new AtomicInteger(0);
    private CloseableHttpClient httpclient = null;
    private LinkedBlockingQueue<FileWrap> filesQueue = new LinkedBlockingQueue<>(5000);

    private boolean sendName = false;
    private String url;
    private Integer nThread;
    private String picDir;
    private String outputDir;
    private String type;
    private int loop = 1;
    private File outputFilePath;

    private long totalImages = 0L;
    private AtomicInteger successImages = new AtomicInteger(0);

    private boolean finished = false;

    public static void main(String[] args) {
        System.out.println("Tvis Client v0.0.1");
        try {
            new SrTvisClientApplication().start(args);
        } catch (Exception ex) {
            logger.info("Exception: " + ex.getMessage() + "!");
        }
    }

    public SrTvisClientApplication() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(200);
        connectionManager.setDefaultMaxPerRoute(20);

        httpclient = HttpClients.custom().setConnectionManager(connectionManager).build();
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


    public void start(String[] args) throws InterruptedException {
        Map<String, String> params = parseParams(args);
        url = params.get("url");
        nThread = params.get("thread") == null ? null : Integer.valueOf(params.get("thread"));
        picDir = params.get("picDir");
        outputDir = params.get("outputDir");
        sendName = Boolean.valueOf(Optional.ofNullable(params.get("sendName")).orElse("false"));
        type = params.get("type");
        loop = Integer.valueOf(Optional.ofNullable(params.get("loop")).orElse("1"));

        if (url == null) {
            System.out.println("必须输入url");
            printUsage();
            return;
        }
        if (nThread == null || nThread < 1) {
            System.out.println("thread 必须大于0");
            printUsage();
            return;
        }
        if (picDir == null) {
            System.out.println("必须输入picDir");
            printUsage();
            return;
        }
        if (loop <= 0) {
            System.out.println("循环次数必须大于0");
            printUsage();
            return;
        }
        System.out.println(params);
        outputFilePath = outputDir == null ? null : new File(outputDir);
        if (outputFilePath != null) {
            outputFilePath.mkdirs();
        }
        if (nThread < 1) {
            System.out.println("线程数不能小于1");
            return;
        }

        // 从目录中读入
        //List<File> files = listFilesRecursively(new File(picDir));
        // 从数据集文件中读入
        //List<File> files = getFgvcDs();
        List<File> files = new ArrayList<>();
        files.add(new File("/media/ps/work/yantao/zjkj/i900m_cutted/tail/car/d00/d00/d08/d34/d24/CAF7180AC4_冀GE3691_02_130700100346_130700304263657649.jpg"));
        files.add(new File("/media/ps/work/yantao/zjkj/i900m_cutted/tail/car/d00/d00/d08/d34/d96/CJL7200J2A5_贵GPM006_02_520000102407_520000104400581127.jpg"));
        files.add(new File("/media/ps/work/yantao/zjkj/i900m_cutted/tail/car/d00/d00/d08/d07/d77/CC6461RM21_冀F790SS_02_130600101367_130600303890295798.jpg"));
        files.add(new File("/media/ps/work/yantao/zjkj/i900m_cutted/tail/car/d00/d00/d08/d24/d15/SVW71215EN_冀A02MR2_02_130100101649_130100308234190362.jpg"));
        files.add(new File("/media/ps/work/yantao/zjkj/i900m_cutted/tail/car/d00/d00/d08/d24/d38/SY7182HS_鲁F7356X_02_373000100395_373000002585262660.jpg"));
        logger.info("数据集文件数：" + files.size() + "!");
        if (files == null || files.size() == 0) {
            return;
        }

        errorImages.set(0);

        Date startTime = new Date();

        ExecutorService es = Executors.newFixedThreadPool(nThread + 1);
        System.out.println("正在执行，请稍后...");

        es.execute(() -> {
            produceImage(files, loop);
        });

        for (int i = 0; i < nThread; i++) {
            es.execute(() -> {
                consumeImage();
            });
        }

        es.shutdown();
        while (!es.awaitTermination(1, TimeUnit.SECONDS)) {
        }
        System.out.println("执行完成");

        Date endTime = new Date();

        /*
         * print result
         */
        long elapseTime = endTime.getTime() - startTime.getTime();
        long imagesPerSec = totalImages * 1000 / elapseTime;
        long successPerSec = successImages.get() * 1000 / elapseTime;
        System.out.println("开始时间:       " + DateFormatUtils.format(startTime, "yyyy-MM-dd HH:mm:ss"));
        System.out.println("结束时间:       " + DateFormatUtils.format(endTime, "yyyy-MM-dd HH:mm:ss"));
        System.out.println("经过时间:       " + elapseTime + "毫秒");
        System.out.println("总图片数量:      " + totalImages);
        System.out.println("错误图片数量:      " + errorImages.get());
        System.out.println("每秒处理图片:     " + imagesPerSec);
        System.out.println("成功返回结果数量:   " + successImages.get());
        System.out.println("每秒成功处理图片:   " + successPerSec);
    }

    private void produceImage(List<File> files, int loop) {
        for (int i = 0; i < loop; i++) {
            for (int j = 0; j < files.size(); j++) {
                File f = files.get(j);
                try {
                    while (!filesQueue.offer(new FileWrap(f, i), 1, TimeUnit.SECONDS)) {
                    }
                    totalImages += 1;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        finished = true;
    }

    private void consumeImage() {
        while (true) {
            try {
                FileWrap fw = filesQueue.poll(1, TimeUnit.SECONDS);
                if (fw != null) {
                    File f = fw.file;
                    int loopIndex = fw.loopIndex;
                    try {
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
                        if ("file".equals(type)) {
                            map.put("TPXX", f);
                            map.put("TPLX", "1");
                            response = postFile(url, map);//postFile(url, map);
//                            response = HttpUtil.post(url, map);
                        } else {
                            map.put("TPLX", "2");
                            map.put("TPXX", Base64.getEncoder().encodeToString(FileUtils.readFileToByteArray(f)));
                            response = postString(url, map);
//                            response = HttpUtil.post(url, map);
                        }
                        map.clear();
                        map = null;

                        if (isSuccessRequest(response)) {
                            successImages.incrementAndGet();
                        } else {
                            errorImages.incrementAndGet();
                            System.out.println("error image:" + f.getName());
                        }
                        logger.info("outputMode=" + appOutputMode + "!");
                        if (appOutputMode.equals("1")) {
                            if (outputFilePath != null) {
                                try (OutputStream fout = new FileOutputStream(outputFilePath.getAbsolutePath() + File.separator + f.getName() + "_" + loopIndex + ".json")) {
                                    if (response == null) {
                                        response = "no response message";
                                    }
                                    fout.write(response.getBytes("UTF-8"));
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                        } else {
                            if (response == null) {
                                response = "no response message";
                            } else {
                                // 解析JSON内容，调用Milvus保存到特征库中
                                logger.info("保存到Milvus特征库中");
                            }
                        }
                    } catch (Exception e) {
                        errorImages.incrementAndGet();
                        System.out.println("error image:" + f.getName());
                        e.printStackTrace();
                    }
                } else {
                    if (finished) {
                        return;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private String hutoolPost(String url, Map<String, Object> data) {
        HttpRequest request = null;
        HttpResponse response = null;
        try {
            request = HttpUtil.createPost(url).form(data);
            response = request.execute();
            return response.body();
        } finally {
//            if (request != null) {
//                request.getConnection().disconnect();
//            }
            if (response != null) {
                response.close();
            }
        }
    }

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


    private boolean isSuccessRequest(String response) {
        try {
            JSONObject json = JSONUtil.parseObj(response);
            Integer code = json.getInt("CODE");
            if (Integer.valueOf(1).equals(code)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    private List<File> listFilesRecursively(File dir) {
        List<File> fileList = new ArrayList<>();
        File[] files = dir.listFiles(pathname -> {
            if (pathname.isDirectory()) {
                return true;
            } else {
                return pathname.getName().toLowerCase().endsWith(".jpg");
            }
        });

        if (files != null && files.length > 0) {
            for (File f : files) {
                if (f.isDirectory()) {
                    fileList.addAll(listFilesRecursively(f));
                } else {
                    fileList.add(f);
                }
            }
        }
        return fileList;
    }

    private void printUsage() {
        StringBuilder sb = new StringBuilder();
        sb.append("Usage:   java -cp ./* com.ghh.vehicle.test.TestVehicle -url=http://localhost:8080/detect -thread=3 -picDir=/pic -outputDir=/output -loop=1 -sendName=true -type=file");
        sb.append("\nurl:           必须，接口地址");
        sb.append("\nthread:        必须，启动的线程数");
        sb.append("\npicDir:        必须，图片目录");
        sb.append("\noutputDir:     结果输出目录，不传则不输出文件");
        sb.append("\nloop:          循环次数，默认为1");
        sb.append("\nsendName:      是否发送图片名称，值为true|false, 默认为false");
        sb.append("\ntype:          发送图片方式: file, string（默认）");
        sb.append("\n\nExample: java -cp ./* com.ghh.vehicle.test.TestVehicle -url=http://localhost:8080/detect -thread=3 -picDir=/pic -outputDir=/output -loop=1 -sendName=false -type=file");
        System.out.println(sb);
    }

    private Map<String, String> parseParams(String[] args) {
        Map<String, String> params = new HashMap<>();
        if (args != null) {
            Pattern p = Pattern.compile("^-(.+?)=(.*)$");
            for (String arg : args) {
                Matcher m = p.matcher(arg);
                if (m.matches()) {
                    String paramName = m.group(1);
                    String paramValue = StringUtils.trim(m.group(2));
                    if (StringUtils.isNotBlank(paramValue)) {
                        params.put(paramName, paramValue);
                    }
                }
            }
        }
        return params;
    }






    private static class FileWrap {
        File file;
        int loopIndex;
        public FileWrap(File file, int loopIndex) {
            this.file = file;
            this.loopIndex = loopIndex;
        }
    }
}
