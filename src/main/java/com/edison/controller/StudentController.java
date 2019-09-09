package com.edison.controller;


import com.edison.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController("/")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("")
    public void inputExcel(@RequestParam(name = "file",required = true) MultipartFile file){
        studentService.inputExcel(file);
    }

    @GetMapping("")
    public void outputExcel(HttpServletResponse response){
        studentService.outputExcel(response);
    }
}
