package org.xs.spider.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class  TianYaParser extends ClassifiedParser{

	public TianYaParser(){
		System.out.println("------------------------tianya bbs parser ");
	    ParserLocator.getInstance().register("bbs.tianya.cn",this);
	}

	@Override
	public String getContentText(Document document) {
		// TODO Auto-generated method stub
		
		String ret = "";
		for(Element s : document.getElementsByAttributeValueContaining("class", "bbs-content")){
			ret = ret + " "+ s.text().toString();
		}
		if(ret.length() > 0){
			ret = ret.substring(1);
		}
		return ret;
//		return null;
	}

}
