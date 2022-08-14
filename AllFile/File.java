//developed by 葉惟欣 time:0608
//檔案物件 加分工能以完成: 讀取餐點資料:直接讀取 CSV 並建立 Entity Class 來創造 Object
package AllFile;
import java.io.*;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.Random;
public class File{
	/**************變數宣告區*****************************************/
	protected int row;
	protected int col;
	protected String item[][];
	protected String filename;
	/**************建構子*********************************************/
	public File(String filename,int row,int col){
		set_file_name(filename);
		set_file_row(row);
		set_file_col(col);
		set_item(row,col);
	}
	/**************服務方法區*****************************************/
	public void set_file_name(String filename){this.filename = filename;}
	public String get_file_name(){return filename;}
	public void set_file_row(int row){this.row = row;}
	public int get_file_row(){return row;}
	public void set_file_col(int col){this.col = col;}
	public int get_file_col(){return col;}
	public void set_item(int row,int col){this.item = new String [row][col];}
	/**************功能方法區*****************************************/
		
	//讀檔案
   	public String [][] create_file(){ 		//回傳讀到檔案的字串陣列
		int counter = -1;                       //紀錄現在記到哪個item了
        	String line = "";
        	final String delimiter = ",";
        	try{
           	 	FileReader fileReader = new FileReader(get_file_name());                  //傳入讀入file的名字
            		BufferedReader reader = new BufferedReader(fileReader);                   //緩衝器存放讀入file的資料
           		while ((line = reader.readLine()) != null){        //迴圈一直跑到下一行沒資料
               			String[] token = line.split(delimiter);    //csv檔用逗號隔開
	       			if(counter!=-1){
					for(int i=0 ;i<get_file_col();i++){
						item[counter][i]=token[i];       //將讀入的內容各自存到字串陣列元素            
					}
	       			}
				counter++;				 	//紀錄現在讀到第幾行的變數+1到下一行
            		}
        	}
        	catch (IOException e){
            		e.printStackTrace();                                    //堆疊
        	}
		return item;
    	}
	//如果要寫出menu的內容，可以使用這裡的(步驟一的功能已完成)

	//***********************************************************************************列印Dos上與JOP上的內容*****************************************
	//步驟一:需要列出所有的餐點以及套餐(可直接複製 CSV 檔的內容至程式碼內變成一個陣列)
	public String create_menu(){
		//為了印出表情圖案!! 表情圖案不能再Dos上面印，只能在JOP上面印出(forJOP的單點菜單列印)
		String menu =  new String(Character.toChars(0x1F601));
		menu +=  new String(Character.toChars(0x1F602));
		menu +=  new String(Character.toChars(0x1F603));
		menu +=  new String(Character.toChars(0x1F604));
		menu +=  new String(Character.toChars(0x1F605));
		menu +=  new String(Character.toChars(0x1F606));
		menu +=  new String(Character.toChars(0x1F609));
		menu +=  new String(Character.toChars(0x1F60A));
		menu +=  new String(Character.toChars(0x1F60B));
		menu +=  new String(Character.toChars(0x1F60C));
		menu +=  new String(Character.toChars(0x1F60D));
		menu +=  new String(Character.toChars(0x1F354));
		menu +=  new String(Character.toChars(0x1F354));
		menu +=  new String(Character.toChars(0x1F354));
		menu +=  "   JaVafood's   ";
		menu +=  new String(Character.toChars(0x1F354));
		menu +=  new String(Character.toChars(0x1F354));
		menu +=  new String(Character.toChars(0x1F354));
		menu +=  new String(Character.toChars(0x1F60A));
		menu +=  new String(Character.toChars(0x1F60B));
		menu +=  new String(Character.toChars(0x1F606));
		menu +=  new String(Character.toChars(0x1F609));
		menu +=  new String(Character.toChars(0x1F60A));
		menu +=  new String(Character.toChars(0x1F60B));
		menu +=  new String(Character.toChars(0x1F60C));
		menu +=  new String(Character.toChars(0x1F60D));
		menu +=  new String(Character.toChars(0x1F60E));
		menu +=  new String(Character.toChars(0x1F60F));
		menu +=  new String(Character.toChars(0x1F612));
		menu +=  new String(Character.toChars(0x1F605));
		menu +=  new String(Character.toChars(0x1F606));
		menu += "\n●○【單點】●○●○●○●○●○●○●○●○●○●○●○●○●○●○●○\n";
		menu +=  new String(Character.toChars(0x1F356));
		menu += "   ";
		menu += "ID  品項    價格    類別     早餐\n";
		//單點的內容印法從readfile()那裏得到的二為陣列為字串而已，就夠單點物件在Customer
		for(int i=0;i<get_file_row();i++){
			if(i%3==0){menu +=  new String(Character.toChars(0x1F354));}
			else if(i%3==1){menu +=  new String(Character.toChars(0x1F355));}
			else{menu +=  new String(Character.toChars(0x1F357));}
			menu += "   ";
			for(int j=0;j<get_file_col();j++){
				menu +=item[i][j]+"   ";
			}
			menu +="\n";
		}
		return menu; 
	}
	//edited by 葉惟欣 time:0618 在Dos上列印單點的資料，這個方法只能在讀用讀單點csv檔來呼叫，其他的話格式會不同
	public String create_singleDos(){
		String menu = "\n●○●○●○●○●○●○●○【JaVaFood's】●○●○●○●○●○●○●○●\n";
		       menu += "\n●○【單點】●○●○●○●○●○●○●○●○●○●○●○●○●○●○●○\n";
		menu += "ID\t品項\t\t價格\t類別\t早餐\n";
		for(int i=0;i<get_file_row();i++){
			for(int j=0;j<get_file_col();j++){
				//為了排版!!!而已
				if((item[i][j] !=null)&&(item [i][j].equals("麥香雞"))){menu +=item[i][j]+"\t\t";}
				else if((item[i][j] !=null)&&(item [i][j].equals("薯餅"))){menu +=item[i][j]+"\t\t";}
				else if((item[i][j] !=null)&&(item [i][j].equals("滿福堡"))){menu +=item[i][j]+"\t\t";}
				else if((item[i][j] !=null)&&(item [i][j].equals("大麥克"))){menu +=item[i][j]+"\t\t";}
				//如果是其他的單點字串內容就都可以
				else{menu +=item[i][j]+"\t";}
			}
			menu +="\n";
		}
		return menu; 
	}
	//edited by 葉惟欣 time:0618 在Dos上列印單點的資料

	//   在JOP上面列印套餐的資料
	public String create_menu_detail(){
		String menu = "\n●○【套餐】●○●○●○●○●○●○●○●○●○●○●○●○●○●○●○\n";
		menu +=  new String(Character.toChars(0x1F356));                                             //emoji表情符號
		menu += "   ";
		menu += "ID       品項        副餐1       副餐2    飲料  價格\n";
		for(int i=0;i<get_file_row();i++){
			menu += "   ";
			for(int j=0;j<get_file_col();j++){
				if((item[i][j] !=null)&&(item [i][j].equals("1"))){item [i][j] = "薯條(小)";}
				if((item[i][j] !=null)&&(item [i][j].equals("2"))){item [i][j] = "麥香雞";}
				if((item[i][j] !=null)&&(item [i][j].equals("3"))){item [i][j] = "可樂(小)";}
				if((item[i][j] !=null)&&(item [i][j].equals("4"))){item [i][j] = "雪碧(小)";}
				if((item[i][j] !=null)&&(item [i][j].equals("5"))){item [i][j] = "薯條(中)";}
				if((item[i][j] !=null)&&(item [i][j].equals("6"))){item [i][j] = "薯餅";}
				if((item[i][j] !=null)&&(item [i][j].equals("7"))){item [i][j] = "滿福堡";}
				if((item[i][j] !=null)&&(item [i][j].equals("8"))){item [i][j] = "薯條(大)";}
				if((item[i][j] !=null)&&(item [i][j].equals("9"))){item [i][j] = "大麥克";}
				if((item[i][j] !=null)&&(item [i][j].equals("10"))){item [i][j] = "玉米濃湯";}
				if((item[i][j] ==null)&&(item [i][j].equals(""))){item [i][j] = "無";}
				menu +=item[i][j]+"    ";
			}
			menu +="\n";
		}
		//emoji表情符號而已
		menu +=  new String(Character.toChars(0x1F602));
		menu +=  new String(Character.toChars(0x1F603));
		menu +=  new String(Character.toChars(0x1F604));
		menu +=  new String(Character.toChars(0x1F605));
		menu +=  new String(Character.toChars(0x1F606));
		menu +=  new String(Character.toChars(0x1F609));
		menu +=  new String(Character.toChars(0x1F60A));
		menu +=  new String(Character.toChars(0x1F60B));
		menu +=  new String(Character.toChars(0x1F60C));
		menu +=  new String(Character.toChars(0x1F60D));
		/*****************
		menu +=  new String(Character.toChars(0x1F60E));
		menu +=  new String(Character.toChars(0x1F60F));
		menu +=  new String(Character.toChars(0x1F612));
		menu +=  new String(Character.toChars(0x1F605));
		menu +=  new String(Character.toChars(0x1F606));
		menu +=  new String(Character.toChars(0x1F609));
		***************/
		menu +=  new String(Character.toChars(0x1F354));
		menu +=  new String(Character.toChars(0x1F354));
		menu +=  new String(Character.toChars(0x1F354));
		menu +=  "   JaVafood's   ";
		menu +=  new String(Character.toChars(0x1F354));
		menu +=  new String(Character.toChars(0x1F354));
		menu +=  new String(Character.toChars(0x1F354));
		menu +=  new String(Character.toChars(0x1F60A));
		menu +=  new String(Character.toChars(0x1F60B));
		menu +=  new String(Character.toChars(0x1F606));
		menu +=  new String(Character.toChars(0x1F609));
		menu +=  new String(Character.toChars(0x1F60A));
		menu +=  new String(Character.toChars(0x1F60B));
		menu +=  new String(Character.toChars(0x1F60C));
		menu +=  new String(Character.toChars(0x1F60D));
		menu +=  new String(Character.toChars(0x1F60E));
		menu +=  new String(Character.toChars(0x1F60F));
		menu +=  new String(Character.toChars(0x1F612));
		menu +=  new String(Character.toChars(0x1F605));
		menu +=  new String(Character.toChars(0x1F606));
		return menu;
	}
	//edited by 葉惟欣 time:0618 在Dos上列印套餐的資料
	public String create_setDos(){
		String menu = "\n●○【套餐】●○●○●○●○●○●○●○●○●○●○●○●○●○●○●○\n";
		menu += "ID\t品項\t\t副餐1\t\t副餐2\t飲料\t\t價格\n";
		for(int i=0;i<get_file_row();i++){
			for(int j=0;j<get_file_col();j++){
				//因為老師給的csv檔裡面附餐是以編號來辨別
				if((item[i][j] !=null)&&(item [i][j].equals("1"))){menu += "薯條(小)";}
				if((item[i][j] !=null)&&(item [i][j].equals("2"))){menu += "麥香雞";}
				if((item[i][j] !=null)&&(item [i][j].equals("3"))){menu += "可樂(小)";}
				if((item[i][j] !=null)&&(item [i][j].equals("4"))){menu += "雪碧(小)";}
				if((item[i][j] !=null)&&(item [i][j].equals("5"))){menu += "薯條(中)";}
				if((item[i][j] !=null)&&(item [i][j].equals("6"))){menu += "薯餅";}
				if((item[i][j] !=null)&&(item [i][j].equals("7"))){menu += "滿福堡";}
				if((item[i][j] !=null)&&(item [i][j].equals("8"))){menu += "薯條(大)";}
				if((item[i][j] !=null)&&(item [i][j].equals("9"))){menu +="大麥克";}
				if((item[i][j] !=null)&&(item [i][j].equals("10"))){menu += "玉米濃湯";}
				if((item[i][j] ==null)&&(item [i][j].equals(""))){menu += "無";}
				else{menu +=item[i][j]+"\t";}
			}
			menu +="\n";
		}
		return menu;
	}
	//edited by 葉惟欣 time:0618 在Dos上列印套餐的資料
}