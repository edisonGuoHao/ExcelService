package com.edison.service;


import com.edison.domain.Student;
import com.edison.excel.support.ExcelTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class StudentService {

    @Autowired
    private ExcelService excelService;

    public void inputExcel(MultipartFile file) {
        List<Student> students = null;
        students = excelService.inputExcel(file, Student.class);
        System.out.println(students);
    }

    public void outputExcel(HttpServletResponse response) {
        List<Student> list = new ArrayList<>(2);
        list.add(new Student("郭豪", 18, "浙江", "14122401"));
        list.add(new Student("郭豪张", 18, "浙江", "14122401"));
        excelService.outputExcel(response, list, "学生列表excel", "学生列表", ExcelTypeEnum.XLSX, Student.class);
    }
}
