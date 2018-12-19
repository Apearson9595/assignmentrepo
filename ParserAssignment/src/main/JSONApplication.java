package main;

import java.io.FileWriter;

import json.JSONArray;
import task.TaskProcessor;
import task.UrlRetriever;

/**
 * application to send GET request, process the response and POST the answer
 *
 */
public class JSONApplication {

	public static void main(String[] args) throws Exception {
		FileWriter writer = new FileWriter("C:/Users/607276723/git/Assignmentrepo/ParserAssignment/src/main/output.txt");
		JSONArray taskurls = new UrlRetriever(writer).retrieveTasks("S195206");
		new TaskProcessor(writer).processTasks(taskurls);
		writer.close();
	}
}
