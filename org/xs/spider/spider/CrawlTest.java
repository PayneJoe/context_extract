package org.xs.spider.spider;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.xs.spider.parser.*;

public class CrawlTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		new SohuParser(); 
		new IqiyiParser();
		new Ku6Parser();
		new BaofengParser();
		new PpsParser();
		new FunshionParser();
		new TudouParser();
		new BaiduTvParser();
		new KankanParser();
		new FiftySixParser();
		new BaomihuaParser();
		new TwentyThreeFortyFiveParser();
		new NineteenFiveParser();
		new DubaParser();
		new V1Parser();
		new Hao123Parser();
		new TangdouParser();
		//
		new BaiduTiebaParser();
		new TianYaParser();
		new BaiduBaikeParser();
		new AutohomeBbsParser();
		
    	String contentText ="";
        Document doc = null;
        String url = "http://club.autohome.com.cn/bbs/thread-o-200214-47638155-1.html";
        int time = 5000;
		doc = Jsoup.connect(url).timeout(time)
				.userAgent("Mozilla/5.0 (X11; Linux i686) AppleWebKit/537.36 "
						+ "(KHTML, like Gecko) Ubuntu Chromium/40.0.2214.111 Chrome/40.0.2214.111 Safari/537.36").
						get();
		
		Parser parser= ParserLocator.getInstance().getParser(url);	         		
 		contentText= parser.getContentText(doc).trim();
 		System.out.println(contentText);
 		
	}

}
