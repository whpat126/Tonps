package com.pt.utils;
/**
 * 加密工具类
 * @author whp 2015-06-12
 *
 */
public class Encode
{

//    public Encode()
//    {
//    }

	/**
	 * 解密字符串
	 * @param encyString 加密后的字符串
	 * @return srcString 解密后的字符串
	 */
    public static String decode(String encyString)
    {
        if(encyString == null)
            return null;
        String srcString = "";
        DES des = new DES(getKey());
        byte sBytes[] = encyString.getBytes();
        for(int i = 0; i < sBytes.length / 16; i++)
        {
            byte theBytes[] = new byte[8];
            for(int j = 0; j <= 7; j++)
            {
                byte byte1 = (byte)(sBytes[16 * i + 2 * j] - 97);
                byte byte2 = (byte)(sBytes[16 * i + 2 * j + 1] - 97);
                theBytes[j] = (byte)(byte1 * 16 + byte2);
            }

            long x = des.bytes2long(theBytes);
            byte result[] = new byte[8];
            des.long2bytes(des.decrypt(x), result);
            srcString = (new StringBuilder()).append(srcString).append(new String(result)).toString();
        }
        srcString=srcString.trim();

        return srcString;
    }

    /**
	 * 加密字符串
	 * @param srcString 解密后的字符串
	 * @return encyString 加密后的字符串
	 */
    public static String encode(String srcString)
    {
        if(srcString == null)
            return null;
        String encyString = "";
        DES des = new DES(getKey());
        byte space = 32;
        byte sBytes[] = srcString.getBytes();
        int length = sBytes.length;
        int newLength = length + (8 - length % 8) % 8;
        byte newBytes[] = new byte[newLength];
        for(int i = 0; i < newLength; i++)
            if(i <= length - 1)
                newBytes[i] = sBytes[i];
            else
                newBytes[i] = space;

        for(int i = 0; i < newLength / 8; i++)
        {
            byte theBytes[] = new byte[8];
            for(int j = 0; j <= 7; j++)
                theBytes[j] = newBytes[8 * i + j];

            long x = des.bytes2long(theBytes);
            byte result[] = new byte[8];
            des.long2bytes(des.encrypt(x), result);
            byte doubleResult[] = new byte[16];
            for(int j = 0; j < 8; j++)
            {
                doubleResult[2 * j] = (byte)((((char)result[j] & 0xf0) >> 4) + 97);
                doubleResult[2 * j + 1] = (byte)(((char)result[j] & 0xf) + 97);
            }

            encyString = (new StringBuilder()).append(encyString).append(new String(doubleResult)).toString();
        }

        return encyString;
    }

    private static long getKey()
    {
        return 0x496324baL;
//        return 0x123123baL;
    }
   
}
