/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author admin
 */
public class Parent {

    private int pid;
    private String pname;
    private boolean gender;
    private String dob;
    private String phoneNumber;
    private String IDcard;
    private String address;
    private String email;
    private String nickname;
    private Boolean status;

    public Parent() {
    }

    public Parent(int pid, String pname, boolean gender, String dob, String phoneNumber, String IDcard, String address, String email, String nickname, Boolean status) {
        this.pid = pid;
        this.pname = pname;
        this.gender = gender;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.IDcard = IDcard;
        this.address = address;
        this.email = email;
        this.nickname = nickname;
        this.status = status;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
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

    public boolean isStatus() {
        return gender;
    }

    public void setStatus(Boolean gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Parent{" + "pid=" + pid + ", pname=" + pname + ", gender=" + gender + ", dob=" + dob + ", phoneNumber=" + phoneNumber + ", IDcard=" + IDcard + ", address=" + address + ", email=" + email + ", nickname=" + nickname + ", status=" + status + '}';
    }

}
