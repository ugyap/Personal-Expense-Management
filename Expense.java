
package pem.com;

import java.util.Date;

/**
 *
 * @author Eugene
 */
public class Expense {
    private Long expenseId = System.currentTimeMillis();
    private Long categoryId; //Foreign key
    private Float amount;
    private Date date;
    private String remark;
    
    public Expense(){
        
    }

    public Expense(Long categoryId, Float amount, Date date, String remark) {
        this.categoryId = categoryId;
        this.amount = amount;
        this.date = date;
        this.remark = remark;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getExpenseId() {
        return expenseId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public Float getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public String getRemark() {
        return remark;
    }
    
    
}
