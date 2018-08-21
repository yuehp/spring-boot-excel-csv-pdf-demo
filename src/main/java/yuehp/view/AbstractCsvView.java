 package yuehp.view;

 import org.springframework.web.servlet.view.AbstractView;

 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import java.util.Map;

/**
 * <p>为此，我们将遵循用于PDF生成的完全相同的方法，其涉及创建抽象视图类，具体视图类和视图解析器。 我正在使用 Super CSV生成csv文件。</p>
 * <p>因此，在 AbstractCsvView 的代码下面，对 Spring 的 AbstractView 类进行 Subclass：</p>
 * 
 * @author Administrator
 * @version 20180821
 */
public abstract class AbstractCsvView extends AbstractView {

    private static final String CONTENT_TYPE = "text/csv";

    public AbstractCsvView() {
        setContentType(CONTENT_TYPE);
    }

    @Override
    protected boolean generatesDownloadContent() {
        return true;
    }


    @Override
    protected final void renderMergedOutputModel(
            Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType(getContentType());
        buildCsvDocument(model, request, response);
    }

    protected abstract void buildCsvDocument(
            Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
            throws Exception;


}