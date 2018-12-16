package main;

import java.io.IOException;
import java.util.Map;

import http.GetRequester;
import http.PostResponse;
import instruction.Add;
import instruction.Concat;
import instruction.Instruction;
import instruction.InstructionProcessor;
import instruction.Multiply;
import parser.Parser;
import parser2.JSONArray;
import parser2.JSONDocument;
import parser2.JSONObject;
import parser2.Parser2;
import task.TaskProcessor;
import task.UrlRetriever;

public class Main {

	public static void main(String[] args) throws Exception {

		JSONArray taskurls = new UrlRetriever().retrieveTasks("S195206");
		new TaskProcessor().processTasks(taskurls);
		}
}
