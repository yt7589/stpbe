package com.zhuanjingkj.stpbe.common.tvis;

import com.zhuanjingkj.stpbe.common.AppConst;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 对原图进行裁剪，绘制识别框，标注品牌车型年款。
 * 当前版本暂时不支持个性化特征和驾驶行为特征标注。
 */
public class TvisSodImage {
    public static BufferedImage downloadIpfsImage(String fileHash) {
        URL url = null;
        BufferedImage img = null;
        try {
            url = new URL(AppConst.IPFS_GW_URL + fileHash);
            System.out.println("##### url:" + (AppConst.IPFS_GW_URL + fileHash) + "!");
            img = ImageIO.read(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public static void drawRect(BufferedImage img, Color color, int x, int y, int w, int h) {
        Graphics2D g2d = img.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(color);
        int lineWith = 1;
        BasicStroke bs1 = new BasicStroke(lineWith);       // 笔画的轮廓（画笔宽度/线宽为5px）
        g2d.setStroke(bs1);
        g2d.drawRect(x, y, w, h);
        g2d.dispose();
    }

    public static void drawString(BufferedImage img, int fontName, int fontSize, Color color, int x, int y, String msg) {
        Graphics2D g2d = img.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(color);
        // 设置字体样式, null 表示使用默认字体, Font.PLAIN 为普通样式, 大小为 25px
        g2d.setFont(new Font(null, fontName, fontSize));
        g2d.drawString(msg, x, y);
        g2d.dispose();
    }

    public static void main(String[] args) {
        BufferedImage img = downloadIpfsImage("QmYphtZ327gmeXwQJFa9dRRsB8C2fAEiVz7eupgbCd4aUa");
        int x = 200, y = 200, w = 300, h = 100;
        drawRect(img, Color.RED, x, y, w, h);
        drawString(img, Font.PLAIN, 20, Color.BLUE, x, y - 5, "奥迪-A6L-2012：京A-XY123");
        File imgFile = new File("d:/temp/a100.jpg");
        try {
            ImageIO.write(img, "jpg", imgFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedImage cutImg = img.getSubimage(x, y, w, h);
        File cutFile = new File("d:/temp/b100.jpg");
        try {
            ImageIO.write(cutImg, "jpg", cutFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("^_^ The End! ^_^");
    }
}
