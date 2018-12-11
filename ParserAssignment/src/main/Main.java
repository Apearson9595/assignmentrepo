package main;

import java.io.IOException;
import java.util.Map;

import http.GetRequester;
import http.PostResponse;
import instruction.Add;
import instruction.Concat;
import instruction.Instruction;
import instruction.Multiply;
import parser.Parser;
import parser2.JSONArray;
import parser2.JSONDocument;
import parser2.JSONObject;
import parser2.Parser2;

public class Main {

	public static void main(String[] args) throws Exception {

		GetRequester http = new GetRequester();
		String requesturl = "/student?id=S195206";
		// response = "/task/3070"
		String response = http.sendGet(requesturl);

		Parser2 parser = new Parser2();
		JSONDocument parserResponse = parser.parse(response);
		JSONObject jsonObject = parserResponse.getAsObject();
		JSONArray taskurls = jsonObject.get("\"tasks\"").getAsArray();
		String taskUrl = null;
		for (JSONDocument document : taskurls.getList()) {		
			try {	
			taskUrl =document.toString().replaceAll("\"", "");
			String task = http.sendGet(taskUrl);
			JSONObject taskResponse = parser.parse(task).getAsObject();

			String taskinstruction = taskResponse.get("\"instruction\"").toString().replaceAll("\"", "");
			;
			JSONArray parameters = taskResponse.get("\"parameters\"").getAsArray();
			String responseurl = taskResponse.get("\"response URL\"").toString().replaceAll("\"", "");
			;
			String answer = null;
			Instruction instruction = null;
			if (taskinstruction.equals("add")) {
				instruction = new Add();
			} else if (taskinstruction.equals("multiply")) {
				instruction = new Multiply();
			} else if (taskinstruction.equals("concat")) {
				instruction = new Concat();
			} else {
				throw new IOException("not a valid instruction");
			}
			String param1 = parameters.get(0).toString().replaceAll("\"", "");
			String param2 = parameters.get(1).toString().replaceAll("\"", "");
			answer = instruction.doInstruction(param1, param2);
			PostResponse post = new PostResponse();
			post.sendPost(responseurl, answer);
			}
			catch (IOException e) {
				PostResponse post = new PostResponse();
				post.sendPost(taskUrl, "Error " + e.getMessage());
			}
		}
	}
}
