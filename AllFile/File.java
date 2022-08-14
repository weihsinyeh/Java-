//developed by �����Y time:0608
//�ɮת��� �[���u��H����: Ū���\�I���:����Ū�� CSV �ëإ� Entity Class �ӳгy Object
package AllFile;
import java.io.*;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.Random;
public class File{
	/**************�ܼƫŧi��*****************************************/
	protected int row;
	protected int col;
	protected String item[][];
	protected String filename;
	/**************�غc�l*********************************************/
	public File(String filename,int row,int col){
		set_file_name(filename);
		set_file_row(row);
		set_file_col(col);
		set_item(row,col);
	}
	/**************�A�Ȥ�k��*****************************************/
	public void set_file_name(String filename){this.filename = filename;}
	public String get_file_name(){return filename;}
	public void set_file_row(int row){this.row = row;}
	public int get_file_row(){return row;}
	public void set_file_col(int col){this.col = col;}
	public int get_file_col(){return col;}
	public void set_item(int row,int col){this.item = new String [row][col];}
	/**************�\���k��*****************************************/
		
	//Ū�ɮ�
   	public String [][] create_file(){ 		//�^��Ū���ɮת��r��}�C
		int counter = -1;                       //�����{�b�O�����item�F
        	String line = "";
        	final String delimiter = ",";
        	try{
           	 	FileReader fileReader = new FileReader(get_file_name());                  //�ǤJŪ�Jfile���W�r
            		BufferedReader reader = new BufferedReader(fileReader);                   //�w�ľ��s��Ū�Jfile�����
           		while ((line = reader.readLine()) != null){        //�j��@���]��U�@��S���
               			String[] token = line.split(delimiter);    //csv�ɥγr���j�}
	       			if(counter!=-1){
					for(int i=0 ;i<get_file_col();i++){
						item[counter][i]=token[i];       //�NŪ�J�����e�U�ۦs��r��}�C����            
					}
	       			}
				counter++;				 	//�����{�bŪ��ĴX�檺�ܼ�+1��U�@��
            		}
        	}
        	catch (IOException e){
            		e.printStackTrace();                                    //���|
        	}
		return item;
    	}
	//�p�G�n�g�Xmenu�����e�A�i�H�ϥγo�̪�(�B�J�@���\��w����)

	//***********************************************************************************�C�LDos�W�PJOP�W�����e*****************************************
	//�B�J�@:�ݭn�C�X�Ҧ����\�I�H�ήM�\(�i�����ƻs CSV �ɪ����e�ܵ{���X���ܦ��@�Ӱ}�C)
	public String create_menu(){
		//���F�L�X���Ϯ�!! ���Ϯפ���ADos�W���L�A�u��bJOP�W���L�X(forJOP�����I���C�L)
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
		menu += "\n�����i���I�j������������������������������������������������������������\n";
		menu +=  new String(Character.toChars(0x1F356));
		menu += "   ";
		menu += "ID  �~��    ����    ���O     ���\\n";
		//���I�����e�L�k�qreadfile()���رo�쪺�G���}�C���r��Ӥw�A�N�����I����bCustomer
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
	//edited by �����Y time:0618 �bDos�W�C�L���I����ơA�o�Ӥ�k�u��bŪ��Ū���Icsv�ɨөI�s�A��L���ܮ榡�|���P
	public String create_singleDos(){
		String menu = "\n�����������������������������iJaVaFood's�j������������������������������\n";
		       menu += "\n�����i���I�j������������������������������������������������������������\n";
		menu += "ID\t�~��\t\t����\t���O\t���\\n";
		for(int i=0;i<get_file_row();i++){
			for(int j=0;j<get_file_col();j++){
				//���F�ƪ�!!!�Ӥw
				if((item[i][j] !=null)&&(item [i][j].equals("������"))){menu +=item[i][j]+"\t\t";}
				else if((item[i][j] !=null)&&(item [i][j].equals("����"))){menu +=item[i][j]+"\t\t";}
				else if((item[i][j] !=null)&&(item [i][j].equals("���ֳ�"))){menu +=item[i][j]+"\t\t";}
				else if((item[i][j] !=null)&&(item [i][j].equals("�j���J"))){menu +=item[i][j]+"\t\t";}
				//�p�G�O��L�����I�r�ꤺ�e�N���i�H
				else{menu +=item[i][j]+"\t";}
			}
			menu +="\n";
		}
		return menu; 
	}
	//edited by �����Y time:0618 �bDos�W�C�L���I�����

	//   �bJOP�W���C�L�M�\�����
	public String create_menu_detail(){
		String menu = "\n�����i�M�\�j������������������������������������������������������������\n";
		menu +=  new String(Character.toChars(0x1F356));                                             //emoji���Ÿ�
		menu += "   ";
		menu += "ID       �~��        ���\1       ���\2    ����  ����\n";
		for(int i=0;i<get_file_row();i++){
			menu += "   ";
			for(int j=0;j<get_file_col();j++){
				if((item[i][j] !=null)&&(item [i][j].equals("1"))){item [i][j] = "����(�p)";}
				if((item[i][j] !=null)&&(item [i][j].equals("2"))){item [i][j] = "������";}
				if((item[i][j] !=null)&&(item [i][j].equals("3"))){item [i][j] = "�i��(�p)";}
				if((item[i][j] !=null)&&(item [i][j].equals("4"))){item [i][j] = "����(�p)";}
				if((item[i][j] !=null)&&(item [i][j].equals("5"))){item [i][j] = "����(��)";}
				if((item[i][j] !=null)&&(item [i][j].equals("6"))){item [i][j] = "����";}
				if((item[i][j] !=null)&&(item [i][j].equals("7"))){item [i][j] = "���ֳ�";}
				if((item[i][j] !=null)&&(item [i][j].equals("8"))){item [i][j] = "����(�j)";}
				if((item[i][j] !=null)&&(item [i][j].equals("9"))){item [i][j] = "�j���J";}
				if((item[i][j] !=null)&&(item [i][j].equals("10"))){item [i][j] = "�ɦ̿@��";}
				if((item[i][j] ==null)&&(item [i][j].equals(""))){item [i][j] = "�L";}
				menu +=item[i][j]+"    ";
			}
			menu +="\n";
		}
		//emoji���Ÿ��Ӥw
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
	//edited by �����Y time:0618 �bDos�W�C�L�M�\�����
	public String create_setDos(){
		String menu = "\n�����i�M�\�j������������������������������������������������������������\n";
		menu += "ID\t�~��\t\t���\1\t\t���\2\t����\t\t����\n";
		for(int i=0;i<get_file_row();i++){
			for(int j=0;j<get_file_col();j++){
				//�]���Ѯv����csv�ɸ̭����\�O�H�s���ӿ�O
				if((item[i][j] !=null)&&(item [i][j].equals("1"))){menu += "����(�p)";}
				if((item[i][j] !=null)&&(item [i][j].equals("2"))){menu += "������";}
				if((item[i][j] !=null)&&(item [i][j].equals("3"))){menu += "�i��(�p)";}
				if((item[i][j] !=null)&&(item [i][j].equals("4"))){menu += "����(�p)";}
				if((item[i][j] !=null)&&(item [i][j].equals("5"))){menu += "����(��)";}
				if((item[i][j] !=null)&&(item [i][j].equals("6"))){menu += "����";}
				if((item[i][j] !=null)&&(item [i][j].equals("7"))){menu += "���ֳ�";}
				if((item[i][j] !=null)&&(item [i][j].equals("8"))){menu += "����(�j)";}
				if((item[i][j] !=null)&&(item [i][j].equals("9"))){menu +="�j���J";}
				if((item[i][j] !=null)&&(item [i][j].equals("10"))){menu += "�ɦ̿@��";}
				if((item[i][j] ==null)&&(item [i][j].equals(""))){menu += "�L";}
				else{menu +=item[i][j]+"\t";}
			}
			menu +="\n";
		}
		return menu;
	}
	//edited by �����Y time:0618 �bDos�W�C�L�M�\�����
}