//developed by 葉惟欣 time:0603
//品項:套餐
//所有套餐都繼承他，爸爸級，如果有需要修改共同套餐請在此處修改，個別請到個別去
package AllSet;
import AllFood.*;
public abstract class Set{
	//變數宣告區
	protected String id;
	protected String name;
	protected AllFood.Food meal1;
	protected AllFood.Food meal2;
	protected AllFood.Food meal3;
	protected int price;
	//建構子
	public Set(String id,String name,AllFood.Food meal1,AllFood.Food meal2,AllFood.Food meal3,String price){
		setID(id);
		setName(name);
		setMeal1(meal1);
		setMeal2(meal2);
		setMeal3(meal3);
		setPrice(Integer.parseInt(price));
	}
	//服務方法區
	public void setID(String id){this.id = id;}
	public String getID(){return id;}
	public void setName(String name){this.name = name;}
	public String getName(){return name;}

	public void setMeal1(AllFood.Food meal1){this.meal1 = meal1;}
	public AllFood.Food getMeal1(){return meal1;}
	public void setMeal2(AllFood.Food meal2){this.meal2 = meal2;}
	public AllFood.Food getMeal2(){return meal2;}
	public void setMeal3(AllFood.Food meal3){this.meal3 = meal3;}
	public AllFood.Food getMeal3(){return meal3;}
	public void setPrice(int price){this.price = price;}
	public int getPrice(){return price;}
	//功能方法區
	public String toString(){
		return "\nID : "+getID()+"\n品項 : "+getName()+"\n副餐一 : "+meal1+"\n副餐二 : "+meal2+"\n副餐三 : "+meal3+"\n價格 : "+getPrice();
	}
}