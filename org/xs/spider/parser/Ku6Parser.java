package org.xs.spider.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Ku6Parser extends ClassifiedParser{
	public Ku6Parser() {
		// TODO Auto-generated constructor stub
		System.out.println("------------------------ku6 video parser ");
	    ParserLocator.getInstance().register("v.ku6.com",this);
	}
	@Override
	public String getContentText(Document document) {
//		System.out.println(this.getClass().getName());
		
		String ret = "";
		
		for(Element e : document.getElementsByAttributeValue("class","ckl_conleftop")){
			ret = ret + " " + e.text().toString();
		}
		
		return ret;
	}
}
