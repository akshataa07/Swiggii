package com.example.dell.lunchbox2;

/**
 * Created by DELL on 18-Oct-18.
 */

public class Provider {
    private String id,servicenm,fnm,mnm,lnm,addr,mob1,mob2,email,passwd,category;


    public Provider() {
    }

    public String getId() {
        return id;
    }

    public String getServicenm() {
        return servicenm;
    }

    public String getFnm() {
        return fnm;
    }

    public String getMnm() {
        return mnm;
    }

    public String getLnm() {
        return lnm;
    }

    public String getAddr() {
        return addr;
    }

    public String getMob1() {
        return mob1;
    }

    public String getMob2() {
        return mob2;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswd() {
        return passwd;
    }

    public String getCategory() {
        return category;
    }

    public Provider(String id, String servicenm, String fnm, String mnm, String lnm, String addr, String mob1, String mob2, String email, String passwd,String category) {
        this.id = id;
        this.servicenm = servicenm;
        this.fnm = fnm;
        this.mnm = mnm;
        this.lnm = lnm;
        this.addr = addr;
        this.mob1 = mob1;
        this.mob2 = mob2;
        this.email = email;
        this.passwd = passwd;
        this.category=category;
    }
}
