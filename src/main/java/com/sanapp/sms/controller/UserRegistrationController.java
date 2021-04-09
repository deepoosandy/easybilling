package com.sanapp.sms.controller;

import com.sanapp.sms.dto.UserDto;
import com.sanapp.sms.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Locale;


@Controller
public class UserRegistrationController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/signup")
    public String showRegistrationForm(Model model){
        model.addAttribute("signup", new UserDto());
        return "signup";
    }

    @PostMapping("/signup")
    public String registerUserAccount(@ModelAttribute("signup")  UserDto userDto,  Model model, Locale locale, RedirectAttributes flash){
        if (iUserService.findByUsername(userDto.getUsername()).getUsername() != null ) {
            model.addAttribute("warning", messageSource.getMessage("text.signup.existe", null, locale));
            return "signup";
        }
        String flashMsg = "";
        if(userDto.getUsername() != null) {
            flashMsg = messageSource.getMessage("text.signup.flash.success", null, locale);
        }


        iUserService.save(userDto);
        flash.addFlashAttribute("success", flashMsg);
        return "redirect:/login";
    }
}
