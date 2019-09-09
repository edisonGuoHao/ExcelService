package com.edison.utils;


import com.edison.domain.ErrorRow;

import java.util.ArrayList;
import java.util.List;

public class ErrorRowList {

    private List<ErrorRow> errorRowList = null;

    private Integer index;

    public ErrorRowList() {
        index = 0;
        errorRowList = new ArrayList<>();
    }

    /**
     * 添加错误行信息
     *
     * @param reason
     */
    public void add(String reason) {
        ErrorRow errorRow = new ErrorRow("第" + (++index) + "行",reason);
        errorRowList.add(errorRow);
    }

    /**
     * 增加索引下标
     */
    public void increaseIndex() {
        index++;
    }

    /**
     * 检查是否出现错误
     */
    public void check() {
        if (errorRowList.size() > 0) {
            //TODO 使用自己定义的异常
            throw new RuntimeException(errorRowList.toString());
        }
    }
}
