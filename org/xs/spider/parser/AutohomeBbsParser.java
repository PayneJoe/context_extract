package org.xs.spider.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class AutohomeBbsParser extends ClassifiedParser{
	public AutohomeBbsParser(){
		System.out.println("------------------------autohome bbs parser ");
	    ParserLocator.getInstance().register("club.autohome.com.cn",this);
	}

	@Override
	public String getContentText(Document document) {
		// TODO Auto-generated method stub
		
		String ret = "";
		for(Element s : document.getElementsByAttributeValueContaining("xname", "content")){
			ret = ret + " "+ s.text().toString();
		}
		if(ret.length() > 0){
			ret = ret.substring(1);
		}
		return ret;
//		return null;
	}
}
