package org.xs.spider.spider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.xs.spider.parser.Parser;
import org.xs.spider.parser.ParserLocator;
import org.xs.spider.parser.*;


class SubThread extends Thread{
         private ArrayList<String> list = new ArrayList<String>();
         private ArrayList<String> errorList = new ArrayList<String>();
         private HashSet<String> errorMessage = new HashSet<String>();
         private ArrayList<String[]> output = new ArrayList<String[]>();
         private String outputFolder;
         private int number;
         private String date;
         private int write_sum=0;
         private String fileNameString;
         //regex
       //  private String keywordRex="content=\"([\\S\\s]+?)\"";
         private String encodeRex="charset=[\"]{0,1}([A-Za-z-0-9]+)";
         //static 
         public static int total=0;
         public static  int crawler_error_sum=0;
         public static  int write_error_sum=0;
         
         public static int titleEmptyCo = 0;
         public static int contentEmptyCo = 0;
         public static int getEncodeFialCo = 0;
         public static int getEncodeRegxUncover = 0;
         private static final int count=3000;
         private static final char CTRL_A='\001';
         private static final char TAB='\t';
         private long fw_num=0;
         
         private  ArrayList<String> uaList;
         public SubThread(ArrayList<String> list, String outputFolder,int threadNumber,String date,String fileName,ArrayList ua)
             {
                 this.list=list;
                 this.outputFolder=outputFolder;
                 this.number=threadNumber;
                 this.date=date;
                 this.fileNameString=fileName;
                 this.uaList  = ua;
                 RegisterSite();
             }
         
         //单独处理的解析方法
         private void RegisterSite(){
             // 
            
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
            
            return;
         }
         
         public String[] getRawCode(String url,int time,int index){
             Document doc=null;
             String contentText ;
             String titleString ;
                 try {
                    doc = Jsoup.connect(url).timeout(time)
                            .userAgent(uaList.get(index % uaList.size())).get();                    
                    //标题， 
                    titleString= doc.title().trim().isEmpty()?"":doc.title().trim();  

                    //正文                
                    Parser parser= ParserLocator.getInstance().getParser(url);         
                
                    contentText= parser.getContentText(doc).trim();
                    if(contentText.isEmpty()||contentText.length()<titleString.length()||contentText.length()<25)
                        contentText="";
                }  catch (Exception e) {
                // TODO Auto-generated catch block
                String error = e.toString();                            
                ShowMessage("crawler raw error:"+error.toString());         
                crawler_error_sum++;
                titleString = "";
                contentText = "";
            }
                 
                 if(titleString.isEmpty())
                 {                      
                     return null;
                 }
                 if(titleString.isEmpty())
                     titleEmptyCo ++;
                 if(contentText.isEmpty())
                     contentEmptyCo ++;
                //编码 meiyou               
                String enc_code="other";                    
                String us=doc.head().toString();
                
                Pattern pattern = Pattern.compile(encodeRex);
                Matcher matcher = pattern.matcher(us);
                if(matcher.find()){
                    enc_code = matcher.group(1).trim();
                }else {
                    getEncodeFialCo ++;
                    System.out.println("获取编码失败:"+getEncodeFialCo);
                    return null;
                }
                
                String[] outStrings={contentText,titleString,enc_code,url};
                return outStrings;
         } 
         
         public void processUrl(String url,int index) {
             String[] outStrings = getRawCode(url,5000,index);
             if(outStrings!=null)
               output.add(outStrings);
             if(output.size()>count){
                 write_sum++;
               check(fileNameString);
             }                   
   }
         
         // use method to run url
         @Override
         public void run() {

                   long startTime = System.currentTimeMillis();
                   int index=0;
                   while (index < list.size()) {
                           // ShowMessage(String.valueOf(index)+" total:"+String.valueOf(++total));     
                            ++total;
                            processUrl(list.get(index++),index);
                   }
                   check(fileNameString);
                   int timeUsed = (int) ((System.currentTimeMillis() - startTime) / (1000 * 60));

                   System.out.println("thread: "+number+"  total time used: " + timeUsed + "minute(s)");
                   try {
                     //  File file = new Fil
                    
                    String lineString = "thread:"+number
                                + " total url:"+total
                                + "  crawler_error_sum"+crawler_error_sum
                                +"  write_error_sum_total:"+write_error_sum
                                + "  write to file error UnsupportedEncodingException: "+getEncodeRegxUncover
                                + "  crawler titleEmptyCo: "+titleEmptyCo
                                + " crawler contentEmptyCo:"+contentEmptyCo
                                + " get encode from head failed sum:"+getEncodeFialCo;
                    File file = new File(outputFolder);
                    if(!file.exists()) file.mkdirs();
                    FileWriter file_errorFile=new FileWriter(outputFolder+"/"+date+"_log.txt",true);
                    file_errorFile.write(lineString+"\r\n");
                    file_errorFile.flush();
                    file_errorFile.close();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
         }                     
         //写入文件
         public void check(String fileName)  {  
             System.out.println("thread:"+number+" writing to file");
             File dir = new File(outputFolder);
             if (!dir.exists())
                      dir.mkdirs();
             String fileNameString = outputFolder+"/"+fileName;
             FileOutputStream fs;
             OutputStreamWriter os; 
             try {
                 fs = new FileOutputStream(fileNameString,true);                
                 os = new OutputStreamWriter(fs, "utf-8");                  
                 for (String[] line : output) {  
                     //contentText,titleString,keyWordString,enc_code,url
                     String content=line[0];
                     String title=line[1];
                     String encode = line[2];
                     String url=line[3];
                    //out
                    String out = url+ CTRL_A + title + CTRL_A + content;  
                    
                    os.write(new String(new String(out.getBytes(encode),encode)
                    .getBytes("utf-8"),"utf-8")+"\r\n");//从文件中读后转为字节码，先按encode编码，再new String解码成Unicode
                    os.flush();                                                                                                 
                 } 
             os.close();
            } catch (Exception e) {
                    // TODO: handle exception
                write_error_sum++;
                String error = e.toString();
                if(error.contains("UnsupportedEncodingException")){
                    getEncodeRegxUncover ++;
                    System.out.println("获取编码的正则有误："+getEncodeRegxUncover);
                }else
                    ShowMessage("write to file error:"+e.toString());
            }                
             output.clear();                           

         }
         private void ShowMessage(String message)
         {
             try {
                     File file = new File(outputFolder);
                     if(!file.exists()) file.mkdirs();
                     if(message.contains("crawler raw error")){
                         FileWriter file_logFileWriter = new FileWriter(outputFolder+"/"+date+"crawler_error_log.txt",true);
                         file_logFileWriter.write("thead:"+number+"error:"+message+"\r\n");
                         file_logFileWriter.flush();
                         file_logFileWriter.close();
                     }else {
                         FileWriter file_logFileWriter = new FileWriter(outputFolder+"/"+date+"write_error_log.txt",true);
                         file_logFileWriter.write("thead:"+number+"error:"+message+"\r\n");
                         file_logFileWriter.flush();
                         file_logFileWriter.close();
                     }              
                    //System.out.printf("thread:%d  %s\r\n", number,message);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                   
         }
                                 
}


public class SpiderRun { 
    static long  load_error_sum=0;
         public static ArrayList<ArrayList<String>> Load(String file,int number)
         {
                   System.out.println("loading data ...");
                   ArrayList<ArrayList<String>> list=new ArrayList<ArrayList<String>>();
                   for(int i=0;i<number;i++)
                   {
                            ArrayList<String> sublist=new ArrayList<String>();
                            list.add(sublist);
                   }
                   try {
                            InputStreamReader isr = new InputStreamReader(new FileInputStream(
                                               file), "UTF-8");
                            BufferedReader br = new BufferedReader(isr);
                            String line = null;
                            int j=0;
                            while ((line = br.readLine()) != null) {
                                     int arrayIndex=j%number;
                                     list.get(arrayIndex).add(line.split(new String(new byte[]{0x01}))[0]);                     
                                     j++;
                            }
                            br.close();
                            isr.close();

                   } catch (Exception e) {
                       load_error_sum++;
                         System.out.println("load error:" + e.toString());
                   }
                   System.out.println("load data finished.");
                   return list;
         }

         public static void main(String[] args) throws IOException {
                  
                  if(args.length<6){
                       System.err.println("no enough args,need 6 only "+args.length);
                       System.exit(3);
                   }   
                  for(int i=0;i<args.length;i++){
                        System.out.println("args"+i+":"+args[i]);
                  }
                   String inputString=args[0];
                   String output=args[1];
                   int threadNumber=Integer.parseInt(args[2]);
                   String dateString=args[3];
                   String fileNameString=args[4];
                   String uaPath = args[5];
                   ArrayList<String> ua = new ArrayList<String>();
                   InputStreamReader isr = new InputStreamReader(new FileInputStream(new File(uaPath)),"utf-8");
                   BufferedReader br = new BufferedReader(isr);
                   String line = null;
                   while((line = br.readLine()) !=null){
                       ua.add(line);
                   }                 
                   ArrayList<ArrayList<String>> list=Load(inputString, threadNumber);                 
                    SubThread[] threads=new SubThread[threadNumber];
                       for (int i = 0; i < threadNumber; i++) {
                                threads[i] = new SubThread(list.get(i),output,i,dateString,fileNameString,ua);
                                threads[i].start();                            
                       } 
         }
}
 
