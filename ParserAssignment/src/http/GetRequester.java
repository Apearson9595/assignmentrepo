package http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Class used for GET HTTP request
 *
 */
public class GetRequester {
	
	/**
	 * Sends a GET HTTP request to external server (http://i2j.openode.io) Appending
	 * the finalurl parameter to complete the URL
	 * 
	 * @param finalurl This is the text to append to the server URL
	 * @return The return JSON response from the GET request
	 * @throws Exception if the server responds with anything other than 200
	 */
	public String sendGet(String finalurl) throws Exception {
		String url = "http://i2j.openode.io";
		url = url + finalurl;
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		System.out.println(response.toString());
		return response.toString();

	}

}
