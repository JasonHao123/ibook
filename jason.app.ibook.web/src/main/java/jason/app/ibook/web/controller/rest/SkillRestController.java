package jason.app.ibook.web.controller.rest;

import jason.app.ibook.commons.api.model.ICategory;
import jason.app.ibook.commons.api.service.ISkillService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/rest")
public class SkillRestController {

    @Autowired(required=false)
    private ISkillService skillService;
    
    @RequestMapping(value="/skill/search",method=RequestMethod.GET)
    public @ResponseBody List<ICategory> getLocationsByPattern(@RequestParam(value="prefix",required=false) String parent) {
        List<ICategory> locations = skillService.findByPattern(parent);
      
        return locations;
    }
}
