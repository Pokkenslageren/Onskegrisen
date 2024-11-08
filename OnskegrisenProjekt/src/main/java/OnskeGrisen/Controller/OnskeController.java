package OnskeGrisen.Controller;

import OnskeGrisen.Model.User;
import OnskeGrisen.Model.WishList;
import OnskeGrisen.Service.OnskeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("")
public class OnskeController {

    private final OnskeService onskeService;

    public OnskeController(OnskeService onskeService){
        this.onskeService = onskeService;
    }

    @GetMapping("/users")
    public String readAllUsers(Model model){
        model.addAttribute("titel","List of users");
        model.addAttribute("users",onskeService.readAllUsers());
        return "user-list";
    }

    @GetMapping("/users/register")
    public String register(Model model) {
        model.addAttribute("user","userlist");
        model.addAttribute("users", onskeService.readAllUsers());
        return "register-user";
    }
    // todo sus postmapping URL. change?
    @PostMapping("/users/register")
    public String register(@ModelAttribute User user) {
        onskeService.registerUser(user);
        return "redirect:/user";
    }

/*    @GetMapping("/users/{user}") //virker
    public ResponseEntity<User> readUser(@PathVariable String user){
        User bruger = onskeService.readUser(user);
        return new ResponseEntity<>(bruger, HttpStatus.OK);
    }*/

    @GetMapping("/users/{user}")
    public String readUser(@PathVariable String user, Model model){
        User bruger = onskeService.readUser(user);
        onskeService.fetchOwnerWishLists(bruger);
        model.addAttribute("titel", bruger.getUsername());
        model.addAttribute("bruger", bruger);
        model.addAttribute("onskelister", bruger.getWishLists());

        return "user-page";
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
