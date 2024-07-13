/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.List;

/**
 *
 * @author Admin
 */
public class MenuFood {
    private int mealid;
    private Food food;

    public MenuFood() {
    }

    public MenuFood(int mealid, Food food) {
        this.mealid = mealid;
        this.food = food;
    }

    public Food getFood() {
        return food;
    }

    

    public int getMealid() {
        return mealid;
    }

    public void setFood(Food food) {
        this.food = food;
    }
   

    public void setMealid(int mealid) {
        this.mealid = mealid;
    }
    
    
}
