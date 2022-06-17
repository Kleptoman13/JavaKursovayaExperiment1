package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Comparator;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ats implements Serializable {
    private int number;
    private int isFreeNumber;
    private String name;
    private String surname;
    private String lastname;
    private String addres;
    private int mobilenumber;
    private String email;

    public Ats(int number, int isFreeNumber ,String name, String lastname, String surname, String addres, int mobilenumber, String email)
    {
        this.setNumber(number);
        this.setIsFreeNumber(isFreeNumber);
        this.setSurname(surname);
        this.setName(name);
        this.setLastname(lastname);
        this.setAddres(addres);
        this.setMobilenumber(mobilenumber);
        this.setEmail(email);
    }

    public Ats() {
    }

    public void setName(String name) {
        this.name = name.strip();
        for (int i = 0; i < this.name.length(); i++) {
            if (!Character.isAlphabetic(this.name.charAt(i))) {
                this.name = "Incorrect";
            }
        }
        this.name = ("" + this.name.charAt(0)).toUpperCase() + this.name.substring(1).toLowerCase();
    }

    public String getName() {
        return name;
    }

    public void setIsFreeNumber(int isFreeNumber) {
        this.isFreeNumber = isFreeNumber;
    }

    public int getIsFreeNumber() {
        return isFreeNumber;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setSurname(String surname) {
        this.surname = surname.strip();
        for (int i = 0; i < this.surname.length(); i++) {
            if (!Character.isAlphabetic(this.surname.charAt(i))) {
                this.surname = "Incorrect";
            }
        }
        this.surname = ("" + this.surname.charAt(0)).toUpperCase() + this.surname.substring(1).toLowerCase();
    }

    public String getSurname() {
        return surname;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public String getAddres() {
        return addres;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setMobilenumber(int mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public int getMobilenumber() {
        return mobilenumber;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname.strip();
        for (int i = 0; i < this.lastname.length(); i++) {
            if (!Character.isAlphabetic(this.lastname.charAt(i))) {
                this.lastname = "Incorrect";
            }
        }
        this.lastname = ("" + this.lastname.charAt(0)).toUpperCase() + this.lastname.substring(1).toLowerCase();
    }

    public String getLastname() {
        return lastname;
    }

    @Override
    public String toString()
    {
        return "Ats{" +
        "number=" + getNumber() +
                ", IsFreeNumber=" + getIsFreeNumber() +
                ", name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                ", lastname='" + getLastname() + '\'' +
                ", addres='" + getAddres() + '\'' +
                ", mobilenumber=" + getMobilenumber() +
                ", email='" + getEmail() + '\'' +
        '}' + System.lineSeparator();

    }

    /*
    * Компараторы для сортировки
    * */

    public static Comparator<Ats> byNumber = Comparator.comparing(o -> o.number);
    public static Comparator<Ats> byName = Comparator.comparing(o -> o.name);
    public static Comparator<Ats> bySurname = Comparator.comparing(o -> o.surname);
    public static Comparator<Ats> byLastName = Comparator.comparing(o -> o.lastname);
    public static Comparator<Ats> byAddres = Comparator.comparing(o -> o.addres);
    public static Comparator<Ats> byMobileNumber = Comparator.comparing(o -> o.mobilenumber);
    public static Comparator<Ats> byEmail = Comparator.comparing(o -> o.email);
}
