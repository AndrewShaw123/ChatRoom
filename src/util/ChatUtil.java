package util;

import java.io.Closeable;

public class ChatUtil {

	/*
		出现异常时候关闭资源的类
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
