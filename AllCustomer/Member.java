package AllCustomer;
public class Member {  //�|��
	private String name;
	private String password;
	private static int point = 0;

	public Member(String name,String password) {
		setName(name);
		setPassword(password);
	}

	//Service methods
	public void setName(String name) { this.name=name; }
	public String getName() { return name; }

	public void  setPassword(String password) { this.password=password; }
	public String getPassword() { return password; }

	//�s���0617:���E�� 
	public void  setpoint(int point) { this.point = point; }
	public int getPoint() { return point; }
	
	public void addPoint() { this.point++; }

    
  }