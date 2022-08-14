//developed by �����Y time:0613
//�гy�o��
package AllFile;
//�ޤJ�M��
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
	/***************�ܼƫŧi*********************************************************/
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
	/***************�غc�l�A�ǤJ���X���N�X******************************************************/
   	public Receipt(AllFood.Food signleobject[],AllSet.Set setobject[],int singlerow,int setrow,String barcode){
		set_singleobject(signleobject);
		set_setobject(setobject);
		set_singlerow(singlerow);
		set_setrow(setrow);
		set_time();
		set_barcode(barcode);
		
	}
	/***************�A�Ȥ�k******************************************************/
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
	/***************�\���k******************************************************/
	public void set_time(){
		Date date = new Date();
      		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");     //�{�b��T�ɶ����榡��  
      		default_date = bartDateFormat.format(date);                                     //���ͤ��Ѥ���Ǩ�榡�Ƹ̭��A�N�|����ɶ��榡��
  		//�o�ӥD�n�O���F�nŪ���{�b�� �~ �� �� �� �� ��
		Calendar calendar = Calendar.getInstance();                                    
		hour = calendar.get(Calendar.HOUR_OF_DAY); //�p��
		minute = calendar.get(Calendar.MINUTE);    //����
		month = calendar.get(Calendar.MONTH);      //���
		nextmonth = month+1;                       //�o���I�����϶�
		year = calendar.get(Calendar.YEAR)-1911;   //�~ ���� �ҥH��-1911
		day = calendar.get(Calendar.DAY_OF_MONTH); //��
	}	
  	public void create_receiptfile(boolean a){ 
      		/***********��X�ɮ�******************************************************************/
      		String OutFilename = "receipt.txt";//���ͭn��X���ɮצW��
        	try{	
			String content = create_content(a);
			File file = new File(OutFilename);    //���ͤ@��outputfile ���ǤJ�n�����W�l 
			file.createNewFile();                 //���ͷs�ɮ�
			FileWriter fw = new FileWriter(file); //new�@�Ӽg�ɮת�����A�g��file�̭�
          		bw = new BufferedWriter(fw);          //new�@�ӽw�ľ�������A��content�����e�i�H�g�Dfile��
          		bw.write(content);                    //�u���g���e
          		JOptionPane.showMessageDialog(null,"�w������X�o��\n���z�ɥ��\�I�r��");
          	
        	}
        	catch(IOException ioe){
          		ioe.printStackTrace();
        	}
		/**********�b�����B�zI/O���ɭԡA�q�`�|�N��close���A�H�K�y���q���귽�����O**************/
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
	//�s���0616:���E�� �W�[�u�f�X���PŪ
	public String create_content(boolean a){
		String content = "";
		content +="             JaVafood's\n";
		content +="          �q�l�o���ҩ��p\n";
		content +="            "+year+"�~ "+month+"-"+nextmonth+"��\n";
		content +="      "+create_abc()+"-"+creat_ninenumbers();
		content +="\n      "+default_date+"\n";
		if (a == true){content +="      �H���X:"+creat_randomnumber()+"   �`�p:"+print_moneytotal_checkpoint()+"\n";}
		else {content +="      �H���X:"+creat_randomnumber()+"    �`�p:"+print_moneytotal()+"\n";}
		content +="      ���X:16516288\n";
		content +="      "+get_barcode()+"\n\n"; 
		content +="      �\�U0613 ��餤�c 21-00\n";
		content +="      ��211149  ��112042 " +nextmonth+"/"+day+"\n";
		content +="      5�餺���o���Ω��Ӵ��o��";
		return content;
	}
	//�����H��ABC�N�X��ӭ^��r��
	public String create_abc(){
		String content="";
		int[] arr=new int[2];              //�w�q�@�Ӿ㫬�}�C�A�}�C���׬���J��
		boolean[] flag=new boolean[26];    //�w�q�@��Boolean���}�C�A�ΨӰ��h���ƭ�
		for(int i=0;i<arr.length;i++){     //�q�L�j�鬰�}�C���
			Random rand=new Random();
			int index;
			do{
				index=rand.nextInt(26);    //�H���ͦ�0-25���Ʀr�ý�ȵ�index
			}while(flag[index]);    //�P�_flag�ȬO�_��true,�p�G��true�h���s��index���
			arr[i]=index+65;        //�j�g�r����ASCII�Ȭ�65-90�A�ҥH��index���ȥ[�W65�A�Ϩ�ŦX�j�g�r����ASCII�Ȱ϶�
			flag[index]=true;       //��������flag�Ȭ�true
		}
		Arrays.sort(arr);//���ɧǱƦC�}�C
		for(int n=0;n<arr.length;n++){
			content+=((char)(arr[n]));//�M���}�C�åB�N���ରchar��
		}
		return content;
	}
	//����9��ƪ��H���Ʀr(�ݵo���N���D�F)
	public int creat_ninenumbers(){
		Random r = new Random();
		return r.nextInt(899999999)+100000000;
	}
	//�H���X(�ݵo���N���D�F)
	public int creat_randomnumber(){
		Random r = new Random();
		return r.nextInt(999)+1000;
	}
	//�L�X�h�ֿ�
	public int print_moneytotal(){
		//�`���B�@�}�l��0
		int moneytotal=0;
		for (int i = 0; i < singlerow; i++){               //Ū�����I�C�Ӫ��󪺻���
			moneytotal += singleobject[i].getPrice();  
		}
		for (int i = 0; i < setrow; i++){                  //Ū���M�\�C�Ӫ��󪺻���
			moneytotal += setobject[i].getPrice();
		}
		return moneytotal;
	}
	//�s���0616:���E�� �W�[�u�f�X���PŪ
	public double print_moneytotal_checkpoint(){
		//�`���B�@�}�l��0
		int moneytotal=0;
		for (int i = 0; i < singlerow; i++){               //Ū�����I�C�Ӫ��󪺻���
			moneytotal += singleobject[i].getPrice();  
		}
		for (int i = 0; i < setrow; i++){                  //Ū���M�\�C�Ӫ��󪺻���
			moneytotal += setobject[i].getPrice();
		}
		return Math.ceil(moneytotal*0.9);
	}
}



