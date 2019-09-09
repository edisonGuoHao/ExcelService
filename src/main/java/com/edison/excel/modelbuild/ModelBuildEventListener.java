package com.edison.excel.modelbuild;

import com.edison.domain.ErrorRow;
import com.edison.excel.context.AnalysisContext;
import com.edison.excel.event.AnalysisEventListener;
import com.edison.excel.exception.ExcelGenerateException;
import com.edison.excel.metadata.ExcelHeadProperty;
import com.edison.excel.util.TypeUtil;
import com.edison.utils.ErrorRowList;
import net.sf.cglib.beans.BeanMap;

import java.util.List;

/**
 * @author jipengfei
 */
public class ModelBuildEventListener extends AnalysisEventListener {

    @Override
    public void invoke(Object object, AnalysisContext context) {
        if (context.getExcelHeadProperty() != null && context.getExcelHeadProperty().getHeadClazz() != null) {
            ErrorRowList errorRowList = (ErrorRowList) context.getCustom();
            try {
                Object resultModel = buildUserModel(context, (List<String>) object);
                context.setCurrentRowAnalysisResult(resultModel);
                errorRowList.increaseIndex();
            } catch (Exception e) {
                e.getStackTrace();
                System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz" + e.getMessage());
                errorRowList.add("数据类型不合法！");
//                throw new ExcelGenerateException(e);
            }
        }
    }

    private Object buildUserModel(AnalysisContext context, List<String> stringList) throws Exception {
        ExcelHeadProperty excelHeadProperty = context.getExcelHeadProperty();
        Object resultModel = excelHeadProperty.getHeadClazz().newInstance();
        if (excelHeadProperty == null) {
            return resultModel;
        }
        BeanMap.create(resultModel).putAll(TypeUtil.getFieldValues(stringList, excelHeadProperty, context.use1904WindowDate()));
        return resultModel;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
}
