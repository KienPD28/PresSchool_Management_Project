/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author admin
 */
public class Lecturers {

    private int lid;
    private String lname;
    private boolean gender;
    private String dob;
    private String phoneNumber;
    private String IDcard;
    private String address;
    private String email;
    private String nickname;
    private String status;

    public Lecturers() {
    }

    public Lecturers(int lid, String lname, boolean gender, String dob, String phoneNumber, String IDcard, String address, String email, String nickname, String status) {
        this.lid = lid;
        this.lname = lname;
        this.gender = gender;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.IDcard = IDcard;
        this.address = address;
        this.email = email;
        this.nickname = nickname;
        this.status = status;
    }

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIDcard() {
        return IDcard;
    }

    public void setIDcard(String IDcard) {
        this.IDcard = IDcard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Lecturers{" + "lid=" + lid + ", lname=" + lname + ", gender=" + gender + ", dob=" + dob + ", phoneNumber=" + phoneNumber + ", IDcard=" + IDcard + ", address=" + address + ", email=" + email + ", nickname=" + nickname + '}';
    }

}
