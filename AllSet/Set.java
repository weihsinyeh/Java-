//developed by �����Y time:0603
//�~��:�M�\
//�Ҧ��M�\���~�ӥL�A�����šA�p�G���ݭn�ק�@�P�M�\�Цb���B�ק�A�ӧO�Ш�ӧO�h
package AllSet;
import AllFood.*;
public abstract class Set{
	//�ܼƫŧi��
	protected String id;
	protected String name;
	protected AllFood.Food meal1;
	protected AllFood.Food meal2;
	protected AllFood.Food meal3;
	protected int price;
	//�غc�l
	public Set(String id,String name,AllFood.Food meal1,AllFood.Food meal2,AllFood.Food meal3,String price){
		setID(id);
		setName(name);
		setMeal1(meal1);
		setMeal2(meal2);
		setMeal3(meal3);
		setPrice(Integer.parseInt(price));
	}
	//�A�Ȥ�k��
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
	//�\���k��
	public String toString(){
		return "\nID : "+getID()+"\n�~�� : "+getName()+"\n���\�@ : "+meal1+"\n���\�G : "+meal2+"\n���\�T : "+meal3+"\n���� : "+getPrice();
	}
}