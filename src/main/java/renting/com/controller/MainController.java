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

    @RequestMapping(value = "/login", method = RequestMethod.GET)
     public String login(Model model){
        return "lockscreen";
    }

   /* @RequestMapping(value = "/com", method = RequestMethod.GET)
    public String com(Model model){
        return "commune";
    }

    @RequestMapping(value = "/city", method = RequestMethod.GET)
    public String city(Model model){
        return "quartier";
    }*/

    @RequestMapping(value = "/equipment", method = RequestMethod.GET)
    public String equipment(Model model){
        return "equipment";
    }

    @RequestMapping(value = "/renting", method = RequestMethod.GET)
    public String log(Model model){
        return "renting";
    }

    @RequestMapping(value = "/moyenPay", method = RequestMethod.GET)
    public String moyenPay(Model model){
        return "moyenpay";
    }

    @RequestMapping(value = "/statutPay", method = RequestMethod.GET)
    public String statutPay(Model model){
        return "statutpay";
    }

    /*@RequestMapping(value = "/typePay", method = RequestMethod.GET)
    public String typePaye(Model model){
        return "typepay";
    }*/

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public String locataire(Model model){
        return "customer";
    }

    @RequestMapping(value = "/civility", method = RequestMethod.GET)
    public String civility(Model model){
        return "civility";
    }

    @RequestMapping(value = "/reporting", method = RequestMethod.GET)
    public String report(Model model){
        return "reporting";
    }

    @RequestMapping(value = "/spent", method = RequestMethod.GET)
    public String spent(Model model){
        return "spent";
    }

    /*@RequestMapping(value = "/twon", method = RequestMethod.GET)
      public String twon(Model model){
        return "twon";
    }*/



}
