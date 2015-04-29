package jason.app.ibook.web.controller.company;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/company")
public class CompanyController {

    @RequestMapping("/list")
    public String list(Model model, @RequestParam(value = "location", required = false) Long location,
            @RequestParam(value = "industry", required = false) Long industry, 
            @RequestParam(value = "type", required = false) Long type,
            @RequestParam(value = "scale", required = false) Long scale) {
        return "company.list";
    }
}
