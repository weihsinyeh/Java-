//developed by 葉惟欣 time : 0613
//創造收據
package AllFile;
//引入套件
import AllFood.*;
import AllSet.*;
import AllCustomer.*;
import javax.swing.JOptionPane;
import java.io.BufferedReader;  
import java.io.BufferedWriter;
import java.io.FileReader;  
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.lang.*;
import java.text.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/***************************************************************************************/
public class Transcation{
	//變數宣告
	private AllFood.Food singleobject [];
	private AllSet.Set setobject [];
	private AllFood.Food giftobject [];
	private int singlerow;
	private int setrow;
	private int giftrow;	
	BufferedWriter bw = null;
	
	private int hour;
	private int minute;
	private int month,nextmonth;
	private int year;
	private int day;
	private String default_date;
	private int [] singlenumber;
	private int [] setnumber;
	private int [] giftnumber;
	private String barcode;
	// 建構子************************************************************************建構子參數也會傳入barcode
   	public Transcation(AllFood.Food signleobject[],AllSet.Set setobject[],AllFood.Food giftobject[],int singlerow,int setrow,int giftrow,int [] singlenumber,int [] setnumber,int [] giftnumber1,String barcode){
		set_singleobject(signleobject);
		set_setobject(setobject);
		set_giftobject(giftobject);
		
		set_singlerow(singlerow);
		set_setrow(setrow);
		set_giftrow(giftrow);
		
		set_time();
		
		set_singlenumber(singlenumber);
		set_setnumber(setnumber);
		set_giftnumber(giftnumber1);		
		
		set_barcode(barcode);
	}
	//服務方法
    public void set_singleobject(AllFood.Food singleobject[]){this.singleobject = singleobject;}
	public void set_setobject(AllSet.Set setobject[]){this.setobject = setobject;}
	public void set_giftobject(AllFood.Food giftobject[]){this.giftobject = giftobject;}
		
	public AllFood.Food [] get_singleobject(){return singleobject;}
	public AllSet.Set [] get_setobject(){return setobject;}	
	public AllFood.Food [] get_giftobject(){return giftobject;}	
	
	public void set_singlerow(int singlerow){this.singlerow = singlerow;}
	public void set_setrow(int setrow){this.setrow = setrow;}
	public void set_giftrow(int giftrow){this.giftrow = giftrow;}
	
	public int get_singlerow(){return singlerow;}
	public int get_setrow(){return setrow;}
	public int get_giftrow(){return giftrow;}
	
	public void set_singlenumber(int [] singlenumber){this.singlenumber = singlenumber;}
	public void set_setnumber(int [] setnumber){this.setnumber = setnumber;}
	public void set_giftnumber(int [] giftnumber1){this.giftnumber = giftnumber1;}
	
	public int [] get_setnumber(){return  setnumber;}
	public int [] get_singlenumber(){return  singlenumber;}
	public int [] get_giftnumber(){return  giftnumber;}
	
	public void set_barcode(String barcode){this.barcode = barcode;}
	public String get_barcode(){return barcode;}
	//功能方法
	public void set_time(){
		Date date = new Date();
      		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");     //現在精確時間的格式化  
      		default_date = bartDateFormat.format(date);                                     //產生今天日期傳到格式化裡面，就會有把時間格式化
  		//這個主要是為了要讀取現在的 年 月 日 時 分 秒
		Calendar calendar = Calendar.getInstance();                                    
		hour = calendar.get(Calendar.HOUR_OF_DAY); //小時
		minute = calendar.get(Calendar.MINUTE);    //分鐘
		month = calendar.get(Calendar.MONTH);      //月份
		nextmonth = month+1;                       //發票兌換的區間
		year = calendar.get(Calendar.YEAR)-1911;   //年 民國 所以減-1911
		day = calendar.get(Calendar.DAY_OF_MONTH); //日
	}	
  	public void create_transcationfile(boolean a){ 
      		/***********輸出檔案******************************************************************/
      		String OutFilename = "transcation.txt";//產生要輸出的檔案名稱
        	try{	
			String content = create_content(a);
			File file = new File(OutFilename);    //產生一個outputfile 先傳入要取的名子 
			file.createNewFile();                 //產生新檔案
			FileWriter fw = new FileWriter(file); //new一個寫檔案的物件，寫到file裡面
          		bw = new BufferedWriter(fw);          //new一個緩衝器的物件，讓content的內容可以寫道file裡
          		bw.write(content);                    //真正寫內容
          		JOptionPane.showMessageDialog(null,"已完成輸出交易明細\n祝您享用餐點愉快");
         
        	}
        	catch(IOException ioe){
          		ioe.printStackTrace();
        	}
		/**********在結束處理I/O的時候，通常會將它close掉，以免造成電腦資源的浪費**************/
        	finally{ 
          		try{
            			if(bw != null)
            				bw.close();
          		}
          		catch(Exception ex){
            			System.out.println("Error in closing the BufferedWriter"+ex);
          		}
        	}
   	}
	//編輯者0616:江鴻麟 增加優惠碼的判讀
	public String create_content(boolean a){ 
		String content = "";
		content +="             JaVafood's\n";
		content +="            交 易 明 細\n";
		content +="      餐廳0613 中壢中央 21-00\n";
		content +="      餐廳電話:03-422-7151\n";
		content +="      機211149  序112042\n";
		content +="      "+default_date+"\n"; 
		content +=set_detail();                      //單點的內容
		content +=single_detail();                   //套餐的內容
		content +=gift_detail();
		int totalnumber =singlerow+setrow;           //單點加套餐的數量
		content +="      共 "+totalnumber+" 項 \t外 帶:\t"+print_moneytotal()+"\n";
		content +="\t\t總 計:\t"+print_moneytotal()+"\n";
		if (a == true){
			content +="\t\t折 扣:\t"+" "+(print_moneytotal() - print_moneytotal_checkpoint())+"\n";
			content +="      發票金額\t\t"+print_moneytotal_checkpoint()+"\n";
			content +="      現金    \t\t"+print_moneytotal_checkpoint()+"\n";
		}
		else {		
			content +="      發票金額\t\t"+print_moneytotal()+"\n";
			content +="      現金    \t\t"+print_moneytotal()+"\n";
		}		
		content +="      "+get_barcode()+"\n";
		content +="      5日內持發票及明細換發票\n";
		
		return content;
	}
	//印出明細套餐的內容:食物名稱，數量，價錢
	public String set_detail(){
		String content="";
		if(setnumber[0]!=0){         //如果套餐一的數量不為0跑裡面的body
			AllSet.Set set1;
			for(int i = 0; i < setrow; i++){ 
				//找到為普通套餐的物件，用套餐物件而不是用套餐名稱的字串，這樣物件才可以方便的讀取裡面的附餐叫甚麼名子 ，價格為多少           
				if((setobject[i].getName()!=null)&&(setobject[i].getName().equals("普通套餐"))){//用套餐名稱"普通套餐"找到套餐物件位在哪個陣列元素裡
					set1 = setobject[i];
					//寫這三個變數只是方便程式碼修改而已
					AllFood.Food meal1 = set1.getMeal1();     //附餐一的物件
					AllFood.Food meal2 = set1.getMeal2();     //附餐二的物件
					AllFood.Food meal3 = set1.getMeal3();     //附餐三的物件
					content += "      餐- ";
					//為什麼附餐一不用確認是否為null，因為csv檔裡面每個套餐都有附餐一
					content += meal1.getName()+"   X"+setnumber[0]+"\t"+set1.getPrice()*setnumber[0]+"TX\n"; //套餐名稱，套餐價格，套餐數量
					if(meal2!=null){               //因為有些套餐沒有附餐二，確認有附餐二才讀附餐二的名子，價格為多少  
						content += "      "+meal2.getName()+"\n";                                   
					}
					if(meal3!=null){               //因為有些套餐沒有附餐三，確認有附餐二才讀附餐三的名子
						content += "      "+meal3.getName()+"\n";
					}
					break;
				}
			}
		}
		if(setnumber[1]!=0){
			AllSet.Set set2;
			for(int i = 0; i < setrow; i++){
				if((setobject[i].getName()!=null)&&(setobject[i].getName().equals("薯餅套餐"))){
					set2 = setobject[i];
					AllFood.Food meal1 = set2.getMeal1();
					AllFood.Food meal2 = set2.getMeal2();
					AllFood.Food meal3 = set2.getMeal3();
					content += "      餐- ";
					content += meal1.getName()+"   X"+setnumber[1]+"\t"+set2.getPrice()*setnumber[1]+"TX\n";
					if(meal2!=null){
						content += "      "+meal2.getName()+"\n";
					}
					if(meal3!=null){
						content += "      "+meal3.getName()+"\n";
					}
					break;
				}
			}
		}
		if(setnumber[2]!=0){
			AllSet.Set set3;
			for(int i = 0; i < setrow; i++){
				if((setobject[i].getName()!=null)&&(setobject [i].getName().equals("吃很飽套餐"))){
					set3 = setobject[i];
					AllFood.Food meal1 = set3.getMeal1();
					AllFood.Food meal2 = set3.getMeal2();
					AllFood.Food meal3 = set3.getMeal3();
					content += "      餐- ";
					content += meal1.getName()+"  X"+setnumber[2]+"\t"+set3.getPrice()*setnumber[2]+"TX\n";
					if(meal2!=null){
						content += "      "+meal2.getName()+"\n";
					}
					if(meal3!=null){
						content += "      "+meal3.getName()+"\n";
					}
					break;
				}
			}
			
		}
		return content;
	}
	//印明細上的單點內容: 食物名稱，數量，價錢
	public String single_detail(){
		String content="";                //原本content裡面沒有資料
		if(singlenumber[0]!=0){           //如果單點一的數量不為0跑裡面的body
			AllFood.Food food;
			//找到為單點一的物件，用單點物件而不是用單點名稱的字串，這樣物件才可以讀取裡面的單點叫甚麼名子，價格為多少  
			for(int i = 0; i < singlerow; i++){
				//用單點名稱"薯條(小)"找到單點物件位在哪個陣列元素裡，因為這樣才方便call物件的名稱，價格
				if((singleobject[i].getName()!=null)&&(singleobject [i].getName().equals("薯條(小)"))){
					food = singleobject[i];                                                        //寫一個food的變數只是方便程式碼修改而已
					content += "      "+food.getName()+"\t     X"+singlenumber[0]+"\t"+food.getPrice()*singlenumber[0]+"TX\n"; //單點名稱，數量，價格
				}
			}
		}
		if(singlenumber[1]!=0){
			AllFood.Food food;
			for(int i = 0; i < singlerow; i++){
				if((singleobject[i].getName()!=null)&&(singleobject [i].getName().equals("麥香雞"))){
					food = singleobject[i];
					content += "      "+food.getName()+"\t     X"+singlenumber[1]+"\t"+food.getPrice()*singlenumber[1]+"TX\n";
				}
			}
		}
		if(singlenumber[2]!=0){
			AllFood.Food food;
			for(int i = 0; i < singlerow; i++){
				if((singleobject[i].getName()!=null)&&(singleobject [i].getName().equals("可樂(小)"))){
					food = singleobject[i];
					content += "      "+food.getName()+"\t     X"+singlenumber[2]+"\t"+food.getPrice()*singlenumber[2]+"TX\n";
				}
			}
		}
		if(singlenumber[3]!=0){
			AllFood.Food food;
			for(int i = 0; i < singlerow; i++){
				if((singleobject[i].getName()!=null)&&(singleobject [i].getName().equals("雪碧(小)"))){
					food = singleobject[i];
					content += "      "+food.getName()+"\t     X"+singlenumber[3]+"\t"+food.getPrice()*singlenumber[3]+"TX\n";
				}
			}
		}
		if(singlenumber[4]!=0){
			AllFood.Food food;
			for(int i = 0; i < singlerow; i++){
				if((singleobject[i].getName()!=null)&&(singleobject [i].getName().equals("薯條(中)"))){
					food = singleobject[i];
					content += "      "+food.getName()+"\t     X"+singlenumber[4]+"\t"+food.getPrice()*singlenumber[4]+"TX\n";
				}
			}
		}
		if(singlenumber[5]!=0){
			AllFood.Food food;
			for(int i = 0; i < singlerow; i++){
				if((singleobject[i].getName()!=null)&&(singleobject [i].getName().equals("薯餅"))){
					food = singleobject[i];
					content += "      "+food.getName()+"\t     X"+singlenumber[5]+"\t"+food.getPrice()*singlenumber[5]+"TX\n";
				}
			}
		}
		if(singlenumber[6]!=0){
			AllFood.Food food;
			for(int i = 0; i < singlerow; i++){
				if((singleobject[i].getName()!=null)&&(singleobject [i].getName().equals("滿福堡"))){
					food = singleobject[i];
					content += "      "+food.getName()+"\t     X"+singlenumber[6]+"\t"+food.getPrice()*singlenumber[6]+"TX\n";
				}
			}
		}
		if(singlenumber[7]!=0){
			AllFood.Food food;
			for(int i = 0; i < singlerow; i++){
				if((singleobject[i].getName()!=null)&&(singleobject [i].getName().equals("薯條(大)"))){
					food = singleobject[i];
					content += "      "+food.getName()+"\t     X"+singlenumber[7]+"\t"+food.getPrice()*singlenumber[7]+"TX\n";
				}
			}
		}
		if(singlenumber[8]!=0){
			AllFood.Food food;
			for(int i = 0; i < singlerow; i++){
				if((singleobject[i].getName()!=null)&&(singleobject [i].getName().equals("大麥克"))){
					food = singleobject[i];
					content += "      "+food.getName()+"\t     X"+singlenumber[8]+"\t"+food.getPrice()*singlenumber[8]+"TX\n";
				}
			}
		}
		if(singlenumber[9]!=0){
			AllFood.Food food;
			for(int i = 0; i < singlerow; i++){
				if((singleobject[i].getName()!=null)&&(singleobject [i].getName().equals("玉米濃湯"))){
					food = singleobject[i];
					content += "      "+food.getName()+"\t     X"+singlenumber[9]+"\t"+food.getPrice()*singlenumber[9]+"TX\n";
				}
			}
		}
		return content;
	}
	//印贈品的明細: 食物名稱，數量，價錢
	public String gift_detail(){// edited by 江鴻麟 time:0618 點數兌換的商品
		String content="";                //原本content裡面沒有資料
		if(giftnumber[0]!=0){           //如果單點一的數量不為0跑裡面的body			
			AllFood.Food food;
			//找到為單點一的物件，用單點物件而不是用單點名稱的字串，這樣物件才可以讀取裡面的單點叫甚麼名子，價格為多少  
			for(int i = 0; i < giftrow; i++){
				//用單點名稱"薯條(小)"找到單點物件位在哪個陣列元素裡，因為這樣才方便call物件的名稱，價格
				if((giftobject[i].getName()!=null)&&(giftobject [i].getName().equals("薯條(小)"))){
					food = giftobject[i];                                                        //寫一個food的變數只是方便程式碼修改而已
					content += "      "+food.getName()+"\t X"+giftnumber[0] +"(點數兌換)\n"; //單點名稱，數量
					break;
				}
			}
		} 
 		if(giftnumber[1]!=0){
			AllFood.Food food;
			for(int i = 0; i < giftrow; i++){
				if((giftobject[i].getName()!=null)&&(giftobject [i].getName().equals("麥香雞"))){
					food = giftobject[i];
					content += "      "+food.getName()+"\t X"+giftnumber[1] + "(點數兌換)\n";
					break;
				}
			}
		}
 		if(giftnumber[2]!=0){
			AllFood.Food food;
			for(int i = 0; i < giftrow; i++){
				if((giftobject[i].getName()!=null)&&(giftobject [i].getName().equals("可樂(小)"))){
					food = giftobject[i];
					content += "      "+food.getName()+"\t X"+giftnumber[2] + "(點數兌換)\n";
					break;
				}
			}
		} 
 		if(giftnumber[3]!=0){
			AllFood.Food food;
			for(int i = 0; i < giftrow; i++){
				if((giftobject[i].getName()!=null)&&(giftobject [i].getName().equals("雪碧(小)"))){
					food = giftobject[i];
					content += "      "+food.getName()+"\t X"+giftnumber[3] + "(點數兌換)\n";
					break;
				}
			}
		} 
		if(giftnumber[4]!=0){
			AllFood.Food food;
			for(int i = 0; i < giftrow; i++){
				if((giftobject[i].getName()!=null)&&(giftobject [i].getName().equals("薯條(中)"))){
					food = giftobject[i];
					content += "      "+food.getName()+"\t X"+giftnumber[4] + "(點數兌換)\n";
					break;
				}
			}
		}
		if(giftnumber[5]!=0){
			AllFood.Food food;
			for(int i = 0; i < giftrow; i++){
				if((giftobject[i].getName()!=null)&&(giftobject [i].getName().equals("薯餅"))){
					food = giftobject[i];
					content += "      "+food.getName()+"\t X"+giftnumber[5] + "(點數兌換)\n";
					break;
				}
			}
		}
		if(giftnumber[6]!=0){
			AllFood.Food food;
			for(int i = 0; i < giftrow; i++){
				if((giftobject[i].getName()!=null)&&(giftobject [i].getName().equals("滿福堡"))){
					food = giftobject[i];
					content += "      "+food.getName()+"\t X"+giftnumber[6] + "(點數兌換)\n";
					break;
				}
			}
		}
		if(giftnumber[7]!=0){
			AllFood.Food food;
			for(int i = 0; i < giftrow; i++){
				if((giftobject[i].getName()!=null)&&(giftobject [i].getName().equals("薯條(大)"))){
					food = giftobject[i];
					content += "      "+food.getName()+"\t X"+giftnumber[7] + "(點數兌換)\n";
					break;
				}
			}
		}
		if(giftnumber[8]!=0){
			AllFood.Food food;
			for(int i = 0; i < giftrow; i++){
				if((giftobject[i].getName()!=null)&&(giftobject [i].getName().equals("大麥克"))){
					food = giftobject[i];
					content += "      "+food.getName()+"\t X"+giftnumber[8] + "(點數兌換)\n";
					break;
				}
			}
		}
		if(giftnumber[9]!=0){
			AllFood.Food food;
			for(int i = 0; i < giftrow; i++){
				if((giftobject[i].getName()!=null)&&(giftobject [i].getName().equals("玉米濃湯"))){
					food = giftobject[i];
					content += "      "+food.getName()+"\t X"+giftnumber[9] + "(點數兌換)\n";
					break;
				}
			}
		} 
		return content;
	}
	//印出總共多少錢
	public int print_moneytotal(){
		//總金額一開始為0
		int moneytotal=0;
		for (int i = 0; i < singlerow; i++){               //讀取單點每個物件的價格
			moneytotal += singleobject[i].getPrice();  
		}
		for (int i = 0; i < setrow; i++){                  //讀取套餐每個物件的價格
			moneytotal += setobject[i].getPrice();
		}
		return moneytotal;
	}
	//編輯者:江鴻麟 增加優惠碼的判讀，如果有用優惠卷總金額也會顯示
	public double print_moneytotal_checkpoint(){
		//總金額一開始為0
		int moneytotal=0;
		for (int i = 0; i < singlerow; i++){               //讀取單點每個物件的價格
			moneytotal += singleobject[i].getPrice();  
		}
		for (int i = 0; i < setrow; i++){                  //讀取套餐每個物件的價格
			moneytotal += setobject[i].getPrice();
		}
		return Math.ceil(moneytotal*0.9);
	}
}



