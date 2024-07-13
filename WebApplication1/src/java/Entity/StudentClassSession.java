/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author admin
 */
public class StudentClassSession {
    private int scid;
    private Student stuid;
    private ClassSession csid;

    public StudentClassSession() {
    }

    public StudentClassSession(int scid, Student stuid, ClassSession csid) {
        this.scid = scid;
        this.stuid = stuid;
        this.csid = csid;
    }

    public int getScid() {
        return scid;
    }

    public void setScid(int scid) {
        this.scid = scid;
    }

    public Student getStuid() {
        return stuid;
    }

    public void setStuid(Student stuid) {
        this.stuid = stuid;
    }

    public ClassSession getCsid() {
        return csid;
    }

    public void setCsid(ClassSession csid) {
        this.csid = csid;
    }

    @Override
    public String toString() {
        return "StudentClassSession{" + "scid=" + scid + ", stuid=" + stuid + ", csid=" + csid + '}';
    }
    
    
    
}
