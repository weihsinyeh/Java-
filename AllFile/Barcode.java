// developed by �����Y time:0613
// �гy�@�����X
package AllFile;
import java.util.Random;
public class Barcode{
	//�ܼƫŧi��
	private String barcode="";
	//�غc�l
	public Barcode(){
		set_barcode();
	}
	//�A�Ȥ�k
	public void set_barcode(){
		Random r = new Random();
		//Barcode�̭��N�u���o5�Ӯ榡
		String code [] = {"�p","�l","�k","�m","�j"};
		int rand [] = new int[12];
		//��rand [] �̭��s���H����5�Ӯ榡
		for(int i=0;i<12;i++){
			rand[i] = r.nextInt(5);
		}
		//�ͥXbarcode ���r��
		for(int i=0;i<12;i++){
			barcode += code[rand[i]];
		}
	} 
	public String get_barcode(){return barcode;}
}