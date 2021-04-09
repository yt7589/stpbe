package com.zhuanjingkj.stpbe.tmdp.util;

import com.zhuanjingkj.stpbe.common.util.PropUtil;
import com.zhuanjingkj.stpbe.tmdp.dto.FileExpDTO;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileUtil {

    private static final String path = PropUtil.getValue("stp.excel.path");

    public static void export(FileExpDTO fileExp) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(fileExp.getTitle());
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        CellRangeAddress cellRangeAddress = new CellRangeAddress(0,0,0,fileExp.getColumns().length);
        sheet.addMergedRegion(cellRangeAddress);
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(fileExp.getFileName());
        HSSFDataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy-MM-dd");
        HSSFCellStyle dataStyle = workbook.createCellStyle();
        dataStyle.setDataFormat(format);
        sheet.setColumnWidth(4, 20 * 256);
        HSSFCellStyle fontStyle = workbook.createCellStyle();
        fontStyle.setAlignment(HorizontalAlignment.CENTER);
        fontStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setColor(Font.COLOR_RED);
        font.setItalic(true);
        font.setFontName("楷体");
        font.setFontHeightInPoints((short)10);
        fontStyle.setFont(font);
        HSSFRow row1 = sheet.createRow(1);
        for (int i = 0; i < fileExp.getColumns().length; i++) {
            HSSFCell cell1 = row1.createCell(i);
            cell1.setCellStyle(fontStyle);
            cell1.setCellValue(fileExp.getColumns()[i]);
        }
        for (int i = 0; i < fileExp.getList().size(); i++) {
            Object obj = fileExp.getList().get(i);
            Class clss = obj.getClass();
            Field[] fields = obj.getClass().getDeclaredFields();
            int column = 0;
            HSSFRow rowi = sheet.createRow(i + 2);
            for (Field field : fields) {
                HSSFCell cell1 = rowi.createCell(column);
                if ("serialVersionUID".equals(field.getName())) {
                    continue;
                }
                try {
                    PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clss);
                    Method getMethod = pd.getReadMethod();
                    Object o1 = getMethod.invoke(obj);
                    cell1.setCellStyle(dataStyle);
                    cell1.setCellValue(o1 + "");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                column ++;
            }
        }
        try {
            //指定文件导出的路径
//            workbook.write(new File(fileExp.getPath() + "//" + fileExp.getFileName() + ".xls"));
            workbook.write(new File(path + fileExp.getFileName() + ".xls"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * excel打包
     * @param fileName
     */
    public static void doZip(String fileName, long count) {
       try {
           ZipOutputStream out = new ZipOutputStream(new FileOutputStream(path + fileName + ".zip"));
           if (count > 0) {
               for (int i = 0; i < count; i++) {
                    FileInputStream in = new FileInputStream(new File(path + fileName + "_" + i +".xls"));
                    BufferedInputStream bis = new BufferedInputStream(in);
                    byte[] bytes = new byte[1024];
                    out.putNextEntry(new ZipEntry(fileName + "_" + i +".xls"));// 将文件夹放入zip中
                    int bt;
                    while ((bt = bis.read(bytes)) > 0) {
                        out.write(bytes, 0, bt);
                    }
                    if (bis != null) {
                        bis.close();
                    }
                    if (in != null) {
                        in.close();
                    }
               }
           }
           out.closeEntry();// 关闭zip文件中之前打开的项
           out.close();
       } catch (IOException e) {
           e.printStackTrace();
       }
    }

    public static void deleteZip(String fileName) {
        File file = new File(path + fileName + ".zip");
        if (file.exists()) {
            file.delete();
        }
    }

    public static void downZip(HttpServletResponse response, String fileName) {
        String epath = path + fileName + ".zip";
        fileName = fileName + ".zip";
        File file = new File(epath);
        FileInputStream fis;
        try {
            fis = new FileInputStream(file);
            byte[] bf = new byte[fis.available()];
            fis.read(bf);
            fis.close();
            response.reset();
            response.setHeader("Content-disposition", "attachment;filename= "+ URLEncoder.encode(fileName, "utf-8"));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream out = new BufferedOutputStream(response.getOutputStream());
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Expose-Headers", "*");
            response.setContentType("application/octet-stream;charset=UTF-8");
            out.write(bf);
            IOUtils.closeQuietly(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 图片上传
     * @param multipartFile
     * @param fileName
     * @param path
     * @param fileName
     * @return
     */
    public static boolean uploadImg(MultipartFile multipartFile, String fileName, String path) {
        boolean flag = false;
        System.out.println("文件上传 path>>>" + path);
//        String path = "D://";
        File file = new File(new File(path).getAbsolutePath()+ "/" + fileName);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            multipartFile.transferTo(file);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("文件上传完成! > " + path + fileName);
        return flag;
    }

    public static Integer callCMD(String cmd) {
        try {
            Process prs = Runtime.getRuntime().exec(cmd);
            return prs.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
