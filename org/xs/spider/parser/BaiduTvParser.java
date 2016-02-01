package org.xs.spider.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class BaiduTvParser extends ClassifiedParser{
	public BaiduTvParser() {
		// TODO Auto-generated constructor stub
		System.out.println("------------------------baidu video parser ");
	    ParserLocator.getInstance().register("v.baidu.com",this);
	}
	@Override
	public String getContentText(Document document) {
		String ret = "";
//		System.out.println(this.getClass().getName());		
		if(ret.isEmpty() == false){
			ret = ret.substring(1);
		}
		return ret;
	}
}
