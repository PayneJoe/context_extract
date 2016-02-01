package org.xs.spider.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class TMALLParser extends ClassifiedParser{

	public TMALLParser(){
		System.out.println("------------------------tmall parser ");
	    ParserLocator.getInstance().register("detail.tmall.com",this);
	    ParserLocator.getInstance().register("item.taobao.com",this);
	}

	@Override
	public String getContentText(Document document) {
		// TODO Auto-generated method stub
		String content = "";
		for(Element s : document.getElementsByClass("attributes-list")){
			content =  " " + content;
			content = content + s.getElementsByTag("li").text().toString();
		}
		return content;
//		return null;
	}
}
