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
			// 关键在于正则表达式的编写，这里浏览器的F12出现的代码和下载的网页源代码不一样
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
		result += "title:" + title + "\n" + "titleDescription:" + titleDescription + "\r\n" + "知乎链接:" + zhiHuURL
				+ "\r\n";
		//result += answers.size() + "answer:\n";
		//为甚么这里的answer只有两个，看了源代码，其他的答案不是静态的，晕
		for (int i = 1; i <= answers.size(); i++) {
			result += "answer" + i + ":" + answers.get(i - 1) + "\n";
		}
		// replaceAll不能在原地改变字符串
		result = result.replaceAll("<br>", "\n");
		// 注意正则表达式如果有\,需要转义
		result = result.replaceAll("<.*?>||\\s+", "");
		return result;
	}

	boolean getRealURL(String url) {
		Pattern pattern = Pattern.compile("question/(.*?)/");
		Matcher matcher = pattern.matcher(url);
		if (matcher.find()) {
			// 在这里已经找到了url
			zhiHuURL = "https://www.zhihu.com/question/" + matcher.group(1);
			return true;
		}
		return false;
	}
}
