package id.co.jody.demo.ui;

import id.co.jody.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class UIController {
    private RegistrationService registrationServiceImpl;

    @Autowired
    public UIController(RegistrationService registrationServiceImpl) {
        this.registrationServiceImpl = registrationServiceImpl;
    }

    @RequestMapping(value = "/registration")
    public String register() {
        return "registration";
    }

    @RequestMapping(value = "/registration", params = {"register"}, method = RequestMethod.POST)
    public String createSpaj(@Valid final User user, final BindingResult bindingResult, Model model) {
//        if(bindingResult.hasErrors()) {
//            return "registration";
//        }
        model.addAttribute("message", "Successfully saved user");
        registrationServiceImpl.register(user);
        return "redirect:/registration";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }
}
