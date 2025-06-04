package opensource.DreamingLibrary.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private static final String CORS_URL_PATTERN = "/**";
    private static final String CORS_METHOD = "*";

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping(CORS_URL_PATTERN)
                .allowedOrigins("http://localhost:3000", "http://15.165.80.148:3000")
                .allowedMethods(CORS_METHOD)
                .allowCredentials(true);
    }
}
