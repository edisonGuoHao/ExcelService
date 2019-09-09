package com.edison.utils;

import com.edison.excel.ExcelWriter;
import com.edison.excel.metadata.BaseRowModel;
import com.edison.excel.metadata.Sheet;
import com.edison.excel.support.ExcelTypeEnum;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class ExcelWriterFactory extends ExcelWriter {
    private OutputStream outputStream;
    private int sheetNo = 1;

    ExcelWriterFactory(OutputStream outputStream, ExcelTypeEnum typeEnum) {
        super(outputStream, typeEnum);
        this.outputStream = outputStream;
    }

    public ExcelWriterFactory write(List<? extends BaseRowModel> list, String sheetName,
                                    BaseRowModel object) {
        this.sheetNo++;
        try {
            Sheet sheet = new Sheet(sheetNo, 0, object.getClass());
            sheet.setSheetName(sheetName);
            this.write(list, sheet);
        } catch (Exception ex) {
            try {
                outputStream.flush();
            } catch (IOException e) {
                //do something
            }
        }
        return this;
    }

    @Override
    public void finish() {
        super.finish();
        try {
            outputStream.flush();
        } catch (IOException e) {
            //do something
        }
    }

    public void close(){
        try {
            outputStream.close();
        } catch (IOException e) {
            //do something
        }
    }
}
