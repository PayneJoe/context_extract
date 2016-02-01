package org.xs.spider.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class BaiduBaikeParser extends ClassifiedParser{

	public BaiduBaikeParser(){
		System.out.println("------------------------baidu baike parser ");
	    ParserLocator.getInstance().register("baike.baidu.com",this);
	}

	@Override
	public String getContentText(Document document) {
		// TODO Auto-generated method stub	
		String ret = "";
		List<String> kList = new ArrayList<String>();
		List<String> vList = new ArrayList<String>();
		for(Element s : document.getElementsByAttributeValue("class", "basic-info")){
			for(Element s1 : s.getElementsByAttributeValue("class", "basicInfo-item name")){
				String k = s1.text().toString();
				Pattern p=Pattern.compile("([\\w\u2E80-\u9FFF]+).*([\\w\u2E80-\u9FFF]+)");
				Matcher matcher = p.matcher(k);
				if(matcher.find()){
					k = "";
					int nM = matcher.groupCount();
					for(int i = 1 ;i <= nM;i++){
						 k = k + matcher.group(i);
//						 System.out.println(matcher.group(0));
					}
				}
				kList.add(k);
			}
			for(Element s2 : s.getElementsByAttributeValue("class", "basicInfo-item value")){
				vList.add(s2.text().toString());
			}
		}
		int minSize = Math.min(kList.size(),vList.size());
		for(int i = 0;i < minSize;i++){
			ret = ret + " " + kList.get(i);
			ret = ret + ":" + vList.get(i);
		}
		for(Element s : document.getElementsByAttributeValue("class", "para")){
			ret = ret + " " + s.text().toString();
		}
		if(ret.length() > 0){
			ret = ret.substring(1);
		}
		return ret;
	}

}
