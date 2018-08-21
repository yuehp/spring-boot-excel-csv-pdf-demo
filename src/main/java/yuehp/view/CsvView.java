package yuehp.view;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import yuehp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * <p>然后，我们编写 AbstractCsvView 类的实现，并使其实现 buildCsvDocument（）方法，如下所示：</p>
 * 
 * @author Administrator
 * @version 20180821
 */
public class CsvView extends AbstractCsvView {


    @SuppressWarnings("unchecked")
	@Override
    protected void buildCsvDocument(Map<String, Object> model, HttpServletRequest request, HttpServletResponse
            response) throws Exception {

        response.setHeader("Content-Disposition", "attachment; filename=\"my-csv-file.csv\"");

        List<User> users = (List<User>) model.get("users");
        String[] header = {"FirstName", "LastName", "LastName", "JobTitle", "Company", "Address", "City", "Country",
                "PhoneNumber"};
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
                CsvPreference.STANDARD_PREFERENCE);

        csvWriter.writeHeader(header);

        for (User user : users) {
            csvWriter.write(user, header);
        }
        csvWriter.close();

    }
}
