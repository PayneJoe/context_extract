package org.xs.spider.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class BaiduTiebaParser extends ClassifiedParser{

	public BaiduTiebaParser(){
		System.out.println("------------------------baidu bbs parser ");
	    ParserLocator.getInstance().register("tieba.baidu.com",this);
	}

	@Override
	public String getContentText(Document document) {
		// TODO Auto-generated method stub	
		String ret = "";
		for(Element s : document.getElementsByAttributeValueContaining("class", "d_post_content j_d_post_content")){
			ret = ret + " "+ s.text().toString();
		}
		if(ret.length() > 0){
			ret = ret.substring(1);
		}
		return ret;
	}
}
