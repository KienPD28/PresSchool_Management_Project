/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author admin
 */
public class AgeCategory {
    private int ageid;
    private String aname;

    public AgeCategory() {
    }

    public AgeCategory(int ageid, String aname) {
        this.ageid = ageid;
        this.aname = aname;
    }

    public int getAgeid() {
        return ageid;
    }

    public void setAgeid(int ageid) {
        this.ageid = ageid;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }
    
}
