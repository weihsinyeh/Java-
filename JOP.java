import javax.swing.*;
import AllFood.*;                 
import AllSet.*;
import AllFile.*;
import AllTime.*;
import AllCustomer.*;

public class JOP{
	public static void main(String args[]){
		//firstoperation ��ܶi�J ���I,�M�\,���\
		//secondoperation ��ܶi�J���I,�M�\,���\
		//thirdoperation ��ܬO�_�[�I
		//fourthoperation ��ܬO�_�ӿ�|��
		int firstoperation, secondoperation, thirdoperation = 0, fourthoperation, fifthoperation, sixthoperation, seventhoperation = 0;
		int singlemealchoose = 0, setmealchoose, breakchoose, AnsFromMenu;
		int temp = 0 ;
		
		//operation �t�C�ȥΨӼȮɦs�񵪮׭�
		int operation1 = 0, operation2 = 0, operation3 = 0, operation4 = 0, operation5 = 1, operation6 = 0, operation7 = 0, operation8 = 0;
		String membername = "";
		String passward = "", confirmpassward = "";
		String couponnumber;
		
		boolean Test = false,Test2 = false, Test3 = false, Test4 = false, Test5 = false, Test6 = false;//4:�O�_�ϥη|��
		String option1[] = {"���", "�I�\"};
		String option2[] = {"���I", "�M�\", "���\"};
		String option3[] = {"�[�I", "�R���\�I", "���\", "�����ݭn"};
		String option4[] = {"�ӽ�", "�U���A��", "�w���|��"};
		String option5[] = {"�T�w", "��^"};
		String option6[] = {"�}�l�I�\�a!"};
		String option7[] = {"���I", "�M�\", "���\", "�I���\�F!"};
		String option8[] = {"���I", "�M�\"};
		String option9[] = {"�n", "���ݭn"};
		String option10[] = {"���I", "�M�\"};
		String option11[] = {"���I", "�M�\", "�I���\�F!"};
		String option12[] = {"���s��J", "���}"};		
		String optionforsingle[] = {"����(�p)", "����(��)", "����(�j)", "�i��(�p)", "����(�p)", "�ɦ̿@��", "�j���J", "������", "����"};
		String optionforset[]= {"���q�M�\", "����M�\", "�Y�ܹ��M�\"};
		String optionforbreakfast[] = {"���ֳ�"};
		String menu = "";
		

	
	do{
		AllCustomer.Customer c = new AllCustomer.Customer();
		//AllCustomer.MemberClub m = AllCustomer.MemberClub.getInstance();
		AllCustomer.MemberClub m = new AllCustomer.MemberClub();
		AllTime.Time t = new AllTime.Time();
		//firstoperation ��ܶi�J ���, �I�\

		operation3 = 3;//���s�~�����׭Ȩ����
		c.singlelist.clear();
		c.setlist.clear();
		c.giftlist.clear();//����ƨϥήɡA���e���M�� 
		c.read_file();

		do{//�I��X
			firstoperation = JOptionPane.showOptionDialog(null, "�w����{", "�I�\��", 1, 3, null, option1, option1[1]);
			operation4 = X(firstoperation);
		}while(operation4 == 1);
		
		if(firstoperation == 0){//�e�{���
			AllFile.File single = new AllFile.File("Single_meal.csv",10,5);
			String item1 [][] = single.create_file();
			AllFile.File set = new AllFile.File("Set_meal.csv",3,6);
			String item2 [][] = set.create_file();
			menu ="";
			menu += single.create_menu();
			menu += set.create_menu_detail();
			do{//�I��X
			
				AnsFromMenu = JOptionPane.showOptionDialog(null, menu, "�I�\��", 1, 3, null, option6, null);
				operation4 = X(AnsFromMenu);
				if(AnsFromMenu == 0){
					firstoperation = 1;
				}
			}while(operation4 == 1);
		}
		
		//�I�\
		if(firstoperation == 1){
			do{
				if((t.read_hour() < 10) || ((t.read_hour() == 10) && (t.read_min() < 30))){//10:30�H�e = �����\
					do{//�I��X
					//secondoperation ��ܶi�J���I,�M�\,���\
					secondoperation = JOptionPane.showOptionDialog(null, "�п��", "�I�\��", 1, 3, null, option2, null);
					operation4 = X(secondoperation);
					}while(operation4 == 1);
				}
				else{//10:30��S���\
					do{//�I��X
						//secondoperation ��ܶi�J���I,�M�\
						secondoperation = JOptionPane.showOptionDialog(null, "�п��", "�I�\��", 1, 3, null, option10, null);
						operation4 = X(secondoperation);
					}while(operation4 == 1);
				}
				do{
					//���I
					if(secondoperation == 0){
						do{//�I��X
							singlemealchoose = JOptionPane.showOptionDialog(null, "�аݻݭn���@���\�I�O?", "�I�\��", 1, 3, null, optionforsingle, null);
							operation4 = X(singlemealchoose);
							if(singlemealchoose != -1){//����ǤJ�Ȭ�-1
								c.add_single_food(optionforsingle[singlemealchoose]);
							}
						}while(operation4 == 1);
					}
					
					//�M�\
					else if(secondoperation == 1){
						do{//�I��X
							setmealchoose = JOptionPane.showOptionDialog(null, "�аݻݭn���@���M�\�O?", "�I�\��", 1, 3, null, optionforset, null);
							operation4 = X(setmealchoose);
							if(setmealchoose != -1){
								c.add_set_food(optionforset[setmealchoose]);
							}						
						}while(operation4 == 1);
					}
					
					//���\
					else if(secondoperation == 2){
						do{//�I��X
							breakchoose = JOptionPane.showOptionDialog(null, "�аݻݭn���@�����\�O?", "�I�\��", 1, 3, null, optionforbreakfast, null);
							operation4 = X(breakchoose);//�إ��\�I�s��list
							if(singlemealchoose != -1){//����ǤJ�Ȭ�-1
								c.add_single_food(optionforsingle[singlemealchoose]);
							}						
						}while(operation4 == 1);
					}
					if((t.read_hour() < 10) || ((t.read_hour() == 10) && (t.read_min() < 30))){//10:30�H�e = �����\
						do{//�I��X
							//sixthoperation ��ܶi�J���I,�M�\,���\, �I���\�F!
							sixthoperation = JOptionPane.showOptionDialog(null, "�п��", "�I�\��", 1, 3, null, option7, null);
							operation4 = X(sixthoperation);
						}while(operation4 == 1);
					}
					else{
						do{//�I��X
							//sixthoperation ��ܶi�J���I,�M�\ ,�I���\�F!
							sixthoperation = JOptionPane.showOptionDialog(null, "�п��", "�I�\��", 1, 3, null, option11, null);
							operation4 = X(sixthoperation);
							if(sixthoperation == 2){sixthoperation++;}//�]���쥻"�I���\�F"�b�ĥ|��
						}while(operation4 == 1);
					}
					if(sixthoperation == 3){//�I���\�F!
						break;
					}
					else{//���I���\���ƶi���I�\
						secondoperation = sixthoperation;
					}					
				}while(true);	
				do{	
					do{//�I��X
						//thirdoperation ��ܬO�_�ݭn����H�U�ﶵ
						thirdoperation = JOptionPane.showOptionDialog(null, "�O�_�ݭn����H�U�ﶵ�O?", "�I�\��", 1, 3, null, option3, null);
						operation4 = X(thirdoperation);	
					}while(operation4 == 1);
					if(thirdoperation == 1){//�R���\�I
						do{
							do{//�I��X
								//operation6 ��ܧR�����I�ήM�\
								operation6 = JOptionPane.showOptionDialog(null, "�аݭn�R�������\�I�O?", "�I�\��", 1, 3, null, option8, null);
								operation4 = X(operation6);	
							}while(operation4 == 1);
							
							if(operation6 == 0){
								do{//�I��X
									if(c.singlelist.size() == 0){
										JOptionPane.showMessageDialog(null, "�V�|�̭��S���F��\n�֥h�I�\�a!" , "�I�\��", 1);
										break;
									}
									do{//�I��X
										//seventhoperation ��ܧR�������\�I(���I)
										seventhoperation = JOptionPane.showOptionDialog(null, "�аݭn�R�������\�I�O?", "�I�\��", 1, 3, null, c.singlelist(), null);
										operation4 = X(firstoperation);
									}while(operation4 == 1);
									c.delete_single_food(c.singlelist()[seventhoperation]);
									operation4 = X(seventhoperation);	
								}while(operation4 == 1);
							}
							else if(operation6 == 1){
								do{//�I��X
									if(c.setlist.size() == 0){
										JOptionPane.showMessageDialog(null, "�V�|�̭��S���F��\n�֥h�I�\�a!" , "�I�\��", 1);
										break;
									}								
									//seventhoperation ��ܧR�������\�I(�M�\)
									do{//�I��X
										seventhoperation = JOptionPane.showOptionDialog(null, "�аݭn�R�������\�I�O?", "�I�\��", 1, 3, null, c.setlist(), null);
										operation4 = X(firstoperation);
									}while(operation4 == 1);									
									c.delete_set_food(c.setlist()[seventhoperation]);
									operation4 = X(seventhoperation);	
								}while(operation4 == 1);
							}
							do{//�I��X
								//operation7 �߰ݬO�_�n�~��R���\�I
								operation7 = JOptionPane.showOptionDialog(null, "�а��ٻݭn�~��R���\�I��?", "�I�\��", 1, 3, null, option9, null);
								operation4 = X(operation7);	
							}while(operation4 == 1);
							if(operation7 == 1){//���X�R���\�I���j��
								Test5 = true;
								break;
							}
							else{
								Test5 = false;
							}
						}while(true);
					}
					else if(thirdoperation == 2){//���\�I
						do{
							do{//�I��X
								//operation6 ��ܴ����I�ήM�\
								operation6 = JOptionPane.showOptionDialog(null, "�аݭn�������\�I�O?", "�I�\��", 1, 3, null, option8, null);
								operation4 = X(operation6);	
							}while(operation4 == 1);
							
							if(operation6 == 0){
								do{//�I��X
									if(c.singlelist.size() == 0){
										JOptionPane.showMessageDialog(null, "�V�|�̭��S���F��\n�֥h�I�\�a!" , "�I�\��", 1);
										break;
									}										
									//seventhoperation ��ܨ��N���\�I(���I)
									seventhoperation = JOptionPane.showOptionDialog(null, "�п�ܭn���N�\�I?", "�I�\��", 1, 3, null, c.singlelist(), null);
									operation4 = X(seventhoperation);	
								}while(operation4 == 1);
								
								do{//�I��X  ��ܴ������\�I(���I)
									singlemealchoose = JOptionPane.showOptionDialog(null, "�п�ܭn�����\�I?", "�I�\��", 1, 3, null, optionforsingle, null);
									operation4 = X(singlemealchoose);
								}while(operation4 == 1);
								c.change_single_food( c.singlelist()[seventhoperation], optionforsingle[singlemealchoose]);
							}
							else if(operation6 == 1){
								do{//�I��X
									if(c.setlist.size() == 0){
										JOptionPane.showMessageDialog(null, "�V�|�̭��S���F��\n�֥h�I�\�a!" , "�I�\��", 1);
										break;
									}								
									//seventhoperation ��ܨ��N���\�I(�M�\)
									seventhoperation = JOptionPane.showOptionDialog(null, "�аݭn�R�������\�I�O?", "�I�\��", 1, 3, null, c.setlist(), null);
									operation4 = X(seventhoperation);	
								}while(operation4 == 1);
								
								do{//�I��X  ��ܴ������\�I(�M�\)
									setmealchoose = JOptionPane.showOptionDialog(null, "�п�ܭn�����\�I?", "�I�\��", 1, 3, null, optionforset, null);
									operation4 = X(setmealchoose);
								}while(operation4 == 1);
								c.change_set_food(c.setlist()[seventhoperation], optionforset[setmealchoose]);							
							}
							do{//�I��X
								//operation7 �߰ݬO�_�n�~���\�I
								operation7 = JOptionPane.showOptionDialog(null, "�а��ٻݭn�~���\�I��?", "�I�\��", 1, 3, null, option9, null);
								operation4 = X(operation7);	
							}while(operation4 == 1);
							if(operation7 == 1){//���X���\�I���j��
								Test5 = true;							
								break;
							}
							else{
								Test5 = false;
							}
						}while(true);
					}
					else if(thirdoperation == 3){
						break;
					}
					else if (thirdoperation == 0){
						break;
					}
				}while(Test5 == true);
			}while(thirdoperation == 0);//�[�I
		do{
			do{//�I��X
				//fourthoperation ��ܬO�_�ӿ�|��
				fourthoperation = JOptionPane.showOptionDialog(null, "�O�_�n�ӿ�|��?", "�I�\��", 1, 3, null, option4, null);
				operation4 = X(fourthoperation);	
			}while(operation4 == 1);		
			//�ӽз|��
			if(fourthoperation == 0 ){
				do{	
					Test = false;//����j��d��
					operation1 = 3;//�����I��X������X�j��
					do{
						membername = JOptionPane.showInputDialog(null, "�|���W�r:");
						if(membername == null){ membername = "0";}//����Exception in thread "main" java.lang.NullPointerException
						if(Y(membername) == true){
							Test = true;// ���X�ӽз|���o�Ӱj��
							operation2 = 1; //���X��ӿ�|�����j��							
							break;
						}
						else if(membername.equals("")){
							JOptionPane.showMessageDialog(null, "�п�J!" , "�I�\��", 1);
						}
					}while(membername.equals("0") || membername.equals(""));
					
					if(Test == true){// ���X�ӽз|���o�Ӱj��
						break;
					}
					
 					do{
						passward =  JOptionPane.showInputDialog(null, "�K�X:");
						if(passward == null){ passward = "0";}//����Exception in thread "main" java.lang.NullPointerException
						if(Y(passward) == true){
							Test = true;// ���X�ӽз|���o�Ӱj��
							operation2 = 1; //���X��ӿ�|�����j��
							break;
						}
						else if(membername.equals("")){
							JOptionPane.showMessageDialog(null, "�п�J!" , "�I�\��", 1);
						}
							
					}while(passward.equals("0") || passward.equals(""));

					if(Test == true){// ���X�ӽз|���o�Ӱj��
						break;
					}
					
					do{	
						confirmpassward =  JOptionPane.showInputDialog(null, "�T�{�K�X:");
						if(confirmpassward == null){ confirmpassward = "0";}//����Exception in thread "main" java.lang.NullPointerException
						if(Y(confirmpassward) == true){
							Test = true;// ���X�ӽз|���o�Ӱj��
							operation2 = 1; //���X��ӿ�|�����j��
							break;
						}
						else if(membername.equals("")){
							JOptionPane.showMessageDialog(null, "�п�J!" , "�I�\��", 1);
						}
					}while(confirmpassward.equals("0") || confirmpassward.equals(""));
					
					if(Test == true){// ���X�ӽз|���o�Ӱj��
						break;
					}
					if(m.checkdifferent(passward, confirmpassward) == false){
						JOptionPane.showMessageDialog(null, "�⦸�K�X��J���@\n�бq�s��J" , "�I�\��", 1);
						operation1 = 1;
					}
				}while(operation1 == 1);//��^���s��g�|��
				if(Test == false){//����W���S�إߧ���ƫ���X�ӫo�ٹ���H�U�{��
					m.addMember (new Member(membername, passward)); 
					JOptionPane.showMessageDialog(null, "���ߥӽЧ���" , "�I�\��", 1);
					operation2 = 1;//�n�J�b��
				}
			}
			//�U���A��
			else if(fourthoperation == 1){
				do{//�I��X
					operation2 = JOptionPane.showOptionDialog(null, "�T�w���ӽ�?", "�I�\��", 1, 2, null, option5, null);
					operation4 = X(operation2);
				}while(operation4 == 1);
				if(operation2 == 0){
					c.create_single_food();//�إ��\�I�s��list
					c.create_set_food(); 
					do{//�I��X
						operation8 = JOptionPane.showOptionDialog(null, "�аݭn�ϥ��u�f����?", "�I�\��", 1, 0, null, option9, null);
						operation4 = X(operation8);
					}while(operation4 == 1);
					if(operation8 == 0){				
						do{
							do{
								couponnumber = JOptionPane.showInputDialog(null, "�п�J�u�f���X:");
								if(couponnumber == null){ couponnumber = "0";}//����Exception in thread "main" java.lang.NullPointerException
								if(Y(couponnumber) == true){
									Test = true;// ���X��J�u�f���o�Ӱj��
									operation2 = 1; //���X��ӿ�|�����j��							
									break;
								}
								else if(couponnumber.equals("")){
									JOptionPane.showMessageDialog(null, "�п�J!" , "�I�\��", 1);
								}
							}while(couponnumber.equals("0") || couponnumber.equals(""));
							Test6 = c.check_code(Integer.parseInt(couponnumber));
							if (Test6 == false){
								do{//�I��X
									operation8 = JOptionPane.showOptionDialog(null, "�V�|�S���o���u�f�X\n�п�ܬO�_���s��J?", "�I�\��", 1, 2, null, option12, null);
									operation4 = X(operation8);
								}while(operation4 == 1);
								if(operation8 == 1){
									operation2 = 1;
								}
							}
							else{
								operation2 = 1;
							}
						}while(operation2 != 1);				
					}
					JOptionPane.showMessageDialog(null, "�z�o���ʶR���F��:\n"+c.Finallist(Test6) , "�I�\��", 1);			
					do{//�I��X
						operation8 = JOptionPane.showOptionDialog(null, "�аݻݭn������Ӷ�?", "�I�\��", 1, 2, null, option9, null);
						operation4 = X(operation8);
					}while(operation4 == 1);
					if(operation8 == 0){c.create_transcation(Test6);}//�C�L����
					c.create_receipt(Test6);
					JOptionPane.showMessageDialog(null, "�w��A�ץ��{" , "�I�\��", 1);
					break;
				}
				else{operation2 = 1;}
			}
			//�w���|��
			else if(fourthoperation == 2){
				Test = false;// ���s�л\�@�� �H�K����W���v�T
				do{
					do{
						membername = JOptionPane.showInputDialog(null, "�|���W�r:");
						if(membername == null){ membername = "0";}//����Exception in thread "main" java.lang.NullPointerException
						if(Y(membername) == true){
							Test = true;// ���X�n�J�|���o�Ӱj��
							operation2 = 1; //���X��ӵn�J�|�����j��							
							break;
						}
						else if(membername.equals("")){
							JOptionPane.showMessageDialog(null, "�п�J!" , "�I�\��", 1);
						}
					}while(membername.equals("0") || membername.equals(""));
					
					if(Test == true){// ���X�n�J�|���o�Ӱj��
						break;
					}
					
 					do{
						passward =  JOptionPane.showInputDialog(null, "�K�X:");
						if(passward == null){ passward = "0";}//����Exception in thread "main" java.lang.NullPointerException
						if(Y(passward) == true){
							Test = true;// ���X�n�J�|���o�Ӱj��
							operation2 = 1; //���X��ӵn�J�|�����j��
							break;
						}
						else if(passward.equals("")){
							JOptionPane.showMessageDialog(null, "�п�J!" , "�I�\��", 1);
						}
							
					}while(passward.equals("0") || passward.equals(""));

					if(Test == true){// ���X�n�J�|���o�Ӱj��
						break;
					}
					if(Test == false){//�T�O��Ƴ�����F
						temp = m.searchMember(membername);//�M��s��
						if (temp != -1){
							if(m.check_password(temp, passward) == true){
								JOptionPane.showMessageDialog(null, "���ߵn�J����\n�٪�" + m.Detailofmembername(temp) + "�Ө쥻�\�U\n�z���Ѿl�I��:" + m.Detailofmemberpoint(temp)   , "�I�\��", 1);
								c.create_single_food();//�إ��\�I�s��list
								c.create_set_food();
								do{//�I��X
									operation8 = JOptionPane.showOptionDialog(null, "�аݭn�ϥ��I�ƧI���\�I��?", "�I�\��", 1, 1, null, option9, null);
									operation4 = X(operation8);
								}while(operation4 == 1);
								if(operation8 == 0){
									do{
										c.checkpoint(m.Detailofmemberpoint(temp));
										do{//�I��X
											operation8 = JOptionPane.showOptionDialog(null, "�п��", "�I�\��", 1, 1, null, c.giftoption(), null);
											operation4 = X(operation8);
										}while(operation4 == 1);	
										if(m.Detailofmemberpoint(temp) >= 1){
											int numberofpoint = c.add_gift(c.giftoption()[operation8], m.Detailofmemberpoint(temp));
											JOptionPane.showMessageDialog(null, "�����I���o!\n�Ѿl�I��"+ numberofpoint , "�I�\��", 1);
											c.giftoption.clear();
											m.setpoint(temp, numberofpoint);
											do{//�I��X
												operation8 = JOptionPane.showOptionDialog(null, "�а��٭n�~��I����?", "�I�\��", 1, 1, null, option9, null);
												operation4 = X(operation8);
											}while(operation4 == 1);
										}
										else{
											break;
										}
										if(operation8 == 1){break;}
									}while(true);
								}
								c.create_gift_food();
								do{//�I��X
									operation8 = JOptionPane.showOptionDialog(null, "�аݭn�ϥ��u�f����?", "�I�\��", 1, 1, null, option9, null);
									operation4 = X(operation8);
								}while(operation4 == 1);
								if(operation8 == 0){				
									do{
										do{
											couponnumber = JOptionPane.showInputDialog(null, "�п�J�u�f���X:");
											if(couponnumber == null){ couponnumber = "0";}//����Exception in thread "main" java.lang.NullPointerException
											if(Y(couponnumber) == true){
												Test = true;// ���X��J�u�f���o�Ӱj��
												operation2 = 1; //���X��ӿ�|�����j��							
												break;
											}
											else if(couponnumber.equals("")){
												JOptionPane.showMessageDialog(null, "�п�J!" , "�I�\��", 1);
											}
										}while(couponnumber.equals("0") || couponnumber.equals(""));
										Test6 = c.check_code(Integer.parseInt(couponnumber));
										if (Test6 == false){
											do{//�I��X
												operation8 = JOptionPane.showOptionDialog(null, "�V�|�S���o���u�f�X\n�п�ܬO�_���s��J?", "�I�\��", 1, 2, null, option12, null);
												operation4 = X(operation8);
											}while(operation4 == 1);
											if(operation8 == 1){
												operation2 = 1;
											}
										}
										else{
											operation2 = 1;
										}
									}while(operation2 != 1);				
								}
								JOptionPane.showMessageDialog(null, "�z�o���ʶR���F��:\n"+c.Finallist(Test6) , "�I�\��", 1);			
								do{//�I��X
									operation8 = JOptionPane.showOptionDialog(null, "�аݻݭn������Ӷ�?", "�I�\��", 1, 2, null, option9, null);
									operation4 = X(operation8);
								}while(operation4 == 1);
								if(operation8 == 0){c.create_transcation(Test6);}//�C�L����
								c.create_receipt(Test6);							
								JOptionPane.showMessageDialog(null, "�w��A�ץ��{" , "�I�\��", 1);
								Test3 = true;//�������|��������
								break;
							}
							else {
								JOptionPane.showMessageDialog(null, "�b���αK�X���~�Э��s��g!" , "�I�\��", 1);
								operation1 = 1;
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "�b���αK�X���~�Э��s��g!" , "�I�\��", 1);
							operation1 = 1;
						}	
					}
				}while(operation1 == 1);//��^���s�n�J�|��
				if(Test3 == true){
					break;
				}
			} 
		}while(operation2 == 1);//�� �U���A���H�ο�z�b���Եn�J �]�p �p�G�I���U���A�����ٷQ�n��|���i�զ^��	
	
	}//�I�\����
	}while(true);//���������I�\��
	
	
	
	
	
	
	
	
	

	

	
	
	
	
	}
	
	

	public static int X(int number){
		int operation3 = 0;
		String option5[] = {"�T�w", "��^"};
		if(number == -1){
			operation3 = JOptionPane.showOptionDialog(null, "�T�w�n���}?", "�I�\��", 1, 2, null, option5, null);
			if(operation3 == 0){
				JOptionPane.showMessageDialog(null, "�w��A�ץ��{" , "�I�\��", 1);
				System.exit(0);
			}
			else {
				return operation3;
			}
		}
		return -2;
	}

	public static boolean Y(String a){
		int operation3 = 0;
		String option5[] = {"�T�w", "��^"};
		if(a.equals("0")){
			operation3 = JOptionPane.showOptionDialog(null, "�T�w�n�������U��?\n", "�I�\��", 1, 2, null, option5, null);		
			if(operation3 == 0){
				return true;
			}
		}
		
		return false;
		
	}

	//public static void Z(){ continue;}
}