/*******************************************************************************
 * Copyright (c) 2013 INTERAMERICAN PROPERTY AND CASUALTY INSURANCE COMPANY S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/copyleft/lesser.html
 * 
 * This library is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * See the GNU Lesser General Public License for more details.
 ******************************************************************************/
package gr.interamerican.bo2.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Utilities for dates
 * 
 * 
 */
public class DateUtils {
	
	
	/**
	 * Date format YYYYMMDD.
	 */
	private static DateFormat dfYYYYMMDD = new SimpleDateFormat("yyyyMMdd"); //$NON-NLS-1$
	
	
	
	/**
	 * Calendar constant 1/1/0001
	 */
    private static Calendar ZERO_CAL = zero();
    
    /**
     * date constant 1/1/0001
     */
    private static Date ZERO_DT = ZERO_CAL.getTime();


    /**
	 * Hidden constructor.
	 * 
	 * This is a utility class having only static methods.
	 * There is no need to create any instance of this class.
	 */
    private DateUtils() {
    	/* empty */
    }

    /**
     * default empty date with value 1/1/0001.
     * 
     * @return date 1/1/0001
     */
    private static Calendar zero() {
    	Calendar cal=new GregorianCalendar();
    	cal.set(Calendar.YEAR, 1);
    	cal.set(Calendar.MONTH,Calendar.JANUARY);
    	cal.set(Calendar.DATE, 1);
    	removeTime(cal);
    	return cal;
    }
    
    /**
     * Gets the first date after Christ, 1 January of Year 1 (0001-01-01).
     * 
     * @return Returns a date with value 0001-01-01.
     */
    public static Date getDay1AD() {
    	return new Date(ZERO_DT.getTime());
    }
    
    /**
     * Gets the first day of the specified month.
     * 
     * @param year year
     * @param month month. Use {@link Calendar} constants for months.
     *       
     * @return Returns the first day of month.
     */
    public static Date getMonth1st(int year, int month) {
    	return getDate(year,month,1);
    	
    }
    
    /**
     * Gets a date.
     * 
     * @param year year
     * @param month month. Use {@link Calendar} constants for months.
     * @param day day
     *       
     * @return Returns the first day of month.
     */
    public static Date getDate(int year, int month, int day) {
    	Calendar cal=new GregorianCalendar();
    	cal.set(Calendar.YEAR, year);
    	cal.set(Calendar.MONTH,month);
    	cal.set(Calendar.DATE,day);
    	removeTime(cal);
    	return new Date(cal.getTimeInMillis());
    }
    
    
    /**
     * removes time from a calendar.
     * 
     * The calendar remains with the same date and time set to 00:00:00.000
     * 
     * @param cal calendar object who's time is removed.
     */
    public static void removeTime(Calendar cal) {
    	cal.set(Calendar.HOUR_OF_DAY, 0);
    	cal.set(Calendar.MINUTE, 0);
    	cal.set(Calendar.SECOND, 0);
    	cal.set(Calendar.MILLISECOND, 0);
    }
    
    /**
     * removes time from a date.
     * 
     * The date remains with the same date and time set to 00:00:00.000
     * 
     * @param date Date who's time is removed.
     */
    public static void removeTime(Date date) {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	removeTime(cal);
    	date.setTime(cal.getTime().getTime());
    }

    /**
     * Shows if a date equals 1/1/0001.
     * 
     * @param dt date.
     * @return returns true is date equals 1/1/0001.
     */
    public static boolean isZero(Date dt) {
        return ZERO_DT.equals(dt);
    }
    
    /**
     * Shows if a calendar equals 1/1/0001.
     * 
     * @param cal calendar.
     * @return returns true is date equals 1/1/0001.
     */
    public static boolean isZero(Calendar cal) {
        return ZERO_CAL.equals(cal);
    }

    /**
     * Gets the time passed between two dates.
     * 
     * The time is counted in days, months or years,
     * according to the parameter <code>type</code> specified.
     * The first parameter <code>fromDate</code> must be less
     * or equal than the second parameter <code>toDate</code>.
     * Otherwise the method will return 0.
     *  
     * @param fromDate
     *            
     * @param toDate
     *            
     * @param type 
     *        Rime integral of the result period of time. Valid values
     *        are <code>Calendar.DATE, Calendar.YEAR, Calendar.MONTH</code>
     *            
     * @return Returns the time passed from <code>fromDate</code> 
     *         until <code>toDate</code> 
     *        
     */
    private static int DateDif(Calendar fromDate, Calendar toDate, int type) {        	
    	Calendar from = new GregorianCalendar();
        from.setTime(fromDate.getTime());

        Calendar to = new GregorianCalendar();
        to.setTime(toDate.getTime());
        
        int count = 0;
        from.add(type, 1);        
        
        while (from.before(to) || from.equals(to)) {       	
            count++;
            from.add(type, 1);
        }
        return count;
    }

    /**
     * Gets the time passed between two dates in days.
     * 
     * The time is counted in days, months or years,
     * according to the parameter <code>type</code> specified.
     * The first parameter <code>fromDate</code> must be less
     * or equal than the second parameter <code>toDate</code>.
     * Otherwise the method will return 0.
     *  
     * @param fromDate
     *            
     * @param toDate
     *            
     *            
     * @return Returns the time passed from <code>fromDate</code> 
     *         until <code>toDate</code> 
     *        
     */
    public static int difDays(Calendar fromDate, Calendar toDate) {
        return DateDif(fromDate, toDate, Calendar.DATE);
    }

    /**
     * Gets the time passed between two dates in years.
     * 
     * The time is counted in days, months or years,
     * according to the parameter <code>type</code> specified.
     * The first parameter <code>fromDate</code> must be less
     * or equal than the second parameter <code>toDate</code>.
     * Otherwise the method will return 0.
     *  
     * @param fromDate
     *            
     * @param toDate
     *            
     *            
     * @return Returns the time passed from <code>fromDate</code> 
     *         until <code>toDate</code> 
     *        
     */
    public static int difYears(Calendar fromDate, Calendar toDate) {
        return DateDif(fromDate, toDate, Calendar.YEAR);
    }

    /**
     * Gets the time passed between two dates in months.
     * 
     * The time is counted in days, months or years,
     * according to the parameter <code>type</code> specified.
     * The first parameter <code>fromDate</code> must be less
     * or equal than the second parameter <code>toDate</code>.
     * Otherwise the method will return 0.
     *  
     * @param fromDate
     *            
     * @param toDate
     *            
     *            
     * @return Returns the time passed from <code>fromDate</code> 
     *         until <code>toDate</code> 
     *        
     */
    public static int difMonths(Calendar fromDate, Calendar toDate) {
        return DateDif(fromDate, toDate, Calendar.MONTH);
    }    
    
    /**
     * Gets the time passed between two dates in days.
     * 
     * The time is counted in days, months or years,
     * according to the parameter <code>type</code> specified.
     * The first parameter <code>fromDate</code> must be less
     * or equal than the second parameter <code>toDate</code>.
     * Otherwise the method will return 0.
     *  
     * @param fromDate
     *            
     * @param toDate
     *            
     *            
     * @return Returns the time passed from <code>fromDate</code> 
     *         until <code>toDate</code> 
     *        
     */
    public static int difDays(Date fromDate, Date toDate) {
    	GregorianCalendar from=new GregorianCalendar();
    	from.setTime(fromDate);
    	GregorianCalendar to=new GregorianCalendar();
    	to.setTime(toDate);
    	
        return DateDif(from, to, Calendar.DATE);
    }  

    /**
     * Gets the time passed between two dates in years.
     * 
     * The time is counted in days, months or years,
     * according to the parameter <code>type</code> specified.
     * The first parameter <code>fromDate</code> must be less
     * or equal than the second parameter <code>toDate</code>.
     * Otherwise the method will return 0.
     *  
     * @param fromDate
     *            
     * @param toDate
     *            
     *            
     * @return Returns the time passed from <code>fromDate</code> 
     *         until <code>toDate</code> 
     *        
     */
    public static int difYears(Date fromDate, Date toDate) {
    	GregorianCalendar from=new GregorianCalendar();
    	from.setTime(fromDate);
    	GregorianCalendar to=new GregorianCalendar();
    	to.setTime(toDate);
    	
        return DateDif(from, to, Calendar.YEAR);
    }

    /**
     * Gets the time passed between two dates in months.
     * 
     * The time is counted in days, months or years,
     * according to the parameter <code>type</code> specified.
     * The first parameter <code>fromDate</code> must be less
     * or equal than the second parameter <code>toDate</code>.
     * Otherwise the method will return 0.
     *  
     * @param fromDate
     *            
     * @param toDate
     *            
     *            
     * @return Returns the time passed from <code>fromDate</code> 
     *         until <code>toDate</code> 
     *        
     */
    public static int difMonths(Date fromDate, Date toDate) {
    	GregorianCalendar from=new GregorianCalendar();
    	from.setTime(fromDate);
    	GregorianCalendar to=new GregorianCalendar();
    	to.setTime(toDate);
    	
        return DateDif(from, to, Calendar.MONTH);
    }   

    /**
     * Adds days months or years to a date.
     * 
     * @param datein input date
     * @param type
     *            (Calendar.DATE,Calendar.MONTH,Calendar.YEAR)
     * @param amount days, months or years that will be added to datein. Can be negative.
     * @return returns the result
     */
    private static Date add(Date datein, int type, int amount) {
        GregorianCalendar result = new GregorianCalendar();
        result.setTime(datein);
        result.add(type, amount);
        return new Date(result.getTimeInMillis());        
    }
    
    /**
     * Adds days to a date.
     * 
     * The parameter <code>days</code> can be negative. In this case 
     * the result will be a date before the input parameter 
     * <code>datein</code>.
     * 
     * @param datein
     * @param days
     * @return result date
     */
    public static Date addDays(Date datein, int days) {
        return add(datein, Calendar.DATE, days);
    }
    
    /**
     * Adds months to a date.
     * 
     * The parameter <code>months</code> can be negative. In this case 
     * the result will be a date before the input parameter 
     * <code>datein</code>.
     * 
     * @param datein
     * @param months
     * @return result date 
     */
    public static Date addMonths(Date datein, int months) {
        return add(datein, Calendar.MONTH, months);
    }

    /**
     * Adds years to a date.
     * 
     * The parameter <code>years</code> can be negative. In this case 
     * the result will be a date before the input parameter 
     * <code>datein</code>.
     * 
     * @param datein
     * @param years
     * @return result date as string
     */
    public static Date addYears(Date datein, int years) {
        return add(datein, Calendar.YEAR, years);
    }
    
    /**
     * Gets a date from the parameter dt.
     * 
     * If dt is a date type (Calendar, Timestamp etc) it will be 
     * converted to a Date object. Otherwise the result of the 
     * objects <code>toString()</code> will be converted to date
     * according to the system locale. 
     * 
     * @param dt object representing a date.
     * 
     * @return Returns a date for this object.
     * 
     * @throws ParseException
     */
    public static Date getDate(Object dt) 
    throws ParseException {
    	if (dt == null) {
    		return null;
    	}
    	if (dt instanceof Date) {
    		return (Date) dt; 
    	}
    	if (dt instanceof Calendar) {
    		Calendar cal = (Calendar) dt;
    		return new Date(cal.getTimeInMillis());
    	}
    	String str = dt.toString().trim();
    	try {        	
       		return Bo2UtilsEnvironment.SINGLETON.getDfShort().parse(str);
    	} catch (ParseException pe) {    		
    		/* try once more with the long date format */
    		try {
    			return Bo2UtilsEnvironment.SINGLETON.getDfLong().parse(str);
    		} catch (ParseException pe2) {    
    			/* Now try one last time with the iso date format */
    			return Bo2UtilsEnvironment.SINGLETON.getDfIso().parse(str);
    		}			
		}
    }
    
    /**
     * Creates a formatted string from a Date.
     * 
     * The formatted string is based on the short date format
     * specified by {@link Bo2UtilsEnvironment#getDfShort()}.
     * 
     * @param dt 
     *        Date to format.
     * 
     * @return Returns a formatted string for this date.
     */
    public static String formatDate(Date dt) {
   		return Bo2UtilsEnvironment.SINGLETON.getDfShort().format(dt);
    }
    
    /**
     * Creates a formatted string from a Date.
     * 
     * The formatted string is based on the ISO date format
     * specified by {@link Bo2UtilsEnvironment#getDfIso()}.
     * 
     * @param dt 
     *        Date to format.
     * 
     * @return Returns a formatted string for this date.
     */
    public static String formatDateIso(Date dt) {
   		return Bo2UtilsEnvironment.SINGLETON.getDfIso().format(dt);
    }
    
    /**
     * Creates a formatted string from a Date.
     * 
     * The formatted string is based on the long date format
     * specified by {@link Bo2UtilsEnvironment#getDfLong()}.
     * 
     * @param cal Date to format.
     * 
     * @return Returns a formatted string for this date.
     */
    public static String formatCalendar(Calendar cal) {    	
   		return Bo2UtilsEnvironment.SINGLETON.getDfLong().format(cal.getTime());
    }
    
    /**
     * Creates a formatted string from a Date.
     * 
     * The formatted string is based on the long date format
     * specified by {@link Bo2UtilsEnvironment#getDfLong()}.
     * 
     * @param dt 
     *        Date to format.
     * 
     * @return Returns a formatted string for this date.
     */
    public static String formatCalendar(Date dt) {
   		return Bo2UtilsEnvironment.SINGLETON.getDfLong().format(dt);
    }
    
    /**
     * Returns the current date, without hours, minutes and seconds.
     * 
     * @return Returns the current date.
     */
    public static Date today() {
    	Calendar cal = Calendar.getInstance();
    	cal.setTimeInMillis(System.currentTimeMillis());    	
    	removeTime(cal);
    	return cal.getTime();    	
    }
    
    /**
     * Converts a Date to a Calendar object.
     * 
     * @param dt
     *        Date to convert.
     *        
     * @return Returns a calendar with the same value as the specified date.
     */
    public static Calendar getCalendar(Date dt) {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(dt);
    	return cal;
    }
    
    /**
     * Formats a Date using a format yyyyMMdd.
     * 
     * @param dt
     *        Date to convert.
     *        
     * @return Returns a calendar with the same value as the specified date.
     */
    public static String formatYYYYMMDD(Date dt) {
    	return dfYYYYMMDD.format(dt);
    }
    
    /**
     * Gets the year of the specified date.
     * 
     * @param date
     *        Date.
     *        
     * @return Returns the year of the specified date.
     */
    public static final int year(Date date) {    	
    	Calendar cal = DateUtils.getCalendar(date);
    	return cal.get(Calendar.YEAR);    	
    }
    
    /**
     * Parses a String dd/mm/yyyy representation of a date
     * and returns a {@link Date}.
     * 
     * @param ddmmyyyy
     * 
     * @return a {@link Date} instance.
     */
    public static Date getDateDDMMYYYY(String ddmmyyyy) {
    	try {
			return Bo2UtilsEnvironment.SINGLETON.getDfShort().parse(ddmmyyyy);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
    }
    
}
