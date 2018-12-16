package parser2;

import java.util.HashMap;
import java.util.Map;

//used extends over Implements as the methods are inherited but more mutable
//to add more methods when needed
public class JSONObject extends JSONDocument{
	
	@Override
	public JSONObject getAsObject() {return this;}
	
	private Map <String, JSONDocument> mapObject = new HashMap<>();

	public void addToMap(String key, JSONDocument value) {
		mapObject.put(key,value); 
	}
	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append('{');
		for (Map.Entry<String, JSONDocument> entry:mapObject.entrySet()) {
			stringBuffer.append(entry.getKey());
			stringBuffer.append('=');
			stringBuffer.append(entry.getValue());					
		stringBuffer.append(',');
		}
		String s = stringBuffer.toString();		
		if(s.charAt(s.length()-1)==',') {
			s = s.substring(0, s.length()-1);
		}
		s = s + "}";
		return s;
	}
	public JSONDocument get(String key) {
		return mapObject.get(key);
		}


}
