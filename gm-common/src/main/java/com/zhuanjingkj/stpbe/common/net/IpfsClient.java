package com.zhuanjingkj.stpbe.common.net;

import com.zhuanjingkj.stpbe.common.AppConst;
import com.zhuanjingkj.stpbe.common.util.PropUtil;
import org.apache.http.impl.client.CloseableHttpClient;
import org.json.JSONObject;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class IpfsClient {
    /**
     * 将文件上传到IPFS文件系统中去，返回值是文件的hash值（如果成功的话），
     * 否则返回空值
     * @param fn
     * @return
     */
    public static Optional<String> uploadFile(String fn) {
        Optional<String> rst = Optional.empty();
        String fileHash = null;
        Map<String, File> params = new HashMap<>();
        params.put("arg", new File(fn));
        try {
            String resp = HttpUtil.postFile(PropUtil.getValue("IPFS_API_URL") + "add", params);
            JSONObject jo = new JSONObject(resp);
            fileHash = jo.getString("Hash");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
        }
        if (fileHash != null) {
            return Optional.of(fileHash);
        } else {
            return Optional.empty();
        }
    }

    public static boolean downloadFile(String fileHash, String dstFn) {
        URL url = null;
        URLConnection conn = null;
        InputStream ins = null;
        byte[] buf = new byte[1024];
        FileOutputStream fos = null;
        boolean rst = true;
        try {
            url = new URL(PropUtil.getValue("IPFS_GW_URL") + fileHash);
            conn = url.openConnection();
            ins = conn.getInputStream();
            fos = new FileOutputStream(dstFn);
            int readLen = 0;
            while ((readLen = ins.read(buf)) != -1) {
                fos.write(buf, 0, readLen);
            }
        } catch (MalformedURLException e) {
            rst = false;
            e.printStackTrace();
        } catch (IOException e) {
            rst = false;
            e.printStackTrace();
        } finally {
            try {
                if (ins != null) {
                    ins.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception ex) {
            }
        }
        return true;
    }

    private static byte[] getFileBytes(String fileHash) {
        URL url = null;
        URLConnection conn = null;
        InputStream ins = null;
        byte[] data = null;
        int bytesRead = 0;
        int offset = 0;
        try {
            url = new URL(PropUtil.getValue("IPFS_GW_URL") + fileHash);
            conn = url.openConnection();
            String contentType = conn.getContentType();
            int contentLength = conn.getContentLength();
            try (InputStream raw = conn.getInputStream()) {
                InputStream in = new BufferedInputStream(raw);
                data = new byte[contentLength];
                while (offset < contentLength) {
                    bytesRead = in.read(data, offset, data.length - offset);
                    if (bytesRead == -1) {
                        break;
                    }
                    offset += bytesRead;
                }

                if (offset != contentLength) {
                    return null;
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return data;
    }

    public static String getTextFile(String fileHash) {
        byte[] data = getFileBytes(fileHash);
        if (data != null) {
            return new String(data);
        } else {
            return "";
        }
    }

    public static String getIpfsUrl(String fileHash) {
        return PropUtil.getValue("IPFS_GW_URL") + fileHash;
    }
}
