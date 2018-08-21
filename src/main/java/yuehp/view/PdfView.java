package yuehp.view;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import yuehp.model.User;

/**
 * <p>之后，我们接下来需要做的就是创建 PdfView 类并从之前创建的 AbstractPdfView 扩展并覆盖 buildPdfDocument（..） 方法来创建我们的 PDF 文档。</p>
 * 
 * @author Administrator
 * @version 20180821
 */
public class PdfView extends AbstractPdfView {
	
    @SuppressWarnings("unchecked")
	@Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // change the file name
        response.setHeader("Content-Disposition", "attachment; filename=\"my-pdf-file.pdf\"");

        List<User> users = (List<User>) model.get("users");
        document.add(new Paragraph("Generated Users " + LocalDate.now()));

        PdfPTable table = new PdfPTable(users.stream().findAny().get().getColumnCount());
        table.setWidthPercentage(100.0f);
        table.setSpacingBefore(10);

        // define font for table header row
        Font font = FontFactory.getFont(FontFactory.TIMES);
        font.setColor(BaseColor.WHITE);

        // define table header cell
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.DARK_GRAY);
        cell.setPadding(5);

        // write table header
        cell.setPhrase(new Phrase("First Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Last Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Age", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Job Title", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Company", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Address", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("City", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Country", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Phone Number", font));
        table.addCell(cell);

        for(User user : users){
            table.addCell(user.getFirstName());
            table.addCell(user.getLastName());
            table.addCell(user.getAge().toString());
            table.addCell(user.getJobTitle());
            table.addCell(user.getCompany());
            table.addCell(user.getAddress());
            table.addCell(user.getCity());
            table.addCell(user.getCountry());
            table.addCell(user.getPhoneNumber());

        }

        document.add(table);
    }
}
