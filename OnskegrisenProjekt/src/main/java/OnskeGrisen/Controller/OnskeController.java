package OnskeGrisen.Controller;

import OnskeGrisen.Model.User;
import OnskeGrisen.Model.Wish;
import OnskeGrisen.Model.WishList;
import OnskeGrisen.Service.OnskeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;


@Controller
@RequestMapping("")
public class OnskeController {


    private final OnskeService onskeService;

    public OnskeController(OnskeService onskeService){
        this.onskeService = onskeService;
    }

/*    @GetMapping("/")
    public String landingPage(){
        return "landing-page";
    }*/

    @GetMapping("/users")
    public String readAllUsers(Model model){
        model.addAttribute("titel","List of users");
        model.addAttribute("users",onskeService.readAllUsers());
        return "user-list";
    }



    @GetMapping("/users/register")
    public String register(Model model) {
        User user = new User();
        model.addAttribute("bruger",user);
        return "register-user";
    }

    @PostMapping("/users/register")
    public String register(@ModelAttribute User user) {
        onskeService.registerUser(user);
        return "redirect:/users";
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

    @PostMapping("users/{user}/update")
    public String updateUser(@ModelAttribute User user) {
        onskeService.updateUser(user, user.getUsername());
        return "redirect:/users";
    }

    @GetMapping("/users/{user}/delete") // todo undersøg om dette skal være post- eller get-mapping. Undersøg om DELETEmapping eventuelt kan virke
    public String deleteUser(@PathVariable("user") String user) { // todo hvad med @path variable?
        //onskeService.deleteWish
        //onskeService.deleteWishLists(user)
        onskeService.deleteUser(user);
        return "redirect:/users";
    }

    @GetMapping("/users/{user}/{wishlist}/delete")
    public String deleteWishList(@PathVariable("user")String user, @PathVariable("wishlist")String wishlist){
        onskeService.deleteWishList(user,wishlist);
        return "redirect:/users/{user}";
    }

    @GetMapping("/users/{user}/{wishlist}")
    public String readWishlist(@PathVariable String user, @PathVariable String wishlist, Model model){
        User bruger = onskeService.readUser(user);
        //onskeService.fetchWishesFromWishlist(bruger,onskeService.readWishlist(bruger, wishlist)); //Returner et array i stedet?
        ArrayList<Wish> onskeliste = onskeService.fetchWishesFromWishlist(bruger,wishlist); //er denne tom? Skal den fyldes ud
        model.addAttribute("bruger", bruger);
        model.addAttribute("onskeliste", onskeliste);
        return "wishlist";
    }

    @GetMapping("/users/{user}/createwishlist") //behøver vi at køre {user}/addwishlist? Kan den ikke bare være /users/addwishlist
    public String createWishList(@PathVariable ("user") String user, Model model){
        User bruger = onskeService.readUser(user);
        WishList wishList = new WishList(); //dette object "peger" ned på postmappingen.
        model.addAttribute("bruger", bruger);
        model.addAttribute("onskeliste", wishList);
        return "create-wishlist";
    }

    @PostMapping("/users/createwishlist") //eller add
    public String saveWishList(@ModelAttribute WishList wishList){
        onskeService.createWishList(wishList.getUserWishListOwner(), wishList.getUserWishListName(), wishList.getWishListDescription());
        return "redirect:/users"; //skal redircte til siden for den tilhørende wishlist
    }

    @GetMapping("/users/{user}/createwish") //kan både gøres fra profilsiden og fra den enkelte ønskeliste
    public String createWish(@PathVariable ("user") String user, Model model){
        User bruger = onskeService.readUser(user);
        Wish wish = new Wish();
        model.addAttribute("bruger", bruger);
        model.addAttribute("onske", wish);
        return "create-wish";
    }

    @PostMapping("/users/{user}/createwish") //TODO: super sus tilgang, men wish.getWishListOwner() returnerer null, for some reason
    public String saveWish(@PathVariable ("user") String user, @ModelAttribute Wish wish, Model model){
        model.addAttribute("bruger",user);
        onskeService.createWish(user,wish.getWishListName(),wish.getWishTitle(),wish.getWishDescription(),wish.getWishPrice(),wish.getWishLink(),false);
        return "redirect:/users";
    }

    @GetMapping("/users/{user}/reservewish")
    public String reserveWish(@PathVariable ("user") String user,Model model){
        User bruger = onskeService.readUser(user);
        model.addAttribute("bruger", user);
        return "reserve-wish";

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

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
