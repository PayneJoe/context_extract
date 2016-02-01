package org.xs.spider.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class KankanParser extends ClassifiedParser{
	public KankanParser() {
		// TODO Auto-generated constructor stub
		System.out.println("------------------------kankan video parser ");
	    ParserLocator.getInstance().register("vod.kankan.com",this);
	}
	@Override
	public String getContentText(Document document) {
		String ret = "";
//		System.out.println(this.getClass().getName());
		Element e = document.getElementById("movie_info_ul");
		if(e != null){
			ret = ret + " " + e.text().toString();
		}
//		for(Element e : document.getElementsByAttributeValue("class", "movie_info_ul")){
//			ret = ret + " " + e.text().toString();
//		}
		
		if(ret.isEmpty() == false){
			ret = ret.substring(1);
		}
		return ret;
	}
}
