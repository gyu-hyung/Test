package Controller;

import java.io.Serializable;

public class MemberDTO implements Serializable {

    private int id;
    private String pw, name, addr;


    public MemberDTO(int id, String pw, String name, String addr) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.addr = addr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}