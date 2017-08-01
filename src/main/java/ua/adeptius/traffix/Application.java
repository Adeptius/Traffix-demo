package ua.adeptius.traffix;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@Component
@SpringBootApplication
public class Application {

    private static Logger LOGGER = LoggerFactory.getLogger(Application.class.getSimpleName());

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
