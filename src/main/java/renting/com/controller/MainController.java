package renting.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by olivier on 01/10/2019.
 */
@Controller
public class MainController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String index(Model model){
        return "index";
    }

    @RequestMapping(value = "/function", method = RequestMethod.GET)
    public String function(Model model){
        return "fonction";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
       public String login(Model model){
        return "login";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(Model model){
        return "test";
    }

    @RequestMapping(value = "/invoice", method = RequestMethod.GET)
    public String invoice(Model model){
        return "invoice";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String user(Model model){
        return "user";
    }

    @RequestMapping(value = "/role", method = RequestMethod.GET)
    public String role(Model model){
        return "role";
    }

    @RequestMapping(value = "/direction", method = RequestMethod.GET)
    public String direction(Model model){
        return "direction";
    }

    @RequestMapping(value = "/statut", method = RequestMethod.GET)
    public String statut(Model model){
        return "statut";
    }

    @RequestMapping(value = "/civility", method = RequestMethod.GET)
    public String civility(Model model){
        return "civility";
    }


    @RequestMapping(value = "/provider", method = RequestMethod.GET)
    public String provider(Model model){
        return "provider";
    }


    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public String category(Model model){
        return "category";
    }







}
