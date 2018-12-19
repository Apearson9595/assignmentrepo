package task;

import java.io.FileWriter;
import java.io.IOException;

import http.GetRequester;
import http.PostResponse;
import instruction.InstructionProcessor;
import json.JSONArray;
import json.JSONDocument;
import json.JSONObject;
import parser.JSONParser;

/**
 * class to process tasks
 *
 */
public class TaskProcessor {

	private FileWriter writer;

	public TaskProcessor(FileWriter writer) {
		this.writer = writer;
	}

	/**
	 * retrieves the tasks from the taskurls, performs the instructions and posts
	 * the answer to the expected url
	 * 
	 * @param urls the taskurls to process
	 * @throws Exception if the server responds with anything other than 200
	 */
	public void processTasks(JSONArray urls) throws Exception {
		GetRequester http = new GetRequester();
		JSONParser parser = new JSONParser();
		String taskUrl = null;
		for (JSONDocument document : urls.getList()) {
			try {
				taskUrl = document.toString().replaceAll("\"", "");
				String task = http.sendGet(taskUrl);
				writer.write(task + "\n");
				JSONObject taskResponse = parser.parse(task).getAsObject();

				String taskInstruction = taskResponse.get("\"instruction\"").toString().replaceAll("\"", "");
				JSONArray parameters = taskResponse.get("\"parameters\"").getAsArray();
				String responseurl = taskResponse.get("\"response URL\"").toString().replaceAll("\"", "");
				String param1 = parameters.get(0).toString().replaceAll("\"", "");
				String param2 = parameters.get(1).toString().replaceAll("\"", "");
				PostResponse post = new PostResponse();
				String answer = new InstructionProcessor().process(taskInstruction, param1, param2);
				post.sendPost(responseurl, answer);
				writer.write(answer + "\n");
			} catch (IOException e) {
				PostResponse post = new PostResponse();
				String error = "Error " + e.getMessage();
				post.sendPost(taskUrl, error);
				writer.write( error + "\n");
			}
		}
	}
}
