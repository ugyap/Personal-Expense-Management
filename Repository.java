
package pem.com;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton Database/Repository
 * @author Eugene
 */
public class Repository {
    public List<Expense> expenses = new ArrayList();
    public List<Category> categories = new ArrayList();
    private static Repository repository;
    
    private Repository(){
    }
    
    public static Repository getRepository(){
        if(repository == null){
            repository = new Repository();
        }
        //create repository once
        return repository;
    }
    
}
