package org.xs.spider.parser;

import org.jsoup.nodes.Document;

public class SUNINGParser extends ClassifiedParser{

	public SUNINGParser(){
		System.out.println("------------------------ suning parser ");
	    ParserLocator.getInstance().register("product.suning.com",this);
	}

	@Override
	public String getContentText(Document document) {
		// TODO Auto-generated method stub
		return document.getElementById("kernelParmeter").getElementsByTag("li").text().toString();
	}
}
