package jpabook.jpashop.Controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.ILoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {

    //Logger log = ILoggerFactory.get

    @RequestMapping("/")
    public String home(){
        log.info("home controller");
        return "home";

    }

}
