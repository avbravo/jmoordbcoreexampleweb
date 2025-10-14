/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui;

/**
 *
 * @author avbravo
 */
public class MenuLink {

    private final String text;
    private final String url;
    private final boolean isActive;
    private final String iconClass; // Para FontAwesome u otro set de iconos

    public MenuLink(String text, String url, boolean isActive, String iconClass) {
        this.text = text;
        this.url = url;
        this.isActive = isActive;
        this.iconClass = iconClass;
    }

    // ... Getters
    public String getText() {
        return text;
    }

    public String getUrl() {
        return url;
    }

    public boolean isActive() {
        return isActive;
    }

    public String getIconClass() {
        return iconClass;
    }
}
