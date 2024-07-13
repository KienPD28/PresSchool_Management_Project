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
public class Feedback {
    private int fid;
    private String ftitle;
    private String fcontent;
    private Date dateFeedback;
    private Student stuid;

    public Feedback() {
    }

    public Feedback(int fid, String ftitle, String fcontent, Date dateFeedback, Student stuid) {
        this.fid = fid;
        this.ftitle = ftitle;
        this.fcontent = fcontent;
        this.dateFeedback = dateFeedback;
        this.stuid = stuid;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getFtitle() {
        return ftitle;
    }

    public void setFtitle(String ftitle) {
        this.ftitle = ftitle;
    }

    public String getFcontent() {
        return fcontent;
    }

    public void setFcontent(String fcontent) {
        this.fcontent = fcontent;
    }

    public Date getDateFeedback() {
        return dateFeedback;
    }

    public void setDateFeedback(Date dateFeedback) {
        this.dateFeedback = dateFeedback;
    }

    public Student getStuid() {
        return stuid;
    }

    public void setStuid(Student stuid) {
        this.stuid = stuid;
    }
    
    
}
