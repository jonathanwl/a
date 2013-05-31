package com.wasoft.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.wasoft.Constant;

public class StringUtil {
	private static final Log log = LogFactory.getLog(StringUtil.class);
	public StringUtil() {
		super();
		// TODO �Զ����ɹ��캯�����
	}
	public static String repeat(String str, int num) 
	{ 		
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < num; i++) 
		{ 
			sb.append(str);			
		} 
		return sb.toString(); 
	} 
	public static String repeat(char ch, int num)
	{
		return repeat(String.valueOf(ch), num);
	}
	/**
	 * ���滻�ַ����滻�ӿ�ʼ������λ�õ��ַ���
	 * @param src	Դ�ַ���
	 * @param rep   �滻�ַ���
	 * @param start ��ʼ
	 * @param end   ����
	 * @return
	 */
	public static String replace(String src, String rep, int start, int end)
	{
		StringBuffer sb = new StringBuffer();
		sb.append(src.substring(0, start)).append(rep);		
		sb.append(src.substring(end));
		return sb.toString();
	}
	public static String replace(String src, String rep, int start)
	{
		StringBuffer sb = new StringBuffer();
		sb.append(src.substring(0, start)).append(rep);
		
		return sb.toString();
	}
	
	/**
	 * ��ָ���ַ����ԭ�ַ���������ָ�����ȵ����ַ���
	 * @param src ԭ�ַ���
	 * @param len ���ַ����ֽڳ���
	 * @param ch  ����ַ�
	 * @param left ����ture:�������  false���������
	 * @return
	 */
	public static String FillWithChar(String src, int len, char ch, boolean left)
	{				
		if (src.length() >= len) 
		{			
			try
			{
				return leftStr(src, len, -1);//src.substring(0, len);
			}
			catch(Exception e)
			{
				log.error(e.getMessage());
				return src;
			}
		} 
		else 
		{
			StringBuffer sb = new StringBuffer();
			if (left) 
			{
				sb.append(repeat(ch, len - src.getBytes().length)).append(src);
			} 
			else 
			{
				sb.append(src).append(repeat(ch, len - src.getBytes().length));
			}
			return sb.toString();
		}
	}
	
	/**
	* ȡ�ַ�����ǰtoCount���ַ�
	*
	* @param str 		�������ַ���
	* @param toCount 	��ȡ����
	* @param more 		��׺�ַ���
	* @return String
	*/
	@Deprecated 
	public static String subString(String str, int toCount, String more)
	{
		int reInt = 0;
		String reStr = "";
		if (str == null)
			return "";
		char[] tempChar = str.toCharArray();
		for (int kk = 0; (kk < tempChar.length && toCount > reInt); kk++) {
			String s1 = String.valueOf(tempChar[kk]);
			byte[] b = s1.getBytes();
			reInt += b.length;
			reStr += tempChar[kk];
		}
		if (toCount == reInt || (toCount == reInt - 1))
			reStr += more;
		return reStr;
	} 
	/*
	public static String subByteString(String src, int beginByteIndex, int endByteIndex)
	{		
		if (src == null)
			return "";
		if (beginByteIndex > endByteIndex) 
			return src;
		
		StringBuffer sb = new StringBuffer();
		char [] ch = src.toCharArray();
		
		for (int i = beginByteIndex, ch_len = 0, index = i; i < endByteIndex; i += ch_len)
		{			
			ch_len = String.valueOf(ch[index]).getBytes().length;			
			sb.append(ch[index]);
			index++; 
		}
		
		return sb.toString();
		
	}
	*/
	@Deprecated 
	public static String bbSubstring(String src, int startIndex, int bytes) throws Exception
	{
		int cutLength = 0;
		int byteNum = bytes;
		byte bt[] = src.getBytes(Constant.CHARSET);

		if (bytes > 1) 
		{
			for (int i = 0; i < byteNum; i++) {
				if (bt[i] < 0) {
					cutLength++;
				}
			}

			if (cutLength % 2 == 0) 
			{
				cutLength /= 2;
			} else {
				cutLength = 0;
			}
		}
		int result = cutLength + (--byteNum);
		
		if (result > bytes) {
			result = bytes;
		}
		if (bytes == 1) {
			if (bt[0] < 0) {
				result += 2;

			} else {
				result += 1;
			}
		}
		String substrx = new String(bt, startIndex, result);
		return substrx;
	} 	
	
	/**
	 * ���ֽڽ�ȡ�������ĺ��ֵ��ַ���
	 * @param sourceԭʼ�ַ���
	 * @param maxByteLen��ȡ���ֽ���
	 * @param flag��ʾ�����ֵķ�ʽ��1��ʾ�����������ʱ��ȫ��-1��ʾ�����������ʱ����
	 * @return
	 */
	public static String leftStr(String source, int maxByteLen, int flag) throws Exception
	{
		//log.debug("leftStr: source=" + source);
        if(source == null || maxByteLen <= 0){
            return "";
        }
        byte[] bStr = source.getBytes(Constant.CHARSET);
        if(maxByteLen >= bStr.length)
        	return source;
        String cStr = new String(bStr, maxByteLen - 1, 2);
        if(cStr.length() == 1 && source.contains(cStr)){
            maxByteLen += flag;
        }
        return new String(bStr, 0, maxByteLen);
    }
	
	public static String bSubstring(String src, int startIndex, int bytes) throws Exception
	{
		//log.debug("bSubstring: src=" + src);
		byte [] b = src.getBytes(Constant.CHARSET);
		byte [] dst = new byte[b.length - startIndex];
		for(int i = 0; i < dst.length; i++)
		{
			dst[i] = b[startIndex + i];
		}		
		return leftStr(new String(dst), bytes, 1);
	}
	public static int bLength(String src) throws Exception
	{
		byte [] b = src.getBytes(Constant.CHARSET);
		return b.length;
	}
	
	
    public static String encodePassword(String password, String algorithm) {
        byte[] unencodedPassword = password.getBytes();

        MessageDigest md = null;

        try {
            // first create an instance, given the provider
            md = MessageDigest.getInstance(algorithm);
        } catch (Exception e) {
            log.error("Exception: " + e);

            return password;
        }

        md.reset();

        // call the update method one or more times
        // (useful when you don't know the size of your data, eg. stream)
        md.update(unencodedPassword);

        // now calculate the hash
        byte[] encodedPassword = md.digest();

        StringBuffer buf = new StringBuffer();

        for (int i = 0; i < encodedPassword.length; i++) {
            if ((encodedPassword[i] & 0xff) < 0x10) {
                buf.append("0");
            }

            buf.append(Long.toString(encodedPassword[i] & 0xff, 16));
        }

        return buf.toString();
    }

    /**
     * Encode a string using Base64 encoding. Used when storing passwords
     * as cookies.
     *
     * @param str
     * @return String
     */
    public static String encodeString(String str)  {
        sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
        return encoder.encodeBuffer(str.getBytes()).trim();
    }

    /**
     * Decode a string using Base64 encoding.
     *
     * @param str
     * @return String
     */
    public static String decodeString(String str) {
        sun.misc.BASE64Decoder dec = new sun.misc.BASE64Decoder();
        try {
            return new String(dec.decodeBuffer(str));
        } catch (IOException io) {
        	throw new RuntimeException(io.getMessage(), io.getCause());
        }
    }
    
    /**
     * ��������ת��Ϊ�ַ���
     * @param is
     * @return
     * @throws Exception
     */
    public static String InputStream2String(InputStream is) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(is)); 
		StringBuffer buffer = new StringBuffer(); 
		String line = ""; 
		while ((line = in.readLine()) != null){ 
		     buffer.append(line); 
		} 
		return buffer.toString();
    }
    
	public static void main(String[] args) throws Exception 
	{
		String str = "12008111301_00235382��01957566            2008-11-17000111782.000";
		for(int i = 6; i <=str.getBytes().length; i++)
		{
			//System.out.println(bSubstring(str, i));
			//System.out.println(i + ":" + bSubstring(str,i));
			//System.out.println(i + ":" + bSubstring(str, 5,i-5));
		}
		
		System.out.println(FillWithChar("23.434343433434", 10, '0', true));
		
	}
}
