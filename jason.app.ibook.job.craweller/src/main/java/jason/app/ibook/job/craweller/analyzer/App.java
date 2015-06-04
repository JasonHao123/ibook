package jason.app.ibook.job.craweller.analyzer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("basedata.js"));
            String line = null;

            while((line=reader.readLine())!=null) {
                if(line.trim().length()==0) continue;
                AnalyzeResult result = analyzeLine(line);
                int type =-1;
                int subType = -1;
                //if(result.getType()==null) continue;
                if("﻿dIndustry".equals(result.getType().trim())) {                
                    type = 7;
                    subType = 1;
                }else if("dCity".equals(result.getType().trim())) {
                    type = 0;
                    subType = 1;
                }else if("dJobtype".equals(result.getType())) {
                    type = 1;
                    subType = 1;
                }
                else if("dSubjobtype".equals(result.getType().trim())) {
                    type = 1;
                    subType = 2;
                }else if("dDate".equals(result.getType().trim())) {
                    type = 3;
                    subType = -1;
                }else if("dExpe".equals(result.getType().trim())) {
                    type = 8;
                    subType = -1;
                }else if("dDegree".equals(result.getType().trim())) {
                    type = 5;
                    subType = -1;
                }else if("dComptype".equals(result.getType().trim())) {
                    type = 9;
                    subType = -1;
                }
                else if("dCompsize".equals(result.getType().trim())) {
                    type = 11;
                    subType = -1;
                }else if("dDistrict".equals(result.getType().trim())) {
                    type = 0;
                    subType = 3;
                }else if("jobtypeClass".equals(result.getType().trim())) {
                    type = 1;
                    subType = 0;
                }else if("industryClass".equals(result.getType().trim())) {
                    type = 7;
                    subType = 0;
                }
                for(Item item:result.getResult()) {
                    item.setType(type);
                    item.setSubType(subType);
                    System.out.println(toSQL(item));
                }
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    private static String toSQL(Item item) {
        // TODO Auto-generated method stub
        StringBuilder builder = new StringBuilder();

        if(item.getParent()!=null) {
        	 builder.append("insert into CATEGORY ");
             builder.append(" (name,PARENT_ID,type,subType,code) ");
             builder.append(" select ");
             builder.append(" '"+item.getName()+"',");
             builder.append(" cata.id,");
             builder.append(" "+item.getType()+",");
             builder.append(" "+item.getSubType()+",");
             builder.append("'"+item.getId()+"' from CATEGORY cata where cata.code ='"+item.getParent()+"' and cata.type ='"+item.getType()+"';");
        }else {
            builder.append("insert into CATEGORY ");
            builder.append(" (name,PARENT_ID,type,subType,code)");
            builder.append(" values (");
            builder.append(" '"+item.getName()+"',");
            builder.append(" null,");
            builder.append(" "+item.getType()+",");
            builder.append(" "+item.getSubType()+",");
            builder.append("'"+item.getId()+"');");
        }


        return builder.toString();
    }

    private static AnalyzeResult analyzeLine(String line) {
        AnalyzeResult result = new AnalyzeResult();
        List<Item> items = new ArrayList<Item>();
        int pos = line.indexOf("=");
        if(pos>0) {
            String var = line.substring(0,pos);
            var = var.replace("var ", "").trim();
            String data = line.substring(pos+1);
    //        System.out.println(var);
            result.setType(var);
            if(data.startsWith("'@")) {
                data = data.substring(2,data.length()-2);
                String[] segments = data.split("@");
                for(String segment:segments) {
                    String[] temp = segment.split("\\|");
                    if(temp.length==2) {
                        Item item = new Item();
                        item.setId(temp[0]);
                        item.setName(temp[1]);
                        items.add(item);                       
                    }else if(temp.length==3) {
                        Item item = new Item();
                        item.setId(temp[0]);
                        item.setName(temp[1]);
                        item.setParent(temp[2]);
                        items.add(item);
                       // System.out.println(temp[0]+" "+temp[1]+" "+temp[2]);
                    }else {
                        System.out.println(segment);
                    }
                }
            }else if(data.startsWith("{") || data.startsWith("[")){
                // analyze jackson data
                data = data.replaceAll("'", "\"");
                ObjectMapper mapper = new ObjectMapper();
                try {
                    if(data.startsWith("[")) {
                        JsonNode user = mapper.readTree(data);
                        Iterator<JsonNode> tree = user.iterator();
                        while(tree.hasNext()) {
                            JsonNode node = tree.next();
                            if(node.has("id") && node.has("name")) {
                                Item item = new Item();
                                item.setId(node.get("id").asText());
                                item.setName(node.get("name").asText());
                                items.add(item);
                            }
                        }
                    }
                } catch (JsonParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (JsonMappingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
           
           // System.out.println(data);
        }
        result.setResult(items);
        return result;
    }
}
