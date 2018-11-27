package Instruction;

public class Concat {

	public Concat() {
	}
		public String doconcat(String params) {
			
			String[] splitString = params.split(",");
			String param1 = splitString [0];
			String param2 = splitString [1];
			String result = param1 + param2;
			
			return result;
			// 3979,1990
	}
}
