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

public class CrawlAutoBrandUrl {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		 String inputString="";
         String output="";
         Document doc = null;
         String url = "http://club.autohome.com.cn/";
         int time = 5000;
         String outputPath = "E:\\work\\project\\site-analysis\\ContentRetrieve\\auto\\brand\\input\\autobrandurl";
         File f = new File(outputPath);
         FileOutputStream fo = new FileOutputStream(f);
         BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fo,"utf8"));
   		 HashMap<String,HashSet<String> > TopicUrlMap = new HashMap<String,HashSet<String> >();

				doc = Jsoup.connect(url).timeout(time)
						.userAgent("Mozilla/5.0 (X11; Linux i686) AppleWebKit/537.36 "
								+ "(KHTML, like Gecko) Ubuntu Chromium/40.0.2214.111 Chrome/40.0.2214.111 Safari/537.36").
								get(); 
	      		String us=doc.head().toString();
	      		Elements brands = doc.getElementsByAttributeValue("class", "forum-brand-box");
	      		if(brands.size() == 0){
	      			System.out.println("brand not found");
	      			return;
	      		}
	      		Element e = brands.get(0);
	      		for(Element a : e.getElementsByTag("a")){
	      			String topicUrl = "http://club.autohome.com.cn" + a.attr("href");
	      			try{
	      					HashSet<String> hs = new HashSet<String>();
				      		Document topicDoc = Jsoup.connect(topicUrl).timeout(time)
									.userAgent("Mozilla/5.0 (X11; Linux i686) AppleWebKit/537.36 "
											+ "(KHTML, like Gecko) Ubuntu Chromium/40.0.2214.111 Chrome/40.0.2214.111 Safari/537.36").
											get();
			      			Elements carea = topicDoc.getElementsByAttributeValue("class", "carea");
			      			if(carea.size() == 0){
			      				System.out.println("area not found ! ");
			      				continue;
			      			}
			      			Elements alldls = carea.get(0).getElementsByTag("dl");
			      			if(alldls == null){
			      				System.out.println("dl not found found !");
			      				continue;
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
				      			System.out.println(post);
				      		}
			      			if(hs.size() > 0){
			      				TopicUrlMap.put(topicUrl, hs);
			      			}
	      			}
	      			catch (Exception e1){
	      				e1.printStackTrace();
	      				continue;
	      			}
	      		}
         for(String tu : TopicUrlMap.keySet()){
        	 for(String u : TopicUrlMap.get(tu)){
        		 bw.write(u + "\n");
        		 bw.flush();
        	 }
         }
         bw.close();
         fo.close();
	}
}
