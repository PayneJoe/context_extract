package org.xs.spider.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class VIPParser extends ClassifiedParser{

	public VIPParser(){
		System.out.println("------------------------VIP parser ");
	    ParserLocator.getInstance().register("www.vip.com",this);
	}

	@Override
	public String getContentText(Document document) {
		// TODO Auto-generated method stub
		String content = "";
		if(document.getElementsByAttributeValue("class", "dc-table fst").size() == 0){
			for(Element s : document.getElementsByAttributeValue("class", "bt_layout bt_lt_par")){
				for(Element e : s.getElementsByTag("li")){
					content = " " + content;
					content = content + e.getElementsByClass("bt_lt_par_t").text().toString();
					content = content + e.getElementsByClass("bt_lt_par_p").text().toString();
				}
//				content = " " + content;
			}
		}
		else{
			for(Element s : document.getElementsByAttributeValue("class", "dc-table fst")){
				int c = 0;
				for(Element e : s.getElementsByTag("td")){
					if(c%2 == 0){
						content = " " + content ;
					}			
					content = content + e.text().toString();
					c = c + 1;
				}
			}
		}
		return content;
//		return null;
	}
}