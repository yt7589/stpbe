package com.zhuanjingkj.stpbe.tvis_server.service.impl;

import com.alibaba.fastjson.JSON;
import com.zhuanjingkj.stpbe.common.tvis.TvisUtil;
import com.zhuanjingkj.stpbe.tvis_server.service.ITvisImageRecogService;
import com.zhuanjingkj.stpbe.tvis_server.vo.TvisImageErrorResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TvisImageRecogService implements ITvisImageRecogService {
    private final static String LIST_CREATE_LIB = "create-lib-list";
    private final static String LIST_QUERY_LIB = "query-lib-list";
    private final static String LIST_UPDATE_LIB = "update-lib-list";
    private final static String LIST_VEHICLE_RECOGNITION = "vehicle-recognition-list";
    private final static String LIST_VEHICLE_COMPARE = "vehicle-compare-list";
    private final static String LIST_VEHICLE_COMPARE_IN_LIB = "vehicle-compare-in-lib-list";
    private final static int REQUEST_ID_LEN = 36;
    private final static int REQUEST_EXPIRED_TIME = 1 * 60 * 1000;
    private final static String MSG = "图片无法识别";
    private volatile int num = 0;

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Resource(name = "redisTemplate2")
    private RedisTemplate<String, byte[]> redisTemplate2;
    @Autowired
    private KafkaTemplate<Integer, String> kafkaTemplate;

    @Value("${result.timeout:10000}")
    private long timeout;

    private final static Logger logger = LoggerFactory.getLogger(TvisImageRecogService.class);

    @Override
    public Map<String, Object> createLib(String name) {
        String response = TvisUtil.sendStringRequest(redisTemplate, redisTemplate2, LIST_CREATE_LIB, name);
        return JSON.parseObject(response);
    }

    @Override
    public Map<String, Object> queryLib(String id) {
        String response = TvisUtil.sendStringRequest(redisTemplate, redisTemplate2, LIST_QUERY_LIB, id);
        return JSON.parseObject(response);
    }

    @Override
    public Map<String, Object> updateLib(Map<String, Object> params) {
        String response = TvisUtil.sendStringRequest(redisTemplate, redisTemplate2, LIST_UPDATE_LIB, JSON.toJSONString(params));
        return JSON.parseObject(response);
    }

    @Override
    public Map<String, Object> recognition(String cameraId, String gcxh, String mrhpt, String hphm, byte[] imageData) {
        String response = TvisUtil.sendByteRequest(redisTemplate, redisTemplate2, LIST_VEHICLE_RECOGNITION, imageData);
        if(StringUtils.equals(response,"0")){
            TvisImageErrorResponse responseError = new TvisImageErrorResponse(4,gcxh,MSG);
            return JSON.parseObject(JSON.toJSONString(responseError));
        }
        // 向Kafka的Topic发送请求
        StringBuilder msg = new StringBuilder("{\"cameraId\": \"" + cameraId + "\", \"json\": " + response + "}");
        kafkaTemplate.send("tvis", 0, msg.toString());
        kafkaTemplate.flush();
        return JSON.parseObject(response);
    }

    @Override
    public Map<String, Object> compareVehicle(String cltzxx1, String cltzxx2) {
        String response = TvisUtil.sendStringRequest(redisTemplate, redisTemplate2, LIST_VEHICLE_COMPARE, TvisUtil.joinParams(cltzxx1, cltzxx2));
        return JSON.parseObject(response);
    }

    @Override
    public Map<String, Object> compareInLib(String cltzxx, String kid, String xsdyz, String fydx, String ys) {
        String response = TvisUtil.sendStringRequest(redisTemplate, redisTemplate2, LIST_VEHICLE_COMPARE_IN_LIB, TvisUtil.joinParams(cltzxx, kid, xsdyz, fydx, ys));
        return JSON.parseObject(response);
    }









    /**
     * 生成西安临时静态识别结果
     * @return
     */
    private String generate_xi_an_rst() {
        StringBuffer resp = new StringBuffer();
        resp.append("{");
        resp.append("    \"JLS\":\"1\",\r\n");
        resp.append("        \"StreamID\":\"0\",\r\n");
        resp.append("        \"CODE\":\"1\",\r\n");
        resp.append("        \"VEH\":[\r\n");
        resp.append("    {\r\n");
        resp.append("        \"WZTZ\":{\"PSFX\":\"1\",\"CLWZ\":\"943,794,522,405\"},\r\n");
        resp.append("        \"SXH\":\"1\",\r\n");
        resp.append("            \"CXTZ\":{\"PPCX\":\"x02461\",\"CLLXZFL\":\"131\",\"PPXHMS\":\"宇通牌-客车-2017\",\"CSYS\":\"A\",\"CLPP\":\"1321\",\"PPXHKXD\":\"2\",\"CLLXFL\":\"13\",\"CXNK\":\"b39490\"},\r\n");
        resp.append("        \"CLTZXL\":\"0.028840,-0.071167,-0.001134,-0.072221,0.030946,-0.021063,0.057031,0.019361,-0.035320,-0.047715,-0.056667,0.095106,-0.038966,0.011341,-0.089435,-0.075907,-0.089273,-0.003564,-0.019118,-0.022278,-0.095430,-0.023250,0.042206,-0.004780,-0.078458,-0.059948,0.014339,-0.045123,-0.021792,-0.025599,-0.043462,0.135368,-0.064322,-0.038196,-0.045609,-0.024303,0.033214,-0.025032,0.042692,-0.047796,-0.073071,0.053305,-0.063471,-0.038804,0.045204,0.031513,-0.088949,0.069912,0.008425,-0.055411,-0.029690,0.005347,-0.048525,-0.060920,0.008263,-0.015716,-0.097779,0.022197,-0.006967,-0.020415,-0.002106,0.057922,-0.007615,-0.018308,-0.081537,-0.016850,-0.058651,-0.036009,-0.019118,0.085871,-0.037670,-0.041963,0.030784,-0.084332,-0.073800,0.040991,-0.063998,0.097212,-0.019847,0.001458,-0.025194,0.007696,-0.031351,-0.065254,-0.021873,-0.024627,-0.049983,0.140553,-0.018713,-0.030298,0.038237,-0.020253,-0.012152,-0.058449,0.061082,-0.033295,-0.098427,0.052333,0.192642,-0.053912,0.004942,-0.056991,-0.024303,-0.007615,-0.018389,0.011422,-0.041477,0.180491,-0.045204,-0.067481,-0.060029,-0.006643,-0.027543,-0.083440,-0.066874,-0.040748,-0.056221,0.014096,0.065456,0.012881,-0.017782,-0.044961,0.082630,-0.036860,0.005833,-0.051684,-0.013934,-0.091055,-0.084332,0.020010,-0.048525,-0.072423,-0.037184,-0.059178,0.171742,-0.052738,0.149464,-0.072504,-0.034510,-0.060920,0.261987,-0.046986,0.034753,0.013529,0.075015,0.064646,-0.130021,0.103612,-0.029852,-0.100615,0.078661,-0.001053,0.047472,-0.095673,0.005833,-0.022521,-0.040262,-0.084170,-0.051320,-0.060596,0.047472,-0.025356,-0.061892,-0.013205,-0.056869,-0.065861,-0.054682,-0.046500,-0.018875,-0.053953,-0.029407,-0.059137,-0.004213,-0.016364,-0.033174,-0.011746,-0.047391,-0.039533,-0.043827,-0.018713,-0.047148,-0.034591,0.193776,0.003888,-0.000810,0.043016,0.043340,-0.044556,0.012071,-0.099643,-0.055330,-0.012557,-0.028030,0.057274,0.028111,0.008911,-0.072909,-0.017579,0.056302,-0.058489,-0.082792,-0.054196,-0.026166,-0.162182,-0.014663,-0.041882,0.143469,0.022845,0.040667,-0.032323,0.103855,0.002268,-0.051360,0.065537,-0.002106,-0.033781,0.007453,-0.024789,0.078904,-0.054439,0.124189,0.009235,0.073071,-0.074489,-0.042976,-0.090893,-0.101749,0.037670,0.037994,-0.011990,-0.020739,0.046257,-0.028921,-0.090731,-0.052819,-0.012233,0.167529,0.026409,-0.067887,-0.038075,-0.044596,-0.056788,0.088787,-0.009640,-0.025599,-0.070074,-0.069952,-0.073800,-0.071937,-0.031513,-0.058611,-0.053467,-0.083440,-0.054925,0.021630,-0.015027\",\r\n");
        resp.append("            \"GXHTZ\":{\"DCJQS\":\"0_66\",\"CCZTW\":\"D_64_127,235,21,23\",\"GJ\":\"31_253,249,22,73\",\"CSZT\":\"\",\"XLJ\":\"\",\"BJ\":\"\",\"CSCH\":\"\",\"CSPS\":\"\",\"CSGH\":\"\",\"TC\":\"\"},\r\n");
        resp.append("        \"TRACK_ID\":\"-1\",\r\n");
        resp.append("            \"HPTZ\":{\"YWLSHP\":\"1\",\"HPZT\":\"1\",\"HPYS\":\"\",\"HPKXD\":\"\",\"MWHPKXD\":\"\",\"HPGG\":\"\",\"HPWZ\":\"\",\"HPZL\":\"\",\"HPHM\":\"\"},\r\n");
        resp.append("        \"JSXWTZ\":{\"ZJSKSJ\":\"0_96\",\"MTCBDTK\":\"\",\"ZJSBJAQD\":\"0_94\",\"ZJSDDH\":\"0_98\",\"FJSZYB\":\"0_97\",\"FJSBJAQD\":\"0_96\",\"ZJSCY\":\"0_94\",\"ZJSZYB\":\"0_94\"}\r\n");
        resp.append("    },\r\n");
        resp.append("    {\r\n");
        resp.append("        \"WZTZ\":{\"PSFX\":\"1\",\"CLWZ\":\"114,767,460,429\"},\r\n");
        resp.append("        \"SXH\":\"2\",\r\n");
        resp.append("            \"CXTZ\":{\"PPCX\":\"x02461\",\"CLLXZFL\":\"131\",\"PPXHMS\":\"宇通牌-客车-2014\",\"CSYS\":\"A\",\"CLPP\":\"1321\",\"PPXHKXD\":\"3\",\"CLLXFL\":\"13\",\"CXNK\":\"b39460\"},\r\n");
        resp.append("        \"CLTZXL\":\"-0.036628,-0.089813,-0.067649,-0.041747,-0.000623,0.018514,-0.017980,-0.064890,-0.079755,-0.080912,-0.111087,-0.054564,-0.038943,-0.055499,-0.024211,-0.072278,-0.019850,-0.049090,-0.043527,0.078152,-0.016823,0.005964,-0.053763,0.040500,-0.097557,-0.066536,0.098091,-0.013530,0.027772,-0.077084,-0.030665,0.088745,0.074859,-0.071654,-0.107437,-0.110464,-0.088478,0.026258,0.023766,0.048066,-0.072945,-0.067204,-0.043126,0.079488,-0.069073,-0.061685,-0.027772,0.121679,-0.007477,-0.082425,-0.084561,0.085985,0.000801,-0.038097,-0.019583,-0.011839,0.052161,-0.017357,0.060083,-0.003917,-0.020740,0.022342,-0.044951,0.006854,-0.135921,0.024033,-0.043527,-0.026080,-0.096133,-0.003026,0.001157,-0.038631,0.066225,-0.010592,-0.003738,0.037741,-0.083894,0.184610,-0.011037,-0.041747,-0.108238,-0.080734,-0.026926,-0.105212,0.051360,-0.063554,-0.031154,-0.063465,-0.016200,-0.057902,0.040589,-0.044328,-0.084205,-0.067515,-0.066403,-0.047176,-0.069340,-0.038898,-0.019227,-0.082069,-0.011750,-0.076105,-0.126219,-0.035738,0.009880,-0.027816,0.107971,0.008812,-0.038720,-0.039788,-0.013441,-0.103966,-0.001958,0.062753,-0.010147,0.010770,-0.060127,0.103254,0.049134,-0.065869,-0.066892,-0.050203,0.034893,-0.068584,0.045930,-0.030309,-0.033646,-0.047710,-0.018069,-0.078152,-0.027193,-0.007121,0.036673,-0.049802,-0.034092,0.021185,0.001513,0.073880,-0.031332,0.127020,0.006231,-0.008990,0.034715,0.066848,-0.030398,-0.026169,-0.031065,0.002937,0.118920,-0.039165,-0.043260,-0.108416,0.097023,0.041836,-0.051004,-0.049669,-0.002225,-0.038097,-0.063421,-0.001513,0.133518,-0.090569,0.015666,0.004184,-0.042815,0.063465,0.034448,0.023944,-0.037563,-0.054208,-0.037919,0.039076,0.021719,0.055454,-0.118475,-0.054831,0.090614,-0.042904,-0.049001,-0.038320,0.010414,-0.036762,-0.079131,0.050559,0.029285,0.085718,0.004807,0.052250,-0.071966,-0.067916,-0.042192,0.016823,0.027060,0.084294,-0.087054,0.004629,-0.014420,0.003471,-0.011394,0.060528,-0.053363,-0.081535,-0.037207,-0.212026,-0.051226,0.007744,0.027505,0.040767,0.009346,-0.095599,0.125151,-0.061685,0.052695,0.136811,0.056700,0.012640,-0.019494,-0.040233,-0.013530,-0.094085,-0.027238,-0.043171,0.078152,-0.022876,-0.069919,0.002581,0.019672,0.116694,-0.050870,-0.033112,0.046464,0.071832,-0.001068,0.069073,-0.049402,-0.053674,0.042103,0.169834,-0.050781,0.004451,-0.059504,-0.047221,-0.020651,-0.067026,0.103165,-0.079666,-0.029730,-0.071031,-0.048467,-0.015043,-0.075215,-0.104856,-0.093818,-0.070319,-0.100227,-0.031021\",\r\n");
        resp.append("            \"GXHTZ\":{\"DCJQS\":\"0_76\",\"CCZTW\":\"\",\"GJ\":\"\",\"CSZT\":\"\",\"XLJ\":\"\",\"BJ\":\"\",\"CSCH\":\"\",\"CSPS\":\"\",\"CSGH\":\"\",\"TC\":\"56_122,120,201,46\"},\r\n");
        resp.append("        \"TRACK_ID\":\"-1\",\r\n");
        resp.append("            \"HPTZ\":{\"YWLSHP\":\"0\",\"HPZT\":\"0\",\"HPYS\":\"\",\"HPKXD\":\"\",\"MWHPKXD\":\"\",\"HPGG\":\"\",\"HPWZ\":\"\",\"HPZL\":\"\",\"HPHM\":\"\"},\r\n");
        resp.append("        \"JSXWTZ\":{\"ZJSKSJ\":\"0_96\",\"MTCBDTK\":\"\",\"ZJSBJAQD\":\"0_94\",\"ZJSDDH\":\"0_98\",\"FJSZYB\":\"0_97\",\"FJSBJAQD\":\"0_96\",\"ZJSCY\":\"0_94\",\"ZJSZYB\":\"0_94\"}\r\n");
        resp.append("    }\r\n");
        resp.append("],\r\n");
        resp.append("    \"ImageUrl\":\"./image_save/0/00/00/00/00/00/20/45/20.jpg\",\r\n");
        resp.append("        \"GCXH\":\"111\",\r\n");
        resp.append("        \"TimeStamp\":\"169688436\"\r\n");
        resp.append("}");
        return resp.toString();
    }

    private String testData = "{\n" +
            "    \"iPicNum\": 1,\n" +
            "    \"iRecognitionTime\": 0,\n" +
            "    \"iPicResult\": [\n" +
            "        {\n" +
            "            \"iVehicleNum\": 1,\n" +
            "            \"iVehicleInfo\": [\n" +
            "                {\n" +
            "                    \"iVehicleRect\": {\n" +
            "                        \"iRight\": 2448,\n" +
            "                        \"iBottom\": 1261,\n" +
            "                        \"iLeft\": 1756,\n" +
            "                        \"iTop\": 470\n" +
            "                    },\n" +
            "                    \"iVehicleExpandRect\": {\n" +
            "                        \"iExpandTop\": 352,\n" +
            "                        \"iExpandBottom\": 1378,\n" +
            "                        \"iExpandLeft\": 1652,\n" +
            "                        \"iExpandRight\": 2550\n" +
            "                    },\n" +
            "                    \"iVehicleDIR\": 1,\n" +
            "                    \"iVehicleClass\": \"Car_SUV\",\n" +
            "                    \"iVehicleClassConf\": 52,\n" +
            "                    \"iVehicleColor1\": 8,\n" +
            "                    \"iVehicleColor2\": 8,\n" +
            "                    \"iVehicleTypeConf\": 41,\n" +
            "                    \"iVehicleType\": \"讴歌牌-TL-2010\",\n" +
            "                    \"iPlateFlag\": 1,\n" +
            "                    \"iPlateInfor\": {\n" +
            "                        \"iPlateType\": 1,\n" +
            "                        \"iPlateCharConfidence\": \"84,84,84,84,84,84,84,\",\n" +
            "                        \"iPlateConfidence\": 84,\n" +
            "                        \"iPlateColor\": 1,\n" +
            "                        \"iPlateCharNum\": 7,\n" +
            "                        \"iPlateNumber\": \"粤A2KX25\",\n" +
            "                        \"iPlateCharacterStyle\": 1\n" +
            "                    },\n" +
            "                    \"iPlateRect\": {\n" +
            "                        \"iRight\": 2212,\n" +
            "                        \"iBottom\": 1214,\n" +
            "                        \"iLeft\": 2070,\n" +
            "                        \"iTop\": 1182\n" +
            "                    },\n" +
            "                    \"iFeatureExtractionNum\": 256,\n" +
            "                    \"iFeatureExtractionValue\": \"-0.004736,-0.009117,0.017604,0.002847,-0.080279,0.091496,0.002455,0.072847,0.065320,-0.022563,-0.046338,-0.013162,0.012925,-0.028899,-0.091249,0.014141,-0.025421,-0.165194,-0.132798,0.008925,0.034332,0.024720,-0.057949,0.044616,-0.129394,-0.048126,0.036669,0.003969,0.043899,0.125122,-0.026973,0.124848,-0.040882,-0.016238,-0.021337,-0.039316,0.143754,0.116458,0.010822,-0.166337,0.036686,-0.022530,0.092644,-0.052196,0.006544,-0.056185,0.119146,0.082312,-0.021820,-0.040381,0.070140,-0.002210,-0.080928,0.082387,-0.029214,0.077987,0.033872,-0.061785,0.032423,-0.041847,0.027796,0.017719,0.011347,0.079459,-0.062602,-0.008496,-0.013449,0.073008,0.092493,-0.066574,-0.000637,0.018556,0.018427,-0.011739,0.004314,-0.036088,-0.052712,-0.064594,-0.045356,0.094697,-0.138997,0.016414,-0.148863,-0.039325,0.072336,-0.029754,0.010638,0.024258,0.065895,0.016312,-0.098799,0.053847,0.028108,0.133353,0.058159,-0.088962,0.001395,-0.043363,-0.073038,0.061902,-0.098152,-0.016723,-0.062046,-0.014858,-0.071603,-0.012566,0.050031,-0.002213,-0.161688,0.004262,0.035987,-0.059261,0.031346,-0.099031,0.052418,-0.015768,-0.066318,-0.001644,-0.007397,0.044578,0.130484,0.153578,0.002293,0.055464,0.091593,0.032596,0.069732,0.012127,0.127695,0.063163,0.021672,-0.021604,-0.035944,0.034970,0.048324,-0.081469,0.020646,-0.011381,-0.047976,-0.038407,0.184172,-0.040599,0.023476,-0.016375,0.079724,0.018155,-0.111196,-0.060977,-0.051530,-0.084692,0.026562,-0.024662,-0.066117,-0.036017,0.018425,0.041388,0.057794,0.062771,0.014153,0.042894,0.063835,-0.000578,0.011749,-0.073878,-0.005416,-0.005216,-0.017069,-0.017454,0.078932,0.046055,0.029811,-0.020678,-0.065994,-0.014385,-0.001107,-0.001906,-0.027139,-0.063589,-0.087001,0.004225,-0.042582,0.044921,-0.051274,-0.002426,-0.045963,-0.036237,-0.057392,0.018059,-0.097112,-0.083280,-0.027466,0.139984,0.097408,-0.126585,-0.072742,0.008992,-0.015604,0.001819,0.034195,-0.013239,-0.100957,0.009222,0.039799,-0.088831,-0.003210,0.000264,-0.060291,0.098869,-0.061370,0.021753,-0.088558,0.076435,-0.010493,-0.002137,0.024360,0.126405,0.084680,-0.002195,-0.071390,-0.027403,-0.060572,-0.010980,-0.031743,-0.031874,-0.078267,0.031621,0.006329,0.009685,-0.011428,0.034783,-0.144949,0.026394,-0.075646,-0.004863,-0.017877,-0.088773,0.085208,-0.042871,-0.020958,-0.001750,-0.060451,-0.071169,-0.063290,0.107815,-0.001819,-0.053126,-0.036327,0.066076,0.046151,-0.016531,-0.013850,0.015729,-0.035006,0.071369,-0.015158,-0.033368\",\n" +
            "                    \"iVehicleXWTZ\": \"8D\",\n" +
            "                    \"iXWTZFX\": [\n" +
            "                        {\n" +
            "                            \"iXWTZFXBottom\": 802,\n" +
            "                            \"iXWTZFXLeft\": 2137,\n" +
            "                            \"iXWTZFXConfdence\": 83,\n" +
            "                            \"iXWTZFXTop\": 733,\n" +
            "                            \"iXWTZFXRight\": 2286,\n" +
            "                            \"iXWTZFXType\": \"Safety_Belt\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"iXWTZFXBottom\": 724,\n" +
            "                            \"iXWTZFXLeft\": 1904,\n" +
            "                            \"iXWTZFXConfdence\": 56,\n" +
            "                            \"iXWTZFXTop\": 705,\n" +
            "                            \"iXWTZFXRight\": 1926,\n" +
            "                            \"iXWTZFXType\": \"Mark\"\n" +
            "                        }\n" +
            "                    ]\n" +
            "                }\n" +
            "            ]\n" +
            "        }\n" +
            "    ],\n" +
            "    \"iStatus\": 0\n" +
            "}";
}
