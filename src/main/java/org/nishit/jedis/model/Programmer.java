package org.nishit.jedis.model;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class Programmer implements Serializable {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Programmer that = (Programmer) o;
        return id == that.id &&
                Objects.equals(company, that.company) &&
                Objects.equals(name, that.name);


    }

    @Override
    public int hashCode() {

        return Objects.hash(id, company, name);
    }



    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String company,name;
}
