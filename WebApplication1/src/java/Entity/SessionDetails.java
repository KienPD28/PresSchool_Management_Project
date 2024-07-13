/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author admin
 */
public class SessionDetails {
    private int sdid;
    private String detail;
    private int sessionNumber;
    private Session sid;

    public SessionDetails() {
    }

    public SessionDetails(int sdid, String detail, int sessionNumber, Session sid) {
        this.sdid = sdid;
        this.detail = detail;
        this.sessionNumber = sessionNumber;
        this.sid = sid;
    }

    public int getSdid() {
        return sdid;
    }

    public void setSdid(int sdid) {
        this.sdid = sdid;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getSessionNumber() {
        return sessionNumber;
    }

    public void setSessionNumber(int sessionNumber) {
        this.sessionNumber = sessionNumber;
    }

    public Session getSid() {
        return sid;
    }

    public void setSid(Session sid) {
        this.sid = sid;
    }
    
    
}
