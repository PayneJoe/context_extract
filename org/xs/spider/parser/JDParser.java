package org.xs.spider.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class JDParser extends ClassifiedParser{

	public JDParser(){
		System.out.println("------------------------ jd parser ");
	    ParserLocator.getInstance().register("item.jd.com",this);
	}

	@Override
	public String getContentText(Document document) {
		// TODO Auto-generated method stub
		return document.getElementById("parameter2").getElementsByTag("li").text().toString();
		
//		return null;
	}
}
