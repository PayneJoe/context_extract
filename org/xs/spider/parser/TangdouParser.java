package org.xs.spider.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class TangdouParser extends ClassifiedParser{
	public TangdouParser() {
		// TODO Auto-generated constructor stub
		System.out.println("------------------------tangdou video parser ");
	    ParserLocator.getInstance().register("www.tangdou.com",this);
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
