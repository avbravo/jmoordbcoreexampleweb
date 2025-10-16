/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.ui.model;

import com.jmoordb.core.ui.menu.MenuLink;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author avbravo
 */
public class WebModel {

    Boolean isAuthentication;
    Boolean hasAuthorization;
    String username;
    String name;
    Long iduser;
    String userRol;
    Long idRol;
    String primaryBtnClass;
    String secondaryBtnClass;
    String cssFramework;
    Boolean isTailwind = Boolean.FALSE;
  

    public WebModel() {
    }

    public WebModel(Boolean isAuthentication, Boolean hasAuthorization, String username, String name, Long iduser, String userRol, Long idRol, String primaryBtnClass, String secondaryBtnClass, String cssFramework) {
        this.isAuthentication = isAuthentication;
        this.hasAuthorization = hasAuthorization;
        this.username = username;
        this.name = name;
        this.iduser = iduser;
        this.userRol = userRol;
        this.idRol = idRol;
        this.primaryBtnClass = primaryBtnClass;
        this.secondaryBtnClass = secondaryBtnClass;
        this.cssFramework = cssFramework;
    }

   

    public Boolean getIsAuthentication() {
        return isAuthentication;
    }

    public void setIsAuthentication(Boolean isAuthentication) {
        this.isAuthentication = isAuthentication;
    }

    public Boolean getHasAuthorization() {
        return hasAuthorization;
    }

    public void setHasAuthorization(Boolean hasAuthorization) {
        this.hasAuthorization = hasAuthorization;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getIduser() {
        return iduser;
    }

    public void setIduser(Long iduser) {
        this.iduser = iduser;
    }

    public String getUserRol() {
        return userRol;
    }

    public void setUserRol(String userRol) {
        this.userRol = userRol;
    }

    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }

    public String getPrimaryBtnClass() {
        return primaryBtnClass;
    }

    public void setPrimaryBtnClass(String primaryBtnClass) {
        this.primaryBtnClass = primaryBtnClass;
    }

    public String getSecondaryBtnClass() {
        return secondaryBtnClass;
    }

    public void setSecondaryBtnClass(String secondaryBtnClass) {
        this.secondaryBtnClass = secondaryBtnClass;
    }

    public String getCssFramework() {
        return cssFramework;
    }

    public void setCssFramework(String cssFramework) {
        this.cssFramework = cssFramework;
    }

    public Boolean getIsTailwind() {
        return isTailwind;
    }

    public void setIsTailwind(Boolean isTailwind) {
        this.isTailwind = isTailwind;
    }

 
    
   

}
