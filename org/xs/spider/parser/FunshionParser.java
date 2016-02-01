package org.xs.spider.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class FunshionParser extends ClassifiedParser{
	public FunshionParser() {
		// TODO Auto-generated constructor stub
		System.out.println("------------------------funshion video parser ");
	    ParserLocator.getInstance().register("www.fun.tv",this);
	}
	@Override
	public String getContentText(Document document) {
		
//		System.out.println(this.getClass().getName());
		
		String ret = "";
		
		for(Element e : document.getElementsByAttributeValue("class", "crumbsline")){
			ret = ret + " " + e.text().toString();
		}
		for(Element e : document.getElementsByAttributeValue("class", "mod-datum")){
			ret = ret + " " + e.text().toString();
		}
//		Element e = document.getElementById("main-rt");
//		if(e != null){
//			ret = ret + " " + e.text().toString();
//		}
		if(ret.isEmpty() == false){
			ret = ret.substring(1);
		}
		return ret;
	}
}
