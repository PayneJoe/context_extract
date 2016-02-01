package org.xs.spider.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class NineteenFiveParser extends ClassifiedParser{

	public NineteenFiveParser() {
		// TODO Auto-generated constructor stub
		System.out.println("------------------------1905 video parser ");
	    ParserLocator.getInstance().register("www.1905.com",this);
	    ParserLocator.getInstance().register("blog.1905.com",this);
	    ParserLocator.getInstance().register("d.1905.com",this);
	    ParserLocator.getInstance().register("v.1905.com",this);
	}
	@Override
	public String getContentText(Document document) {
		String ret = "";
		for(Element s : document.getElementsByAttributeValue("class", "mdb-movie")){
			ret = ret + " " + s.text().toString();
		}
		for(Element s : document.getElementsByAttributeValue("class", "db h100b pb02")){
			ret = ret + " " + s.text().toString();
		}
		for(Element s : document.getElementsByAttributeValue("class", "fr right_cont")){
			ret = ret + " " + s.text().toString();
		}
		for(Element s : document.getElementsByAttributeValue("class", "film_review")){
			ret = ret + " " + s.text().toString();
		}
		Element s = document.getElementById("blog_article");
		if(s != null){
			ret = ret + " " +s.text().toString();
		}
		Element s1 = document.getElementById("contentNews");
		if(s1 != null){
			ret = ret + " " + s1.text().toString();
		}
		Element s2 = document.getElementById("feed_list");
		if(s2 != null){
			ret = ret + " " + s2.text().toString();
		}
		Element s3 = document.getElementById("ddyp");
		if(s3 != null){
			ret = ret + " " + s3.text().toString();
		}
		Element s4 = document.getElementById("tab_filmabout");
		if(s4 != null){
			ret = ret + " " + s4.text().toString();
		}
		if(ret.isEmpty() == false){
			ret = ret.substring(1);
		}
		
		return ret;
	}
}
