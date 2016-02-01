package org.xs.spider.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class TudouParser extends ClassifiedParser{
	public TudouParser() {
		// TODO Auto-generated constructor stub
		System.out.println("------------------------tudou video parser ");
	    ParserLocator.getInstance().register("www.tudou.com",this);
	}
	@Override
	public String getContentText(Document document) {
		String ret = "";
//		System.out.println(this.getClass().getName());		
		Element e = document.getElementById("feedInfo");
		if(e != null){
			ret = ret + " " + e.text().toString();
		}
//		for(Element e1 : document.getElementsByAttributeValue("class", "v_tags")){
//			ret = ret + " " + e1.text().toString();
//		}
		e = document.getElementById("information");
		if(e != null){
			ret = ret + " " + e.text().toString();
		}
		if(ret.isEmpty() == false){
			ret = ret.substring(1);
		}
		return ret;
	}
}
