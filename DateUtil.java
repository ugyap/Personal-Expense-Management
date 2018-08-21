
package pem.com;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Eugene
 */
public class DateUtil {
    //This class is to convert Date to desire format
    public static Date stringToDate(String date) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            return df.parse(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public static String dateToString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(date);
    }
    
    public static String getYearMonth(Date date){
        SimpleDateFormat ym = new SimpleDateFormat("yyyy, MMM");
        return ym.format(date);
    }
    
    public static Integer getYear(Date date){
        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        return new Integer(year.format(date));
    }

}
