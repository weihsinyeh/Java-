//developed by葉惟欣 time:0602
//品項:飲料
//所有飲料都繼承他 爸爸級
package AllFood;
public abstract class Drink extends Food{
	public Drink(String id,String name,String price,String category,String breakfast){
		super(id,name,price,category,breakfast);
	}
}