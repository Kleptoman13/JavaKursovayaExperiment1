package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AtsList implements Serializable {
    private ArrayList<Ats> list;

    public AtsList()
    {
        list = new ArrayList<>();
    }


    public void add(Ats ats) {
        list.add(ats);
    }

    public void AssignNumber(int selected_number, int selected_banner, int selected_numberI)
    {
        list.get(selected_banner).setNumber(selected_number);
        list.remove(selected_numberI);
    }

    public ArrayList getAtsList()
    {
        return this.list;
    }

    @Override
    public String toString()
    {
        return "atsList" + list;
     }
}
