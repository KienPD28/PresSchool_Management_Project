/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author admin
 */
public class Session {

    private int sid;
    private String sname;
    private String sessionDescription;
    private int totalSession;
    private AgeCategory age;
    private String status;

    public Session() {
    }

    public Session(int sid, String sname, String sessionDescription, int totalSession, AgeCategory age, String status) {
        this.sid = sid;
        this.sname = sname;
        this.sessionDescription = sessionDescription;
        this.totalSession = totalSession;
        this.age = age;
        this.status = status;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSessionDescription() {
        return sessionDescription;
    }

    public void setSessionDescription(String sessionDescription) {
        this.sessionDescription = sessionDescription;
    }

    public int getTotalSession() {
        return totalSession;
    }

    public void setTotalSession(int totalSession) {
        this.totalSession = totalSession;
    }

    public AgeCategory getAge() {
        return age;
    }

    public void setAge(AgeCategory age) {
        this.age = age;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Session{" + "sid=" + sid + ", sname=" + sname + ", sessionDescription=" + sessionDescription + ", totalSession=" + totalSession + ", age=" + age + ", status=" + status + '}';
    }

}
