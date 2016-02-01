package org.xs.spider.parser;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 15-5-27.
 * 使用对象定位器模式管理各类Parser
 */
public class ParserLocator {

    private static ParserLocator locator = new ParserLocator();
    private HashMap<String, Parser> parserHashMap = new HashMap<String, Parser>();
    private HashSet<String> ecommerceSet = new HashSet<String>();
    BasicParser basicParser = new BasicParser();
  
    public Parser getParser(String url) {
        String host = getHost(url);
//    	String host = null;
//    	Pattern p = Pattern.compile("(?<=http://|\\.)[^.]*?\\.(com|cn|net|org|biz|info|cc|tv)",Pattern.CASE_INSENSITIVE);
//		Matcher matcher = p.matcher(url);
//		if(matcher.find()){
//			host = matcher.group().toLowerCase();
//		}
        if ((host != null) && (host.isEmpty() == false)) {
        	if(parserHashMap.containsKey(host)){
        		return parserHashMap.get(host);
        	}
        	else{
        		return basicParser;
        	}
        }
        else{
        	return null;
        }
    }


    public void register(String host, Parser parser) {
        parserHashMap.put(host, parser);
    }

    public void unregister(String host) {
        parserHashMap.remove(host);
    }

    public static ParserLocator getInstance() {
        return locator;
    }

    private ParserLocator() {
    	
    	ecommerceSet.add("item.jd.com");
    	ecommerceSet.add("detail.tmall.com");
    	ecommerceSet.add("item.taobao.com");
    	ecommerceSet.add("product.suning.com");
    	ecommerceSet.add("item.gome.com.cn");
    	ecommerceSet.add("item.yhd.com");
    	ecommerceSet.add("www.vip.com");
    	
    }


    private static String getHost(String url) {
        try {
            URL u = new URL(url);
            return u.getHost();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
