package task;

import http.GetRequester;
import json.JSONArray;
import json.JSONDocument;
import json.JSONObject;
import parser.JSONParser;

/**
 * retrieves the taskurls's for GET HTTP request
 *
 */
public class UrlRetriever {

	/**
	 * retrieves taskurls for a given studnetId
	 * 
	 * @param studentId to retrieve task for
	 * @return the JSONArray of the task url for the given student
	 * @throws Exception if HTPP request response is not 200 or a parsing issue is
	 *                   found
	 */
	public JSONArray retrieveTasks(String studentId) throws Exception {

		GetRequester http = new GetRequester();
		String requesturl = "/student?id=" + studentId;
		String response = http.sendGet(requesturl);
		JSONParser parser = new JSONParser();
		JSONDocument parserResponse = parser.parse(response);
		JSONObject jsonObject = parserResponse.getAsObject();
		JSONArray taskurls = jsonObject.get("\"tasks\"").getAsArray();
		return taskurls;
	}

}