package parser;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class Parser {

	public Parser() {
		// TODO Auto-generated constructor stub
		//{"id":"s195206","tasks":["/task/3070","/task/5105","/task/5586","/task/5590","/task/8423","/task/5473","/task/6528","/task/3383","/task/8386","/task/8016"]}
	}
	public String parseurl(String jSON) {
		return "/task/3070";
	}
	
	public Map<String, String> parsetask(String jSON) {
		Map <String, String> jSONMap;
		String instruction = "add";
		String parameters = "3979,1990";
		String responseurl = "/answer/3070";
		jSONMap = new HashMap <String,String>();
		jSONMap.put("instruction", instruction);
		jSONMap.put("parameters", parameters);
		jSONMap.put("responseurl", responseurl);
		
		return jSONMap;
	}
}
