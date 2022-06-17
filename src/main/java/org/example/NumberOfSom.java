package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
@JsonIgnoreProperties(ignoreUnknown = true)
public class NumberOfSom implements Serializable {

    private int selected_number;
    private int selected_banner;
    private int selected_numberI;

    public NumberOfSom(int selected_number, int selected_banner, int selected_numberI)
    {
        this.setSelected_number(selected_number);
        this.setSelected_banner(selected_banner);
        this.setSelected_numberI(selected_numberI);
    }

    public NumberOfSom(){}

    public void setSelected_number(int selected_number) {
        this.selected_number = selected_number;
    }

    public int getSelected_number() {
        return selected_number;
    }

    public void setSelected_banner(int selected_banner) {
        this.selected_banner = selected_banner;
    }

    public int getSelected_banner() {
        return selected_banner;
    }

    public void setSelected_numberI(int selected_numberI) {
        this.selected_numberI = selected_numberI;
    }

    public int getSelected_numberI() {
        return selected_numberI;
    }

    @Override
    public String toString()
    {
        return "Numbers{" +
                "selected_number=" + getSelected_number() +
                ", selected_banner=" + getSelected_banner() +
                ", selected_numberI=" + getSelected_numberI() +
                '}' + System.lineSeparator();
                
    }
}
