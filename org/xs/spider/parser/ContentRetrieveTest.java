package org.xs.spider.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

class ContentRetrieveTest {	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new BaiduBaikeParser();
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
		
		 Document doc=null;
		 String url = "http://baike.baidu.com/subview/863/5948039.htm";
		 int time = 5000;
		 try{
				doc = Jsoup.connect(url).timeout(time)
						.userAgent("Mozilla/5.0 (X11; Linux i686) AppleWebKit/537.36 "
								+ "(KHTML, like Gecko) Ubuntu Chromium/40.0.2214.111 Chrome/40.0.2214.111 Safari/537.36").
								get();
				System.out.println(doc.head().text().toString());
				Parser p = ParserLocator.getInstance().getParser(url);
				System.out.println(p.getContentText(doc));
		 }
		catch (Exception e){
			System.out.println("content retrieve failed !");
			return;
		}
	}
}
