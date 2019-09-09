package com.edison.service;

import com.edison.excel.metadata.BaseRowModel;
import com.edison.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSON;
import com.edison.exception.ExcelException;
import com.edison.domain.ErrorRow;
import com.edison.utils.ErrorRowList;
import com.edison.utils.ExcelUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @ClassName: ExcelService
 * @description:
 * @author: edison_Kwok
 * @Date: create in 2019/8/18 15:21
 * @Version: 1.0
 */
@Service
public class ExcelServiceImpl implements ExcelService {

    @Override
    public <T extends BaseRowModel> List<T> inputExcel(MultipartFile file, Class<T> clazz) {
        List<T> tList = null;
        try {
            tList = ExcelUtil.readExcel(file, clazz);
        } catch (ExcelException e) {
            throw new RuntimeException("文件格式不正确！");
        }
        this.validateEachData(tList);
        return tList;
    }

    @Override
    public <T extends BaseRowModel> void outputExcel(HttpServletResponse response, List<T> VMList, String filename, String sheetName, ExcelTypeEnum excelTypeEnum, Class<T> clazz) {
        try {
            ExcelUtil.writeExcel(response, VMList, filename, sheetName, excelTypeEnum, clazz);
        } catch (ExcelException e) {
            //TODO 抛出自己定义对象异常
            e.printStackTrace();
        }
    }

    @Override
    public <T extends BaseRowModel> List<T> inputExcel(MultipartFile file, Class<T> clazz, int sheetNo) {
        List<T> tList = null;
        try {
            tList = ExcelUtil.readExcel(file, clazz, sheetNo);
        } catch (ExcelException e) {
            throw new RuntimeException("文件格式不正确！");
        }
        this.validateEachData(tList);
        return tList;
    }

    @Override
    public <T extends BaseRowModel> List<T> inputExcel(MultipartFile file, Class<T> clazz, int sheetNo, int headLineNum) {
        List<T> tList = null;
        try {
            tList = ExcelUtil.readExcel(file, clazz, sheetNo, headLineNum);
        } catch (ExcelException e) {
            throw new RuntimeException("文件格式不正确！");
        }
        this.validateEachData(tList);
        return tList;
    }

    /**
     * validate主动校验方式
     *
     * @param obj
     * @param <T>
     */
    private <T extends BaseRowModel> void validate(@Valid T obj) {
        Set<ConstraintViolation<@Valid T>> validateSet = Validation.buildDefaultValidatorFactory()
                .getValidator()
                .validate(obj, new Class[0]);
        if (!CollectionUtils.isEmpty(validateSet)) {
            String messages = validateSet.stream()
                    .map(ConstraintViolation::getMessage)
                    .reduce((m1, m2) -> m1 + "；" + m2)
                    .orElse("参数输入有误！");
            throw new IllegalArgumentException(messages);

        }
    }

    /**
     * 校验列表中的每一个参数
     *
     * @param tList
     * @param <T>
     */
    private <T extends BaseRowModel> void validateEachData(List<T> tList) {
        ErrorRowList errorRowList = new ErrorRowList();
        for (T t : tList) {
            try {
                this.validate(t);
            } catch (Exception e) {
                errorRowList.add(e.getMessage());
            }
        }
        errorRowList.check();
    }


}
