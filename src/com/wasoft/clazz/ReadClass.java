package com.wasoft.clazz;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

public class ReadClass {
    
    //16���������ַ��� 
    private static String hexString = "0123456789ABCDEF"; 

    /**
     * ���ֽ������ָ�����Ȳ��ֱ����16���������ַ���
     * @param buffer ��������ֽ�����
     * @param length ָ���ĳ���
     * @return ��������Ӷ��ɵ��ַ���
     */
    public static String encode(byte[] buffer,int length) 
    {     
        StringBuilder sbr = new StringBuilder();
        //���ֽ�������ÿ���ֽڲ���2λ16�������� 
        for(int i=0;i<length;i++) 
        { 
            sbr.append(hexString.charAt((buffer[i]&0xf0)>>4)); 
            sbr.append(hexString.charAt(buffer[i]&0x0f)); 
            sbr.append("  ");
        } 
        return sbr.toString(); 
    } 
    
    /**
     * ��ȡһ��Class�ļ������������ֽ�ת��Ϊ16���������������ַ���ʽ���
     * @param inputPath  �����ļ�������·��
     * @param outputPath ����ļ�������·��
     * @throws IOException ��д�����п����׳����쳣
     */
    public static void rwclass(String inputPath, String outputPath) throws IOException
    {
        //��ȡClass�ļ�Ҫ���ֽ�������
        BufferedInputStream bis = new BufferedInputStream(
                new FileInputStream(inputPath));
        //���ת������ļ�Ҫ���ַ������
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
     * �������ڷ���
     * @param args
     * @throws IOException
     */
    public static void main(String[] args)
    {
        //ָ�����롢����ļ�������·��
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