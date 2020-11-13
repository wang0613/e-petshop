package com.qhit.petshop.pojo;


import java.io.Serializable;
import java.sql.Date;
/**
 * @author 王海龙
 */
public class Pet  implements Serializable {
//    id int(4) PRIMARY key auto_increment,
//    name VARCHAR(50),
//    type_name varchar(20),
//    health int(4),
//    love int(4),
//    birthday TIMESTAMP,
//    owner_id int(4), -- 宠物主人
//    store_id int(4) -- 宠物商店
    private Integer id;
    private String name;
    private String type_name;
    private Integer health;
    private Integer love;
    private Date  birthday;
    private Integer owner_id;
    private Integer store_id;

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

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public Integer getLove() {
        return love;
    }

    public void setLove(Integer love) {
        this.love = love;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Integer owner_id) {
        this.owner_id = owner_id;
    }

    public Integer getStore_id() {
        return store_id;
    }

    public void setStore_id(Integer store_id) {
        this.store_id = store_id;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type_name='" + type_name + '\'' +
                ", health=" + health +
                ", love=" + love +
                ", birthday=" + birthday +
                ", owner_id=" + owner_id +
                ", store_id=" + store_id +
                '}';
    }
}
