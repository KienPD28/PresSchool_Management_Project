/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author admin
 */
public class ClassSession {

    private int csid;
    private Class classID;
    private SchoolYear yid;
    private Session sid;
    private Room rid;
    private boolean status;

    public ClassSession() {
    }

    public ClassSession(int csid, Class classID, SchoolYear yid, Session sid, Room rid, boolean status) {
        this.csid = csid;
        this.classID = classID;
        this.yid = yid;
        this.sid = sid;
        this.rid = rid;
        this.status = status;
    }

    public int getCsid() {
        return csid;
    }

    public void setCsid(int csid) {
        this.csid = csid;
    }

    public Class getClassID() {
        return classID;
    }

    public void setClassID(Class classID) {
        this.classID = classID;
    }

    public SchoolYear getYid() {
        return yid;
    }

    public void setYid(SchoolYear yid) {
        this.yid = yid;
    }

    public Session getSid() {
        return sid;
    }

    public void setSid(Session sid) {
        this.sid = sid;
    }

    public Room getRid() {
        return rid;
    }

    public void setRid(Room rid) {
        this.rid = rid;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ClassSession{" + "csid=" + csid + ", classID=" + classID + ", yid=" + yid + ", sid=" + sid + ", rid=" + rid + ", status=" + status + '}';
    }

}
