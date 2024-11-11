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

//@Controller
public class LoginController {
    @GetMapping("/login")
    String getLogin(){


        return "login";
    }
}
