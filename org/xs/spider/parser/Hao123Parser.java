package org.xs.spider.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Hao123Parser extends ClassifiedParser{
	public Hao123Parser() {
		// TODO Auto-generated constructor stub
		System.out.println("------------------------hao123 video parser ");
	    ParserLocator.getInstance().register("v.hao123.com",this);
	}
	@Override
	public String getContentText(Document document) {
		String ret = "";
//		System.out.println(this.getClass().getName());
//		Element e = document.getElementById("video_tags");
//		if(e != null){
//			ret = ret + " " + e.text().toString();
//		}
		for(Element e : document.getElementsByAttributeValue("class", "intro-detail clearfix")){
			ret = ret + " " + e.text().toString();
		}

		if(ret.isEmpty() == false){
			ret = ret.substring(1);
		}
		return ret;
	}
}
