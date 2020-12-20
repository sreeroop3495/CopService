package com.project.continental.CopService.model;

public class Pay {

    /**
     * This model is for accepting payment
     */

    String regno;
    String section;
    int amount;

    public  Pay(){}
    public Pay(String regno, String section, int amount) {
        this.regno = regno;
        this.section = section;
        this.amount = amount;
    }

    public String getRegno() {
        return regno;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
