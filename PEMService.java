/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pem.com;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eugene
 */
public class PEMService {

    private Repository repo = Repository.getRepository();

    private Scanner in = new Scanner(System.in); //input from keyboard 
    private int numChoice;

    ReportService report = new ReportService();
    
    public PEMService (){
        reStoreRepository();
    }

    public void showMenu() {
        while (true) {
            printMenu();
            switch (numChoice) {
                case 1:
                    //add category
                    addCategory();
                    pressEnterToCont();
                    break;
                case 2:
                    categoryList();
                    pressEnterToCont();
                    break;
                case 3:
                    enterExpense();
                    pressEnterToCont();
                    break;
                case 4:
                    expenseList();
                    pressEnterToCont();
                    break;
                case 5:
                    monthlyExpense();
                    pressEnterToCont();
                    break;
                case 6:
                    annualExpense();
                    pressEnterToCont();
                    break;
                case 7:
                    categoriseExpenseList();
                    pressEnterToCont();
                    break;
                case 0:
                    exit();
                    break;
                default:
                    pressEnterToCont();
                    break;
            }
        }
    }

    public void printMenu() {
        System.out.println("PEM Menu");
        System.out.println("1. Add Category");
        System.out.println("2. Category List");
        System.out.println("3. Enter Expense");
        System.out.println("4. Expense List");
        System.out.println("5. Monthly Expense List");
        System.out.println("6. Yearly Expense List");
        System.out.println("7. Categorise Expense List");
        System.out.println("0. Exit");
        System.out.println("");
        System.out.print("Enter no.: ");
        numChoice = in.nextInt();
        System.out.println("");
    }

    public void pressEnterToCont() {
        try {
            System.out.println("...Press Enter to continue...");
            System.in.read();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void addCategory() {
        in.nextLine(); // Read the nextline below to output
        System.out.print("Add category name: ");
        String categoryName = in.nextLine();

        Category cat = new Category(categoryName);
        repo.categories.add(cat); //add new category to category arrayList
        System.out.println("Category " + categoryName + " is added...");
    }

    public void categoryList() {
        //print all categories
        System.out.println("Categories: ");
        List<Category> cList = repo.categories;
        for (int i = 0; i < cList.size(); i++) {
            Category cat = cList.get(i);
            System.out.println((i + 1) + ". " + cat.getName() + ", " + cat.getCategoryId());
        }

    }

    public void enterExpense() {
        //select category then enter expense
        System.out.println("Enter expense: ");
        categoryList();
        System.out.print("Enter no. to select category: ");
        int select = in.nextInt();
        System.out.println("You have selected " + select);

        System.out.print("Enter Amount: ");
        float amount = in.nextFloat();
        System.out.print("Enter remark; ");
        in.nextLine();
        String remark = in.nextLine();

        //select the created Category object from category arraylist
        Category selectedCat = repo.categories.get(select - 1);

        System.out.print("Enter Date (dd/mm/yyyy): ");
        String dateAsString = in.nextLine();
        Date date = DateUtil.stringToDate(dateAsString);
        //input date as string and make it to date using method in DateUtil
        //Date date = new Date();

        //adding details into Expense object properties
        Expense exp = new Expense();
        exp.setCategoryId(selectedCat.getCategoryId());
        //assign categoryId from Category object into categoryId in Expense object
        exp.setAmount(amount);
        exp.setRemark(remark);
        exp.setDate(date);

        //store expense object in repository
        repo.expenses.add(exp);
        System.out.println("System: Expense successfully added!");

    }

    private void expenseList() {
        System.out.println("Expenses: ");
        List<Expense> eList = repo.expenses;

        for (int i = 0; i < eList.size(); i++) {
            Expense exp = eList.get(i);
            String categoryName = getCategoryNameById(exp.getCategoryId());
            String dateString = DateUtil.dateToString(exp.getDate());

            System.out.println((i + 1) + ". " + categoryName + ", " + exp.getAmount()
                    + ", " + exp.getRemark() + ", " + dateString);
        }

    }

    private void monthlyExpense() {
        Map<String, Float> expenseMap = report.getMonthlyExpense();
        Set<String> keys = expenseMap.keySet();
        //remember the keys are yearMonth in yyyy,MMM format

        System.out.println("Monthly Expense: ");
        //looping from 1 key to next key
        for (String k : keys) {
            System.out.println(k + ": " + expenseMap.get(k));
        }
    }

    private void annualExpense() {
        Map<Integer, Float> expenseAnnualMap = report.getAnnualExpense();
        Set<Integer> keys = expenseAnnualMap.keySet();
        //remember the keys are yearMonth in yyyy format

        System.out.println("Annual Expense: ");
        //looping from 1 key to next key
        for (Integer k : keys) {
            System.out.println(k + ": " + expenseAnnualMap.get(k));
        }
    }

    private void categoriseExpenseList() {
        System.out.println("Categories of expenses: ");
        //no. 10
    }

    private void exit() {
        //stored data to database before exiting
        storeRepository();
        System.exit(0);
    }

    String getCategoryNameById(Long catId) {
        for (Category c : repo.categories) {
            if (c.getCategoryId().equals(catId)) {
                return c.getName();
            }
        }
        return null;
    }

    private void storeRepository() {
       serialize("expenses.ser", repo.expenses);
       serialize("categories.ser", repo.categories);
    }
    
    public void serialize(String file, Object obj){
        try {
            FileOutputStream fout = new FileOutputStream(file);
            ObjectOutputStream obOut = new ObjectOutputStream(fout);
            
            obOut.writeObject(obj); // store expenses in file
            
            obOut.close();
            fout.close();
        } catch (FileNotFoundException exF) {
            exF.printStackTrace();
        } catch (IOException exIO) {
            exIO.printStackTrace();
        }
    }
    
    public Object deserialize(String file){
        try {
            FileInputStream fin = new FileInputStream(file);
            ObjectInputStream obIn = new ObjectInputStream(fin);
            
            Object input = obIn.readObject();
            return input;
        } catch (Exception ex) {
            System.out.println("No data yet...");
            return null;
        }
        
    }
    
    private void reStoreRepository() {
           List<Expense> expenses = (List<Expense>) deserialize("expenses.ser");
           List<Category> categories = (List<Category>) deserialize("categories.ser");
           
           if(expenses != null){
               repo.expenses = expenses;
           }
           
           if(categories != null){
               repo.categories = categories;
           }
    }
}
