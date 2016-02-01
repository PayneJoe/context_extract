package org.xs.spider.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class WasuParser extends ClassifiedParser{
	public WasuParser() {
		// TODO Auto-generated constructor stub
		System.out.println("------------------------wasu video parser ");
	    ParserLocator.getInstance().register("www.wasu.cn",this);
	}
	@Override
	public String getContentText(Document document) {
		String ret = "";
	//	System.out.println(this.getClass().getName());
//		Element e = document.getElementById("movie_info_ul");
//		if(e != null){
//			ret = ret + " " + e.text().toString();
//		}
		for(Element e : document.getElementsByAttributeValue("class", "play_seat")){
			ret = ret + " " + e.text().toString();
		}
		for(Element e : document.getElementsByAttributeValue("class", "ws_w970_col8 ws_w1155_col10")){
			ret = ret + " " + e.text().toString();
		}
		
		if(ret.isEmpty() == false){
			ret = ret.substring(1);
		}
		return ret;
	}
}
