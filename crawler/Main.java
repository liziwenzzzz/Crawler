package crawler;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) throws IOException {
		String result = Sprider.sendGet("https://www.zhihu.com/explore/recommendations");
		ArrayList<ZhiHu> resultList = Sprider.getZhiHu(result);
		for (ZhiHu zhiHu : resultList) //forѭ���ӻ�����
		{
			FileReadWriter.writeToFile(zhiHu.toString()+"\n", "E:\\zhihu.txt",true ); //�������ÿ��ÿ��zhihu��Ҫ���ļ�д��
			System.out.println(zhiHu);
		}
			
	}
}
