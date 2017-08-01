package ua.adeptius.traffix.background;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.adeptius.traffix.controllers.PersonController;

import java.util.concurrent.TimeUnit;

public class BackgroundUpdater extends Thread {

    private static Logger LOGGER = LoggerFactory.getLogger(BackgroundManager.class.getSimpleName());

    public BackgroundUpdater() {
        this.setDaemon(true);
        this.setName("BackgroundUpdater");
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(TimeUnit.HOURS.toMillis(1));
            } catch (InterruptedException e) {
                // That will never happen
            }
            try {
                PersonController.updateList();
                LOGGER.info("Background persons generating success");
            }catch (Exception e){
                LOGGER.error("Error while generating persons in background", e);
            }
        }
    }
}
