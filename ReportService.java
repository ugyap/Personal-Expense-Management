
package pem.com;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * TreeMap of Monthly Total
 * 
 * 2017,Jan -> 200
 * 2017,Feb -> 100
 * 2017,Nov -> 500
 * 2018,Apr -> 1000
 * 
 * if there is a match in month and year, e.g 2017,Jan -> 500
 * New: 
 * 2017,Jan -> 200+500 = 700
 * 
 * TreeMap of Yearly Total
 * 
 * 2017 -> 200+100+500
 * 2018 -> 1000
 * 
 * @author Eugene
 */
public class ReportService {
    Repository repo = Repository.getRepository();
    
    public Map<String,Float> getMonthlyExpense() {
        Map<String,Float> m = new TreeMap();
        //Reason to use treemap: To organize map with sorted keys
        
        for(Expense e : repo.expenses){
            Date eDate = e.getDate();
            String yearMonth = DateUtil.getYearMonth(eDate);
            //get date in format of yyyy,MMM
            //this will act as a matching component with the keys in map
            
            //if there is a match, add expense into total in map
            if(m.containsKey(yearMonth)){
                Float total = m.get(yearMonth); //store value in temp variable
                total += e.getAmount(); //get new total = old + new
                m.put(yearMonth, total); //update new total
            } else {
                m.put(yearMonth, e.getAmount());
                //if there is no match, create a new key -> value 
            }
        }
        return m; // return map of total expense monthly in different years
    }
    
    public Map<Integer,Float> getAnnualExpense(){
        /*Map<String,Float> expenseMapYear = getMonthlyExpense(); 
        In top code, we add extra work to map keys with same month and year
        then following with matching the same year */
        
        //create new Map for years
        Map<Integer,Float> mY = new TreeMap();
        
        for(Expense e : repo.expenses){
            Date eDate = e.getDate();
            Integer years = DateUtil.getYear(eDate);
            //get date in format of yyyy 
            //this will act as a matching component with the keys in map
            
            //if there is a match, add expense into total in map
            if(mY.containsKey(years)){
                Float total = mY.get(years); //store value in temp variable
                total += e.getAmount(); //get new total = old + new
                mY.put(years, total); //update new total
            } else {
                mY.put(years, e.getAmount());
                //if there is no match, create a new key -> value 
            }
        }
        return mY; // return map of total expense monthly in different years        
    }
}
