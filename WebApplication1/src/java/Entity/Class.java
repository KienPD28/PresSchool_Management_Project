/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author admin
 */
public class Class {
    private int classid;
    private String clname;

    public Class() {
    }

    public Class(int classid, String clname, Room rid) {
        this.classid = classid;
        this.clname = clname;
    }

    

    public int getClassid() {
        return classid;
    }

    public void setClassid(int classid) {
        this.classid = classid;
    }

    public String getClname() {
        return clname;
    }

    public void setClname(String clname) {
        this.clname = clname;
    }

    @Override
    public String toString() {
        return "Class{" + "classid=" + classid + ", clname=" + clname + '}';
    }



    
    
}
