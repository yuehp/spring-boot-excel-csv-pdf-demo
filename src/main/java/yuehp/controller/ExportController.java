package yuehp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import yuehp.service.UserService;

/**
 * <p>这里没什么可说的，ExportController 将一些数据添加到模型中，我们将在视图上显示这些数据。</p>
 * 
 * @author Administrator
 * @version 20180821
 */
@Controller
public class ExportController {

    @Autowired
    private UserService userService;

    /**
     * Handle request to download an Excel document
     */
    @GetMapping("/download{extension}")
    public String download(Model model, @PathVariable String extension) {
    	System.out.println("----download method----" + extension);

        model.addAttribute("users", userService.findAllUsers());
        return "";
    }


}
