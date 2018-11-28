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

public class Main {

		public static void main(String[] args) throws Exception {

			GetRequester http = new GetRequester();

			System.out.println("Send Http GET request to request task");			
			String requesturl = "/student?id=S195206";
			// response = "/task/3070"
			String response	= http.sendGet(requesturl);
			
			Parser parser = new Parser();
			String parserResponse = parser.parseurl(response);
			
			System.out.println("Send GET request using request task response");
			// task = {"instruction":"add","parameters":[3979, 1990],"response URL":"/answer/3070"}
			String task = http.sendGet(parserResponse);
			Map<String, String> taskresponsemap = parser.parsetask(task);
			
			String taskinstruction = taskresponsemap.get("instruction");
			String parameters = taskresponsemap.get("parameters");
			String responseurl = taskresponsemap.get("responseurl");
			String answer = null;
			Instruction instruction = null;
			if (taskinstruction.equals ("add") ) {
				instruction = new Add();
				}
			else if (taskinstruction.equals ("multiply") ) {
				instruction = new Multiply();
				}
			else if (taskinstruction.equals ("concat") ) {
				instruction = new Concat();
				}
			else {
				throw new IOException("not a valid instruction");
			}
			answer = instruction.doInstruction(parameters);	
			PostResponse post = new PostResponse();
			System.out.println("Send POST request with answer");
			post.sendPost(responseurl,answer);
     				
		}
}

