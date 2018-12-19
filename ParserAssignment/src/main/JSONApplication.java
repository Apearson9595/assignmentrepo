package main;

import json.JSONArray;
import task.TaskProcessor;
import task.UrlRetriever;

/**
 * application to send GET request, process the response and POST the answer
 *
 */
public class JSONApplication {

	public static void main(String[] args) throws Exception {

		JSONArray taskurls = new UrlRetriever().retrieveTasks("S195206");
		new TaskProcessor().processTasks(taskurls);
	}
}
