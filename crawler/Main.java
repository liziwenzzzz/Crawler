package crawler;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) throws IOException {
		String result = Sprider.sendGet("https://www.zhihu.com/explore/recommendations");
		ArrayList<ZhiHu> resultList = Sprider.getZhiHu(result);
		for (ZhiHu zhiHu : resultList) //for循环加花括号
		{
			FileReadWriter.writeToFile(zhiHu.toString()+"\n", "E:\\zhihu.txt",true ); //这里好像每次每个zhihu都要打开文件写入
			System.out.println(zhiHu);
		}
			
	}
}
