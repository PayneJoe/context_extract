package org.xs.spider.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class IqiyiParser extends ClassifiedParser{
	public IqiyiParser() {
		// TODO Auto-generated constructor stub
		System.out.println("------------------------iqiyi video parser ");
	    ParserLocator.getInstance().register("www.iqiyi.com",this);
	    ParserLocator.getInstance().register("vip.iqiyi.com",this);
	}
	@Override
	public String getContentText(Document document) {
//		System.out.println(this.getClass().getName());
		
		String ret = "";
//		for(Element s : document.getElementsByAttributeValue("class", "colR")){
//			ret = ret + " " + s.text().toString();
//		}
		for(Element e : document.getElementsByAttributeValue("class", "mod-jiemu-msg mb30")){
			ret = ret + " " + e.text().toString();
		}
		Element e = document.getElementById("widget-vshort-desc");
		if(e != null){
			ret = ret + " " + e.text().toString();
		}
		e = document.getElementById("datainfo-taglist");
		if(e != null){
			ret = ret + " " + e.text().toString();
		}
		if(ret.isEmpty() == false){
			ret = ret.substring(1);
		}
		return ret;
	}
}
