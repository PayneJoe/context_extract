package org.xs.spider.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class BaofengParser extends ClassifiedParser{
	public BaofengParser() {
		// TODO Auto-generated constructor stub
		System.out.println("------------------------baofeng video parser ");
	    ParserLocator.getInstance().register("www.baofeng.com",this);
	}
	@Override
	public String getContentText(Document document) {
		
//		System.out.println(this.getClass().getName());
		
		String ret = "";
		
		for(Element e : document.getElementsByAttributeValue("class", "play-mod-l")){
			ret = ret + " " + e.text().toString();
		}
		for(Element e : document.getElementsByAttributeValue("class", "play-nav-l-new")){
			ret = ret + " " + e.text().toString();
		}

		return ret;
	}
}
