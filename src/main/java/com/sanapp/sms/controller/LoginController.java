package com.sanapp.sms.controller;

import com.sanapp.sms.dto.ItemList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Locale;

@Controller
public class LoginController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping(value = "/login")
    public String loginLandingPage(@RequestParam(value = "error", required = false) String error,
                                   @RequestParam(value = "logout", required = false) String logout,
                                   Model model, Principal principal, RedirectAttributes flash, Locale locale) {

        if(principal != null) {
            flash.addAttribute("info", messageSource.getMessage("text.login.already", null, locale));
            return "redirect:/";
        }

        if(error != null) {
            model.addAttribute("error", messageSource.getMessage("text.login.error", null, locale));
        }

        if(logout != null) {
            model.addAttribute("success", messageSource.getMessage("text.login.logout", null, locale));
        }


        return  "login";
    }

    @GetMapping(value = "/index")
    public String indexLandingPage() {
        return "index";
    }



    @GetMapping(value = {"/editpage","/editselectedItem"})
    public String editItemLandingPage(Model model) {
        model.addAttribute("itemList", new ItemList());
        return  "editItem";
    }

    @GetMapping(value = "/listAll")
    public String listAllItemsLandingPage(Model model) {

        return  "listAllItems";
    }

    @GetMapping(value="/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
    }

}
