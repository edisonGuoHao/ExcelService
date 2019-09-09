package com.edison.domain;

import com.edison.excel.annotation.ExcelProperty;
import com.edison.excel.metadata.BaseRowModel;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Data
@EqualsAndHashCode(callSuper = true)
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Student extends BaseRowModel {

    @ExcelProperty(index = 0, value = "姓名")
    @NotNull(message = "姓名不能为空")
    private String name;
    @ExcelProperty(index = 1, value = "年龄")
    @Min(value = 12,message = "年龄不能太小")
    private Integer age;
    @ExcelProperty(index = 2, value = "地址")
    private String address;
    @ExcelProperty(index = 3, value = "学号")
    private String schoolId;
}
