package jason.app.ibook.web.controller.rest;

import jason.app.ibook.commons.api.model.ICategory;
import jason.app.ibook.commons.api.service.IJobCategoryService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/rest")
public class CategoryRestController {

    @Autowired(required=false)
    private IJobCategoryService categoryService;
    
    @RequestMapping(value="/category/list",method=RequestMethod.GET)
    public @ResponseBody List<ICategory> getLocations(@RequestParam(value="parent",required=false) Long parent) {
        List<ICategory> locations = categoryService.findByParent(parent);
      
        return locations;
    }

}
