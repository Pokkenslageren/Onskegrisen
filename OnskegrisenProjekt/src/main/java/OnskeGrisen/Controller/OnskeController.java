package OnskeGrisen.Controller;

import OnskeGrisen.Model.User;
import OnskeGrisen.Model.Wish;
import OnskeGrisen.Model.WishList;
import OnskeGrisen.Service.OnskeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@Controller
@RequestMapping("")
public class OnskeController {




    private final OnskeService onskeService;

    public OnskeController(OnskeService onskeService) {
        this.onskeService = onskeService;
    }

/*    @GetMapping("/")
    public String landingPage(){
        return "landing-page";
    }*/

/*    @GetMapping("/home") //virker ikke
    public String home(){
        return "landing-page";
    }*/


    @GetMapping("")
    String landingPage(){
        return "home-page";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("bruger", new User());
        return "login";
    }

    @PostMapping("/login")
    public String logins(@ModelAttribute User user, Model model){
        model.addAttribute("bruger",user);
        return "redirect:/users/" + user.getUsername();
    }

    @GetMapping("/users")
    public String readAllUsers(Model model) {
        model.addAttribute("titel", "List of users");
        model.addAttribute("users", onskeService.readAllUsers());
        return "user-list";
    }

    @GetMapping("/users/register")
    public String register(Model model) {
        User user = new User();
        model.addAttribute("bruger", user);
        return "register-user";
    }

    @PostMapping("/users/register")
    public String register(@ModelAttribute User user) {
        onskeService.registerUser(user);
        return "redirect:/login";
    }

    @GetMapping("/users/{user}")
    public String readUser(@PathVariable String user, Model model) {
        User bruger = onskeService.readUser(user);
        onskeService.fetchOwnerWishLists(bruger);
        model.addAttribute("titel", bruger.getUsername());
        model.addAttribute("bruger", bruger);
        model.addAttribute("onskelister", bruger.getWishLists());

        return "user-page";
    }

    @GetMapping("/{user}/{wishlist}/delete")
    public String deleteWishList(@PathVariable("user") String user, @PathVariable("wishlist") String wishlist) {
        onskeService.deleteWishList(user, wishlist);
        return "redirect:/users/{user}";
    }

    @GetMapping("/users/{user}/{wishlist}/{wish}/delete")
    public String deleteWish(@PathVariable("user") String user, @PathVariable("wishlist") String wishlist, @PathVariable("wish") String wish) {
        onskeService.deleteWish(wishlist, wish);
        return "redirect:/users/{user}/{wishlist}";
    }

    @GetMapping("/users/{user}/{wishlist}")
    public String readWishlist(@PathVariable("user") String user, @PathVariable("wishlist") String wishlist, Model model) {
        User bruger = onskeService.readUser(user);
        ArrayList<Wish> onskeliste = onskeService.fetchWishesFromWishlist(bruger, wishlist);
        String onskelistebeskrivelse = onskeService.getWishListDescription(bruger, wishlist);
        model.addAttribute("onskelistenavn", wishlist);
        model.addAttribute("bruger", bruger);
        model.addAttribute("onskeliste", onskeliste);
        model.addAttribute("onskelistebeskrivelse", onskelistebeskrivelse);
        return "wishlist";
    }

    @GetMapping("/users/{user}/createwishlist")
    public String createWishList(@PathVariable("user") String user, Model model) {
        User bruger = onskeService.readUser(user);
        WishList wishList = new WishList();
        model.addAttribute("bruger", bruger);
        model.addAttribute("onskeliste", wishList);
        return "create-wishlist";
    }

    @PostMapping("/users/createwishlist/{user}") //eller add
    public String saveWishList(@PathVariable ("user")String user, @ModelAttribute WishList wishList) {
        wishList.setUserWishListOwner(user);
        onskeService.createWishList(wishList.getUserWishListOwner(), wishList.getUserWishListName(), wishList.getWishListDescription());
        return "redirect:/users/{user}";
    }

    @GetMapping("/users/{user}/{wishlist}/createwish")
    public String createWish(@PathVariable("user") String user, @PathVariable("wishlist") String wishlist, Model model) {
        User bruger = onskeService.readUser(user);
        Wish wish = new Wish();
        wish.setWishListName(wishlist); //
        model.addAttribute("bruger", bruger);
        model.addAttribute("onske", wish);
        model.addAttribute("onskelistenavn", wishlist);
        return "create-wish";
    }

    @PostMapping("/users/{user}/{wishlist}/createwish")
    public String saveWish(@PathVariable("user") String user, @PathVariable("wishlist") String wishlist, @ModelAttribute Wish wish) {
        wish.setWishListName(wishlist);
        onskeService.createWish(user, wishlist, wish.getWishTitle(), wish.getWishDescription(), wish.getWishPrice(), wish.getWishLink(), false);
        return "redirect:/users/" + user + "/" + wishlist;
    }

    @GetMapping("/users/{user}/{wishlist}/reservewish")
    public String reserveWish(@PathVariable ("user") String user, @PathVariable("wishlist")String wishlist, Model model){
        User bruger = onskeService.readUser(user);
        ArrayList<Wish> onskeliste = onskeService.fetchWishesFromWishlist(bruger,wishlist);
        //Wish wish = onskeliste.get(0);
        Wish wish = new Wish();
        model.addAttribute("nytonske",wish);
        model.addAttribute("brugernavn",user);
        model.addAttribute("bruger", bruger);
        model.addAttribute("onskelistenavn",wishlist);
        model.addAttribute("onskeliste",onskeliste);
        return "reserve-wish-simple";
    }

    @PostMapping("/users/{user}/wishreserved")
    public String wishReserved(@ModelAttribute Wish wish,@PathVariable("user")String user ){
        //
        onskeService.reserveWish(user,wish.getWishListName(),wish.getWishTitle());
        return "redirect:/users";
    }

    //TODO: DO NOT TOUCH:
    /*
    @GetMapping("/users/{user}/{wishlist}/reservewish")
    public String reserveWish(@PathVariable ("user") String user, @PathVariable("wishlist")String wishlist, Model model){
        User bruger = onskeService.readUser(user);
        ArrayList<Wish> onskeliste = onskeService.fetchWishesFromWishlist(bruger,wishlist);
        Wish wish = new Wish();
        model.addAttribute("bruger", user);
        model.addAttribute("onskeliste",onskeliste);
        model.addAttribute("onske",wish);
        return "reserve-wish";
    }



    @PostMapping("/users/{user}/{wishlist}/reservedwish")
    public String createwishlist(@PathVariable ("user")String user, @PathVariable("wishlist")String wishlist, @ModelAttribute Wish wish){

        onskeService.reserveWish(user, wishlist,wish.getWishTitle());

        return "redirect:/users/{user}/{wishlist}";
    }

     */
}
