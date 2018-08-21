package yuehp.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

import yuehp.viewresolver.CsvViewResolver;
import yuehp.viewresolver.ExcelViewResolver;
import yuehp.viewresolver.PdfViewResolver;

/**
 * 
 * <p>文章中，继承了 Adapter 类。现在 interface 有默认方法，所以直接实现接口即可。</p>
 * 
 * @author Administrator
 * @version 20180821
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.defaultContentType(MediaType.APPLICATION_JSON)
		                .favorPathExtension(true);
	}

	/**
	 * <h3>配置 ContentNegotiatingViewResolver</h3>
	 * 
	 * <p>在我们的示例中，我们将使用URL扩展来帮助确定媒体类型。</p>
	 * <p>此外，我们在没有文件扩展名或文件类型未知的情况下将默认媒体类型设置为TEXT_JSON。</p>
	 * <p>此外，我们需要设置将由Spring注入的内容协商管理器，以及我们的应用程序可能产生的每种可能输出格式的不同解析器。</p>
	 * <p>最后，我们为PDF，XLS和CSV输出创建了不同的视图解析器</p>
	 * 
	 * <p>ViewResolver的实现，它根据请求文件名或Accept标头解析视图。</p>
	 * <p>它告诉Web控制器返回ModelAndViews或 View 名称，并根据各种标准选择正确的数据表示策略。</p>
	 * 
	 * <h3>View 查找步骤：</h3>
	 * <ol>
	 * 		<li>文件扩展名</li>
	 * 		<li>请求参数</li>
	 * 		<li>用 Java Activation Framework 确定 Content-Type</li>
	 * 		<li>使用 HTTP Accept header</li>
	 * </ol>
	 */
	@Bean
	public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setContentNegotiationManager(manager);

		// Define all possible view resolvers
		List<ViewResolver> resolvers = new ArrayList<>();

		resolvers.add(pdfViewResolver());
		resolvers.add(csvViewResolver());
		resolvers.add(excelViewResolver());

		resolver.setViewResolvers(resolvers);
		return resolver;
	}

	/**
	 * Configure View resolver to provide XLS output using Apache POI library to
	 * generate XLS output for an object content
	 * 
	 * <p>依赖：Apache POI</p>
	 */
	@Bean
	public ViewResolver excelViewResolver() {
		return new ExcelViewResolver();
	}

	/**
	 * Configure View resolver to provide Csv output using Super Csv library to
	 * generate Csv output for an object content
	 * 
	 * <p>依赖：Super Csv</p>
	 */
	@Bean
	public ViewResolver csvViewResolver() {
		return new CsvViewResolver();
	}

	/**
	 * Configure View resolver to provide Pdf output using iText library to generate
	 * pdf output for an object content
	 * 
	 * <p>依赖：iText Pdf</p>
	 */
	@Bean
	public ViewResolver pdfViewResolver() {
		return new PdfViewResolver();
	}

}
