package org.xs.spider.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class YHDParser extends ClassifiedParser{
	public YHDParser(){
		System.out.println("------------------------ yhd parser ");
	    ParserLocator.getInstance().register("item.yhd.com",this);
	}

	@Override
	public String getContentText(Document document) {
		// TODO Auto-generated method stub
		String content = "";
		for(Element s : document.getElementsByAttributeValue("class", "des_info clearfix")){
			for(Element e : s.getElementsByTag("dd")){
				content = " " + content ;
				content = content + e.text().toString();
			}
		}
		return content ;
	}
}
