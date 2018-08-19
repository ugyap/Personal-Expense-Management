
package pem.com;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Eugene
 */
public class DateUtil {
    //not creating object by using static
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
}
