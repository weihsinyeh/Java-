import javax.swing.*;
import AllFood.*;                 
import AllSet.*;
import AllFile.*;
import AllTime.*;
import AllCustomer.*;

public class JOP{
	public static void main(String args[]){
		//firstoperation 選擇進入 單點,套餐,早餐
		//secondoperation 選擇進入單點,套餐,早餐
		//thirdoperation 選擇是否加點
		//fourthoperation 選擇是否申辦會員
		int firstoperation, secondoperation, thirdoperation = 0, fourthoperation, fifthoperation, sixthoperation, seventhoperation = 0;
		int singlemealchoose = 0, setmealchoose, breakchoose, AnsFromMenu;
		int temp = 0 ;
		
		//operation 系列僅用來暫時存放答案值
		int operation1 = 0, operation2 = 0, operation3 = 0, operation4 = 0, operation5 = 1, operation6 = 0, operation7 = 0, operation8 = 0;
		String membername = "";
		String passward = "", confirmpassward = "";
		String couponnumber;
		
		boolean Test = false,Test2 = false, Test3 = false, Test4 = false, Test5 = false, Test6 = false;//4:是否使用會員
		String option1[] = {"菜單", "點餐"};
		String option2[] = {"單點", "套餐", "早餐"};
		String option3[] = {"加點", "刪除餐點", "換餐", "都不需要"};
		String option4[] = {"申請", "下次再說", "已有會員"};
		String option5[] = {"確定", "返回"};
		String option6[] = {"開始點餐吧!"};
		String option7[] = {"單點", "套餐", "早餐", "點完餐了!"};
		String option8[] = {"單點", "套餐"};
		String option9[] = {"要", "不需要"};
		String option10[] = {"單點", "套餐"};
		String option11[] = {"單點", "套餐", "點完餐了!"};
		String option12[] = {"重新輸入", "離開"};		
		String optionforsingle[] = {"薯條(小)", "薯條(中)", "薯條(大)", "可樂(小)", "雪碧(小)", "玉米濃湯", "大麥克", "麥香雞", "薯餅"};
		String optionforset[]= {"普通套餐", "薯餅套餐", "吃很飽套餐"};
		String optionforbreakfast[] = {"滿福堡"};
		String menu = "";
		

	
	do{
		AllCustomer.Customer c = new AllCustomer.Customer();
		//AllCustomer.MemberClub m = AllCustomer.MemberClub.getInstance();
		AllCustomer.MemberClub m = new AllCustomer.MemberClub();
		AllTime.Time t = new AllTime.Time();
		//firstoperation 選擇進入 菜單, 點餐

		operation3 = 3;//重新洗掉答案值防止重複
		c.singlelist.clear();
		c.setlist.clear();
		c.giftlist.clear();//防止重複使用時，內容未清除 
		c.read_file();

		do{//點擊X
			firstoperation = JOptionPane.showOptionDialog(null, "歡迎光臨", "點餐機", 1, 3, null, option1, option1[1]);
			operation4 = X(firstoperation);
		}while(operation4 == 1);
		
		if(firstoperation == 0){//呈現菜單
			AllFile.File single = new AllFile.File("Single_meal.csv",10,5);
			String item1 [][] = single.create_file();
			AllFile.File set = new AllFile.File("Set_meal.csv",3,6);
			String item2 [][] = set.create_file();
			menu ="";
			menu += single.create_menu();
			menu += set.create_menu_detail();
			do{//點擊X
			
				AnsFromMenu = JOptionPane.showOptionDialog(null, menu, "點餐機", 1, 3, null, option6, null);
				operation4 = X(AnsFromMenu);
				if(AnsFromMenu == 0){
					firstoperation = 1;
				}
			}while(operation4 == 1);
		}
		
		//點餐
		if(firstoperation == 1){
			do{
				if((t.read_hour() < 10) || ((t.read_hour() == 10) && (t.read_min() < 30))){//10:30以前 = 有早餐
					do{//點擊X
					//secondoperation 選擇進入單點,套餐,早餐
					secondoperation = JOptionPane.showOptionDialog(null, "請選擇", "點餐機", 1, 3, null, option2, null);
					operation4 = X(secondoperation);
					}while(operation4 == 1);
				}
				else{//10:30後沒早餐
					do{//點擊X
						//secondoperation 選擇進入單點,套餐
						secondoperation = JOptionPane.showOptionDialog(null, "請選擇", "點餐機", 1, 3, null, option10, null);
						operation4 = X(secondoperation);
					}while(operation4 == 1);
				}
				do{
					//單點
					if(secondoperation == 0){
						do{//點擊X
							singlemealchoose = JOptionPane.showOptionDialog(null, "請問需要哪一項餐點呢?", "點餐機", 1, 3, null, optionforsingle, null);
							operation4 = X(singlemealchoose);
							if(singlemealchoose != -1){//防止傳入值為-1
								c.add_single_food(optionforsingle[singlemealchoose]);
							}
						}while(operation4 == 1);
					}
					
					//套餐
					else if(secondoperation == 1){
						do{//點擊X
							setmealchoose = JOptionPane.showOptionDialog(null, "請問需要哪一項套餐呢?", "點餐機", 1, 3, null, optionforset, null);
							operation4 = X(setmealchoose);
							if(setmealchoose != -1){
								c.add_set_food(optionforset[setmealchoose]);
							}						
						}while(operation4 == 1);
					}
					
					//早餐
					else if(secondoperation == 2){
						do{//點擊X
							breakchoose = JOptionPane.showOptionDialog(null, "請問需要哪一項早餐呢?", "點餐機", 1, 3, null, optionforbreakfast, null);
							operation4 = X(breakchoose);//建立餐點存錄list
							if(singlemealchoose != -1){//防止傳入值為-1
								c.add_single_food(optionforsingle[singlemealchoose]);
							}						
						}while(operation4 == 1);
					}
					if((t.read_hour() < 10) || ((t.read_hour() == 10) && (t.read_min() < 30))){//10:30以前 = 有早餐
						do{//點擊X
							//sixthoperation 選擇進入單點,套餐,早餐, 點完餐了!
							sixthoperation = JOptionPane.showOptionDialog(null, "請選擇", "點餐機", 1, 3, null, option7, null);
							operation4 = X(sixthoperation);
						}while(operation4 == 1);
					}
					else{
						do{//點擊X
							//sixthoperation 選擇進入單點,套餐 ,點完餐了!
							sixthoperation = JOptionPane.showOptionDialog(null, "請選擇", "點餐機", 1, 3, null, option11, null);
							operation4 = X(sixthoperation);
							if(sixthoperation == 2){sixthoperation++;}//因為原本"點完餐了"在第四個
						}while(operation4 == 1);
					}
					if(sixthoperation == 3){//點完餐了!
						break;
					}
					else{//為點完餐重複進行點餐
						secondoperation = sixthoperation;
					}					
				}while(true);	
				do{	
					do{//點擊X
						//thirdoperation 選擇是否需要執行以下選項
						thirdoperation = JOptionPane.showOptionDialog(null, "是否需要執行以下選項呢?", "點餐機", 1, 3, null, option3, null);
						operation4 = X(thirdoperation);	
					}while(operation4 == 1);
					if(thirdoperation == 1){//刪除餐點
						do{
							do{//點擊X
								//operation6 選擇刪除單點或套餐
								operation6 = JOptionPane.showOptionDialog(null, "請問要刪除哪種餐點呢?", "點餐機", 1, 3, null, option8, null);
								operation4 = X(operation6);	
							}while(operation4 == 1);
							
							if(operation6 == 0){
								do{//點擊X
									if(c.singlelist.size() == 0){
										JOptionPane.showMessageDialog(null, "糟糕裡面沒有東西\n快去點餐吧!" , "點餐機", 1);
										break;
									}
									do{//點擊X
										//seventhoperation 選擇刪除哪個餐點(單點)
										seventhoperation = JOptionPane.showOptionDialog(null, "請問要刪除哪個餐點呢?", "點餐機", 1, 3, null, c.singlelist(), null);
										operation4 = X(firstoperation);
									}while(operation4 == 1);
									c.delete_single_food(c.singlelist()[seventhoperation]);
									operation4 = X(seventhoperation);	
								}while(operation4 == 1);
							}
							else if(operation6 == 1){
								do{//點擊X
									if(c.setlist.size() == 0){
										JOptionPane.showMessageDialog(null, "糟糕裡面沒有東西\n快去點餐吧!" , "點餐機", 1);
										break;
									}								
									//seventhoperation 選擇刪除哪個餐點(套餐)
									do{//點擊X
										seventhoperation = JOptionPane.showOptionDialog(null, "請問要刪除哪個餐點呢?", "點餐機", 1, 3, null, c.setlist(), null);
										operation4 = X(firstoperation);
									}while(operation4 == 1);									
									c.delete_set_food(c.setlist()[seventhoperation]);
									operation4 = X(seventhoperation);	
								}while(operation4 == 1);
							}
							do{//點擊X
								//operation7 詢問是否要繼續刪除餐點
								operation7 = JOptionPane.showOptionDialog(null, "請問還需要繼續刪除餐點嗎?", "點餐機", 1, 3, null, option9, null);
								operation4 = X(operation7);	
							}while(operation4 == 1);
							if(operation7 == 1){//跳出刪除餐點的迴圈
								Test5 = true;
								break;
							}
							else{
								Test5 = false;
							}
						}while(true);
					}
					else if(thirdoperation == 2){//換餐點
						do{
							do{//點擊X
								//operation6 選擇換單點或套餐
								operation6 = JOptionPane.showOptionDialog(null, "請問要換哪種餐點呢?", "點餐機", 1, 3, null, option8, null);
								operation4 = X(operation6);	
							}while(operation4 == 1);
							
							if(operation6 == 0){
								do{//點擊X
									if(c.singlelist.size() == 0){
										JOptionPane.showMessageDialog(null, "糟糕裡面沒有東西\n快去點餐吧!" , "點餐機", 1);
										break;
									}										
									//seventhoperation 選擇取代的餐點(單點)
									seventhoperation = JOptionPane.showOptionDialog(null, "請選擇要取代餐點?", "點餐機", 1, 3, null, c.singlelist(), null);
									operation4 = X(seventhoperation);	
								}while(operation4 == 1);
								
								do{//點擊X  選擇換哪個餐點(單點)
									singlemealchoose = JOptionPane.showOptionDialog(null, "請選擇要換的餐點?", "點餐機", 1, 3, null, optionforsingle, null);
									operation4 = X(singlemealchoose);
								}while(operation4 == 1);
								c.change_single_food( c.singlelist()[seventhoperation], optionforsingle[singlemealchoose]);
							}
							else if(operation6 == 1){
								do{//點擊X
									if(c.setlist.size() == 0){
										JOptionPane.showMessageDialog(null, "糟糕裡面沒有東西\n快去點餐吧!" , "點餐機", 1);
										break;
									}								
									//seventhoperation 選擇取代的餐點(套餐)
									seventhoperation = JOptionPane.showOptionDialog(null, "請問要刪除哪個餐點呢?", "點餐機", 1, 3, null, c.setlist(), null);
									operation4 = X(seventhoperation);	
								}while(operation4 == 1);
								
								do{//點擊X  選擇換哪個餐點(套餐)
									setmealchoose = JOptionPane.showOptionDialog(null, "請選擇要換的餐點?", "點餐機", 1, 3, null, optionforset, null);
									operation4 = X(setmealchoose);
								}while(operation4 == 1);
								c.change_set_food(c.setlist()[seventhoperation], optionforset[setmealchoose]);							
							}
							do{//點擊X
								//operation7 詢問是否要繼續換餐點
								operation7 = JOptionPane.showOptionDialog(null, "請問還需要繼續換餐點嗎?", "點餐機", 1, 3, null, option9, null);
								operation4 = X(operation7);	
							}while(operation4 == 1);
							if(operation7 == 1){//跳出換餐點的迴圈
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
			}while(thirdoperation == 0);//加點
		do{
			do{//點擊X
				//fourthoperation 選擇是否申辦會員
				fourthoperation = JOptionPane.showOptionDialog(null, "是否要申辦會員?", "點餐機", 1, 3, null, option4, null);
				operation4 = X(fourthoperation);	
			}while(operation4 == 1);		
			//申請會員
			if(fourthoperation == 0 ){
				do{	
					Test = false;//防止迴圈卡住
					operation1 = 3;//防止點到X後跳不出迴圈
					do{
						membername = JOptionPane.showInputDialog(null, "會員名字:");
						if(membername == null){ membername = "0";}//防止Exception in thread "main" java.lang.NullPointerException
						if(Y(membername) == true){
							Test = true;// 跳出申請會員這個迴圈
							operation2 = 1; //跳出整個辦會員的迴圈							
							break;
						}
						else if(membername.equals("")){
							JOptionPane.showMessageDialog(null, "請輸入!" , "點餐機", 1);
						}
					}while(membername.equals("0") || membername.equals(""));
					
					if(Test == true){// 跳出申請會員這個迴圈
						break;
					}
					
 					do{
						passward =  JOptionPane.showInputDialog(null, "密碼:");
						if(passward == null){ passward = "0";}//防止Exception in thread "main" java.lang.NullPointerException
						if(Y(passward) == true){
							Test = true;// 跳出申請會員這個迴圈
							operation2 = 1; //跳出整個辦會員的迴圈
							break;
						}
						else if(membername.equals("")){
							JOptionPane.showMessageDialog(null, "請輸入!" , "點餐機", 1);
						}
							
					}while(passward.equals("0") || passward.equals(""));

					if(Test == true){// 跳出申請會員這個迴圈
						break;
					}
					
					do{	
						confirmpassward =  JOptionPane.showInputDialog(null, "確認密碼:");
						if(confirmpassward == null){ confirmpassward = "0";}//防止Exception in thread "main" java.lang.NullPointerException
						if(Y(confirmpassward) == true){
							Test = true;// 跳出申請會員這個迴圈
							operation2 = 1; //跳出整個辦會員的迴圈
							break;
						}
						else if(membername.equals("")){
							JOptionPane.showMessageDialog(null, "請輸入!" , "點餐機", 1);
						}
					}while(confirmpassward.equals("0") || confirmpassward.equals(""));
					
					if(Test == true){// 跳出申請會員這個迴圈
						break;
					}
					if(m.checkdifferent(passward, confirmpassward) == false){
						JOptionPane.showMessageDialog(null, "兩次密碼輸入不一\n請從新輸入" , "點餐機", 1);
						operation1 = 1;
					}
				}while(operation1 == 1);//返回重新填寫會員
				if(Test == false){//防止上面沒建立完資料後跳出來卻還實質行以下程式
					m.addMember (new Member(membername, passward)); 
					JOptionPane.showMessageDialog(null, "恭喜申請完成" , "點餐機", 1);
					operation2 = 1;//登入帳號
				}
			}
			//下次再說
			else if(fourthoperation == 1){
				do{//點擊X
					operation2 = JOptionPane.showOptionDialog(null, "確定不申請?", "點餐機", 1, 2, null, option5, null);
					operation4 = X(operation2);
				}while(operation4 == 1);
				if(operation2 == 0){
					c.create_single_food();//建立餐點存錄list
					c.create_set_food(); 
					do{//點擊X
						operation8 = JOptionPane.showOptionDialog(null, "請問要使用優惠卷嗎?", "點餐機", 1, 0, null, option9, null);
						operation4 = X(operation8);
					}while(operation4 == 1);
					if(operation8 == 0){				
						do{
							do{
								couponnumber = JOptionPane.showInputDialog(null, "請輸入優惠卷碼:");
								if(couponnumber == null){ couponnumber = "0";}//防止Exception in thread "main" java.lang.NullPointerException
								if(Y(couponnumber) == true){
									Test = true;// 跳出輸入優惠卷這個迴圈
									operation2 = 1; //跳出整個辦會員的迴圈							
									break;
								}
								else if(couponnumber.equals("")){
									JOptionPane.showMessageDialog(null, "請輸入!" , "點餐機", 1);
								}
							}while(couponnumber.equals("0") || couponnumber.equals(""));
							Test6 = c.check_code(Integer.parseInt(couponnumber));
							if (Test6 == false){
								do{//點擊X
									operation8 = JOptionPane.showOptionDialog(null, "糟糕沒有這個優惠碼\n請選擇是否重新輸入?", "點餐機", 1, 2, null, option12, null);
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
					JOptionPane.showMessageDialog(null, "您這次購買的東西:\n"+c.Finallist(Test6) , "點餐機", 1);			
					do{//點擊X
						operation8 = JOptionPane.showOptionDialog(null, "請問需要交易明細嗎?", "點餐機", 1, 2, null, option9, null);
						operation4 = X(operation8);
					}while(operation4 == 1);
					if(operation8 == 0){c.create_transcation(Test6);}//列印明細
					c.create_receipt(Test6);
					JOptionPane.showMessageDialog(null, "歡迎再度光臨" , "點餐機", 1);
					break;
				}
				else{operation2 = 1;}
			}
			//已有會員
			else if(fourthoperation == 2){
				Test = false;// 重新覆蓋一次 以免受到上面影響
				do{
					do{
						membername = JOptionPane.showInputDialog(null, "會員名字:");
						if(membername == null){ membername = "0";}//防止Exception in thread "main" java.lang.NullPointerException
						if(Y(membername) == true){
							Test = true;// 跳出登入會員這個迴圈
							operation2 = 1; //跳出整個登入會員的迴圈							
							break;
						}
						else if(membername.equals("")){
							JOptionPane.showMessageDialog(null, "請輸入!" , "點餐機", 1);
						}
					}while(membername.equals("0") || membername.equals(""));
					
					if(Test == true){// 跳出登入會員這個迴圈
						break;
					}
					
 					do{
						passward =  JOptionPane.showInputDialog(null, "密碼:");
						if(passward == null){ passward = "0";}//防止Exception in thread "main" java.lang.NullPointerException
						if(Y(passward) == true){
							Test = true;// 跳出登入會員這個迴圈
							operation2 = 1; //跳出整個登入會員的迴圈
							break;
						}
						else if(passward.equals("")){
							JOptionPane.showMessageDialog(null, "請輸入!" , "點餐機", 1);
						}
							
					}while(passward.equals("0") || passward.equals(""));

					if(Test == true){// 跳出登入會員這個迴圈
						break;
					}
					if(Test == false){//確保資料都有填了
						temp = m.searchMember(membername);//尋找編號
						if (temp != -1){
							if(m.check_password(temp, passward) == true){
								JOptionPane.showMessageDialog(null, "恭喜登入完成\n還迎" + m.Detailofmembername(temp) + "來到本餐廳\n您的剩餘點數:" + m.Detailofmemberpoint(temp)   , "點餐機", 1);
								c.create_single_food();//建立餐點存錄list
								c.create_set_food();
								do{//點擊X
									operation8 = JOptionPane.showOptionDialog(null, "請問要使用點數兌換餐點嗎?", "點餐機", 1, 1, null, option9, null);
									operation4 = X(operation8);
								}while(operation4 == 1);
								if(operation8 == 0){
									do{
										c.checkpoint(m.Detailofmemberpoint(temp));
										do{//點擊X
											operation8 = JOptionPane.showOptionDialog(null, "請選擇", "點餐機", 1, 1, null, c.giftoption(), null);
											operation4 = X(operation8);
										}while(operation4 == 1);	
										if(m.Detailofmemberpoint(temp) >= 1){
											int numberofpoint = c.add_gift(c.giftoption()[operation8], m.Detailofmemberpoint(temp));
											JOptionPane.showMessageDialog(null, "完成兌換囉!\n剩餘點數"+ numberofpoint , "點餐機", 1);
											c.giftoption.clear();
											m.setpoint(temp, numberofpoint);
											do{//點擊X
												operation8 = JOptionPane.showOptionDialog(null, "請問還要繼續兌換嗎?", "點餐機", 1, 1, null, option9, null);
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
								do{//點擊X
									operation8 = JOptionPane.showOptionDialog(null, "請問要使用優惠卷嗎?", "點餐機", 1, 1, null, option9, null);
									operation4 = X(operation8);
								}while(operation4 == 1);
								if(operation8 == 0){				
									do{
										do{
											couponnumber = JOptionPane.showInputDialog(null, "請輸入優惠卷碼:");
											if(couponnumber == null){ couponnumber = "0";}//防止Exception in thread "main" java.lang.NullPointerException
											if(Y(couponnumber) == true){
												Test = true;// 跳出輸入優惠卷這個迴圈
												operation2 = 1; //跳出整個辦會員的迴圈							
												break;
											}
											else if(couponnumber.equals("")){
												JOptionPane.showMessageDialog(null, "請輸入!" , "點餐機", 1);
											}
										}while(couponnumber.equals("0") || couponnumber.equals(""));
										Test6 = c.check_code(Integer.parseInt(couponnumber));
										if (Test6 == false){
											do{//點擊X
												operation8 = JOptionPane.showOptionDialog(null, "糟糕沒有這個優惠碼\n請選擇是否重新輸入?", "點餐機", 1, 2, null, option12, null);
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
								JOptionPane.showMessageDialog(null, "您這次購買的東西:\n"+c.Finallist(Test6) , "點餐機", 1);			
								do{//點擊X
									operation8 = JOptionPane.showOptionDialog(null, "請問需要交易明細嗎?", "點餐機", 1, 2, null, option9, null);
									operation4 = X(operation8);
								}while(operation4 == 1);
								if(operation8 == 0){c.create_transcation(Test6);}//列印明細
								c.create_receipt(Test6);							
								JOptionPane.showMessageDialog(null, "歡迎再度光臨" , "點餐機", 1);
								Test3 = true;//結束整格會員的部分
								break;
							}
							else {
								JOptionPane.showMessageDialog(null, "帳號或密碼有誤請重新填寫!" , "點餐機", 1);
								operation1 = 1;
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "帳號或密碼有誤請重新填寫!" , "點餐機", 1);
							operation1 = 1;
						}	
					}
				}while(operation1 == 1);//返回重新登入會員
				if(Test3 == true){
					break;
				}
			} 
		}while(operation2 == 1);//為 下次再說以及辦理帳號候登入 設計 如果點擊下次再說但還想要辦會員可調回選	
	
	}//點餐結束
	}while(true);//不停重複點餐機
	
	
	
	
	
	
	
	
	

	

	
	
	
	
	}
	
	

	public static int X(int number){
		int operation3 = 0;
		String option5[] = {"確定", "返回"};
		if(number == -1){
			operation3 = JOptionPane.showOptionDialog(null, "確定要離開?", "點餐機", 1, 2, null, option5, null);
			if(operation3 == 0){
				JOptionPane.showMessageDialog(null, "歡迎再度光臨" , "點餐機", 1);
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
		String option5[] = {"確定", "返回"};
		if(a.equals("0")){
			operation3 = JOptionPane.showOptionDialog(null, "確定要取消註冊嗎?\n", "點餐機", 1, 2, null, option5, null);		
			if(operation3 == 0){
				return true;
			}
		}
		
		return false;
		
	}

	//public static void Z(){ continue;}
}