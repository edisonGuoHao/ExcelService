package com.edison.service;


import com.edison.excel.metadata.BaseRowModel;
import com.edison.excel.support.ExcelTypeEnum;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;

public interface ExcelService {

    /**
     * 根据文件流解析对象集合
     *
     * @param file
     * @param clazz
     * @param <T>
     * @return
     */
    public <T extends BaseRowModel> List<T> inputExcel(MultipartFile file, Class<T> clazz);

    /**
     * 导出excel文件
     *
     * @param response
     * @param VMList
     * @param filename
     * @param sheetName
     * @param excelTypeEnum
     * @param clazz
     * @param <T>
     */
    public <T extends BaseRowModel> void outputExcel(HttpServletResponse response, List<T> VMList, String filename, String sheetName, ExcelTypeEnum excelTypeEnum, Class<T> clazz);

    /**
     * 选择不同的sheet进行选取
     *
     * @param clazz
     * @param sheetNo
     * @param <T>
     * @return
     */
    public <T extends BaseRowModel> List<T> inputExcel(MultipartFile file, Class<T> clazz, int sheetNo);

    /**
     * 可以选择不同的sheet，不同的headLine
     *
     * @param rowModel
     * @param sheetNo
     * @param headLineNum
     * @param <T>
     * @return
     */
    public <T extends BaseRowModel> List<T> inputExcel(MultipartFile file, Class<T> rowModel, int sheetNo, int headLineNum);
}
