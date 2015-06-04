package jason.app.ibook.job.craweller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleBean {
	private Logger logger = LoggerFactory.getLogger(ExampleBean.class);
    public Result sayHello(Task task) throws MalformedURLException, IOException {
/**    	logger.info("Hello example bean!"+name);
    	String url = name;
    	if(url.indexOf(">")>=0) {
    		url = url.substring(url.indexOf(">")+1);
    	}
    	if(url.indexOf("<")>=0) {
    		url = url.substring(0,url.indexOf("<"));
    	}
    	//int pageNo = Integer.parseInt(url.substring(url.indexOf("/p")+2));
    	url = "http://company.zhaopin.com"+url;
    	Document doc = Jsoup.parse(new URL(url),5000);
    	Elements companys = doc.select(".jobs-list-box");
    	Iterator<Element> elements = companys.iterator();
    	List<Company> companies = new ArrayList<Company>();

    	while(elements.hasNext()) {
    		Element element  = elements.next();
    		// logger.info(element.html());
    		Element nameField = element.select(".checkjobs > a").first();
    		Company company = new Company();
    		company.setId(nameField.attr("href"));
    		company.setName(nameField.text());
    		companies.add(company);
    	}
    	Result result = new Result();
    	Iterator<Element> pages = doc.select(".pageBar span").iterator();
    	boolean isCurrent = false;
    	while(pages.hasNext()) {
    		Element ele = pages.next();
    		if(ele.hasClass("current")) {
    			isCurrent = true;
    			continue;
    		}
    		if(isCurrent) {
    			result.setNextPage(ele.child(0).attr("href"));
    			isCurrent = false;
    			break;
    		}
    	}
    	
    	result.setCompany(companies);
        return result;
        */
    	Result result = new Result();
    	List<Company> companies = new ArrayList<Company>();
    	for(int i=task.getId();i<task.getId()+4;i++) {
    		Company company = new Company();
    		company.setId(""+i);
    		company.setName("company"+i);
    		companies.add(company);
    	}
    	result.setCompany(companies);
    	if(task.getId()<10) {
	    	task.setId(task.getId()+4);
	    	result.setTask(task);
    	}
    	return result;
    }
}