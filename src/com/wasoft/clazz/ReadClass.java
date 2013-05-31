package com.wasoft.clazz;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

public class ReadClass {
    
    //16进制数字字符集 
    private static String hexString = "0123456789ABCDEF"; 

    /**
     * 将字节数组的指定长度部分编码成16进制数字字符串
     * @param buffer 待编码的字节数组
     * @param length 指定的长度
     * @return 编码后连接而成的字符串
     */
    public static String encode(byte[] buffer,int length) 
    {     
        StringBuilder sbr = new StringBuilder();
        //将字节数组中每个字节拆解成2位16进制整数 
        for(int i=0;i<length;i++) 
        { 
            sbr.append(hexString.charAt((buffer[i]&0xf0)>>4)); 
            sbr.append(hexString.charAt(buffer[i]&0x0f)); 
            sbr.append("  ");
        } 
        return sbr.toString(); 
    } 
    
    /**
     * 读取一个Class文件，将其所有字节转换为16进制整数，并以字符形式输出
     * @param inputPath  输入文件的完整路径
     * @param outputPath 输出文件的完整路径
     * @throws IOException 读写过程中可能抛出的异常
     */
    public static void rwclass(String inputPath, String outputPath) throws IOException
    {
        //读取Class文件要用字节输入流
        BufferedInputStream bis = new BufferedInputStream(
                new FileInputStream(inputPath));
        //输出转换后的文件要用字符输出流
        BufferedWriter bw = new BufferedWriter(
                new FileWriter(outputPath));
        
        int readSize = 16;
        byte[] buffer_read = new byte[readSize];
        String line;
        String lineNumber = "0000000";
        String strReplace;
        int i = 0;
       while ((readSize = bis.read(buffer_read,0,readSize))!= -1)
        {
            line = encode(buffer_read,readSize);
            strReplace = Integer.toHexString(i);
            lineNumber = lineNumber.substring(0, 7-strReplace.length());
            lineNumber = lineNumber+strReplace;
            line = lineNumber+"0h: "+line;
            bw.write(line);
            bw.newLine();
            i++;
        }
        bis.close();
        bw.close();
    }

    /**
     * 程序的入口方法
     * @param args
     * @throws IOException
     */
    public static void main(String[] args)
    {
        //指定输入、输出文件的完整路径
        String inputPath = "D:\\My Data\\My Documents\\eclipse\\workspace\\test\\FileHandle.class";
        String outputPath = "D:\\My Data\\My Documents\\eclipse\\workspace\\test\\FileHandle.txt";
        
        try {
            rwclass(inputPath, outputPath);    
            System.out.println("Successfully !");
       } catch (IOException ioe) {
            System.err.println("Something wrong with reading or writing !");
            ioe.printStackTrace();            
        }    
        
    }

}