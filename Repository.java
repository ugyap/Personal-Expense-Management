
package pem.com;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton Database/Repository
 * 
 *      ExpensesList = Expense1 -> Expense2 -> Expense3
 *      CategoriesList = Category1 -> Category2 -> Category3
 * 
 * @author Eugene
 */
public class Repository {
    
    public List<Expense> expenses = new ArrayList();
    
    public List<Category> categories = new ArrayList();
    
    private static Repository repository;
    
    private Repository(){
    }
    
    public static Repository getRepository(){
        //if repository not created, create one
        //Avoid creating multiple repositories
        if(repository == null){
            repository = new Repository();
        }
      
        return repository;
    }
    
}
