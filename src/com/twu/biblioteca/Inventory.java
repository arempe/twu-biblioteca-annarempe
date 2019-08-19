package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.ArrayList;

public class Inventory {
    protected ArrayList<Item> inventory;
    protected int num_checked_in;

    protected PrintStream out;

    protected String inv_header_msg;

    protected Inventory(PrintStream out, String header_msg){
        this.out = out;
        this.inv_header_msg = header_msg;
        this.inventory = new ArrayList<Item>();
        this.num_checked_in = 0;
    }

    public Item searchInv(String title) {
        Item to_return = null;
        for(Item item : this.inventory){
            if(item.getTitle().equals(title)){
                to_return = item;
            }
        }
        return to_return;
    }

    public String getInvHeaderMsg(){
        return this.inv_header_msg;
    }

    public int getNumCheckedIn() {
        return this.num_checked_in;
    }

    protected int selectionToInd(int selection) {
        int inv_itr = 0;
        int return_ind = -1;
        int usr_selection = selection;

        for(Item item: this.inventory){
            if(item.getStatus()){
                usr_selection--;
            }
            if(usr_selection == 0){
                return_ind = inv_itr;
                break;
            }
            else{
                inv_itr++;
            }
        }
        return return_ind;
    }

    public String checkedInToString(){
        StringBuilder checked_in_items = new StringBuilder("");

        int i = 0;
        for(Item item: this.inventory){
            if(item.getStatus()){
                i++;
                checked_in_items.append(String.format("%d)\t", i));
                checked_in_items.append(item.toString());
                checked_in_items.append("\n");
            }
        }
        return String.valueOf(checked_in_items);
    }

    protected void printCheckedInItems(){
        this.out.print(checkedInToString());

    }

    protected void displayInventory(){
        this.out.print(this.inv_header_msg);
        printCheckedInItems();
    }

    public boolean checkOutItem(int selection, String usr_lib_num){
        //selection based off of list of items printed which only displays available books
        int ind = selectionToInd(selection);
        boolean success = false;
        Item to_check_out = this.inventory.get(ind);
        if(to_check_out.getStatus()) {
            to_check_out.setStatus(false);
            to_check_out.setCheckedOutBy(usr_lib_num);
            success = true;
            this.num_checked_in--;
        }
        else{
            this.out.println("Item is not available");
        }
        return success;
    }

    public boolean checkInItem(String title) {
        boolean check_in_success = false;
        for(Item item : this.inventory){
            if(item.getTitle().equals(title)){
                if(!item.getStatus()){
                    item.setStatus(true);
                    item.setCheckedOutBy(null);
                    this.num_checked_in++;
                    check_in_success = true;
                    break;
                }
            }
        }
        return check_in_success;
    }



}
