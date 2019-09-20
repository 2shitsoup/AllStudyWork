package com.jiuyan.StudyNetty.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname: IndexController
 * @Description TODO
 * @Date: 2019-09-17 17:18
 * @Created by JiuyanLAY
 */
@RestController
public class IndexController {

    @RequestMapping("/home")
    public String home() {
        return "Welcom home!";
    }


}
