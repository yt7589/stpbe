package com.zhuanjingkj.zjcbe.utility.web.arron;

import com.zhuanjingkj.zjcbe.commondata.baseDTO.HttpResultDTO;
import com.zhuanjingkj.zjcbe.commondata.enums.HttpResponseStatus;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: wangliying
 * @create: 2018-06-14
 **/
public class HttpClientUtil {
    //默认采用的http协议的HttpClient对象
    private static HttpClient client4HTTP;

    //默认采用的https协议的HttpClient对象
    private static HttpClient client4HTTPS;

    private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    static {
        try {
            client4HTTP = HCB.custom().build();
            client4HTTPS = HCB.custom().ssl().build();
        } catch (HttpProcessException e) {
            logger.error("创建HttpClient对象出错：{}", e);
        }
    }

    /**
     * @Description:创建连接 判定是否开启连接池、及url是http还是https
     * 如果已开启连接池，则自动调用build方法，从连接池中获取client对象
     * 否则，直接返回相应的默认client对象
     * @param: [config]
     * @return: void
     * @Author: wangliying
     * @Date: 2018/6/15
     */
    private static void create(HttpConfig config) throws HttpProcessException {
        if (config.hcb() != null && config.hcb().isSetPool) { //如果设置了hcb对象，且配置了连接池，则直接从连接池取
            if (config.url().toLowerCase().startsWith("https://")) {
                config.client(config.hcb().ssl().build());
            } else {
                config.client(config.hcb().build());
            }
        } else {
            if (config.client() == null) {//如果为空，设为默认client对象
                if (config.url().toLowerCase().startsWith("https://")) {
                    config.client(client4HTTPS);
                } else {
                    config.client(client4HTTP);
                }
            }
        }
    }


    /**
     * @param client   client对象
     * @param url      资源地址
     * @param headers  请求头信息
     * @param context  http上下文，用于cookie操作
     * @param encoding 编码
     * @return 返回处理结果
     * @Description:以Get方式请求资源或服务 可以自定义参数
     * @Author: wangliying
     * @Date: 2018/6/14
     */
    public static HttpResultDTO get(HttpClient client, String url, Header[] headers, HttpContext context, String encoding) {
        return get(HttpConfig.custom().client(client).url(url).headers(headers).context(context).encoding(encoding));
    }

    /**
     * 以Get方式，请求资源或服务
     *
     * @param config 请求参数配置
     * @return
     * @Author: wangliying
     * @Date: 2018/6/14
     */
    public static HttpResultDTO get(HttpConfig config) {
        try {
            return send(config.method(HttpMethods.GET));

        } catch (HttpProcessException e) {
            e.printStackTrace();
            logger.error("http请求get资源失败：url " + config.url() + " 错误：" + e.getMessage());
            return new HttpResultDTO(HttpResponseStatus.UNKNOWN, 0, null);
        }

    }

    /**
     * 以Post方式，请求资源或服务
     *
     * @param client   client对象
     * @param url      资源地址
     * @param parasMap 请求参数
     * @param headers  请求头信息
     * @param context  http上下文，用于cookie操作
     * @param encoding 编码
     * @return 返回处理结果
     * @Author: wangliying
     * @Date: 2018/6/14
     */
    public static HttpResultDTO post(HttpClient client, String url, Header[] headers, Map<String, Object> parasMap, HttpContext context, String encoding) {
        return post(HttpConfig.custom().client(client).url(url).headers(headers).map(parasMap).context(context).encoding(encoding));
    }

    /**
     * 以Post方式，请求资源或服务
     *
     * @param config 请求参数配置
     * @return
     * @Author: wangliying
     * @Date: 2018/6/14
     */
    public static HttpResultDTO post(HttpConfig config) {
        try {
            return send(config.method(HttpMethods.POST));
        } catch (HttpProcessException e) {
            e.printStackTrace();
            logger.error("http请求post资源失败：url " + config.url() + " 错误：" + e.getMessage());
            return new HttpResultDTO(HttpResponseStatus.UNKNOWN, 0, null);
        }
    }

    //<editor-fold description="现在用不到的方法">

    /**
     * 以Put方式，请求资源或服务
     *
     * @param client   client对象
     * @param url      资源地址
     * @param parasMap 请求参数
     * @param headers  请求头信息
     * @param context  http上下文，用于cookie操作
     * @param encoding 编码
     * @return 返回处理结果
     * @throws HttpProcessException
     */
    public static HttpResultDTO put(HttpClient client, String url, Map<String, Object> parasMap, Header[] headers, HttpContext context, String encoding) throws HttpProcessException {
        return put(HttpConfig.custom().client(client).url(url).headers(headers).map(parasMap).context(context).encoding(encoding));
    }

    /**
     * 以Put方式，请求资源或服务
     *
     * @param config 请求参数配置
     * @return
     * @throws HttpProcessException
     */
    public static HttpResultDTO put(HttpConfig config) throws HttpProcessException {
        return send(config.method(HttpMethods.PUT));
    }

    /**
     * 以Delete方式，请求资源或服务
     *
     * @param client   client对象
     * @param url      资源地址
     * @param headers  请求头信息
     * @param context  http上下文，用于cookie操作
     * @param encoding 编码
     * @return 返回处理结果
     * @throws HttpProcessException
     */
    public static HttpResultDTO delete(HttpClient client, String url, Header[] headers, HttpContext context, String encoding) throws HttpProcessException {
        return delete(HttpConfig.custom().client(client).url(url).headers(headers).context(context).encoding(encoding));
    }

    /**
     * 以Delete方式，请求资源或服务
     *
     * @param config 请求参数配置
     * @return
     * @throws HttpProcessException
     */
    public static HttpResultDTO delete(HttpConfig config) throws HttpProcessException {
        return send(config.method(HttpMethods.DELETE));
    }

    /**
     * 以Patch方式，请求资源或服务
     *
     * @param client   client对象
     * @param url      资源地址
     * @param parasMap 请求参数
     * @param headers  请求头信息
     * @param context  http上下文，用于cookie操作
     * @param encoding 编码
     * @return 返回处理结果
     * @throws HttpProcessException
     */
    public static HttpResultDTO patch(HttpClient client, String url, Map<String, Object> parasMap, Header[] headers, HttpContext context, String encoding) throws HttpProcessException {
        return patch(HttpConfig.custom().client(client).url(url).headers(headers).map(parasMap).context(context).encoding(encoding));
    }

    /**
     * 以Patch方式，请求资源或服务
     *
     * @param config 请求参数配置
     * @return
     * @throws HttpProcessException
     */
    public static HttpResultDTO patch(HttpConfig config) throws HttpProcessException {
        return send(config.method(HttpMethods.PATCH));
    }

    /**
     * 以Head方式，请求资源或服务
     *
     * @param client   client对象
     * @param url      资源地址
     * @param headers  请求头信息
     * @param context  http上下文，用于cookie操作
     * @param encoding 编码
     * @return 返回处理结果
     * @throws HttpProcessException
     */
    public static HttpResultDTO head(HttpClient client, String url, Header[] headers, HttpContext context, String encoding) throws HttpProcessException {
        return head(HttpConfig.custom().client(client).url(url).headers(headers).context(context).encoding(encoding));
    }

    /**
     * 以Head方式，请求资源或服务
     *
     * @param config 请求参数配置
     * @return
     * @throws HttpProcessException
     */
    public static HttpResultDTO head(HttpConfig config) throws HttpProcessException {
        return send(config.method(HttpMethods.HEAD));
    }

    /**
     * 以Options方式，请求资源或服务
     *
     * @param client   client对象
     * @param url      资源地址
     * @param headers  请求头信息
     * @param context  http上下文，用于cookie操作
     * @param encoding 编码
     * @return 返回处理结果
     * @throws HttpProcessException
     */
    public static HttpResultDTO options(HttpClient client, String url, Header[] headers, HttpContext context, String encoding) throws HttpProcessException {
        return options(HttpConfig.custom().client(client).url(url).headers(headers).context(context).encoding(encoding));
    }

    /**
     * 以Options方式，请求资源或服务
     *
     * @param config 请求参数配置
     * @return
     * @throws HttpProcessException
     */
    public static HttpResultDTO options(HttpConfig config) throws HttpProcessException {
        return send(config.method(HttpMethods.OPTIONS));
    }

    /**
     * 以Trace方式，请求资源或服务
     *
     * @param client   client对象
     * @param url      资源地址
     * @param headers  请求头信息
     * @param context  http上下文，用于cookie操作
     * @param encoding 编码
     * @return 返回处理结果
     * @throws HttpProcessException
     */
    public static HttpResultDTO trace(HttpClient client, String url, Header[] headers, HttpContext context, String encoding) throws HttpProcessException {
        return trace(HttpConfig.custom().client(client).url(url).headers(headers).context(context).encoding(encoding));
    }

    /**
     * 以Trace方式，请求资源或服务
     *
     * @param config 请求参数配置
     * @return
     * @throws HttpProcessException
     */
    public static HttpResultDTO trace(HttpConfig config) throws HttpProcessException {
        return send(config.method(HttpMethods.TRACE));
    }

    /**
     * 下载文件
     *
     * @param client  client对象
     * @param url     资源地址
     * @param headers 请求头信息
     * @param context http上下文，用于cookie操作
     * @param out     输出流
     * @return 返回处理结果
     * @throws HttpProcessException
     */
    public static OutputStream down(HttpClient client, String url, Header[] headers, HttpContext context, OutputStream out) throws HttpProcessException {
        return down(HttpConfig.custom().client(client).url(url).headers(headers).context(context).out(out));
    }

    /**
     * 下载文件
     *
     * @param config 请求参数配置
     * @return 返回处理结果
     * @throws HttpProcessException
     */
    public static OutputStream down(HttpConfig config) throws HttpProcessException {
        return fmt2Stream(execute(config.method(HttpMethods.GET)), config.out());
    }

    /**
     * 上传文件
     *
     * @param client  client对象
     * @param url     资源地址
     * @param headers 请求头信息
     * @param context http上下文，用于cookie操作
     * @return 返回处理结果
     * @throws HttpProcessException
     */
    public static HttpResultDTO upload(HttpClient client, String url, Header[] headers, HttpContext context) throws HttpProcessException {
        return upload(HttpConfig.custom().client(client).url(url).headers(headers).context(context));
    }

    /**
     * 上传文件
     *
     * @param config 请求参数配置
     * @return 返回处理结果
     * @throws HttpProcessException
     */
    public static HttpResultDTO upload(HttpConfig config) throws HttpProcessException {
        if (config.method() != HttpMethods.POST && config.method() != HttpMethods.PUT) {
            config.method(HttpMethods.POST);
        }
        return send(config);
    }

    //</editor-fold>

    /**
     * 请求资源或服务
     *
     * @param config
     * @return
     * @throws HttpProcessException
     * @Author: wangliying
     * @Date: 2018/6/14
     */
    public static HttpResultDTO send(HttpConfig config) throws HttpProcessException {
        return fmt2Result(execute(config), config.outenc());
    }

    /**
     * 请求资源或服务
     *
     * @return 返回http处理结果
     * @throws HttpProcessException
     * @Author: wangliying
     * @Date: 2018/6/14
     */
    private static HttpResponse execute(HttpConfig config) throws HttpProcessException {
        create(config);//获取链接
        HttpResponse resp = null;
        try {
            //创建请求对象
            HttpRequestBase request = getRequest(config.url(), config.method());

            //设置header信息
            request.setHeaders(config.headers());

            //判断是否支持设置entity(仅HttpPost、HttpPut、HttpPatch支持)
            if (HttpEntityEnclosingRequestBase.class.isAssignableFrom(request.getClass())) {
                List<NameValuePair> nvps = new ArrayList<NameValuePair>();

                //检测url中是否存在参数
                config.url(Utils.checkHasParas(config.url(), nvps, config.inenc()));

                //装填参数
                HttpEntity entity = Utils.map2HttpEntity(nvps, config.map(), config.inenc());

                //设置参数到请求对象中
                ((HttpEntityEnclosingRequestBase) request).setEntity(entity);

                logger.info("请求地址：" + config.url());
                if (nvps.size() > 0) {
                    logger.info("请求参数：" + nvps.toString());
                }
            } else {
                try {
                    if (config.map() != null && !config.map().isEmpty()) {
                        config.url(Utils.checkHasParas(config.url(), config.map(), config.inenc()));
                        request.setURI(new URI(config.url()));
                    }
                    int idx = config.url().indexOf("?");
                    logger.info("请求地址：" + config.url().substring(0, (idx > 0 ? idx : config.url().length())));
                    if (idx > 0) {
                        logger.info("请求参数：" + config.url().substring(idx + 1));
                    }
                } catch (Exception ex) {
                    throw new HttpProcessException(ex);
                }
            }
            //执行请求操作，并拿到结果（同步阻塞）
            resp = (config.context() == null) ? config.client().execute(request) : config.client().execute(request, config.context());

            if (config.isReturnRespHeaders()) {
                //获取所有response的header信息
                config.headers(resp.getAllHeaders());
            }

            //获取结果实体
            return resp;

        } catch (IOException e) {
            throw new HttpProcessException(e);
        }
    }

    /**
     * 请求结果转化为HttpResultDTO
     *
     * @param resp     HttpResponse对象
     * @param encoding 编码
     * @return HttpResultDTO
     * @throws HttpProcessException
     * @Author: wangliying
     * @Date: 2018/6/14
     */
    private static HttpResultDTO fmt2Result(HttpResponse resp, String encoding) throws HttpProcessException {
        String body = "";
        try {

            int statusCode = resp.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                return new HttpResultDTO(HttpResponseStatus.FAIL, statusCode, EntityUtils.toString(resp.getEntity()));
            }

            if (resp.getEntity() != null) {
                // 按指定编码转换结果实体为String类型
                body = EntityUtils.toString(resp.getEntity(), encoding);
                logger.info(body);
            }
            EntityUtils.consume(resp.getEntity());
            return new HttpResultDTO(HttpResponseStatus.SUCCESS, statusCode, body);

        } catch (IOException e) {
            throw new HttpProcessException(e);
        } finally {
            close(resp);
        }
    }

    /**
     * 转化为流
     *
     * @param out 输出流
     * @return
     * @throws HttpProcessException
     * @Author: wangliying
     * @Date: 2018/6/14
     */
    public static OutputStream fmt2Stream(HttpResponse resp, OutputStream out) throws HttpProcessException {
        try {
            resp.getEntity().writeTo(out);
            EntityUtils.consume(resp.getEntity());
        } catch (IOException e) {
            throw new HttpProcessException(e);
        } finally {
            close(resp);
        }
        return out;
    }

    /**
     * 根据请求方法名，获取request对象
     *
     * @param url    资源地址
     * @param method 请求方式
     * @return
     * @Author: wangliying
     * @Date: 2018/6/14
     */
    private static HttpRequestBase getRequest(String url, HttpMethods method) {
        HttpRequestBase request = null;
        switch (method.getCode()) {
            case 0:// HttpGet
                request = new HttpGet(url);
                break;
            case 1:// HttpPost
                request = new HttpPost(url);
                break;
            case 2:// HttpHead
                request = new HttpHead(url);
                break;
            case 3:// HttpPut
                request = new HttpPut(url);
                break;
            case 4:// HttpDelete
                request = new HttpDelete(url);
                break;
            case 5:// HttpTrace
                request = new HttpTrace(url);
                break;
            case 6:// HttpPatch
                request = new HttpPatch(url);
                break;
            case 7:// HttpOptions
                request = new HttpOptions(url);
                break;
            default:
                request = new HttpPost(url);
                break;
        }
        return request;
    }

    /**
     * 尝试关闭response
     *
     * @param resp HttpResponse对象
     */
    private static void close(HttpResponse resp) {
        try {
            if (resp == null) {
                return;
            }
            //如果CloseableHttpResponse 是resp的父类，则支持关闭
            if (CloseableHttpResponse.class.isAssignableFrom(resp.getClass())) {
                ((CloseableHttpResponse) resp).close();
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
