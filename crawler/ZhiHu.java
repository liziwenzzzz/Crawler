package crawler;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZhiHu {
	public String title;
	public String titleDescription;
	public String zhiHuURL;
	public ArrayList<String> answers;

	public ZhiHu(String url) {
		title = "";
		titleDescription = "";
		zhiHuURL = "";
		answers = new ArrayList<>();
		if (getRealURL(url)) {
			String content = Sprider.sendGet(zhiHuURL);
			Pattern pattern;
			Matcher matcher;
			// title
			// �ؼ�����������ʽ�ı�д�������������F12���ֵĴ�������ص���ҳԴ���벻һ��
			pattern = Pattern.compile("QuestionHeader-title\">(.*?)</h1>");
			matcher = pattern.matcher(content);
			if (matcher.find())
				title = matcher.group(1);
			// titleDescription
			pattern = Pattern.compile("RichText.*?>(.*?)</span>");
			matcher = pattern.matcher(content);
			if (matcher.find())
				titleDescription = matcher.group(1);
			// answers
			pattern = Pattern.compile("RichText CopyrightRichText-richText.*?>(.*?)</span>");
			matcher = pattern.matcher(content);
			while (matcher.find()) {
				answers.add(matcher.group(1));
			}
		}
	}

	@Override
	public String toString() {
		String result = "";
		result += "title:" + title + "\n" + "titleDescription:" + titleDescription + "\r\n" + "֪������:" + zhiHuURL
				+ "\r\n";
		//result += answers.size() + "answer:\n";
		//Ϊ��ô�����answerֻ������������Դ���룬�����Ĵ𰸲��Ǿ�̬�ģ���
		for (int i = 1; i <= answers.size(); i++) {
			result += "answer" + i + ":" + answers.get(i - 1) + "\n";
		}
		// replaceAll������ԭ�ظı��ַ���
		result = result.replaceAll("<br>", "\n");
		// ע��������ʽ�����\,��Ҫת��
		result = result.replaceAll("<.*?>||\\s+", "");
		return result;
	}

	boolean getRealURL(String url) {
		Pattern pattern = Pattern.compile("question/(.*?)/");
		Matcher matcher = pattern.matcher(url);
		if (matcher.find()) {
			// �������Ѿ��ҵ���url
			zhiHuURL = "https://www.zhihu.com/question/" + matcher.group(1);
			return true;
		}
		return false;
	}
}
