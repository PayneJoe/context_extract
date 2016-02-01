package org.xs.spider.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class DubaParser extends ClassifiedParser{
	public DubaParser() {
		// TODO Auto-generated constructor stub
		System.out.println("------------------------duba video parser ");
	    ParserLocator.getInstance().register("v.duba.com",this);
	}
	@Override
	public String getContentText(Document document) {
		String ret = "";
//		System.out.println(this.getClass().getName());
//		Element e = document.getElementById("video_tags");
//		if(e != null){
//			ret = ret + " " + e.text().toString();
//		}
		for(Element e : document.getElementsByAttributeValue("class", "infolist")){
			ret = ret + " " + e.text().toString();
		}

		if(ret.isEmpty() == false){
			ret = ret.substring(1);
		}
		return ret;
	}
}
