/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author admin
 */
public class Account {
    private int aid;
    private String username;
    private String password;
    private int role;
    private Parent pid;
    private Lecturers lid;

    public Account() {
    }

    public Account(int aid, String username, String password, int role, Parent pid, Lecturers lid) {
        this.aid = aid;
        this.username = username;
        this.password = password;
        this.role = role;
        this.pid = pid;
        this.lid = lid;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public Parent getPid() {
        return pid;
    }

    public void setPid(Parent pid) {
        this.pid = pid;
    }

    public Lecturers getLid() {
        return lid;
    }

    public void setLid(Lecturers lid) {
        this.lid = lid;
    }

    @Override
    public String toString() {
        return "Account{" + "aid=" + aid + ", username=" + username + ", password=" + password + ", role=" + role + ", pid=" + pid + ", lid=" + lid + '}';
    }
    
    

}
