package org.xs.spider.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class PpsParser extends ClassifiedParser{
	public PpsParser() {
		// TODO Auto-generated constructor stub
		System.out.println("------------------------pps video parser ");
	    ParserLocator.getInstance().register("v.pptv.com",this);
	}
	@Override
	public String getContentText(Document document) {
		
//		System.out.println(this.getClass().getName());
		
		String ret = "";
		
		for(Element e : document.getElementsByAttributeValue("class", "intro-content intro-short")){
			ret = ret + " " + e.text().toString();
		}
		
		return ret;
	}
}
