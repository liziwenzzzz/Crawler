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
			//����URL��URLConnection����
			URL realURL = new URL(url);   
			URLConnection connection = realURL.openConnection();
			//�õ�һ��������������ָ�����뷽ʽΪ"utf-8"
			buf = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			String line = "";
			while ((line = buf.readLine()) != null) {
				result += line+"\n";
				//�鿴Դ�����Ƿ��������տ�ʼ������ԭ������Ϊeclipse�Ŀ���̨���������������
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

	//ͨ��������ʽ�õ����������,������ZhiHu����
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
