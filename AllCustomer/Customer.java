//developed by 葉惟欣 time:0608
//edited by 何艾穎 time:0610
//edited by 葉惟欣 time:0613
//edited by 葉惟欣 time:0615
//edited by 葉惟欣 time:0618
//客人物件(含點餐時的食物創建)
package AllCustomer;
import java.util.Random;
import java.util.Arrays;
import AllFood.*;         //引入單點的套件
import AllSet.*;          //引入套餐的套件
import AllFile.*;	  //引入讀檔案的套件
import java.util.LinkedList; //存放食物(單點，套件)的清單
public class Customer {
	/**************變數宣告區*****************************************/

	private static int foodnumber = 0;                     //計算目前點的單點食物	

	private String item1[][] = new String[10][5];                 //存放單點的資訊
	private String item2[][] = new String[6][3];                  //存放套餐的資訊
	private String member_gift_list [][] = new String [10][3];    //存放禮物的資訊
	private String promotion_list [][] = new String [15][2];      //存放優惠卷的資訊
	public String giftfood = "";
	
	public LinkedList<String> singlelist = new LinkedList<String>();    //存放單點的購物清單
	public LinkedList<String> setlist = new LinkedList<String>();       //存放套餐的購物清單
	public LinkedList<String> giftlist = new LinkedList<String>();      //存放贈品的清單
	public LinkedList<String> giftoption = new LinkedList<String>();    //存放可以當作選項的餐點(點數能夠支付)

	public AllFood.Food meals[][] = new AllFood.Food[3][3];             //套餐用來裝單點物件的陣列

	public AllFood.Food singleobject[];                                 //存放單點物件的購物清單
	public AllSet.Set setobject[];                                      //存放套餐物件的購物清單
	public AllFood.Food giftobject[];                                   //存放贈品(只有單點)物件的清單
	public int moneytotal = 0;
	public int moneytotal2 = 0;
	
	public int singlenumber[]  =  new int[10];         //單點每份的數量
	public int setnumber[] = new int [3];              //套餐每份的數量
	public int giftfoodnumber[] = new int [10];	   //點數兌換後每份贈品的數量(只有單點)
	
	//宣告單點物件的名稱
	public AllFood.SmallFried smallfried;
	public AllFood.MaixiangChicken maixiangchicken;
	public AllFood.Cola cola;
	public AllFood.Soda soda;
	public AllFood.MiddleFried middlefried;
	public AllFood.PotatoCake potatocake;
	public AllFood.Manfubao manfubao;
	public AllFood.BigFried bigfried;
	public AllFood.BigMac bigmac;
	public AllFood.CornBisque cornbisque;

	AllCustomer.MemberClub m = new AllCustomer.MemberClub();                 //*********************************************

	/*************讀取檔案*********************/
	public void read_file(){
		AllFile.File single = new AllFile.File("Single_meal.csv",10,5);
		item1 = single.create_file();
		//edited by 葉惟欣 time:0618
		System.out.println(single.create_singleDos());
		
		AllFile.File set = new AllFile.File("Set_meal.csv",3,6);
		item2 = set.create_file();
		//edited by 葉惟欣 time:0618
		System.out.println(set.create_setDos());

		AllFile.File member_gift = new AllFile.File("Member_Gift.csv",10,3);
		member_gift_list =member_gift.create_file();
	
		AllFile.File promotion = new AllFile.File("Promotion_Code.csv",15,2);
		promotion_list = promotion.create_file();
		
	}
	/*******************************************/
	/************************************************************加點食物*******************************************/

	public void add_single_food(String singlefood){singlelist.add(singlefood);}//增加單點食物
	public void add_set_food(String setfood){setlist.add(setfood);}//增加套餐食物

	//編譯者0617:江鴻麟 簡化 (增加禮物清單上的單點字串名稱)
	public int add_gift(String giftfood,int point){
		int p = 0;
		if(giftfood=="薯條(小)"){
			giftlist.add(giftfood);
			p=10;                     //薯條(小)的點數
		}
		else if(giftfood=="麥香雞") {
			giftlist.add(giftfood);
			p=15; 
		}
		else if(giftfood=="可樂(小)") {
			giftlist.add(giftfood);
			p=10; 
		}
		else if(giftfood=="雪碧(小)") {
			giftlist.add(giftfood);
			p=10; 
		}
		else if(giftfood=="薯條(中)") {
			giftlist.add(giftfood);
			p=15;
		}
		else if(giftfood=="薯餅") {
			giftlist.add(giftfood); 
			p=10;
		}
		else if(giftfood=="滿福堡") {
			giftlist.add(giftfood); 
			p=10; 
		}
		else if(giftfood=="薯條(大)") {
			giftlist.add(giftfood); 
			p=20;
		}
		else if(giftfood=="大麥克") { 
			giftlist.add(giftfood); 
			p=20;
		}
		else if(giftfood=="玉米濃湯") {
			giftlist.add(giftfood);
			p=20; 
		}
		return (point - p);
	}
	//編譯者0617:江鴻麟 存放可以當作選項的贈品(點數能夠支付)
	public void checkpoint(int point){

		if(point>= 10) { 
			giftfood = "薯條(小)";
			giftoption.add(giftfood);
		}
		if(point>=15) {
			giftfood = "麥香雞";				
			giftoption.add(giftfood);
		}
		if(point>=10){ 
			giftfood = "可樂(小)";
			giftoption.add(giftfood);
		}
		if(point>=10){ 
			giftfood = "雪碧(小)";
			giftoption.add(giftfood);
		}
		if(point>=15){ 
			giftfood = "薯條(中)";
			giftoption.add(giftfood);
		}
		if(point>=10){ 
			giftfood = "薯餅";
			giftoption.add(giftfood); 
		}
		if(point>=20) { 
			giftfood ="滿福堡";
			giftoption.add(giftfood); 
		}
		if(point>=20) { 
			giftfood = "薯條(大)";
			giftoption.add(giftfood); 
		}
		if(point>=20) {
			giftfood = "大麥克";				
			giftoption.add(giftfood); 
		}	
		if(point>=20) { 
			giftfood = "玉米濃湯";
			giftoption.add(giftfood);
		}
		if(giftoption.size() == 0){
			giftoption.add("糟糕您的點數不足");
		}
	}
	/*****************************************************************刪除與換餐***********************************************/

	public void delete_single_food(String singlefood){    //刪除單點食物
		Boolean success = false;
		success = singlelist.remove(singlefood);      //直接用LinkedList裡面的方法
		if(success){System.out.println("移除成功");}
		else{System.out.println("移除失敗");}
	}
	public void delete_set_food(String setfood){          //刪除套餐食物
		Boolean success = false;
		success = setlist.remove(setfood);            //直接用LinkedList裡面的方法
		if(success){System.out.println("移除成功");}
		else{System.out.println("移除失敗");}
	}
	public void change_single_food(String bechanged,String food){  //換餐:單點食物
		int number = singlelist.indexOf(bechanged);            //直接用LinkedList裡面的方法 先找到要換的單點字串名稱
		singlelist.set(number,food);                           //直接用LinkedList裡面的方法 在直接換掉LinkedList裡面的data內容
	}
	public void change_set_food(String bechanged,String setfood){  //換餐:套餐食物
		int number = setlist.indexOf(bechanged);               //直接用LinkedList裡面的方法 先找到要換的套餐字串名稱 
		setlist.set(number,setfood);                           //直接用LinkedList裡面的方法 在直接換掉LinkedList裡面的data內容
	}

	public boolean check_code(int coding){	//檢查优惠卷是否存在
		boolean code=false;
		for (int i = 0; i < 15; i++) {  //用for迴圈找優惠卷碼
			for (int j = 0; j < 2; j++) {
		   		if ( Integer.parseInt(promotion_list [i][j]) == coding ) {
					code=true;
					System.out.println("優惠券使用成功！"); 
				}
		  	}
		}
		if(code==false) { System.out.println("優惠券使用失敗！"); }
		return code;
	}
	//################################################################建構物件#########################################################/

	/***************************建構單點物件 ##注意單點物件的建構必須再套餐物件建構前***********************************************/

	public void create_single_food(){
		int size = singlelist.size();                     //先看整個單點清單的大小，也就是總共買(幾項)單點食物
		singleobject = new AllFood.Food [size];           //用父類別多型來宣告總共買(幾項)的單點物件
		for (int i = 0; i < size; i++) {                  //跑for會全找到 單點清單(字串)所對應到的名字，來建構對應到的名字的物件
            		if(singlelist.get(i)=="薯條(小)"){       
				smallfried = new AllFood.SmallFried(item1[0][0],item1[0][1],item1[0][2],item1[0][3],item1[0][4]); //傳遞的建構子參數為 讀檔案進來的資料
				singleobject[i] = smallfried;     //將建構好的單點物件放到單點物件清單上
				singlenumber[0]++;                //同時也在計次到底點了多少"單點物件"
			}
			else if(singlelist.get(i)=="麥香雞"){
				maixiangchicken = new AllFood.MaixiangChicken(item1[1][0],item1[1][1],item1[1][2],item1[1][3],item1[1][4]);
				singleobject[i] = maixiangchicken;
				singlenumber[1]++;
			}
			else if(singlelist.get(i)=="可樂(小)"){
				cola = new AllFood.Cola(item1[2][0],item1[2][1],item1[2][2],item1[2][3],item1[2][4]);
				singleobject[i] = cola;
				singlenumber[2]++;
			}
			else if(singlelist.get(i)=="雪碧(小)"){
				soda = new AllFood.Soda(item1[3][0],item1[3][1],item1[3][2],item1[3][3],item1[3][4]);
				singleobject[i] = soda;
				singlenumber[3]++;
			}
			else if(singlelist.get(i)=="薯條(中)"){
				middlefried = new AllFood.MiddleFried(item1[4][0],item1[4][1],item1[4][2],item1[4][3],item1[4][4]);
				singleobject[i] = middlefried;
				singlenumber[4]++;
			}
			else if(singlelist.get(i)=="薯餅"){
				potatocake = new AllFood.PotatoCake(item1[5][0],item1[5][1],item1[5][2],item1[5][3],item1[5][4]);
				singleobject[i] = potatocake;
				singlenumber[5]++;
			}
			else if(singlelist.get(i)=="滿福堡"){
				manfubao = new AllFood.Manfubao(item1[6][0],item1[6][1],item1[6][2],item1[6][3],item1[6][4]);
				singleobject[i] = manfubao;
				singlenumber[6]++;
			}
			else if(singlelist.get(i)=="薯條(大)"){
				bigfried = new AllFood.BigFried(item1[7][0],item1[7][1],item1[7][2],item1[7][3],item1[7][4]);
				singleobject[i] = bigfried ;
				singlenumber[7]++;
			}
			else if(singlelist.get(i)=="大麥克"){
				bigmac = new AllFood.BigMac(item1[8][0],item1[8][1],item1[8][2],item1[8][3],item1[8][4]);
				singleobject[i] = bigmac ;
				singlenumber[8]++;
			}
			else if(singlelist.get(i)=="玉米濃湯"){
				cornbisque = new AllFood.CornBisque(item1[9][0],item1[9][1],item1[9][2],item1[9][3],item1[9][4]);
				singleobject[i] = cornbisque;
				singlenumber[9]++;
			}
			
        	}
	}
		
	/***************************單點物件建構完成 **********************************************************************************************/
	/***************************建構套餐物件 ##注意單點物件的建構必須再套餐物件建構前***********************************************/
	public void create_set_food(){
		for(int i = 0;i<3;i++){             //用for迴圈找到編號的內容
			for(int j = 2;j<5;j++){
				/***********判斷式裡的條件為字串物件相比 <注意> 字串物件兩兩判斷是否相等請使用這種判斷方式 (不然會報錯)******************/
				if(item2[i][j]!=null&&item2[i][j].equals("1")){                                  //因為老師給的csv檔的附餐是以編號，所以這邊是在匹配編號的內容
					smallfried = new AllFood.SmallFried(item1[0][0],item1[0][1],item1[0][2],item1[0][3],item1[0][4]); //建構到餐裡的單點物件
					meals[i][j-2]=smallfried;                                                                         //存到專門放套餐單點的陣列裡面
				}           
				else if(item2[i][j]!=null&&item2[i][j].equals("2")){
					maixiangchicken = new AllFood.MaixiangChicken(item1[1][0],item1[1][1],item1[1][2],item1[1][3],item1[1][4]);
					meals[i][j-2]=maixiangchicken;
				}
				else if(item2[i][j]!=null&&item2[i][j].equals("3")){
					cola = new AllFood.Cola(item1[2][0],item1[2][1],item1[2][2],item1[2][3],item1[2][4]);
					meals[i][j-2]=cola;
				}
				else if(item2[i][j]!=null&&item2[i][j].equals("4")){
					soda = new AllFood.Soda(item1[3][0],item1[3][1],item1[3][2],item1[3][3],item1[3][4]);
					meals[i][j-2]=soda;
				}
				else if(item2[i][j]!=null&&item2[i][j].equals("5")){
					middlefried = new AllFood.MiddleFried(item1[4][0],item1[4][1],item1[4][2],item1[4][3],item1[4][4]);
					meals[i][j-2]=middlefried;}
				else if(item2[i][j]!=null&&item2[i][j].equals("6")){
					potatocake = new AllFood.PotatoCake(item1[5][0],item1[5][1],item1[5][2],item1[5][3],item1[5][4]);
					meals[i][j-2]=potatocake;
				}
				else if(item2[i][j]!=null&&item2[i][j].equals("7")){
					manfubao = new AllFood.Manfubao(item1[6][0],item1[6][1],item1[6][2],item1[6][3],item1[6][4]);
					meals[i][j-2]=manfubao;
				}
				else if(item2[i][j]!=null&&item2[i][j].equals("8")){
					bigfried = new AllFood.BigFried(item1[7][0],item1[7][1],item1[7][2],item1[7][3],item1[7][4]);
					meals[i][j-2]=bigfried;
				}
				else if(item2[i][j]!=null&&item2[i][j].equals("9")){
					bigmac = new AllFood.BigMac(item1[8][0],item1[8][1],item1[8][2],item1[8][3],item1[8][4]);
					meals[i][j-2]=bigmac;
				}
				else if(item2[i][j]!=null&&item2[i][j].equals("10")){
					cornbisque = new AllFood.CornBisque(item1[9][0],item1[9][1],item1[9][2],item1[9][3],item1[9][4]);
					meals[i][j-2]=cornbisque;
				}
				else{meals[i][j-2]=null;}
			}
		}

		int size = setlist.size();                //套餐字串清單的大小
		setobject = new AllSet.Set [size];        //套餐物件的大小
		for (int i = 0; i < size; i++){           //用迴圈找到對應的套餐字串
			if(setlist.get(i)=="普通套餐"){
				AllSet.Set1 set1 = new AllSet.Set1(item2[0][0],item2[0][1],meals[0][0],meals[0][1],meals[0][2],item2[0][5]); //建構子參數傳剛剛單點建構
				setobject[i] = set1;                                                                                         //把建構的套餐物件放到套餐物件清單上
				setnumber[0]++;                                                                                              //同時套餐的數量也增加
			}
			else if(setlist.get(i)=="薯餅套餐"){
				AllSet.Set2 set2 = new AllSet.Set2(item2[1][0],item2[1][1],meals[1][0],meals[1][1],meals[1][2],item2[1][5]);
				setobject[i] = set2;
				setnumber[1]++;
			}
			else if(setlist.get(i)=="吃很飽套餐"){
				AllSet.Set3 set3 = new AllSet.Set3(item2[2][0],item2[2][1],meals[2][0],meals[2][1],meals[2][2],item2[2][5]);
				setobject[i] = set3;
				setnumber[2]++;
			}
		}
	}
	/***************************套餐物件建構完成 **********************************************************************************************/

	/**********************************************************建構贈品物件 與  建構單點物件一樣******************************************************************/
	//edited:江鴻麟 date:0617 增加giftfoodnumber
	public void create_gift_food(){
		int size = giftlist.size();
		giftobject = new AllFood.Food [size];
		for (int i = 0; i < size; i++) {
            		if(giftlist.get(i)=="薯條(小)"){
				smallfried = new AllFood.SmallFried(item1[0][0],item1[0][1],item1[0][2],item1[0][3],item1[0][4]); 
				giftobject[i] = smallfried;
				giftfoodnumber[0]++;
			}
			else if(giftlist.get(i)=="麥香雞"){
				maixiangchicken = new AllFood.MaixiangChicken(item1[1][0],item1[1][1],item1[1][2],item1[1][3],item1[1][4]);
				giftobject[i] = maixiangchicken;
				giftfoodnumber[1]++;
			}
			else if(giftlist.get(i)=="可樂(小)"){
				cola = new AllFood.Cola(item1[2][0],item1[2][1],item1[2][2],item1[2][3],item1[2][4]);
				giftobject[i] = cola;
				giftfoodnumber[2]++;
			}
			else if(giftlist.get(i)=="雪碧(小)"){
				soda = new AllFood.Soda(item1[3][0],item1[3][1],item1[3][2],item1[3][3],item1[3][4]);
				giftobject[i] = soda;
				giftfoodnumber[3]++;
			}
			else if(giftlist.get(i)=="薯條(中)"){
				middlefried = new AllFood.MiddleFried(item1[4][0],item1[4][1],item1[4][2],item1[4][3],item1[4][4]);
				giftobject[i] = middlefried;
				giftfoodnumber[4]++;
			}
			else if(giftlist.get(i)=="薯餅"){
				potatocake = new AllFood.PotatoCake(item1[5][0],item1[5][1],item1[5][2],item1[5][3],item1[5][4]);
				giftobject[i] = potatocake;
				giftfoodnumber[5]++;
			}
			else if(giftlist.get(i)=="滿福堡"){
				manfubao = new AllFood.Manfubao(item1[6][0],item1[6][1],item1[6][2],item1[6][3],item1[6][4]);
				giftobject[i] = manfubao;
				giftfoodnumber[6]++;
			}
			else if(giftlist.get(i)=="薯條(大)"){
				bigfried = new AllFood.BigFried(item1[7][0],item1[7][1],item1[7][2],item1[7][3],item1[7][4]);
				giftobject[i] = bigfried ;
				giftfoodnumber[7]++;
			}
			else if(giftlist.get(i)=="大麥克"){
				bigmac = new AllFood.BigMac(item1[8][0],item1[8][1],item1[8][2],item1[8][3],item1[8][4]);
				giftobject[i] = bigmac ;
				giftfoodnumber[8]++;
			}
			else if(giftlist.get(i)=="玉米濃湯"){
				cornbisque = new AllFood.CornBisque(item1[9][0],item1[9][1],item1[9][2],item1[9][3],item1[9][4]);
				giftobject[i] = cornbisque;
				giftfoodnumber[9]++;
			}
			
        	}

	}
/*****************************************************贈品物件建構完成 **********************************************************************************************/

	/****************步驟三:點餐明細功能已完成，在Dos上面的，但現在沒有被呼叫到這個函數**************************************************************************/
	public void print_content(){
		for (int i = 0; i < singlelist.size(); i++){
			System.out.println(singleobject[i]);
		}
		for (int i = 0; i < setlist.size(); i++){
			System.out.println(setobject[i]);
		}
		System.out.println("\n贈送:");
		for (int i = 0; i < giftlist.size(); i++){
			System.out.println(giftobject[i].getName());
		}
		System.out.println();
	}
	/***************步驟三:列印出總金額********************************************************************************************************/
	public int print_moneytotal(){
		moneytotal = 0;
		for (int i = 0; i < singlelist.size(); i++){
			moneytotal += singleobject[i].getPrice();
		}
		for (int i = 0; i < setlist.size(); i++){
			moneytotal += setobject[i].getPrice();
		}
		return moneytotal;
	}
	//編輯者0616:江鴻麟 增加優惠碼的判讀(如果有優惠碼就都乘0.9)如果有優惠卷就會跑這個迴圈
	public double print_moneytotal_checkpoint(){
		moneytotal2 = 0;
 		for (int i = 0; i < singlelist.size(); i++){
			moneytotal2 += singleobject[i].getPrice();
 		} 
 		for (int i = 0; i < setlist.size(); i++){
			moneytotal2 += setobject[i].getPrice();
		} 
		return Math.ceil(moneytotal2*0.9);
	}
	/****************************************************************優惠價*********************************************************************/
	public void discount(boolean code){if(code==true){ System.out.println("折扣后："+moneytotal*0.9); }} //如果有的話就在Dos上面印出折扣後的價錢
	//單點字串的清單內容的建立
	public String[] singlelist(){
		String singlelist1[] = new String[singlelist.size()];
		for(int i = 0; i < singlelist.size(); i++){
			singlelist1[i] = singlelist.get(i);
		}
		return singlelist1;
	}
	//贈品字串的清單內容的建立
	public String[] giftoption(){
		String giftoption1[] = new String[giftoption.size()];
		for(int i = 0; i < giftoption.size(); i++){
			giftoption1[i] = giftoption.get(i);
		}
		return giftoption1;
	}
	//套餐字串的清單內容的建立
	public String[] setlist(){
		String setlist1[] = new String[setlist.size()];
		for(int i = 0; i < setlist.size(); i++){
			setlist1[i] = setlist.get(i);
		}
		return setlist1;
	}
	//編輯者0616:江鴻麟 增加優惠碼的判讀
	public String Finallist(boolean a){
		String finallist = "";
		//印出單點與套餐與贈品的內容
		finallist += single_detail();      //此方法在下面
		finallist += set_detail();         //此方法在下面
		if(giftlist.size() > 0 ){
			finallist += gift_detail();
		}
		//只是為了排版而已
		if(a == true){
			finallist += "                                         折扣:"+ " " + (print_moneytotal() - print_moneytotal_checkpoint()) +"TX\n";
			finallist += "                                         總計: " + print_moneytotal_checkpoint() +"TX";	
		}
		else{
			finallist += "                                         總計: " + print_moneytotal() +"TX";
		}
		return finallist;
	}
	/*****************************************JOP裡面明細的呈現******************************************************************************************/
	public String single_detail(){
		String content="";                //原本content裡面沒有資料
		if(singlenumber[0]!=0){           //如果單點一的數量不為0跑裡面的body
			AllFood.Food food;
			//找到為單點一的物件，用單點物件而不是用單點名稱的字串，這樣物件才可以讀取裡面的單點叫甚麼名子，價格為多少  
			for(int i = 0; i < singlelist.size(); i++){
				//用單點名稱"薯條(小)"找到單點物件位在哪個陣列元素裡，因為這樣才方便call物件的名稱，價格
				if((singleobject[i].getName()!=null)&&(singleobject [i].getName().equals("薯條(小)"))){
					food = singleobject[i];                                                        //寫一個food的變數只是方便程式碼修改而已
					content += "      "+food.getName()+"\t         X"+singlenumber[0]+"       價格: "+food.getPrice()*singlenumber[0]+"TX\n"; //單點名稱，數量，價格
					break;
				}
			}
		} 
		if(singlenumber[1]!=0){
			AllFood.Food food;
			for(int i = 0; i < singlelist.size(); i++){
				if((singleobject[i].getName()!=null)&&(singleobject [i].getName().equals("麥香雞"))){
					food = singleobject[i];
					content += "      "+food.getName()+"\t           X"+singlenumber[1]+"       價格: "+food.getPrice()*singlenumber[1]+"TX\n";
					break;
				}
			}
		}
		if(singlenumber[2]!=0){
			AllFood.Food food;
			for(int i = 0; i < singlelist.size(); i++){
				if((singleobject[i].getName()!=null)&&(singleobject [i].getName().equals("可樂(小)"))){
					food = singleobject[i];
					content += "      "+food.getName()+"\t        X"+singlenumber[2]+"       價格: "+food.getPrice()*singlenumber[2]+"TX\n";
					break;
				}
			}
		}
		if(singlenumber[3]!=0){
			AllFood.Food food;
			for(int i = 0; i < singlelist.size(); i++){
				if((singleobject[i].getName()!=null)&&(singleobject [i].getName().equals("雪碧(小)"))){
					food = singleobject[i];
					content += "      "+food.getName()+"\t        X"+singlenumber[3]+"       價格: "+food.getPrice()*singlenumber[3]+"TX\n";
					break;
				}
			}
		}
		if(singlenumber[4]!=0){
			AllFood.Food food;
			for(int i = 0; i < singlelist.size(); i++){
				if((singleobject[i].getName()!=null)&&(singleobject [i].getName().equals("薯條(中)"))){
					food = singleobject[i];
					content += "      "+food.getName()+"\t        X"+singlenumber[4]+"       價格: "+food.getPrice()*singlenumber[4]+"TX\n";
					break;
				}
			}
		}
		if(singlenumber[5]!=0){
			AllFood.Food food;
			for(int i = 0; i < singlelist.size(); i++){
				if((singleobject[i].getName()!=null)&&(singleobject [i].getName().equals("薯餅"))){
					food = singleobject[i];
					content += "      "+food.getName()+"\t                X"+singlenumber[5]+"       價格: "+food.getPrice()*singlenumber[5]+"TX\n";
					break;
				}
			}
		}
		if(singlenumber[6]!=0){
			AllFood.Food food;
			for(int i = 0; i < singlelist.size(); i++){
				if((singleobject[i].getName()!=null)&&(singleobject [i].getName().equals("滿福堡"))){
					food = singleobject[i];
					content += "      "+food.getName()+"\t          X"+singlenumber[6]+"       價格: "+food.getPrice()*singlenumber[6]+"TX\n";
					break;
				}
			}
		}
		if(singlenumber[7]!=0){
			AllFood.Food food;
			for(int i = 0; i < singlelist.size(); i++){
				if((singleobject[i].getName()!=null)&&(singleobject [i].getName().equals("薯條(大)"))){
					food = singleobject[i];
					content += "      "+food.getName()+"\t         X"+singlenumber[7]+"       價格: "+food.getPrice()*singlenumber[7]+"TX\n";
					break;
				}
			}
		}
		if(singlenumber[8]!=0){
			AllFood.Food food;
			for(int i = 0; i < singlelist.size(); i++){
				if((singleobject[i].getName()!=null)&&(singleobject [i].getName().equals("大麥克"))){
					food = singleobject[i];
					content += "      "+food.getName()+"\t          X"+singlenumber[8]+"       價格: "+food.getPrice()*singlenumber[8]+"TX\n";
					break;
				}
			}
		}
		if(singlenumber[9]!=0){
			AllFood.Food food;
			for(int i = 0; i < singlelist.size(); i++){
				if((singleobject[i].getName()!=null)&&(singleobject [i].getName().equals("玉米濃湯"))){
					food = singleobject[i];
					content += "      "+food.getName()+"\t       X"+singlenumber[9]+"       價格: "+food.getPrice()*singlenumber[9]+"TX\n";
					break;
				}
			}
		}
		return content;
	}
	public String set_detail(){
		String content="";
		if(setnumber[0]!=0){         //如果套餐一的數量不為0跑裡面的body
			AllSet.Set set1;
			for(int i = 0; i < setlist.size(); i++){ 
				//找到為普通套餐的物件，用套餐物件而不是用套餐名稱的字串，這樣物件才可以方便的讀取裡面的附餐叫甚麼名子 ，價格為多少           
				if((setobject[i].getName()!=null)&&(setobject[i].getName().equals("普通套餐"))){//用套餐名稱"普通套餐"找到套餐物件位在哪個陣列元素裡
					set1 = setobject[i];
					//寫這三個變數只是方便程式碼修改而已
					AllFood.Food meal1 = set1.getMeal1();     //附餐一的物件
					AllFood.Food meal2 = set1.getMeal2();     //附餐二的物件
					AllFood.Food meal3 = set1.getMeal3();     //附餐三的物件
					content += "      餐- ";
					//為什麼附餐一不用確認是否為null，因為csv檔裡面每個套餐都有附餐一
					content += meal1.getName()+"   X"+setnumber[0]+"       價格: "+set1.getPrice()*setnumber[0]+"TX\n"; //套餐名稱，套餐價格，套餐數量
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
			for(int i = 0; i < setlist.size(); i++){
				if((setobject[i].getName()!=null)&&(setobject[i].getName().equals("薯餅套餐"))){
					set2 = setobject[i];
					AllFood.Food meal1 = set2.getMeal1();
					AllFood.Food meal2 = set2.getMeal2();
					AllFood.Food meal3 = set2.getMeal3();
					content += "      餐- ";
					content += meal1.getName()+"   X"+setnumber[1]+"       價格: "+set2.getPrice()*setnumber[1]+"TX\n";
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
			for(int i = 0; i < setlist.size(); i++){
				if((setobject[i].getName()!=null)&&(setobject [i].getName().equals("吃很飽套餐"))){
					set3 = setobject[i];
					AllFood.Food meal1 = set3.getMeal1();
					AllFood.Food meal2 = set3.getMeal2();
					AllFood.Food meal3 = set3.getMeal3();
					content += "      餐- ";
					content += meal1.getName()+"   X"+setnumber[2]+"       價格: "+set3.getPrice()*setnumber[2]+"TX\n";
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
	public String gift_detail(){// edited by 江鴻麟 time:0617 點數兌換的商品
		String content="";                  //原本content裡面沒有資料
		if(giftfoodnumber[0]!=0){           //如果單點一的數量不為0跑裡面的body			
			AllFood.Food food;
			//找到為單點一的物件，用單點物件而不是用單點名稱的字串，這樣物件才可以讀取裡面的單點叫甚麼名子，價格為多少  
			for(int i = 0; i < giftlist .size(); i++){
				//用單點名稱"薯條(小)"找到單點物件位在哪個陣列元素裡，因為這樣才方便call物件的名稱，價格
				if((giftobject[i].getName()!=null)&&(giftobject [i].getName().equals("薯條(小)"))){
					food = giftobject[i];                                                        //寫一個food的變數只是方便程式碼修改而已
					content += "      "+food.getName()+"\t         X"+giftfoodnumber[0] +"       (點數兌換)\n"; //單點名稱，數量
					break;
				}
			}
		} 
 		if(giftfoodnumber[1]!=0){
			AllFood.Food food;
			for(int i = 0; i < giftlist .size(); i++){
				if((giftobject[i].getName()!=null)&&(giftobject [i].getName().equals("麥香雞"))){
					food = giftobject[i];
					content += "      "+food.getName()+"\t           X"+giftfoodnumber[1] + "       (點數兌換)\n";
					break;
				}
			}
		}
 		if(giftfoodnumber[2]!=0){
			AllFood.Food food;
			for(int i = 0; i < giftlist .size(); i++){
				if((giftobject[i].getName()!=null)&&(giftobject [i].getName().equals("可樂(小)"))){
					food = giftobject[i];
					content += "      "+food.getName()+"\t        X"+giftfoodnumber[2] + "       (點數兌換)\n";
					break;
				}
			}
		} 
 		if(giftfoodnumber[3]!=0){
			AllFood.Food food;
			for(int i = 0; i < giftlist .size(); i++){
				if((giftobject[i].getName()!=null)&&(giftobject [i].getName().equals("雪碧(小)"))){
					food = giftobject[i];
					content += "      "+food.getName()+"\t        X"+giftfoodnumber[3] + "       (點數兌換)\n";
					break;
				}
			}
		} 
		if(giftfoodnumber[4]!=0){
			AllFood.Food food;
			for(int i = 0; i < giftlist .size(); i++){
				if((giftobject[i].getName()!=null)&&(giftobject [i].getName().equals("薯條(中)"))){
					food = giftobject[i];
					content += "      "+food.getName()+"\t        X"+giftfoodnumber[4] + "       (點數兌換)\n";
					break;
				}
			}
		}
		if(giftfoodnumber[5]!=0){
			AllFood.Food food;
			for(int i = 0; i < giftlist .size(); i++){
				if((giftobject[i].getName()!=null)&&(giftobject [i].getName().equals("薯餅"))){
					food = giftobject[i];
					content += "      "+food.getName()+"\t                X"+giftfoodnumber[5] + "   (點數兌換)\n";
					break;
				}
			}
		}
		if(giftfoodnumber[6]!=0){
			AllFood.Food food;
			for(int i = 0; i < giftlist .size(); i++){
				if((giftobject[i].getName()!=null)&&(giftobject [i].getName().equals("滿福堡"))){
					food = giftobject[i];
					content += "      "+food.getName()+"\t          X"+giftfoodnumber[6] + "       (點數兌換)\n";
					break;
				}
			}
		}
		if(giftfoodnumber[7]!=0){
			AllFood.Food food;
			for(int i = 0; i < giftlist .size(); i++){
				if((giftobject[i].getName()!=null)&&(giftobject [i].getName().equals("薯條(大)"))){
					food = giftobject[i];
					content += "      "+food.getName()+"\t         X"+giftfoodnumber[7] + "       (點數兌換)\n";
					break;
				}
			}
		}
		if(giftfoodnumber[8]!=0){
			AllFood.Food food;
			for(int i = 0; i < giftlist .size(); i++){
				if((giftobject[i].getName()!=null)&&(giftobject [i].getName().equals("大麥克"))){
					food = giftobject[i];
					content += "      "+food.getName()+"\t          X"+giftfoodnumber[8] + "       (點數兌換)\n";
					break;
				}
			}
		}
		if(giftfoodnumber[9]!=0){
			AllFood.Food food;
			for(int i = 0; i < giftlist .size(); i++){
				if((giftobject[i].getName()!=null)&&(giftobject [i].getName().equals("玉米濃湯"))){
					food = giftobject[i];
					content += "      "+food.getName()+"\t       X"+giftfoodnumber[9] + "     (點數兌換)\n";
					break;
				}
			}
		} 
		return content;
	}
	//edited by 葉惟欣 time:0613##############################################################################################################################################
	//edited by 江鴻麟 time:0616 將方法拆開以及優惠碼的判斷

	//創造發票跟收據的物件的方法
	public void create_receipt(boolean a){
		//建構參數物件
		AllFile.Barcode barcode = new AllFile.Barcode();
		//傳入的建構子的參數是(單點的物件陣列，套餐的物件陣列，現在購物清單上的單點數量，現在購物清單上的套餐數量，現在購物清單上的套餐數量)
		AllFile.Receipt receipt = new AllFile.Receipt(singleobject,setobject,singlelist.size(),setlist.size(),barcode.get_barcode());
		receipt.create_receiptfile(a);
		//讓發票上的共幾項歸零
		int singlenumber[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		int giftfoodnumber[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		int setnumber[] = {0, 0, 0};
	}
	//創造明細的方法
	public void create_transcation(boolean a){
		//建構明細物件
		AllFile.Barcode barcode = new AllFile.Barcode();
		//傳入的建構子的參數是(單點的物件陣列，套餐的物件陣列，現在購物清單上的單點數量，現在購物清單上的套餐數量，每一份單點有幾個，每一份套餐有幾個，現在購物清單上的套餐數量)
		AllFile.Transcation transcation = new AllFile.Transcation(singleobject,setobject,giftobject,singlelist.size(),setlist.size(),giftlist.size(),singlenumber,setnumber,giftfoodnumber,barcode.get_barcode());
		transcation.create_transcationfile(a);
	}
}