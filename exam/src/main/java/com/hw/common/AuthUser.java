package com.hw.common;

/**
 * @author  
 * @date 2020/11/18 4:51 下午
 */
public class AuthUser {
    private Integer id;
    private String username;
    private Integer role;

    public AuthUser() {
    }

    public AuthUser(Integer id, String username, Integer role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "AuthUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
