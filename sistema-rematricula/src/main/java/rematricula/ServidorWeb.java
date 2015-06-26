package rematricula;

import javax.inject.Inject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mustache.MustacheProperties;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;


@SpringBootApplication
public class ServidorWeb {
	
	
	@Inject
	private MustacheProperties mustache;

	@Bean
	public CustomMustacheViewResolver viewResolver(com.samskivert.mustache.Mustache.Compiler mustacheCompiler){
		CustomMustacheViewResolver resolver = new CustomMustacheViewResolver();
		resolver.setPrefix(this.mustache.getPrefix());
		resolver.setSuffix(this.mustache.getSuffix());
		resolver.setCache(this.mustache.isCache());
		resolver.setViewNames(this.mustache.getViewNames());
		resolver.setContentType(this.mustache.getContentType());
		resolver.setCharset(this.mustache.getCharset());
		resolver.setCompiler(mustacheCompiler);
		resolver.setOrder(Ordered.LOWEST_PRECEDENCE - 10);
		return resolver;
	}
	
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer(){
	    return new MyCustomizer();
	}

	private static class MyCustomizer implements EmbeddedServletContainerCustomizer {

	    @Override
	    public void customize(ConfigurableEmbeddedServletContainer container) {
	        container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500"));
	        container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404"));
	    }

	}
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(ServidorWeb.class, args);
	}
}
