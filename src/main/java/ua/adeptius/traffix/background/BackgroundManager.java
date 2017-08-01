package ua.adeptius.traffix.background;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ua.adeptius.traffix.annotations.AfterSpringLoadComplete;

@Component
public class BackgroundManager {

    private static Logger LOGGER = LoggerFactory.getLogger(BackgroundManager.class.getSimpleName());

    @AfterSpringLoadComplete
    public void startUpdater(){
        LOGGER.info("Background manager is starting all background tasks");
        new BackgroundUpdater().start();
    }
}
