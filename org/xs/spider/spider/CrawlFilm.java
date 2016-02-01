package org.xs.spider.spider;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CrawlFilm {

	static int to = 5000;
	
	private static HashSet<String> RetrieveUrlPage(String url) throws IOException{
		
		HashSet<String> hs = new HashSet<String>();
  		Document topicDoc = getDoc(url);
			Element carea = topicDoc.getElementById("content");
			if(carea == null){
				System.out.println("area not found ! ");
				return hs;
			}
			int count = 0;
			for(Element dl : carea.getElementsByTag("li")){
				Elements ea = dl.getElementsByTag("img");
				if(ea.size() == 0){
					continue;
				}
				String post = ea.get(0).attr("alt");
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

         HashSet<String> movieSet = new HashSet<String>();
         
         String outputPath = "E:\\work\\project\\site-analysis\\ContentRetrieve\\video\\dict\\TvSeries.dic";
         File f = new File(outputPath);
         FileOutputStream fo = new FileOutputStream(f);
         BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fo,"utf8"));
   		 HashMap<String,HashSet<String> > TopicUrlMap = new HashMap<String,HashSet<String> >();

		// brand
//       String url = "http://v.qq.com/list/2_-1_-1_-1_1_0_0_20_-1_-1_0_-1.html";
//       mainDoc = getDoc(url);
//		Element e = mainDoc.getElementById("content");
//		if(e == null){
//			System.out.println(" get movie group failed !  ");
//			return;
//		}
//		for(Element a : e.getElementsByTag("li")){
	    	String pageUrl = "http://v.qq.com/list/2_-1_-1_-1_1_0_0_20_-1_-1_0_-1.html";
//	      	String topic = a.text().toString();
	      	int pageNum = 0;
	      	try{
	      		while(true){
//	      			if(pageNum >= 40){
//	      				break;
//	      			}
	      			System.out.println("-------------------" + pageUrl);	      			
	      			HashSet<String> hs = RetrieveUrlPage(pageUrl);	
	      			if(hs.size() > 0){
	      				//TopicUrlMap.put(topic, hs);
	      				System.out.println(" size : " + hs.size());
	      		      	 for(String u : hs){
	      	        		 movieSet.add(u);
	      	        	 }
	      			}
	      			else{
	      				System.out.println("retrieve url page failed : " + pageUrl + " " + pageNum);
	      				pageNum++;
	      				continue;
	      			}
	      			Document pageDoc = getDoc(pageUrl);
	      			Elements next = pageDoc.getElementsByAttributeValue("class", "next");
	      			if((next == null) || (next.size() == 0)){
	      				System.out.println("no next page : "+ pageUrl);
	      				break;
	      			}
	      			pageUrl = next.get(1).attr("href");
	      			System.out.println(" page " + pageNum + " success !");
	      			pageNum++;
	      		}
			  }
	      	catch (Exception e1){
	      		e1.printStackTrace();
	      		return;
	      	}
//	     }
		
		for(String m : movieSet){
			bw.write(m+"\n");
			bw.flush();
		}
		
         bw.close();
         fo.close();
	}

}
