package http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Class used for post response
 *
 */
public class PostResponse {

	/**
	 * Sends a POST HTTP request to external server (http://i2j.openode.io)
	 * Appending the posturl parameter to complete the URL
	 * the answer parameter is the contents of the POST request
	 * @param posturl This is the text to append to the server URL
	 * @param answer This is the body of the post request
	 * @throws Exception if the server responds with anything other than 200
	 */
	public void sendPost(String posturl, String answer) throws Exception {

		String url = "http://i2j.openode.io";
		url = url + posturl;
		URL finalposturl = new URL(url);
		HttpURLConnection con = (HttpURLConnection) finalposturl.openConnection();

		// add request header
		con.setRequestMethod("POST");

		// Send post request
		con.setDoOutput(true);
		DataOutputStream road = new DataOutputStream(con.getOutputStream());
		road.writeBytes(answer);
		road.flush();
		road.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + answer);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		System.out.println(response.toString());

	}
}
