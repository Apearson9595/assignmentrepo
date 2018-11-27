package main;

import java.util.Map;

import Instruction.Add;
import Instruction.Concat;
import Instruction.Instruction;
import Instruction.Add;
import http.GetRequester;
import http.PostResponse;
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
			
			//add process
			Add add = new Add();
			String parameters = taskresponsemap.get("parameters");
			String answer = add.doadd(parameters);
			String responseurl = taskresponsemap.get("responseurl");
			
			//multiply  process
			//Multiply multiply = new Multiply();
			//String parameters = taskresponsemap.get("parameters");
			//String answer = multiply.domultiply(parameters);
			//String responseurl = taskresponsemap.get("responseurl");
			
			//concat  process
			//Concat concat = new Concat();
			//String parameters = taskresponsemap.get("parameters");
			//String answer = concat.doconcat(parameters);
			//String responseurl = taskresponsemap.get("responseurl");
			
			PostResponse post = new PostResponse();
			System.out.println("Send POST request with answer");	
     		post.sendPost(responseurl,answer);
     	    				
		}
}

