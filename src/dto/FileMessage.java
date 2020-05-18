package dto;

import java.io.File;
import java.io.Serializable;

public class FileMessage implements Serializable {

    /*
        发送文件的类 暂时未使用到（还没完善发送文件功能）
    */
    private String sendName;
    private File file;
    public FileMessage(){}
    public FileMessage(String sendName, File file) {
        this.sendName = sendName;
        this.file = file;
    }

    public String getSendName() {
        return sendName;
    }

    public File getFile() {
        return file;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "FileMessage{" +
                "sendName='" + sendName + '\'' +
                ", file=" + file +
                '}';
    }
}
