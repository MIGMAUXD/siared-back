
package BackendSiadseUfps.siadse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200") // Permitir solicitudes desde http://localhost:4200
                        .allowedMethods("*") // Permitir los métodos HTTP especificados
                        .allowedHeaders("*"); // Permitir todos los encabezados; //Enable CORS for all endpoints
            }
        };
    }
}