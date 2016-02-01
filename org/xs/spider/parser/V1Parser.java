package org.xs.spider.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class V1Parser extends ClassifiedParser{
	public V1Parser() {
		// TODO Auto-generated constructor stub
		System.out.println("------------------------v1 video parser ");
	    ParserLocator.getInstance().register("www.v1.cn",this);
	    ParserLocator.getInstance().register("v.v1.cn",this);
	}
	@Override
	public String getContentText(Document document) {
		String ret = "";
//		System.out.println(this.getClass().getName());
//		Element e = document.getElementById("video_tags");
//		if(e != null){
//			ret = ret + " " + e.text().toString();
//		}
		for(Element e : document.getElementsByAttributeValue("class", "summaryBox")){
			ret = ret + " " + e.text().toString();
		}

		if(ret.isEmpty() == false){
			ret = ret.substring(1);
		}
		return ret;
	}
}
