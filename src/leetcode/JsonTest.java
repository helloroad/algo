package leetcode;
import org.json.*;
public class JsonTest {

	public JsonTest() {
		
	}
	
	public void test() {
		String jsonStr = "{\"abc\":\"abc\"}";
		JSONObject jObj = new JSONObject(jsonStr);
		String str = jObj.toString();
		String abc = jObj.getString("abc");
		
		Object obj = new JSONObject(new Object());
	}

}
