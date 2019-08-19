package com.twu.biblioteca;

public class Item {
    protected String title;
    protected boolean status;
    protected String checked_out_by;

    protected Item(String title){
        this.title = title;
        this.status = true;
        this.checked_out_by = null;
    }

    protected String getTitle(){
        return this.title;
    }

    protected boolean getStatus(){
        return this.status;
    }

    protected void setStatus(boolean status){
        this.status = status;
    }

    protected String getCheckedOutBy(){
        return this.checked_out_by;
    }

    protected void setCheckedOutBy(String usr_lib_num) {
        this.checked_out_by = usr_lib_num;
    }
}
