//edited by AiIng time:0610
package AllCustomer;
import java.util.Random;
import java.util.Arrays;

   public class MemberClub {
     	static Member member[]=new Member [99999999];
    	static int numOfMember = 0;
/* 	private static MemberClub instance;
	private MemberClub(){}
	public static MemberClub getInstance(){
		if(instance == null){
			instance = new MemberClub();
		}
		return instance;
	} */	
   	/*** ****************Functional method*******************************/

	public static void addMember ( Member m ) {
 
      		if ( numOfMember < member.length ) {
            	member [ numOfMember++ ] =m; 
            }
     	}

    public static int searchMember( String name ) {
    	int temp = -1;

       	for(int i=0;i<member.length;i++){
          	if( member[i]!=null ) { 
          		if( member[i].getName().equals(name) ) {
					temp=i; 
				}
			}
		}
		
	return temp;
  	}

 	public static boolean check_password( int temp ,String password ) {
		if(member[temp].getPassword().equals(password)) {
			member[temp].addPoint();
			return true;
		}
		return false;
	} 
	
	public static boolean checkdifferent(String password, String confirmpassword){
		if(password.equals(confirmpassword)){return true;}
		
		return false;
	}
	
	public static String Detailofmembername(int temp){
		return member[temp].getName();
	}
	public static int Detailofmemberpoint(int temp){
		return	member[temp].getPoint();
	}
	//½s¿èªÌ0617:¦¿ÂEÅï 
	public void setpoint(int temp, int point){
		member[temp].setpoint(point);
	}
}
   
