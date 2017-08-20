package crawler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileReadWriter {
	public static void writeToFile(String content, String pathName, boolean isAppend) throws IOException {
		// �ȴ���Ŀ¼���ڴ����ļ������д������
		File file = new File(pathName);
		// ����ļ������ھʹ���
		if (file.exists()) {
			// �õ�Ŀ¼��
			pathName=pathName.replaceAll("\\\\", "/");//���ַָ�����һ���ԣ�����Ӧ��û�����˰ɣ����Σ������ֳ���ת�壬�ִ���һ�Σ�
			int index = pathName.lastIndexOf("/");//window��Ŀ¼��һ����/Ϊ�ָ���
			String dir = pathName.substring(0, index);
			// ����Ŀ¼
			File fileDir = new File(dir);
			fileDir.mkdirs();// mkdir�����Ѿ����ڵ�Ŀ¼�½���Ŀ¼��mkdirs:���ǰ���Ŀ¼�����ڣ��Ͱ�ǰ���Ŀ¼Ҳ��������
			// �����ļ�
			file.createNewFile(); //���ھͲ��ᴴ�����ļ�
		}
		// д���ļ�
		FileWriter fileWriter = null;// fileWriter��try�ⴴ�����ڹرգ�����try�ﴴ���Ǿֲ�������������finally�ر�
		try {
			fileWriter = new FileWriter(file, isAppend); //FIleWriter����ѡ���Ƿ�׷��
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
