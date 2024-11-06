package OnskeGrisen.Controller;

import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import OnskeGrisen.OnskeApplication;

@Controller
@RequestMapping("")
public class OnskeController {
    private final OnskeApplication onskeApplication;

    public OnskeController(OnskeApplication onskeApplication){
        this.onskeApplication = onskeApplication;
    }


    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/login/{user}")
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
        return "createwishlist";
    }

    @PostMapping("/login/{user}/updatewishlist")
    public String updatewishlist(){
        return "updatewishlist";
    }

    @PostMapping("/login/{user}/deletewishlist")
    public String deletewishlist(){
        return "deletewishlist";
    }

    @PostMapping("/login/{user}/{wishlist}/createwish")
    public String createwish(){
        return "createwish";
    }

    @PostMapping("/login/{user}/{wishlist}/updatewish")
    public String updatewish(){
        return "updatewish";
    }

    @PostMapping("/login/{user}/{wishlist}/deletewish")
    public String deletewish(){
        return "deletewish";
        }
}
