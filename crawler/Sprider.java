package crawler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.lang.model.element.PackageElement;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

public class Sprider {
	public static String sendGet(String url) {
		BufferedReader buf = null;
		String result = "";
		try {
			//创建URL和URLConnection对象
			URL realURL = new URL(url);   
			URLConnection connection = realURL.openConnection();
			//得到一个输入流，可以指定编码方式为"utf-8"
			buf = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			String line = "";
			while ((line = buf.readLine()) != null) {
				result += line+"\n";
				//查看源代码是否完整，刚开始不完整原来是因为eclipse的控制台输出被限制行数了
				//System.out.println(line+"\n");
			}
			return result;
		} catch (Exception e) {
			throw new RuntimeException("get wrong");
		} finally {
			try {
				if (buf != null)
					buf.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	//通过正则表达式得到问题的链接,来创建ZhiHu对象
	public static ArrayList<ZhiHu> getZhiHu(String targetStr) {
		Pattern urlRegex = Pattern.compile("<h2>.*?question_link.*?href=\"(.*?)\"");
		Matcher url = urlRegex.matcher(targetStr);
		ArrayList<ZhiHu> list = new ArrayList<>();
		while (url.find()) {
			//System.out.println(url.group(1));
			ZhiHu zhiHu = new ZhiHu(url.group(1));
			list.add(zhiHu);
		}
		return list;
	}
}
