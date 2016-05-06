package ar.edu.unlp.bbdd2.heracles.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {
	
	private static SimpleDateFormat dateFormat;

	private static SimpleDateFormat getDateFormat() {
		if (dateFormat == null) {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		}
		return dateFormat;

	}

	public static Date formatDate(Date date) {
		String format = getDateFormat().format(date);
		try {
			return getDateFormat().parse(format);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	public static Date formatDate(String date) {
		try {
			return getDateFormat().parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	public static String formatDateToString(Date date) {
		return getDateFormat().format(date);		
	}

}