package util;

import java.io.Closeable;

public class ChatUtil {

	/*
		�����쳣ʱ��ر���Դ����
	*/
	public static void close(Closeable... targets) {
		for(Closeable t:targets) {
			try {
				if(t!=null) {
					t.close();
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}

}
