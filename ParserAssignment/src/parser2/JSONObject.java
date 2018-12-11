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
		return mapObject.toString().replace(" ", "");
		}
	public JSONDocument get(String key) {
		return mapObject.get(key);
		}


}
