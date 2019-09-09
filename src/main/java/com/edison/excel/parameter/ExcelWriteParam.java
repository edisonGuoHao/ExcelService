package com.edison.excel.parameter;

import com.edison.excel.ExcelWriter;
import com.edison.excel.support.ExcelTypeEnum;

import java.io.OutputStream;

/**
 * {@link ExcelWriter}
 *
 * @author jipengfei
 */
@Deprecated
public class ExcelWriteParam {

    /**
     */
    private OutputStream outputStream;

    /**
     */
    private ExcelTypeEnum type;

    public ExcelWriteParam(OutputStream outputStream, ExcelTypeEnum type) {
        this.outputStream = outputStream;
        this.type = type;

    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public ExcelTypeEnum getType() {
        return type;
    }

    public void setType(ExcelTypeEnum type) {
        this.type = type;
    }
}
