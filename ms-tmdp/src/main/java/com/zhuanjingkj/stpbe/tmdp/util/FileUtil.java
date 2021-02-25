package com.zhuanjingkj.stpbe.tmdp.util;

import com.zhuanjingkj.stpbe.common.util.PropUtil;
import com.zhuanjingkj.stpbe.tmdp.dto.FileExpDTO;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class FileUtil {

    public static void export(HttpServletResponse response, FileExpDTO fileExp) {

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

                    System.out.println("字段名称："+field.getName() + " 字段值： "+o1);

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
            workbook.write(new File(fileExp.getPath() + "//" + fileExp.getFileName() + ".xls"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            response.setContentType("application/x-download");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + new String(fileExp.getFileName().getBytes(), "ISO8859-1") + ".xls");
            workbook.write(os);
            os.flush();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(os!=null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static boolean uploadImg(MultipartFile file, String fileName) {
        boolean flag = false;
        String path = PropUtil.getValue("stp.img.path");
        System.out.println("图片上传 path>>>" + path);
//        String path = "D://";
        File file1 = new File(path + fileName);
        try {
            file.transferTo(file1);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
