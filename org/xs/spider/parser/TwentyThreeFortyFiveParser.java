package org.xs.spider.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class TwentyThreeFortyFiveParser extends ClassifiedParser{
	public TwentyThreeFortyFiveParser() {
		// TODO Auto-generated constructor stub
		System.out.println("------------------------2345 video parser ");
	    ParserLocator.getInstance().register("tv.2345.com",this);
	    ParserLocator.getInstance().register("dianying.2345.com",this);
	    ParserLocator.getInstance().register("dongman.2345.com",this); 
	}
	@Override
	public String getContentText(Document document) {
		String ret = "";
//		System.out.println(this.getClass().getName());
//		Element e = document.getElementById("video_tags");
//		if(e != null){
//			ret = ret + " " + e.text().toString();
//		}
		for(Element e : document.getElementsByAttributeValue("class", "starPicTxt")){
			ret = ret + " " + e.text().toString();
		}
		for(Element e : document.getElementsByAttributeValue("class", "dlTxt clearfix")){
			ret = ret + " " + e.text().toString();
		}
		for(Element e : document.getElementsByAttributeValue("class", "v-star-info")){
			ret = ret + " " + e.text().toString();
		}
//		for(Element e : document.getElementsByAttributeValue("class", "listCon")){
//			ret = ret + " " + e.text().toString();
//		}
		if(ret.isEmpty() == false){
			ret = ret.substring(1);
		}
		return ret;
	}
}
