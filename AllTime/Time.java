//developed by �����Y time:0613
package AllTime;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

public class Time{
	static SimpleDateFormat datetime;
	static Calendar calendar;
	static Date date;
	
	static int hour;
	static int min;

	public void create_time(){
	
		datetime = new SimpleDateFormat("�{�b�ɶ���: yyy/MM/dd hh:mm:ss");// hh:mm:ss
		date = new Date();
		String strdate = datetime.format(date);
		//System.out.println(strdate);
		
		/*********************Ū���p�ɥ�**************************/
		Calendar calendar = Calendar.getInstance();            
		hour = calendar.get(Calendar.HOUR_OF_DAY);
		min = calendar.get(Calendar.MINUTE);
	}
	public int read_hour(){
		create_time();
		return hour;
	}
	public int read_min(){
		create_time();
		return min;
	}
}