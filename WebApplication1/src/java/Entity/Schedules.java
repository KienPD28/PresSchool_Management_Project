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
public class Schedules {
    private int scheID;
    private SessionDetails sdid;
    private Date date;
    private ClassSession csid;

    public Schedules() {
    }

    public Schedules(int scheID, SessionDetails sdid, Date date, ClassSession csid) {
        this.scheID = scheID;
        this.sdid = sdid;
        this.date = date;
        this.csid = csid;
    }

    public int getScheID() {
        return scheID;
    }

    public void setScheID(int scheID) {
        this.scheID = scheID;
    }

    public SessionDetails getSdid() {
        return sdid;
    }

    public void setSdid(SessionDetails sdid) {
        this.sdid = sdid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ClassSession getCsid() {
        return csid;
    }

    public void setCsid(ClassSession csid) {
        this.csid = csid;
    }
    
    
    
}
