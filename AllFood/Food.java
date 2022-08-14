//developed by葉惟欣 time:0602
//品項:食物
//此檔案為所有單點的祖先類別，阿公級，所有都繼承他，所以要新增任何共同項都在Food這裡改，如果是要在個別項目中新增請到個別項目中
package AllFood;  //變成package
public abstract class Food{
	//變數宣告區
	protected int id;
	protected String name;
	protected int price;
	protected String category;
	protected Boolean breakfastBoolean;
	protected String breakfastString;
	//建構子
	public Food(String id,String name,String price,String category,String breakfast){
		setID(Integer.parseInt(id));
		setName(name);
		setPrice(Integer.parseInt(price));
		setCategory(category);
		/*********兩種set法****************************************/
		setBreakfastBoolean(Boolean.parseBoolean(breakfast));
		setBreakfastString(breakfast);
	}
	//服務方法
	public void setID(int id){this.id = id;}
	public int getID(){return id;}
	public void setName(String name){this.name = name;}
	public String getName(){return name;}
	public void setPrice(int price){this.price = price;}
	public int getPrice(){return price;}
	public void setCategory(String category){this.category = category;}
	public String getCategory(){return category;}

	/***************Breakfast的有無有時候可能要用Boolean的方式讀取*******************************************************/

	public void setBreakfastBoolean(Boolean breakfastBoolean){ this.breakfastBoolean = breakfastBoolean; }
	public Boolean getBreakfastBoolean(){return breakfastBoolean;}

	/***************Breakfast的有無有時候可能要用String的方式讀取，因此我這邊用了兩個************************************/

	public void setBreakfastString(String breakfastString){ this.breakfastString = breakfastString; }
	public String getBreakfastString(){return breakfastString;}

	//功能方法
	//0610 編輯者:江鴻麟 
	public String toString(){
		return "品項 : "+getName()+"\t價格 : "+getPrice()+"\n";
	}
	
}