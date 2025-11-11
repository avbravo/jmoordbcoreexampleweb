/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.util;

/**
 *
 * @author avbravo
 */
public class CountData {
   private String name;
   private Long count;

    public CountData(String name, Long count) {
        this.name = name;
        this.count = count;
    }

    public CountData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "CountData{" + "name=" + name + ", count=" + count + '}';
    }
   
 
}
