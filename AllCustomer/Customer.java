//developed by �����Y time:0608
//edited by ���o time:0610
//edited by �����Y time:0613
//edited by �����Y time:0615
//edited by �����Y time:0618
//�ȤH����(�t�I�\�ɪ������Ы�)
package AllCustomer;
import java.util.Random;
import java.util.Arrays;
import AllFood.*;         //�ޤJ���I���M��
import AllSet.*;          //�ޤJ�M�\���M��
import AllFile.*;	  //�ޤJŪ�ɮת��M��
import java.util.LinkedList; //�s�񭹪�(���I�A�M��)���M��
public class Customer {
	/**************�ܼƫŧi��*****************************************/

	private static int foodnumber = 0;                     //�p��ثe�I�����I����	

	private String item1[][] = new String[10][5];                 //�s����I����T
	private String item2[][] = new String[6][3];                  //�s��M�\����T
	private String member_gift_list [][] = new String [10][3];    //�s��§������T
	private String promotion_list [][] = new String [15][2];      //�s���u�f������T
	public String giftfood = "";
	
	public LinkedList<String> singlelist = new LinkedList<String>();    //�s����I���ʪ��M��
	public LinkedList<String> setlist = new LinkedList<String>();       //�s��M�\���ʪ��M��
	public LinkedList<String> giftlist = new LinkedList<String>();      //�s���ث~���M��
	public LinkedList<String> giftoption = new LinkedList<String>();    //�s��i�H��@�ﶵ���\�I(�I�Ư����I)

	public AllFood.Food meals[][] = new AllFood.Food[3][3];             //�M�\�ΨӸ˳��I���󪺰}�C

	public AllFood.Food singleobject[];                                 //�s����I�����ʪ��M��
	public AllSet.Set setobject[];                                      //�s��M�\�����ʪ��M��
	public AllFood.Food giftobject[];                                   //�s���ث~(�u�����I)���󪺲M��
	public int moneytotal = 0;
	public int moneytotal2 = 0;
	
	public int singlenumber[]  =  new int[10];         //���I�C�����ƶq
	public int setnumber[] = new int [3];              //�M�\�C�����ƶq
	public int giftfoodnumber[] = new int [10];	   //�I�ƧI����C���ث~���ƶq(�u�����I)
	
	//�ŧi���I���󪺦W��
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

	/*************Ū���ɮ�*********************/
	public void read_file(){
		AllFile.File single = new AllFile.File("Single_meal.csv",10,5);
		item1 = single.create_file();
		//edited by �����Y time:0618
		System.out.println(single.create_singleDos());
		
		AllFile.File set = new AllFile.File("Set_meal.csv",3,6);
		item2 = set.create_file();
		//edited by �����Y time:0618
		System.out.println(set.create_setDos());

		AllFile.File member_gift = new AllFile.File("Member_Gift.csv",10,3);
		member_gift_list =member_gift.create_file();
	
		AllFile.File promotion = new AllFile.File("Promotion_Code.csv",15,2);
		promotion_list = promotion.create_file();
		
	}
	/*******************************************/
	/************************************************************�[�I����*******************************************/

	public void add_single_food(String singlefood){singlelist.add(singlefood);}//�W�[���I����
	public void add_set_food(String setfood){setlist.add(setfood);}//�W�[�M�\����

	//�sĶ��0617:���E�� ²�� (�W�[§���M��W�����I�r��W��)
	public int add_gift(String giftfood,int point){
		int p = 0;
		if(giftfood=="����(�p)"){
			giftlist.add(giftfood);
			p=10;                     //����(�p)���I��
		}
		else if(giftfood=="������") {
			giftlist.add(giftfood);
			p=15; 
		}
		else if(giftfood=="�i��(�p)") {
			giftlist.add(giftfood);
			p=10; 
		}
		else if(giftfood=="����(�p)") {
			giftlist.add(giftfood);
			p=10; 
		}
		else if(giftfood=="����(��)") {
			giftlist.add(giftfood);
			p=15;
		}
		else if(giftfood=="����") {
			giftlist.add(giftfood); 
			p=10;
		}
		else if(giftfood=="���ֳ�") {
			giftlist.add(giftfood); 
			p=10; 
		}
		else if(giftfood=="����(�j)") {
			giftlist.add(giftfood); 
			p=20;
		}
		else if(giftfood=="�j���J") { 
			giftlist.add(giftfood); 
			p=20;
		}
		else if(giftfood=="�ɦ̿@��") {
			giftlist.add(giftfood);
			p=20; 
		}
		return (point - p);
	}
	//�sĶ��0617:���E�� �s��i�H��@�ﶵ���ث~(�I�Ư����I)
	public void checkpoint(int point){

		if(point>= 10) { 
			giftfood = "����(�p)";
			giftoption.add(giftfood);
		}
		if(point>=15) {
			giftfood = "������";				
			giftoption.add(giftfood);
		}
		if(point>=10){ 
			giftfood = "�i��(�p)";
			giftoption.add(giftfood);
		}
		if(point>=10){ 
			giftfood = "����(�p)";
			giftoption.add(giftfood);
		}
		if(point>=15){ 
			giftfood = "����(��)";
			giftoption.add(giftfood);
		}
		if(point>=10){ 
			giftfood = "����";
			giftoption.add(giftfood); 
		}
		if(point>=20) { 
			giftfood ="���ֳ�";
			giftoption.add(giftfood); 
		}
		if(point>=20) { 
			giftfood = "����(�j)";
			giftoption.add(giftfood); 
		}
		if(point>=20) {
			giftfood = "�j���J";				
			giftoption.add(giftfood); 
		}	
		if(point>=20) { 
			giftfood = "�ɦ̿@��";
			giftoption.add(giftfood);
		}
		if(giftoption.size() == 0){
			giftoption.add("�V�|�z���I�Ƥ���");
		}
	}
	/*****************************************************************�R���P���\***********************************************/

	public void delete_single_food(String singlefood){    //�R�����I����
		Boolean success = false;
		success = singlelist.remove(singlefood);      //������LinkedList�̭�����k
		if(success){System.out.println("�������\");}
		else{System.out.println("��������");}
	}
	public void delete_set_food(String setfood){          //�R���M�\����
		Boolean success = false;
		success = setlist.remove(setfood);            //������LinkedList�̭�����k
		if(success){System.out.println("�������\");}
		else{System.out.println("��������");}
	}
	public void change_single_food(String bechanged,String food){  //���\:���I����
		int number = singlelist.indexOf(bechanged);            //������LinkedList�̭�����k �����n�������I�r��W��
		singlelist.set(number,food);                           //������LinkedList�̭�����k �b��������LinkedList�̭���data���e
	}
	public void change_set_food(String bechanged,String setfood){  //���\:�M�\����
		int number = setlist.indexOf(bechanged);               //������LinkedList�̭�����k �����n�����M�\�r��W�� 
		setlist.set(number,setfood);                           //������LinkedList�̭�����k �b��������LinkedList�̭���data���e
	}

	public boolean check_code(int coding){	//�ˬdɬ�f���O�_�s�b
		boolean code=false;
		for (int i = 0; i < 15; i++) {  //��for�j����u�f���X
			for (int j = 0; j < 2; j++) {
		   		if ( Integer.parseInt(promotion_list [i][j]) == coding ) {
					code=true;
					System.out.println("�u�f��ϥΦ��\�I"); 
				}
		  	}
		}
		if(code==false) { System.out.println("�u�f��ϥΥ��ѡI"); }
		return code;
	}
	//################################################################�غc����#########################################################/

	/***************************�غc���I���� ##�`�N���I���󪺫غc�����A�M�\����غc�e***********************************************/

	public void create_single_food(){
		int size = singlelist.size();                     //���ݾ�ӳ��I�M�檺�j�p�A�]�N�O�`�@�R(�X��)���I����
		singleobject = new AllFood.Food [size];           //�Τ����O�h���ӫŧi�`�@�R(�X��)�����I����
		for (int i = 0; i < size; i++) {                  //�]for�|����� ���I�M��(�r��)�ҹ����쪺�W�r�A�ӫغc�����쪺�W�r������
            		if(singlelist.get(i)=="����(�p)"){       
				smallfried = new AllFood.SmallFried(item1[0][0],item1[0][1],item1[0][2],item1[0][3],item1[0][4]); //�ǻ����غc�l�ѼƬ� Ū�ɮ׶i�Ӫ����
				singleobject[i] = smallfried;     //�N�غc�n�����I��������I����M��W
				singlenumber[0]++;                //�P�ɤ]�b�p���쩳�I�F�h��"���I����"
			}
			else if(singlelist.get(i)=="������"){
				maixiangchicken = new AllFood.MaixiangChicken(item1[1][0],item1[1][1],item1[1][2],item1[1][3],item1[1][4]);
				singleobject[i] = maixiangchicken;
				singlenumber[1]++;
			}
			else if(singlelist.get(i)=="�i��(�p)"){
				cola = new AllFood.Cola(item1[2][0],item1[2][1],item1[2][2],item1[2][3],item1[2][4]);
				singleobject[i] = cola;
				singlenumber[2]++;
			}
			else if(singlelist.get(i)=="����(�p)"){
				soda = new AllFood.Soda(item1[3][0],item1[3][1],item1[3][2],item1[3][3],item1[3][4]);
				singleobject[i] = soda;
				singlenumber[3]++;
			}
			else if(singlelist.get(i)=="����(��)"){
				middlefried = new AllFood.MiddleFried(item1[4][0],item1[4][1],item1[4][2],item1[4][3],item1[4][4]);
				singleobject[i] = middlefried;
				singlenumber[4]++;
			}
			else if(singlelist.get(i)=="����"){
				potatocake = new AllFood.PotatoCake(item1[5][0],item1[5][1],item1[5][2],item1[5][3],item1[5][4]);
				singleobject[i] = potatocake;
				singlenumber[5]++;
			}
			else if(singlelist.get(i)=="���ֳ�"){
				manfubao = new AllFood.Manfubao(item1[6][0],item1[6][1],item1[6][2],item1[6][3],item1[6][4]);
				singleobject[i] = manfubao;
				singlenumber[6]++;
			}
			else if(singlelist.get(i)=="����(�j)"){
				bigfried = new AllFood.BigFried(item1[7][0],item1[7][1],item1[7][2],item1[7][3],item1[7][4]);
				singleobject[i] = bigfried ;
				singlenumber[7]++;
			}
			else if(singlelist.get(i)=="�j���J"){
				bigmac = new AllFood.BigMac(item1[8][0],item1[8][1],item1[8][2],item1[8][3],item1[8][4]);
				singleobject[i] = bigmac ;
				singlenumber[8]++;
			}
			else if(singlelist.get(i)=="�ɦ̿@��"){
				cornbisque = new AllFood.CornBisque(item1[9][0],item1[9][1],item1[9][2],item1[9][3],item1[9][4]);
				singleobject[i] = cornbisque;
				singlenumber[9]++;
			}
			
        	}
	}
		
	/***************************���I����غc���� **********************************************************************************************/
	/***************************�غc�M�\���� ##�`�N���I���󪺫غc�����A�M�\����غc�e***********************************************/
	public void create_set_food(){
		for(int i = 0;i<3;i++){             //��for�j����s�������e
			for(int j = 2;j<5;j++){
				/***********�P�_���̪����󬰦r�ꪫ��ۤ� <�`�N> �r�ꪫ����P�_�O�_�۵��Шϥγo�اP�_�覡 (���M�|����)******************/
				if(item2[i][j]!=null&&item2[i][j].equals("1")){                                  //�]���Ѯv����csv�ɪ����\�O�H�s���A�ҥH�o��O�b�ǰt�s�������e
					smallfried = new AllFood.SmallFried(item1[0][0],item1[0][1],item1[0][2],item1[0][3],item1[0][4]); //�غc���\�̪����I����
					meals[i][j-2]=smallfried;                                                                         //�s��M����M�\���I���}�C�̭�
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

		int size = setlist.size();                //�M�\�r��M�檺�j�p
		setobject = new AllSet.Set [size];        //�M�\���󪺤j�p
		for (int i = 0; i < size; i++){           //�ΰj����������M�\�r��
			if(setlist.get(i)=="���q�M�\"){
				AllSet.Set1 set1 = new AllSet.Set1(item2[0][0],item2[0][1],meals[0][0],meals[0][1],meals[0][2],item2[0][5]); //�غc�l�Ѽƶǭ����I�غc
				setobject[i] = set1;                                                                                         //��غc���M�\������M�\����M��W
				setnumber[0]++;                                                                                              //�P�ɮM�\���ƶq�]�W�[
			}
			else if(setlist.get(i)=="����M�\"){
				AllSet.Set2 set2 = new AllSet.Set2(item2[1][0],item2[1][1],meals[1][0],meals[1][1],meals[1][2],item2[1][5]);
				setobject[i] = set2;
				setnumber[1]++;
			}
			else if(setlist.get(i)=="�Y�ܹ��M�\"){
				AllSet.Set3 set3 = new AllSet.Set3(item2[2][0],item2[2][1],meals[2][0],meals[2][1],meals[2][2],item2[2][5]);
				setobject[i] = set3;
				setnumber[2]++;
			}
		}
	}
	/***************************�M�\����غc���� **********************************************************************************************/

	/**********************************************************�غc�ث~���� �P  �غc���I����@��******************************************************************/
	//edited:���E�� date:0617 �W�[giftfoodnumber
	public void create_gift_food(){
		int size = giftlist.size();
		giftobject = new AllFood.Food [size];
		for (int i = 0; i < size; i++) {
            		if(giftlist.get(i)=="����(�p)"){
				smallfried = new AllFood.SmallFried(item1[0][0],item1[0][1],item1[0][2],item1[0][3],item1[0][4]); 
				giftobject[i] = smallfried;
				giftfoodnumber[0]++;
			}
			else if(giftlist.get(i)=="������"){
				maixiangchicken = new AllFood.MaixiangChicken(item1[1][0],item1[1][1],item1[1][2],item1[1][3],item1[1][4]);
				giftobject[i] = maixiangchicken;
				giftfoodnumber[1]++;
			}
			else if(giftlist.get(i)=="�i��(�p)"){
				cola = new AllFood.Cola(item1[2][0],item1[2][1],item1[2][2],item1[2][3],item1[2][4]);
				giftobject[i] = cola;
				giftfoodnumber[2]++;
			}
			else if(giftlist.get(i)=="����(�p)"){
				soda = new AllFood.Soda(item1[3][0],item1[3][1],item1[3][2],item1[3][3],item1[3][4]);
				giftobject[i] = soda;
				giftfoodnumber[3]++;
			}
			else if(giftlist.get(i)=="����(��)"){
				middlefried = new AllFood.MiddleFried(item1[4][0],item1[4][1],item1[4][2],item1[4][3],item1[4][4]);
				giftobject[i] = middlefried;
				giftfoodnumber[4]++;
			}
			else if(giftlist.get(i)=="����"){
				potatocake = new AllFood.PotatoCake(item1[5][0],item1[5][1],item1[5][2],item1[5][3],item1[5][4]);
				giftobject[i] = potatocake;
				giftfoodnumber[5]++;
			}
			else if(giftlist.get(i)=="���ֳ�"){
				manfubao = new AllFood.Manfubao(item1[6][0],item1[6][1],item1[6][2],item1[6][3],item1[6][4]);
				giftobject[i] = manfubao;
				giftfoodnumber[6]++;
			}
			else if(giftlist.get(i)=="����(�j)"){
				bigfried = new AllFood.BigFried(item1[7][0],item1[7][1],item1[7][2],item1[7][3],item1[7][4]);
				giftobject[i] = bigfried ;
				giftfoodnumber[7]++;
			}
			else if(giftlist.get(i)=="�j���J"){
				bigmac = new AllFood.BigMac(item1[8][0],item1[8][1],item1[8][2],item1[8][3],item1[8][4]);
				giftobject[i] = bigmac ;
				giftfoodnumber[8]++;
			}
			else if(giftlist.get(i)=="�ɦ̿@��"){
				cornbisque = new AllFood.CornBisque(item1[9][0],item1[9][1],item1[9][2],item1[9][3],item1[9][4]);
				giftobject[i] = cornbisque;
				giftfoodnumber[9]++;
			}
			
        	}

	}
/*****************************************************�ث~����غc���� **********************************************************************************************/

	/****************�B�J�T:�I�\���ӥ\��w�����A�bDos�W�����A���{�b�S���Q�I�s��o�Ө��**************************************************************************/
	public void print_content(){
		for (int i = 0; i < singlelist.size(); i++){
			System.out.println(singleobject[i]);
		}
		for (int i = 0; i < setlist.size(); i++){
			System.out.println(setobject[i]);
		}
		System.out.println("\n�ذe:");
		for (int i = 0; i < giftlist.size(); i++){
			System.out.println(giftobject[i].getName());
		}
		System.out.println();
	}
	/***************�B�J�T:�C�L�X�`���B********************************************************************************************************/
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
	//�s���0616:���E�� �W�[�u�f�X���PŪ(�p�G���u�f�X�N����0.9)�p�G���u�f���N�|�]�o�Ӱj��
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
	/****************************************************************�u�f��*********************************************************************/
	public void discount(boolean code){if(code==true){ System.out.println("�馩�Z�G"+moneytotal*0.9); }} //�p�G�����ܴN�bDos�W���L�X�馩�᪺����
	//���I�r�ꪺ�M�椺�e���إ�
	public String[] singlelist(){
		String singlelist1[] = new String[singlelist.size()];
		for(int i = 0; i < singlelist.size(); i++){
			singlelist1[i] = singlelist.get(i);
		}
		return singlelist1;
	}
	//�ث~�r�ꪺ�M�椺�e���إ�
	public String[] giftoption(){
		String giftoption1[] = new String[giftoption.size()];
		for(int i = 0; i < giftoption.size(); i++){
			giftoption1[i] = giftoption.get(i);
		}
		return giftoption1;
	}
	//�M�\�r�ꪺ�M�椺�e���إ�
	public String[] setlist(){
		String setlist1[] = new String[setlist.size()];
		for(int i = 0; i < setlist.size(); i++){
			setlist1[i] = setlist.get(i);
		}
		return setlist1;
	}
	//�s���0616:���E�� �W�[�u�f�X���PŪ
	public String Finallist(boolean a){
		String finallist = "";
		//�L�X���I�P�M�\�P�ث~�����e
		finallist += single_detail();      //����k�b�U��
		finallist += set_detail();         //����k�b�U��
		if(giftlist.size() > 0 ){
			finallist += gift_detail();
		}
		//�u�O���F�ƪ��Ӥw
		if(a == true){
			finallist += "                                         �馩:"+ " " + (print_moneytotal() - print_moneytotal_checkpoint()) +"TX\n";
			finallist += "                                         �`�p: " + print_moneytotal_checkpoint() +"TX";	
		}
		else{
			finallist += "                                         �`�p: " + print_moneytotal() +"TX";
		}
		return finallist;
	}
	/*****************************************JOP�̭����Ӫ��e�{******************************************************************************************/
	public String single_detail(){
		String content="";                //�쥻content�̭��S�����
		if(singlenumber[0]!=0){           //�p�G���I�@���ƶq����0�]�̭���body
			AllFood.Food food;
			//��쬰���I�@������A�γ��I����Ӥ��O�γ��I�W�٪��r��A�o�˪���~�i�HŪ���̭������I�s�ƻ�W�l�A���欰�h��  
			for(int i = 0; i < singlelist.size(); i++){
				//�γ��I�W��"����(�p)"�����I�����b���Ӱ}�C�����̡A�]���o�ˤ~��Kcall���󪺦W�١A����
				if((singleobject[i].getName()!=null)&&(singleobject [i].getName().equals("����(�p)"))){
					food = singleobject[i];                                                        //�g�@��food���ܼƥu�O��K�{���X�ק�Ӥw
					content += "      "+food.getName()+"\t         X"+singlenumber[0]+"       ����: "+food.getPrice()*singlenumber[0]+"TX\n"; //���I�W�١A�ƶq�A����
					break;
				}
			}
		} 
		if(singlenumber[1]!=0){
			AllFood.Food food;
			for(int i = 0; i < singlelist.size(); i++){
				if((singleobject[i].getName()!=null)&&(singleobject [i].getName().equals("������"))){
					food = singleobject[i];
					content += "      "+food.getName()+"\t           X"+singlenumber[1]+"       ����: "+food.getPrice()*singlenumber[1]+"TX\n";
					break;
				}
			}
		}
		if(singlenumber[2]!=0){
			AllFood.Food food;
			for(int i = 0; i < singlelist.size(); i++){
				if((singleobject[i].getName()!=null)&&(singleobject [i].getName().equals("�i��(�p)"))){
					food = singleobject[i];
					content += "      "+food.getName()+"\t        X"+singlenumber[2]+"       ����: "+food.getPrice()*singlenumber[2]+"TX\n";
					break;
				}
			}
		}
		if(singlenumber[3]!=0){
			AllFood.Food food;
			for(int i = 0; i < singlelist.size(); i++){
				if((singleobject[i].getName()!=null)&&(singleobject [i].getName().equals("����(�p)"))){
					food = singleobject[i];
					content += "      "+food.getName()+"\t        X"+singlenumber[3]+"       ����: "+food.getPrice()*singlenumber[3]+"TX\n";
					break;
				}
			}
		}
		if(singlenumber[4]!=0){
			AllFood.Food food;
			for(int i = 0; i < singlelist.size(); i++){
				if((singleobject[i].getName()!=null)&&(singleobject [i].getName().equals("����(��)"))){
					food = singleobject[i];
					content += "      "+food.getName()+"\t        X"+singlenumber[4]+"       ����: "+food.getPrice()*singlenumber[4]+"TX\n";
					break;
				}
			}
		}
		if(singlenumber[5]!=0){
			AllFood.Food food;
			for(int i = 0; i < singlelist.size(); i++){
				if((singleobject[i].getName()!=null)&&(singleobject [i].getName().equals("����"))){
					food = singleobject[i];
					content += "      "+food.getName()+"\t                X"+singlenumber[5]+"       ����: "+food.getPrice()*singlenumber[5]+"TX\n";
					break;
				}
			}
		}
		if(singlenumber[6]!=0){
			AllFood.Food food;
			for(int i = 0; i < singlelist.size(); i++){
				if((singleobject[i].getName()!=null)&&(singleobject [i].getName().equals("���ֳ�"))){
					food = singleobject[i];
					content += "      "+food.getName()+"\t          X"+singlenumber[6]+"       ����: "+food.getPrice()*singlenumber[6]+"TX\n";
					break;
				}
			}
		}
		if(singlenumber[7]!=0){
			AllFood.Food food;
			for(int i = 0; i < singlelist.size(); i++){
				if((singleobject[i].getName()!=null)&&(singleobject [i].getName().equals("����(�j)"))){
					food = singleobject[i];
					content += "      "+food.getName()+"\t         X"+singlenumber[7]+"       ����: "+food.getPrice()*singlenumber[7]+"TX\n";
					break;
				}
			}
		}
		if(singlenumber[8]!=0){
			AllFood.Food food;
			for(int i = 0; i < singlelist.size(); i++){
				if((singleobject[i].getName()!=null)&&(singleobject [i].getName().equals("�j���J"))){
					food = singleobject[i];
					content += "      "+food.getName()+"\t          X"+singlenumber[8]+"       ����: "+food.getPrice()*singlenumber[8]+"TX\n";
					break;
				}
			}
		}
		if(singlenumber[9]!=0){
			AllFood.Food food;
			for(int i = 0; i < singlelist.size(); i++){
				if((singleobject[i].getName()!=null)&&(singleobject [i].getName().equals("�ɦ̿@��"))){
					food = singleobject[i];
					content += "      "+food.getName()+"\t       X"+singlenumber[9]+"       ����: "+food.getPrice()*singlenumber[9]+"TX\n";
					break;
				}
			}
		}
		return content;
	}
	public String set_detail(){
		String content="";
		if(setnumber[0]!=0){         //�p�G�M�\�@���ƶq����0�]�̭���body
			AllSet.Set set1;
			for(int i = 0; i < setlist.size(); i++){ 
				//��쬰���q�M�\������A�ήM�\����Ӥ��O�ήM�\�W�٪��r��A�o�˪���~�i�H��K��Ū���̭������\�s�ƻ�W�l �A���欰�h��           
				if((setobject[i].getName()!=null)&&(setobject[i].getName().equals("���q�M�\"))){//�ήM�\�W��"���q�M�\"���M�\�����b���Ӱ}�C������
					set1 = setobject[i];
					//�g�o�T���ܼƥu�O��K�{���X�ק�Ӥw
					AllFood.Food meal1 = set1.getMeal1();     //���\�@������
					AllFood.Food meal2 = set1.getMeal2();     //���\�G������
					AllFood.Food meal3 = set1.getMeal3();     //���\�T������
					content += "      �\- ";
					//��������\�@���νT�{�O�_��null�A�]��csv�ɸ̭��C�ӮM�\�������\�@
					content += meal1.getName()+"   X"+setnumber[0]+"       ����: "+set1.getPrice()*setnumber[0]+"TX\n"; //�M�\�W�١A�M�\����A�M�\�ƶq
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
			for(int i = 0; i < setlist.size(); i++){
				if((setobject[i].getName()!=null)&&(setobject[i].getName().equals("����M�\"))){
					set2 = setobject[i];
					AllFood.Food meal1 = set2.getMeal1();
					AllFood.Food meal2 = set2.getMeal2();
					AllFood.Food meal3 = set2.getMeal3();
					content += "      �\- ";
					content += meal1.getName()+"   X"+setnumber[1]+"       ����: "+set2.getPrice()*setnumber[1]+"TX\n";
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
				if((setobject[i].getName()!=null)&&(setobject [i].getName().equals("�Y�ܹ��M�\"))){
					set3 = setobject[i];
					AllFood.Food meal1 = set3.getMeal1();
					AllFood.Food meal2 = set3.getMeal2();
					AllFood.Food meal3 = set3.getMeal3();
					content += "      �\- ";
					content += meal1.getName()+"   X"+setnumber[2]+"       ����: "+set3.getPrice()*setnumber[2]+"TX\n";
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
	public String gift_detail(){// edited by ���E�� time:0617 �I�ƧI�����ӫ~
		String content="";                  //�쥻content�̭��S�����
		if(giftfoodnumber[0]!=0){           //�p�G���I�@���ƶq����0�]�̭���body			
			AllFood.Food food;
			//��쬰���I�@������A�γ��I����Ӥ��O�γ��I�W�٪��r��A�o�˪���~�i�HŪ���̭������I�s�ƻ�W�l�A���欰�h��  
			for(int i = 0; i < giftlist .size(); i++){
				//�γ��I�W��"����(�p)"�����I�����b���Ӱ}�C�����̡A�]���o�ˤ~��Kcall���󪺦W�١A����
				if((giftobject[i].getName()!=null)&&(giftobject [i].getName().equals("����(�p)"))){
					food = giftobject[i];                                                        //�g�@��food���ܼƥu�O��K�{���X�ק�Ӥw
					content += "      "+food.getName()+"\t         X"+giftfoodnumber[0] +"       (�I�ƧI��)\n"; //���I�W�١A�ƶq
					break;
				}
			}
		} 
 		if(giftfoodnumber[1]!=0){
			AllFood.Food food;
			for(int i = 0; i < giftlist .size(); i++){
				if((giftobject[i].getName()!=null)&&(giftobject [i].getName().equals("������"))){
					food = giftobject[i];
					content += "      "+food.getName()+"\t           X"+giftfoodnumber[1] + "       (�I�ƧI��)\n";
					break;
				}
			}
		}
 		if(giftfoodnumber[2]!=0){
			AllFood.Food food;
			for(int i = 0; i < giftlist .size(); i++){
				if((giftobject[i].getName()!=null)&&(giftobject [i].getName().equals("�i��(�p)"))){
					food = giftobject[i];
					content += "      "+food.getName()+"\t        X"+giftfoodnumber[2] + "       (�I�ƧI��)\n";
					break;
				}
			}
		} 
 		if(giftfoodnumber[3]!=0){
			AllFood.Food food;
			for(int i = 0; i < giftlist .size(); i++){
				if((giftobject[i].getName()!=null)&&(giftobject [i].getName().equals("����(�p)"))){
					food = giftobject[i];
					content += "      "+food.getName()+"\t        X"+giftfoodnumber[3] + "       (�I�ƧI��)\n";
					break;
				}
			}
		} 
		if(giftfoodnumber[4]!=0){
			AllFood.Food food;
			for(int i = 0; i < giftlist .size(); i++){
				if((giftobject[i].getName()!=null)&&(giftobject [i].getName().equals("����(��)"))){
					food = giftobject[i];
					content += "      "+food.getName()+"\t        X"+giftfoodnumber[4] + "       (�I�ƧI��)\n";
					break;
				}
			}
		}
		if(giftfoodnumber[5]!=0){
			AllFood.Food food;
			for(int i = 0; i < giftlist .size(); i++){
				if((giftobject[i].getName()!=null)&&(giftobject [i].getName().equals("����"))){
					food = giftobject[i];
					content += "      "+food.getName()+"\t                X"+giftfoodnumber[5] + "   (�I�ƧI��)\n";
					break;
				}
			}
		}
		if(giftfoodnumber[6]!=0){
			AllFood.Food food;
			for(int i = 0; i < giftlist .size(); i++){
				if((giftobject[i].getName()!=null)&&(giftobject [i].getName().equals("���ֳ�"))){
					food = giftobject[i];
					content += "      "+food.getName()+"\t          X"+giftfoodnumber[6] + "       (�I�ƧI��)\n";
					break;
				}
			}
		}
		if(giftfoodnumber[7]!=0){
			AllFood.Food food;
			for(int i = 0; i < giftlist .size(); i++){
				if((giftobject[i].getName()!=null)&&(giftobject [i].getName().equals("����(�j)"))){
					food = giftobject[i];
					content += "      "+food.getName()+"\t         X"+giftfoodnumber[7] + "       (�I�ƧI��)\n";
					break;
				}
			}
		}
		if(giftfoodnumber[8]!=0){
			AllFood.Food food;
			for(int i = 0; i < giftlist .size(); i++){
				if((giftobject[i].getName()!=null)&&(giftobject [i].getName().equals("�j���J"))){
					food = giftobject[i];
					content += "      "+food.getName()+"\t          X"+giftfoodnumber[8] + "       (�I�ƧI��)\n";
					break;
				}
			}
		}
		if(giftfoodnumber[9]!=0){
			AllFood.Food food;
			for(int i = 0; i < giftlist .size(); i++){
				if((giftobject[i].getName()!=null)&&(giftobject [i].getName().equals("�ɦ̿@��"))){
					food = giftobject[i];
					content += "      "+food.getName()+"\t       X"+giftfoodnumber[9] + "     (�I�ƧI��)\n";
					break;
				}
			}
		} 
		return content;
	}
	//edited by �����Y time:0613##############################################################################################################################################
	//edited by ���E�� time:0616 �N��k��}�H���u�f�X���P�_

	//�гy�o���򦬾ڪ����󪺤�k
	public void create_receipt(boolean a){
		//�غc�Ѽƪ���
		AllFile.Barcode barcode = new AllFile.Barcode();
		//�ǤJ���غc�l���ѼƬO(���I������}�C�A�M�\������}�C�A�{�b�ʪ��M��W�����I�ƶq�A�{�b�ʪ��M��W���M�\�ƶq�A�{�b�ʪ��M��W���M�\�ƶq)
		AllFile.Receipt receipt = new AllFile.Receipt(singleobject,setobject,singlelist.size(),setlist.size(),barcode.get_barcode());
		receipt.create_receiptfile(a);
		//���o���W���@�X���k�s
		int singlenumber[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		int giftfoodnumber[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		int setnumber[] = {0, 0, 0};
	}
	//�гy���Ӫ���k
	public void create_transcation(boolean a){
		//�غc���Ӫ���
		AllFile.Barcode barcode = new AllFile.Barcode();
		//�ǤJ���غc�l���ѼƬO(���I������}�C�A�M�\������}�C�A�{�b�ʪ��M��W�����I�ƶq�A�{�b�ʪ��M��W���M�\�ƶq�A�C�@�����I���X�ӡA�C�@���M�\���X�ӡA�{�b�ʪ��M��W���M�\�ƶq)
		AllFile.Transcation transcation = new AllFile.Transcation(singleobject,setobject,giftobject,singlelist.size(),setlist.size(),giftlist.size(),singlenumber,setnumber,giftfoodnumber,barcode.get_barcode());
		transcation.create_transcationfile(a);
	}
}