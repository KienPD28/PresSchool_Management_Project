/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.sql.Date;

/**
 *
 * @author admin
 */
public class Menu {
    private int mid;
    private Date date;
    private String menu;
    private AgeCategory ageid;
    private MealTime mealID;

    public Menu() {
    }

    public Menu(int mid, Date date, String menu, AgeCategory ageid, MealTime mealID) {
        this.mid = mid;
        this.date = date;
        this.menu = menu;
        this.ageid = ageid;
        this.mealID = mealID;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public AgeCategory getAgeid() {
        return ageid;
    }

    public void setAgeid(AgeCategory ageid) {
        this.ageid = ageid;
    }

    public MealTime getMealID() {
        return mealID;
    }

    public void setMealID(MealTime mealID) {
        this.mealID = mealID;
    }

    @Override
    public String toString() {
        return "Menu{" + "mid=" + mid + ", date=" + date + ", menu=" + menu + ", ageid=" + ageid + ", mealID=" + mealID + '}';
    }

    
    
}
