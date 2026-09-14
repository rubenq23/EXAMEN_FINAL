package ipss.VentasFix.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityBeansConfig {

    @Bean  //se declara como Bean para poder inyectar el PassWordEnconder
    public PasswordEncoder passwordEncoder() { //la dependencia Spring Security como dependencia habilita el PasswordEncoder
        return new BCryptPasswordEncoder();
    }
}
