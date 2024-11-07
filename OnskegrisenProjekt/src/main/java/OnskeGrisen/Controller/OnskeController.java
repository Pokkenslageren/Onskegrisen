package OnskeGrisen.Controller;

import OnskeGrisen.Model.User;
import OnskeGrisen.Service.OnskeService;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import OnskeGrisen.OnskeApplication;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("")
public class OnskeController {

    private final OnskeService onskeService;

    public OnskeController(OnskeService onskeService){
        this.onskeService = onskeService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user","userlist");
        model.addAttribute("users", onskeService.getUserList());
        return "register-user";
    }
    // todo sus postmapping URL. change?
    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        onskeService.registerUser(user);
        return "redirect:/user";
    }

    @GetMapping("/{user}/update")
    public String updateUser(String name, Model model) {
        model.addAttribute("userToBeUpdated", onskeService.readUser(name));
        return "update-user";
    }

    @PostMapping("/{user}/update")
    public String updateUser(@ModelAttribute User user) {
        onskeService.updateUser(user, user.getUsername());
        return "redirect:/user";
    }

    @GetMapping("/{user}/delete") // todo undersøg om dette skal være post- eller get-mapping. Undersøg om DELETEmapping eventuelt kan virke
    public String deleteUser(User user) { // todo hvad med @path variable?
        onskeService.deleteUser(user);
        return "redirect:/landing-page";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/{user}")
    public String user(){
        return "user";
    }

    @GetMapping("/login/{user}/{wishlist}")
    public String wishlist(){
        return "wishlist";
    }

    @GetMapping("/login/{user}/{wish}")
    public String wish(){
        return "wish";
    }

    @PostMapping("/login/{user}/createwishlist")
    public String createwishlist(){
        return "create-wishlist";
    }

    @PostMapping("/login/{user}/updatewishlist")
    public String updatewishlist(){
        return "update-wishlist";
    }

    @PostMapping("/login/{user}/deletewishlist")
    public String deletewishlist(){
        return "redirect:/wishlists";
    }

    @PostMapping("/login/{user}/{wishlist}/createwish")
    public String createwish(){
        return "create-wish";
    }

    @PostMapping("/login/{user}/{wishlist}/updatewish")
    public String updatewish(){
        return "update-wish";
    }

    @PostMapping("/login/{user}/{wishlist}/deletewish")
    public String deletewish(RedirectAttributes redirectAttributes){
        return "redirect:/wishlist";
        }
}
