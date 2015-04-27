package jason.app.ibook.commons.service.web.controller;

import jason.app.ibook.commons.api.model.ICategory;
import jason.app.ibook.commons.api.service.ILocationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/secure/location")
public class LocationManagementController {
    @Autowired
    private ILocationService locationService;
    
    @RequestMapping(value="/addCountry",method=RequestMethod.GET)
    public @ResponseBody ICategory addCountry(@RequestParam(value="name") String name) {

        return locationService.createCountry(name);
    }
    
    @RequestMapping(value="/addState",method=RequestMethod.GET)
    public @ResponseBody ICategory addState(@RequestParam(value="name") String name,@RequestParam(value="parent") Long parent) {

        return locationService.createState(name,parent);
    }
    
    @RequestMapping(value="/addCity",method=RequestMethod.GET)
    public @ResponseBody ICategory addCity(@RequestParam(value="name") String name,@RequestParam(value="parent") Long parent) {

        return locationService.createCity(name,parent);
    }
    
    @RequestMapping(value="/addDistrict",method=RequestMethod.GET)
    public @ResponseBody ICategory addDistrict(@RequestParam(value="name") String name,@RequestParam(value="parent") Long parent) {

        return locationService.createDistrict(name,parent);
    }
}
