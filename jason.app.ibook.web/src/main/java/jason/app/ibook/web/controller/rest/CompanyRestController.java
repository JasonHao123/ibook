package jason.app.ibook.web.controller.rest;

import jason.app.ibook.job.api.model.Company;
import jason.app.ibook.job.api.model.Department;
import jason.app.ibook.job.api.service.ICompanyService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/rest")
public class CompanyRestController {
    @Autowired(required=false)
   private ICompanyService companyService;
    
    @RequestMapping(value="/department/list",method=RequestMethod.GET)
    public @ResponseBody List<Department> getDepartments(@RequestParam(value="companyId",required=false) Long parent) {
        if(companyService!=null) {
            Company company = new Company();
            company.setId(parent);
            return companyService.findDepartments(company);
        }else {
            List<Department> departments = new ArrayList<Department>();
            for(int i=0;i<5;i++) {
            Department company = new Department();
            company.setId((long) i);
            company.setName("test"+i);
            departments.add(company);
            }
            return departments;
        }
      
       
    }
}
