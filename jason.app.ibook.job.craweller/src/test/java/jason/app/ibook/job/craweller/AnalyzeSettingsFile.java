package jason.app.ibook.job.craweller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class AnalyzeSettingsFile {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			BufferedReader reader = new BufferedReader(new FileReader("basedata"));
			String line = null;
			while((line=reader.readLine())!=null) {
				analyzeLine(line);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void analyzeLine(String line) {
		// TODO Auto-generated method stub
		Hashtable<String,Integer> typeMap = new Hashtable<String,Integer>();
		Hashtable<String,Integer> subTypeMap = new Hashtable<String,Integer>();
		typeMap.put("dIndustry",1);
		typeMap.put("dCity",0);
		typeMap.put("dJobtype",0);
		typeMap.put("dSubjobtype",0);
		typeMap.put("dDate",0);
		typeMap.put("dExpe",0);
		typeMap.put("dDegree",0);
		typeMap.put("dComptype",0);
		typeMap.put("dCompsize",0);
		typeMap.put("dDistrict",0);
		typeMap.put("dDegree",0);
		typeMap.put("dDegree",0);
		
		int pos = line.indexOf("=");
		if(pos>0) {
			String type = line.substring(0,pos);
			type = type.replace("var", "").trim();
			String data = line.substring(pos+1);
			if(data.startsWith("'@")) {
				data = data.substring(2);
				data = data.substring(0,data.length()-3);
				String[] items = data.split("@");			
				System.out.println(type);
				for(String item:items) {
				System.out.println(item);
				
			}

			}
		}
	}

}
