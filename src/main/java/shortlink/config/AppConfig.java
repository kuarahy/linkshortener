package shortlink.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.shortlink.service.Shortener;

@Configuration
public class AppConfig {
    @Bean
    public Shortener shortener() {
        return new Shortener();
    }
}