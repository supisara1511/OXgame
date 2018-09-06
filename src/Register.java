import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Properties;
import org.json.JSONException;
import org.json.JSONObject;


public class Register {
    public static JSONObject sendToServer(String username,String password,String name,String surname,String email) throws MalformedURLException, ProtocolException, IOException, JSONException {
    String url = "http://www.9develop.com:12123/api/user/register";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		String urlParameters = "username="+username+"&password="+password+"&email="+email+"&name="+name+"&surname="+surname;

		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
                if(con.getResponseCode()==200){
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    JSONObject jsonObj = new JSONObject(response.toString());

                    return jsonObj;

                }else{
                    System.out.println("username or password incorrect");
                }
				return null;
    }
}
