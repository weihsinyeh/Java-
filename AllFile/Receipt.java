//developed by 葉惟欣 time:0613
//創造發票
package AllFile;
//引入套件
import AllFood.*;
import AllSet.*;
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
public class Receipt{
	/***************變數宣告*********************************************************/
	private AllFood.Food singleobject [];
	private AllSet.Set setobject [];
	private int singlerow;
	private int setrow;
	BufferedWriter bw = null;
	
	private int hour;
	private int minute;
	private int month,nextmonth;
	private int year;
	private int day;
	private String default_date;
	private String barcode;
	/***************建構子，傳入條碼的代碼******************************************************/
   	public Receipt(AllFood.Food signleobject[],AllSet.Set setobject[],int singlerow,int setrow,String barcode){
		set_singleobject(signleobject);
		set_setobject(setobject);
		set_singlerow(singlerow);
		set_setrow(setrow);
		set_time();
		set_barcode(barcode);
		
	}
	/***************服務方法******************************************************/
      	public void set_singleobject(AllFood.Food singleobject[]){this.singleobject = singleobject;}
	public void set_setobject(AllSet.Set setobject[]){this.setobject = setobject;}
	public AllFood.Food [] get_singleobject(){return singleobject;}
	public AllSet.Set [] get_setobject(){return setobject;}	
	public void set_singlerow(int singlerow){this.singlerow = singlerow;}
	public void set_setrow(int setrow){this.setrow = setrow;}
	public int get_singlerow(){return singlerow;}
	public int get_setrow(){return setrow;}
	public void set_barcode(String barcode){this.barcode=barcode;}
	public String get_barcode(){return barcode;}
	/***************功能方法******************************************************/
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
  	public void create_receiptfile(boolean a){ 
      		/***********輸出檔案******************************************************************/
      		String OutFilename = "receipt.txt";//產生要輸出的檔案名稱
        	try{	
			String content = create_content(a);
			File file = new File(OutFilename);    //產生一個outputfile 先傳入要取的名子 
			file.createNewFile();                 //產生新檔案
			FileWriter fw = new FileWriter(file); //new一個寫檔案的物件，寫到file裡面
          		bw = new BufferedWriter(fw);          //new一個緩衝器的物件，讓content的內容可以寫道file裡
          		bw.write(content);                    //真正寫內容
          		JOptionPane.showMessageDialog(null,"已完成輸出發票\n祝您享用餐點愉快");
          	
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
		content +="          電子發票證明聯\n";
		content +="            "+year+"年 "+month+"-"+nextmonth+"月\n";
		content +="      "+create_abc()+"-"+creat_ninenumbers();
		content +="\n      "+default_date+"\n";
		if (a == true){content +="      隨機碼:"+creat_randomnumber()+"   總計:"+print_moneytotal_checkpoint()+"\n";}
		else {content +="      隨機碼:"+creat_randomnumber()+"    總計:"+print_moneytotal()+"\n";}
		content +="      賣方碼:16516288\n";
		content +="      "+get_barcode()+"\n\n"; 
		content +="      餐廳0613 桃園中壢 21-00\n";
		content +="      機211149  序112042 " +nextmonth+"/"+day+"\n";
		content +="      5日內持發票及明細換發票";
		return content;
	}
	//產生隨機ABC代碼兩個英文字母
	public String create_abc(){
		String content="";
		int[] arr=new int[2];              //定義一個整型陣列，陣列長度為輸入值
		boolean[] flag=new boolean[26];    //定義一個Boolean型陣列，用來除去重複值
		for(int i=0;i<arr.length;i++){     //通過迴圈為陣列賦值
			Random rand=new Random();
			int index;
			do{
				index=rand.nextInt(26);    //隨機生成0-25的數字並賦值給index
			}while(flag[index]);    //判斷flag值是否為true,如果為true則重新為index賦值
			arr[i]=index+65;        //大寫字母的ASCII值為65-90，所以給index的值加上65，使其符合大寫字母的ASCII值區間
			flag[index]=true;       //讓對應的flag值為true
		}
		Arrays.sort(arr);//按升序排列陣列
		for(int n=0;n<arr.length;n++){
			content+=((char)(arr[n]));//遍歷陣列並且將其轉為char型
		}
		return content;
	}
	//產生9位數的隨機數字(看發票就知道了)
	public int creat_ninenumbers(){
		Random r = new Random();
		return r.nextInt(899999999)+100000000;
	}
	//隨機碼(看發票就知道了)
	public int creat_randomnumber(){
		Random r = new Random();
		return r.nextInt(999)+1000;
	}
	//印出多少錢
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
	//編輯者0616:江鴻麟 增加優惠碼的判讀
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



