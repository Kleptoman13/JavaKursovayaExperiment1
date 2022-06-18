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

    public void setTempAts(Ats tempAts)
    {
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getEmail().equals(tempAts.getEmail()))
            {
                list.set(i, tempAts);
            }
        }
    }


    public void deleteByNumber(String number) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getNumber() == Integer.parseInt(number, 10)) {
                list.remove(i);
            }
        }
    }

    public ArrayList<Ats> getAtsList()
    {
        return list;
    }

    @Override
    public String toString()
    {
        return "atsList" + list;
     }
}
