package logic;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {
	
	public static  String getCurrencyTime()
	{
		SimpleDateFormat sdf = new SimpleDateFormat();
		Date date= new Date();
		return sdf.format(date);
	}
	
}
