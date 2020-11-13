package com.qhit.petshop.pojo;

import java.io.Serializable;
import java.sql.Date;
/**
 * @author 王海龙
 */
public class Account implements Serializable {
    private Integer id; //交易id
    private Integer deal_type; //-- 交易类型 1商店卖给宠物  2 主人卖个商店,
    private Integer pet_id; //-- 宠物id,
    private Integer seller_id; // -- 买家id,
    private Integer buyer_id; // -- 卖家id,
    private Integer price; //  价格
    private Date deal_time; //交易时间


    public Integer getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(Integer buyer_id) {
        this.buyer_id = buyer_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDeal_type() {
        return deal_type;
    }

    public void setDeal_type(Integer deal_type) {
        this.deal_type = deal_type;
    }

    public Integer getPet_id() {
        return pet_id;
    }

    public void setPet_id(Integer pet_id) {
        this.pet_id = pet_id;
    }

    public Integer getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(Integer seller_id) {
        this.seller_id = seller_id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getDeal_time() {
        return deal_time;
    }

    public void setDeal_time(Date deal_time) {
        this.deal_time = deal_time;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", deal_type=" + deal_type +
                ", pet_id=" + pet_id +
                ", seller_id=" + seller_id +
                ", buyer_id=" + buyer_id +
                ", price=" + price +
                ", deal_time=" + deal_time +
                '}';
    }
}
