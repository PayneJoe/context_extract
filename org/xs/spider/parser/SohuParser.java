package org.xs.spider.parser;

import org.eclipse.jdt.core.dom.ThisExpression;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class SohuParser extends ClassifiedParser{

	public SohuParser() {
		// TODO Auto-generated constructor stub
		System.out.println("------------------------sohu video parser ");
	    ParserLocator.getInstance().register("my.tv.sohu.com",this);
	    ParserLocator.getInstance().register("tv.sohu.com",this);
	}
	@Override
	public String getContentText(Document document) {
		
//		System.out.println(this.getClass().getName());
		
		String ret = "";
		for(Element s : document.getElementsByAttributeValue("class", "colR")){
			ret = ret + " " + s.text().toString();
			break;
		}
		for(Element s : document.getElementsByAttributeValue("class", "info info-con")){
			ret = ret + " " + s.text().toString();
			break;
		}
		Element e = document.getElementById("crumbsBar");
		if(e != null){
			ret = ret + " " + e.text().toString();
		}
		if(ret.isEmpty() == false){
			ret = ret.substring(1);
		}
		return ret;
	}
}
