package com.yqyan.portal.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class User implements Serializable {
    private Integer id;

    @NotNull(message = "姓名不能为空!")
    private String name;

    @NotNull(message = "年龄不能为空!")
    @Max(value = 120, message = "年龄不能超过120岁!")
    @Min(value = 1, message = "年龄不能低于1岁!")
    private Integer age;

    @NotNull(message = "性别不能为空!")
    @Max(value = 1, message = "只能取0或1，0: 女性，1: 男性")
    @Min(value = 0,  message = "只能取0或1，0: 女性，1: 男性")
    private Byte gender;

    private String address;

    private static final long serialVersionUID = 1L;

    public User() {
    }

    public User(Integer id, String name, Integer age, Byte gender, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", age=").append(age);
        sb.append(", gender=").append(gender);
        sb.append(", address=").append(address);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}