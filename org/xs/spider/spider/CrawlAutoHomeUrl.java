package org.xs.spider.spider;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CrawlAutoHomeUrl {

	static int to = 5000;
	
	private static HashSet<String> RetrieveUrlPage(String url) throws IOException{
		
		HashSet<String> hs = new HashSet<String>();
  		Document topicDoc = getDoc(url);
			Elements carea = topicDoc.getElementsByAttributeValue("class", "carea");
			if(carea.size() == 0){
				System.out.println("area not found ! ");
				return hs;
			}
			Elements alldls = carea.get(0).getElementsByTag("dl");
			if(alldls == null){
				System.out.println("dl not found found !");
				return hs;
			}
			int count = 0;
			for(Element dl : alldls){
				if(count == 0){
					count++;
					continue;
				} 
				count++;
				Elements alla = dl.getElementsByAttribute("href");
				if(alla.size() == 0){
					System.out.println("href not found !");
					continue;
				}
				String post = "http://club.autohome.com.cn" + alla.get(0).attr("href");
				hs.add(post);
				//System.out.println(post);
			}
			return hs;
	}
	
	private static Document getDoc(String url) throws IOException{
		Document doc;
		doc = Jsoup.connect(url).timeout(to)
				.userAgent("Mozilla/5.0 (X11; Linux i686) AppleWebKit/537.36 "
						+ "(KHTML, like Gecko) Ubuntu Chromium/40.0.2214.111 Chrome/40.0.2214.111 Safari/537.36").
						get(); 
		return doc;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		 String inputString="";
         String output="";
         Document mainDoc = null;

         String outputPath = "E:\\work\\project\\site-analysis\\ContentRetrieve\\auto\\autohomeurl.others";
         File f = new File(outputPath);
         FileOutputStream fo = new FileOutputStream(f);
         BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fo,"utf8"));
   		 HashMap<String,HashSet<String> > TopicUrlMap = new HashMap<String,HashSet<String> >();


	    // topic
//       String url = "http://club.autohome.com.cn/#pvareaid=103419";
//    		mainDoc = getDoc(url);
//    	    String us= mainDoc.head().toString();
//	    Element e = mainDoc.getElementById("tab-6");
// 		if((e == null) || (e.size() == 0)){
//			System.out.println("tab-6 failed !");
//			return;
//		}
//	    for(Element a : e.getElementsByTag("a")){

		// brand
       String url = "http://club.autohome.com.cn/";
       mainDoc = getDoc(url);
		Elements e = mainDoc.getElementsByAttributeValue("class", "forum-brand-box");
		if((e == null) || (e.size() == 0)){
			System.out.println("forum-brand-box failed !");
			return;
		}
		for(Element a : e.get(0).getElementsByTag("a")){
	    	String pageUrl = "http://club.autohome.com.cn" + a.attr("href");
	      	String topic = a.text().toString();
	      	int pageNum = 0;
	      	try{
	      		while(true){
	      			if(pageNum == 0){
	      				pageNum++;
	      				continue;
	      			}
	      			// skip first page as it's already crawled in test period
	      			HashSet<String> hs = RetrieveUrlPage(pageUrl);	
	      			if(hs.size() > 0){
	      				//TopicUrlMap.put(topic, hs);
	      		      	 for(String u : hs){
	      	        		 bw.write(u + "\n");
	      	        		 bw.flush();
	      	        	 }
	      			}
	      			else{
	      				System.out.println("retrieve url page failed : " + pageUrl);
	      				break;
	      			}
	      			Document pageDoc = getDoc(pageUrl);
	      			Elements next = pageDoc.getElementsByAttributeValue("class", "afpage");
	      			if((next == null) || (next.size() == 0)){
	      				System.out.println("no next page : "+ pageUrl);
	      				break;
	      			}
	      			pageUrl = "http://club.autohome.com.cn" + next.get(0).attr("href");
	      			System.out.println(topic + " : page " + pageNum + " success !");
	      			pageNum++;
	      		}
			  }
	      	catch (Exception e1){
	      		e1.printStackTrace();
	      		continue;
	      	}
	     }
         bw.close();
         fo.close();
	}
}
