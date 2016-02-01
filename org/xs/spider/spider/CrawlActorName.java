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

public class CrawlActorName {

	static int to = 5000;
	
	private static HashSet<String> RetrieveUrlPage(String url) throws IOException{
		
		HashSet<String> hs = new HashSet<String>();
		Document topicDoc = null;
		try{
			topicDoc = getDoc(url);
		}
		catch (Exception e){
			System.out.println("Get html failed : " + url);
			return hs;
		}
			Elements carea = topicDoc.getElementsByAttributeValue("class", "picBox");
			if((carea == null) || (carea.size() == 0)){
				System.out.println("area not found ! ");
				return hs;
			}
			int count = 0;
			for(Element dl : carea.get(0).getElementsByTag("li")){
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
         
         String outputPath = "E:\\work\\project\\site-analysis\\ContentRetrieve\\video\\dict\\scriptwriters.dic";
         File f = new File(outputPath);
         FileOutputStream fo = new FileOutputStream(f);
         BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fo,"utf8"));
   		 HashMap<String,HashSet<String> > TopicUrlMap = new HashMap<String,HashSet<String> >();

	    	String pageUrl = "http://www.1905.com/mdb/star/vocation-5/m1.html";

	      	int pageNum = 0;
	      	try{
	      		while(true){
//	      			if(pageNum >= 300){
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
	      			Element nextPage = pageDoc.getElementById("new_page");
	      			if((nextPage == null)){
	      				System.out.println("no next page : "+ pageUrl);
	      				break;
	      			}
	      			Elements next = nextPage.getElementsByTag("a");
	      			if((next == null) || (next.size() == 0)){
	      				System.out.println("no next page : "+ pageUrl);
	      				break;
	      			}
	      			if(next.get(next.size()-1).text().equals("下一页") == false){
	      				System.out.println("it is over !");
	      				break;
	      			}
	      			pageUrl = "http://www.1905.com" + next.get(next.size()-1).attr("href");
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
