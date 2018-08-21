package yuehp.view;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import yuehp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * <p>我们可以使用2种文件格式创建Excel文档。 .xls是旧格式，.xlsx是基于XML的新格式。 </p>
 * <p>我们使用apache POI创建excel文件，在创建.xls文件时确保org.apache.poi：poi依赖项在类路径上。</p> 
 * <p>使用.xlsx文件时，需要org.apache.poi：poi-ooxml依赖项。</p>
 * 
 * <p>从 AbstractXlsView 扩展的 ExcelView。 我们通过覆盖 buildExcelDocument 来创建 excel 文档，其余的是自解释的。</p>
 * 
 * <p>Spring还提供了2个其他抽象类AbstractXlsxView和AbstractXlsxStreamingView来创建xlsx文件。</p>
 * <p>使用大型 Excel 文档时，使用流式 xlsx 视图是有利可图的。 流式视图使用较少的内存，可以提高大型 Excel 文档的性能。</p>
 * 
 * @author Administrator
 * @version 20180821
 */
public class ExcelView extends AbstractXlsView{

    @Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      Workbook workbook,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

        // change the file name
        response.setHeader("Content-Disposition", "attachment; filename=\"my-xls-file.xls\"");

        @SuppressWarnings("unchecked")
        List<User> users = (List<User>) model.get("users");

        // create excel xls sheet
        Sheet sheet = workbook.createSheet("User Detail");
        sheet.setDefaultColumnWidth(30);

        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        font.setBold(true);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);


        // create header row
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("FirstName");
        header.getCell(0).setCellStyle(style);
        header.createCell(1).setCellValue("LastName");
        header.getCell(1).setCellStyle(style);
        header.createCell(2).setCellValue("Age");
        header.getCell(2).setCellStyle(style);
        header.createCell(3).setCellValue("Job Title");
        header.getCell(3).setCellStyle(style);
        header.createCell(4).setCellValue("Company");
        header.getCell(4).setCellStyle(style);
        header.createCell(5).setCellValue("Address");
        header.getCell(5).setCellStyle(style);
        header.createCell(6).setCellValue("City");
        header.getCell(6).setCellStyle(style);
        header.createCell(7).setCellValue("Country");
        header.getCell(7).setCellStyle(style);
        header.createCell(8).setCellValue("Phone Number");
        header.getCell(8).setCellStyle(style);



        int rowCount = 1;

        for(User user : users){
            Row userRow =  sheet.createRow(rowCount++);
            userRow.createCell(0).setCellValue(user.getFirstName());
            userRow.createCell(1).setCellValue(user.getLastName());
            userRow.createCell(2).setCellValue(user.getAge());
            userRow.createCell(3).setCellValue(user.getJobTitle());
            userRow.createCell(4).setCellValue(user.getCompany());
            userRow.createCell(5).setCellValue(user.getAddress());
            userRow.createCell(6).setCellValue(user.getCity());
            userRow.createCell(7).setCellValue(user.getCountry());
            userRow.createCell(8).setCellValue(user.getPhoneNumber());

            }

    }

}
