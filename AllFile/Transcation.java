//developed by �����Y time : 0613
//�гy����
package AllFile;
//�ޤJ�M��
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
	//�ܼƫŧi
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
	// �غc�l************************************************************************�غc�l�ѼƤ]�|�ǤJbarcode
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
	//�A�Ȥ�k
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
	//�\���k
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
  	public void create_transcationfile(boolean a){ 
      		/***********��X�ɮ�******************************************************************/
      		String OutFilename = "transcation.txt";//���ͭn��X���ɮצW��
        	try{	
			String content = create_content(a);
			File file = new File(OutFilename);    //���ͤ@��outputfile ���ǤJ�n�����W�l 
			file.createNewFile();                 //���ͷs�ɮ�
			FileWriter fw = new FileWriter(file); //new�@�Ӽg�ɮת�����A�g��file�̭�
          		bw = new BufferedWriter(fw);          //new�@�ӽw�ľ�������A��content�����e�i�H�g�Dfile��
          		bw.write(content);                    //�u���g���e
          		JOptionPane.showMessageDialog(null,"�w������X�������\n���z�ɥ��\�I�r��");
         
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
		content +="            �� �� �� ��\n";
		content +="      �\�U0613 ���c���� 21-00\n";
		content +="      �\�U�q��:03-422-7151\n";
		content +="      ��211149  ��112042\n";
		content +="      "+default_date+"\n"; 
		content +=set_detail();                      //���I�����e
		content +=single_detail();                   //�M�\�����e
		content +=gift_detail();
		int totalnumber =singlerow+setrow;           //���I�[�M�\���ƶq
		content +="      �@ "+totalnumber+" �� \t�~ �a:\t"+print_moneytotal()+"\n";
		content +="\t\t�` �p:\t"+print_moneytotal()+"\n";
		if (a == true){
			content +="\t\t�� ��:\t"+" "+(print_moneytotal() - print_moneytotal_checkpoint())+"\n";
			content +="      �o�����B\t\t"+print_moneytotal_checkpoint()+"\n";
			content +="      �{��    \t\t"+print_moneytotal_checkpoint()+"\n";
		}
		else {		
			content +="      �o�����B\t\t"+print_moneytotal()+"\n";
			content +="      �{��    \t\t"+print_moneytotal()+"\n";
		}		
		content +="      "+get_barcode()+"\n";
		content +="      5�餺���o���Ω��Ӵ��o��\n";
		
		return content;
	}
	//�L�X���ӮM�\�����e:�����W�١A�ƶq�A����
	public String set_detail(){
		String content="";
		if(setnumber[0]!=0){         //�p�G�M�\�@���ƶq����0�]�̭���body
			AllSet.Set set1;
			for(int i = 0; i < setrow; i++){ 
				//��쬰���q�M�\������A�ήM�\����Ӥ��O�ήM�\�W�٪��r��A�o�˪���~�i�H��K��Ū���̭������\�s�ƻ�W�l �A���欰�h��           
				if((setobject[i].getName()!=null)&&(setobject[i].getName().equals("���q�M�\"))){//�ήM�\�W��"���q�M�\"���M�\�����b���Ӱ}�C������
					set1 = setobject[i];
					//�g�o�T���ܼƥu�O��K�{���X�ק�Ӥw
					AllFood.Food meal1 = set1.getMeal1();     //���\�@������
					AllFood.Food meal2 = set1.getMeal2();     //���\�G������
					AllFood.Food meal3 = set1.getMeal3();     //���\�T������
					content += "      �\- ";
					//��������\�@���νT�{�O�_��null�A�]��csv�ɸ̭��C�ӮM�\�������\�@
					content += meal1.getName()+"   X"+setnumber[0]+"\t"+set1.getPrice()*setnumber[0]+"TX\n"; //�M�\�W�١A�M�\����A�M�\�ƶq
					if(meal2!=null){               //�]�����ǮM�\�S�����\�G�A�T�{�����\�G�~Ū���\�G���W�l�A���欰�h��  
						content += "      "+meal2.getName()+"\n";                                   
					}
					if(meal3!=null){               //�]�����ǮM�\�S�����\�T�A�T�{�����\�G�~Ū���\�T���W�l
						content += "      "+meal3.getName()+"\n";
					}
					break;
				}
			}
		}
		if(setnumber[1]!=0){
			AllSet.Set set2;
			for(int i = 0; i < setrow; i++){
				if((setobject[i].getName()!=null)&&(setobject[i].getName().equals("����M�\"))){
					set2 = setobject[i];
					AllFood.Food meal1 = set2.getMeal1();
					AllFood.Food meal2 = set2.getMeal2();
					AllFood.Food meal3 = set2.getMeal3();
					content += "      �\- ";
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
				if((setobject[i].getName()!=null)&&(setobject [i].getName().equals("�Y�ܹ��M�\"))){
					set3 = setobject[i];
					AllFood.Food meal1 = set3.getMeal1();
					AllFood.Food meal2 = set3.getMeal2();
					AllFood.Food meal3 = set3.getMeal3();
					content += "      �\- ";
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
	//�L���ӤW�����I���e: �����W�١A�ƶq�A����
	public String single_detail(){
		String content="";                //�쥻content�̭��S�����
		if(singlenumber[0]!=0){           //�p�G���I�@���ƶq����0�]�̭���body
			AllFood.Food food;
			//��쬰���I�@������A�γ��I����Ӥ��O�γ��I�W�٪��r��A�o�˪���~�i�HŪ���̭������I�s�ƻ�W�l�A���欰�h��  
			for(int i = 0; i < singlerow; i++){
				//�γ��I�W��"����(�p)"�����I�����b���Ӱ}�C�����̡A�]���o�ˤ~��Kcall���󪺦W�١A����
				if((singleobject[i].getName()!=null)&&(singleobject [i].getName().equals("����(�p)"))){
					food = singleobject[i];                                                        //�g�@��food���ܼƥu�O��K�{���X�ק�Ӥw
					content += "      "+food.getName()+"\t     X"+singlenumber[0]+"\t"+food.getPrice()*singlenumber[0]+"TX\n"; //���I�W�١A�ƶq�A����
				}
			}
		}
		if(singlenumber[1]!=0){
			AllFood.Food food;
			for(int i = 0; i < singlerow; i++){
				if((singleobject[i].getName()!=null)&&(singleobject [i].getName().equals("������"))){
					food = singleobject[i];
					content += "      "+food.getName()+"\t     X"+singlenumber[1]+"\t"+food.getPrice()*singlenumber[1]+"TX\n";
				}
			}
		}
		if(singlenumber[2]!=0){
			AllFood.Food food;
			for(int i = 0; i < singlerow; i++){
				if((singleobject[i].getName()!=null)&&(singleobject [i].getName().equals("�i��(�p)"))){
					food = singleobject[i];
					content += "      "+food.getName()+"\t     X"+singlenumber[2]+"\t"+food.getPrice()*singlenumber[2]+"TX\n";
				}
			}
		}
		if(singlenumber[3]!=0){
			AllFood.Food food;
			for(int i = 0; i < singlerow; i++){
				if((singleobject[i].getName()!=null)&&(singleobject [i].getName().equals("����(�p)"))){
					food = singleobject[i];
					content += "      "+food.getName()+"\t     X"+singlenumber[3]+"\t"+food.getPrice()*singlenumber[3]+"TX\n";
				}
			}
		}
		if(singlenumber[4]!=0){
			AllFood.Food food;
			for(int i = 0; i < singlerow; i++){
				if((singleobject[i].getName()!=null)&&(singleobject [i].getName().equals("����(��)"))){
					food = singleobject[i];
					content += "      "+food.getName()+"\t     X"+singlenumber[4]+"\t"+food.getPrice()*singlenumber[4]+"TX\n";
				}
			}
		}
		if(singlenumber[5]!=0){
			AllFood.Food food;
			for(int i = 0; i < singlerow; i++){
				if((singleobject[i].getName()!=null)&&(singleobject [i].getName().equals("����"))){
					food = singleobject[i];
					content += "      "+food.getName()+"\t     X"+singlenumber[5]+"\t"+food.getPrice()*singlenumber[5]+"TX\n";
				}
			}
		}
		if(singlenumber[6]!=0){
			AllFood.Food food;
			for(int i = 0; i < singlerow; i++){
				if((singleobject[i].getName()!=null)&&(singleobject [i].getName().equals("���ֳ�"))){
					food = singleobject[i];
					content += "      "+food.getName()+"\t     X"+singlenumber[6]+"\t"+food.getPrice()*singlenumber[6]+"TX\n";
				}
			}
		}
		if(singlenumber[7]!=0){
			AllFood.Food food;
			for(int i = 0; i < singlerow; i++){
				if((singleobject[i].getName()!=null)&&(singleobject [i].getName().equals("����(�j)"))){
					food = singleobject[i];
					content += "      "+food.getName()+"\t     X"+singlenumber[7]+"\t"+food.getPrice()*singlenumber[7]+"TX\n";
				}
			}
		}
		if(singlenumber[8]!=0){
			AllFood.Food food;
			for(int i = 0; i < singlerow; i++){
				if((singleobject[i].getName()!=null)&&(singleobject [i].getName().equals("�j���J"))){
					food = singleobject[i];
					content += "      "+food.getName()+"\t     X"+singlenumber[8]+"\t"+food.getPrice()*singlenumber[8]+"TX\n";
				}
			}
		}
		if(singlenumber[9]!=0){
			AllFood.Food food;
			for(int i = 0; i < singlerow; i++){
				if((singleobject[i].getName()!=null)&&(singleobject [i].getName().equals("�ɦ̿@��"))){
					food = singleobject[i];
					content += "      "+food.getName()+"\t     X"+singlenumber[9]+"\t"+food.getPrice()*singlenumber[9]+"TX\n";
				}
			}
		}
		return content;
	}
	//�L�ث~������: �����W�١A�ƶq�A����
	public String gift_detail(){// edited by ���E�� time:0618 �I�ƧI�����ӫ~
		String content="";                //�쥻content�̭��S�����
		if(giftnumber[0]!=0){           //�p�G���I�@���ƶq����0�]�̭���body			
			AllFood.Food food;
			//��쬰���I�@������A�γ��I����Ӥ��O�γ��I�W�٪��r��A�o�˪���~�i�HŪ���̭������I�s�ƻ�W�l�A���欰�h��  
			for(int i = 0; i < giftrow; i++){
				//�γ��I�W��"����(�p)"�����I�����b���Ӱ}�C�����̡A�]���o�ˤ~��Kcall���󪺦W�١A����
				if((giftobject[i].getName()!=null)&&(giftobject [i].getName().equals("����(�p)"))){
					food = giftobject[i];                                                        //�g�@��food���ܼƥu�O��K�{���X�ק�Ӥw
					content += "      "+food.getName()+"\t X"+giftnumber[0] +"(�I�ƧI��)\n"; //���I�W�١A�ƶq
					break;
				}
			}
		} 
 		if(giftnumber[1]!=0){
			AllFood.Food food;
			for(int i = 0; i < giftrow; i++){
				if((giftobject[i].getName()!=null)&&(giftobject [i].getName().equals("������"))){
					food = giftobject[i];
					content += "      "+food.getName()+"\t X"+giftnumber[1] + "(�I�ƧI��)\n";
					break;
				}
			}
		}
 		if(giftnumber[2]!=0){
			AllFood.Food food;
			for(int i = 0; i < giftrow; i++){
				if((giftobject[i].getName()!=null)&&(giftobject [i].getName().equals("�i��(�p)"))){
					food = giftobject[i];
					content += "      "+food.getName()+"\t X"+giftnumber[2] + "(�I�ƧI��)\n";
					break;
				}
			}
		} 
 		if(giftnumber[3]!=0){
			AllFood.Food food;
			for(int i = 0; i < giftrow; i++){
				if((giftobject[i].getName()!=null)&&(giftobject [i].getName().equals("����(�p)"))){
					food = giftobject[i];
					content += "      "+food.getName()+"\t X"+giftnumber[3] + "(�I�ƧI��)\n";
					break;
				}
			}
		} 
		if(giftnumber[4]!=0){
			AllFood.Food food;
			for(int i = 0; i < giftrow; i++){
				if((giftobject[i].getName()!=null)&&(giftobject [i].getName().equals("����(��)"))){
					food = giftobject[i];
					content += "      "+food.getName()+"\t X"+giftnumber[4] + "(�I�ƧI��)\n";
					break;
				}
			}
		}
		if(giftnumber[5]!=0){
			AllFood.Food food;
			for(int i = 0; i < giftrow; i++){
				if((giftobject[i].getName()!=null)&&(giftobject [i].getName().equals("����"))){
					food = giftobject[i];
					content += "      "+food.getName()+"\t X"+giftnumber[5] + "(�I�ƧI��)\n";
					break;
				}
			}
		}
		if(giftnumber[6]!=0){
			AllFood.Food food;
			for(int i = 0; i < giftrow; i++){
				if((giftobject[i].getName()!=null)&&(giftobject [i].getName().equals("���ֳ�"))){
					food = giftobject[i];
					content += "      "+food.getName()+"\t X"+giftnumber[6] + "(�I�ƧI��)\n";
					break;
				}
			}
		}
		if(giftnumber[7]!=0){
			AllFood.Food food;
			for(int i = 0; i < giftrow; i++){
				if((giftobject[i].getName()!=null)&&(giftobject [i].getName().equals("����(�j)"))){
					food = giftobject[i];
					content += "      "+food.getName()+"\t X"+giftnumber[7] + "(�I�ƧI��)\n";
					break;
				}
			}
		}
		if(giftnumber[8]!=0){
			AllFood.Food food;
			for(int i = 0; i < giftrow; i++){
				if((giftobject[i].getName()!=null)&&(giftobject [i].getName().equals("�j���J"))){
					food = giftobject[i];
					content += "      "+food.getName()+"\t X"+giftnumber[8] + "(�I�ƧI��)\n";
					break;
				}
			}
		}
		if(giftnumber[9]!=0){
			AllFood.Food food;
			for(int i = 0; i < giftrow; i++){
				if((giftobject[i].getName()!=null)&&(giftobject [i].getName().equals("�ɦ̿@��"))){
					food = giftobject[i];
					content += "      "+food.getName()+"\t X"+giftnumber[9] + "(�I�ƧI��)\n";
					break;
				}
			}
		} 
		return content;
	}
	//�L�X�`�@�h�ֿ�
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
	//�s���:���E�� �W�[�u�f�X���PŪ�A�p�G�����u�f���`���B�]�|���
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



