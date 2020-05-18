package util;

import java.io.*;

public class IOUtil {

    public static void  saveUserIcon(File file,String name){

        String fileName=file.toString();
        String suffix=fileName.substring(fileName.lastIndexOf("."));
        System.out.println("PATH=src\\img\\usericon\\"+name+suffix);
        FileOutputStream fos=null;
        FileInputStream fis=null;
        try{
            fis=new FileInputStream(file);
            fos=new FileOutputStream("src\\img\\usericon\\"+name+suffix);

            byte[] flush = new byte[1024];
            int len = -1;
            while ((len = fis.read(flush)) != -1) {
               fos.write(flush, 0, len);
               fos.flush();
            }

        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void writeHistory(String message,String clientOrService,boolean isAppend){
        BufferedWriter bw=null;
        try {
            bw=new BufferedWriter(new FileWriter("src\\history\\"+clientOrService+"History.txt",isAppend));
            bw.write(message);
            bw.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public static String readHistory(String clientOrService){
        BufferedReader br=null;
        StringBuilder Message=new StringBuilder();
        try {
            String str="";
            File file=new File("src\\history\\"+clientOrService+"History.txt");
            if(!file.exists()){
                return "";
            }
            br=new BufferedReader(new FileReader(file));
            while((str = br.readLine()) != null){
                Message.append(str);
                Message.append("\n");
            }
            return Message.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    //实现接收文件的方法
    public static void saveFile(File receive,File save){
        FileInputStream fis=null;
        FileOutputStream fos=null;
        try {
            fis=new FileInputStream(receive);
            fos=new FileOutputStream(save);
            byte[] b = new byte[1024];
            int len=-1;
            while((len=fis.read(b))!=-1){
                fos.write(b);
                fos.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
