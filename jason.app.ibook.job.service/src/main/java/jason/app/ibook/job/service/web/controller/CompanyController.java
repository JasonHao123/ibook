package jason.app.ibook.job.service.web.controller;

import jason.app.ibook.commons.api.model.ICategory;
import jason.app.ibook.commons.api.service.ILocationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/location")
public class CompanyController {

    @Autowired
    private ILocationService locationService;
    
    @RequestMapping(value="/list",method=RequestMethod.GET)
    public @ResponseBody List<ICategory> getLocations(@RequestParam(value="parent",required=false) Long parent) {
        List<ICategory> locations = locationService.findByParent(parent);
      
        return locations;
    }
}
