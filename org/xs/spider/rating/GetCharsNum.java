package org.xs.spider.rating;

import java.util.HashMap;
import java.util.Map;

public class GetCharsNum
{

	public static Map<String, Integer> getNum(String str)
	{
		/** ���������ַ� */
		int chCharacter = 0;
		/** ���ı���ַ� */
		int chPunctuationCharacter = 0;
		/** �����ַ� */
		int otherCharacter = 0;
		Map<String, Integer> map = new HashMap<String, Integer>();
		str.trim();
		char[] chars = str.toCharArray();

		for (int i = 0; i < chars.length; i++)
		{
			if (isChinese(chars[i]))
			{
				chCharacter++;
			} else if (isChinesePunctuation(chars[i]))
			{
				chPunctuationCharacter++;
			} else
			{
				otherCharacter++;
				//System.out.println(chars[i]);
			}
		}

		map.put("chCharacter", chCharacter);
		map.put("chPunctuationCharacter", chPunctuationCharacter);
		map.put("otherCharacter", otherCharacter);

		return map;
	}

	/***
	 * �ж��ַ��Ƿ�Ϊ����
	 * 
	 * @param ch
	 *            ��Ҫ�жϵ��ַ�
	 * @return ���ķ���true�������ķ���false
	 */
	private static boolean isChinese(char ch)
	{
		// ��ȡ���ַ���UniCodeBlock
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(ch);
		/*
		 * CJK_UNIFIED_IDEOGRAPHS // ���պ�ͳһ�������� CJK_COMPATIBILITY_IDEOGRAPHS //
		 * ���պ������ַ� CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A // ���պ�ͳһ������������A
		 * GENERAL_PUNCTUATION // һ�������, �ж����ĵġ��� CJK_SYMBOLS_AND_PUNCTUATION //
		 * ���źͱ��, �ж����ĵġ��� HALFWIDTH_AND_FULLWIDTH_FORMS // ��Ǽ�ȫ���ַ�, �ж����ĵģ���
		 */
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B)
		{
			return true;
		}
		return false;
	}

	/***
	 * �ж��ַ��Ƿ�Ϊ���ı��
	 * 
	 * @param ch
	 *            ��Ҫ�жϵ��ַ�
	 * @return ���ķ���true�������ķ���false
	 */
	private static boolean isChinesePunctuation(char ch)
	{
		// ��ȡ���ַ���UniCodeBlock
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(ch);
		/*
		 * CJK_UNIFIED_IDEOGRAPHS // ���պ�ͳһ�������� CJK_COMPATIBILITY_IDEOGRAPHS //
		 * ���պ������ַ� CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A // ���պ�ͳһ������������A
		 * GENERAL_PUNCTUATION // һ�������, �ж����ĵġ��� CJK_SYMBOLS_AND_PUNCTUATION //
		 * ���źͱ��, �ж����ĵġ��� HALFWIDTH_AND_FULLWIDTH_FORMS // ��Ǽ�ȫ���ַ�, �ж����ĵģ���
		 */
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION)
		{
			return true;
		}
		return false;
	}
}
