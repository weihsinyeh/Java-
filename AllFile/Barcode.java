// developed by 葉惟欣 time:0613
// 創造一維條碼
package AllFile;
import java.util.Random;
public class Barcode{
	//變數宣告區
	private String barcode="";
	//建構子
	public Barcode(){
		set_barcode();
	}
	//服務方法
	public void set_barcode(){
		Random r = new Random();
		//Barcode裡面就只有這5個格式
		String code [] = {"▉","▍","▎","▌","▏"};
		int rand [] = new int[12];
		//讓rand [] 裡面存放隨機的5個格式
		for(int i=0;i<12;i++){
			rand[i] = r.nextInt(5);
		}
		//生出barcode 的字串
		for(int i=0;i<12;i++){
			barcode += code[rand[i]];
		}
	} 
	public String get_barcode(){return barcode;}
}