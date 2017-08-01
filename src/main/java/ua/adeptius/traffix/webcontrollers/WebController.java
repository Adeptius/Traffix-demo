package ua.adeptius.traffix.webcontrollers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.adeptius.traffix.messaging.Message;
import ua.adeptius.traffix.controllers.PersonController;
import ua.adeptius.traffix.exceptions.NotEnoughSurnamesException;
import ua.adeptius.traffix.model.Person;

import java.util.List;

import static ua.adeptius.traffix.messaging.Message.Status.Error;
import static ua.adeptius.traffix.messaging.Message.Status.Success;

@Controller
public class WebController {

    private static Logger LOGGER = LoggerFactory.getLogger(WebController.class.getSimpleName());

    @RequestMapping("/")
    public String showHello(){
        return "readme";
    }



    @RequestMapping(value = "/setUserCount", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Message getUsers(@RequestParam int count){
        LOGGER.info("Admin request for change person count to {}", count);
        try{
            PersonController.updateList(count);
            LOGGER.info("Admin request success");
            return new Message(Success, "Count set");

        }catch (NotEnoughSurnamesException e){
            LOGGER.info("Admin request not success because not enough surnames");
            return new Message(Error, "Sorry, not enough surnames for this request");

        }catch (Exception e){
            LOGGER.error("Admin request not success because exception", e);
            return new Message(Error, "Sorry, internal error. Try again later.");
        }
    }


    @RequestMapping(value = "/getUsers", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<Person> getUsers(){
        List<Person> currentList = PersonController.getCurrentList();
        LOGGER.info("Returning list with {} persons", currentList.size());
        return currentList;
    }

}
