/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author admin
 */
public class Food {
    private int foodid;
    private String fname;

    public Food() {
    }

    public Food(int foodid, String fname, int calo) {
        this.foodid = foodid;
        this.fname = fname;
    }

    public int getFoodid() {
        return foodid;
    }

    public void setFoodid(int foodid) {
        this.foodid = foodid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    @Override
    public String toString() {
        return "Food{" + "foodid=" + foodid + ", fname=" + fname + '}';
    }
    
    

  
    
}
