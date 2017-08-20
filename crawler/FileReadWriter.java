package crawler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileReadWriter {
	public static void writeToFile(String content, String pathName, boolean isAppend) throws IOException {
		// 先创建目录，在创建文件，最后写入内容
		File file = new File(pathName);
		// 如果文件不存在就创建
		if (file.exists()) {
			// 得到目录名
			pathName=pathName.replaceAll("\\\\", "/");//保持分隔符的一致性，这样应该没问题了吧，（晕，这里又出现转义，又错了一次）
			int index = pathName.lastIndexOf("/");//window的目录不一定是/为分隔符
			String dir = pathName.substring(0, index);
			// 创建目录
			File fileDir = new File(dir);
			fileDir.mkdirs();// mkdir：在已经存在的目录下建立目录；mkdirs:如果前面的目录不存在，就把前面的目录也建立起来
			// 创建文件
			file.createNewFile(); //存在就不会创建新文件
		}
		// 写入文件
		FileWriter fileWriter = null;// fileWriter在try外创建便于关闭，若在try里创建是局部变量，不能在finally关闭
		try {
			fileWriter = new FileWriter(file, isAppend); //FIleWriter可以选择是否追加
			fileWriter.write(content);
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fileWriter != null)
					fileWriter.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
