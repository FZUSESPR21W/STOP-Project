package com.fzu.stop.pojo;

/**
 * @author 梁达毅
 */
public class AuthorityDo {
    Integer id;
    String authorityName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    @Override
    public String toString() {
        return "AuthorityDo{" +
                "id=" + id +
                ", authorityName='" + authorityName + '\'' +
                '}';
    }
}
