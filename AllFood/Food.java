//developed by�����Y time:0602
//�~��:����
//���ɮ׬��Ҧ����I���������O�A�����šA�Ҧ����~�ӥL�A�ҥH�n�s�W����@�P�����bFood�o�̧�A�p�G�O�n�b�ӧO���ؤ��s�W�Ш�ӧO���ؤ�
package AllFood;  //�ܦ�package
public abstract class Food{
	//�ܼƫŧi��
	protected int id;
	protected String name;
	protected int price;
	protected String category;
	protected Boolean breakfastBoolean;
	protected String breakfastString;
	//�غc�l
	public Food(String id,String name,String price,String category,String breakfast){
		setID(Integer.parseInt(id));
		setName(name);
		setPrice(Integer.parseInt(price));
		setCategory(category);
		/*********���set�k****************************************/
		setBreakfastBoolean(Boolean.parseBoolean(breakfast));
		setBreakfastString(breakfast);
	}
	//�A�Ȥ�k
	public void setID(int id){this.id = id;}
	public int getID(){return id;}
	public void setName(String name){this.name = name;}
	public String getName(){return name;}
	public void setPrice(int price){this.price = price;}
	public int getPrice(){return price;}
	public void setCategory(String category){this.category = category;}
	public String getCategory(){return category;}

	/***************Breakfast�����L���ɭԥi��n��Boolean���覡Ū��*******************************************************/

	public void setBreakfastBoolean(Boolean breakfastBoolean){ this.breakfastBoolean = breakfastBoolean; }
	public Boolean getBreakfastBoolean(){return breakfastBoolean;}

	/***************Breakfast�����L���ɭԥi��n��String���覡Ū���A�]���ڳo��ΤF���************************************/

	public void setBreakfastString(String breakfastString){ this.breakfastString = breakfastString; }
	public String getBreakfastString(){return breakfastString;}

	//�\���k
	//0610 �s���:���E�� 
	public String toString(){
		return "�~�� : "+getName()+"\t���� : "+getPrice()+"\n";
	}
	
}