package com.qhit.petshop.dao.impl;

import com.qhit.petshop.dao.AccountDao;
import com.qhit.petshop.dao.BaseDao;
import com.qhit.petshop.pojo.Account;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 * @author 王海龙
 */
public class AccountDaoImpl extends BaseDao implements AccountDao {


    @Override
    public void addAccount(Account account) {
        String sql = "insert into account(deal_type,pet_id,seller_id,buyer_id,price) values(?,?,?,?,?);";
        Object[] objs = {account.getDeal_type(),account.getPet_id(),account.getSeller_id(),account.getBuyer_id(),account.getPrice()};
        update(sql, objs);

    }

    @Override
    public void updateAccount(Account account) {

        String sql = "update account set deal_type = ?,pet_id = ? ,seller_id = ?,buyer_id = ?,price = ?  where id = ?;";
        Object[] objs = {account.getDeal_type(),account.getPet_id(),account.getSeller_id(),account.getBuyer_id(),account.getPrice(),account.getId()};
        update(sql, objs);
    }

    @Override
    public void deleteAccount(Integer id) {
        String sql = "delete from account where id = ?;";

         update(sql, id);

    }

    @Override
    public Account findAccount(Integer id) {
        Connection conn = getConnection();
        PreparedStatement prep = null;
        ResultSet rs = null;
        Account account = null;
        try {
            String sql = "select * from account where id = ?";
            prep = conn.prepareStatement(sql);
            prep.setInt(1, id);
            rs = prep.executeQuery();

            while (rs.next()) {
                account = new Account();
                Integer id1 = rs.getInt("id");
                Integer deal_type = rs.getInt("deal_type");
                Integer pet_id = rs.getInt("pet_id");
                Integer seller_id = rs.getInt("seller_id");
                Integer buyer_id = rs.getInt("buyer_id");
                Integer price = rs.getInt("price");
                Date deal_time = rs.getDate("deal_time");

                account.setId(id1);
                account.setDeal_type(deal_type);
                account.setSeller_id(seller_id);
                account.setDeal_time(deal_time);
                account.setPet_id(pet_id);
                account.setPrice(price);
                account.setBuyer_id(buyer_id);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            closeAll(rs,prep,conn);
        }

        return account;

    }

    @Override
    public List<Account> findAccountList() {
        Connection conn = getConnection();
        PreparedStatement prep = null;
        ResultSet rs = null;
        List<Account> list = null;
        Account account = null;
        try {
            String sql = "select * from account";
            prep = conn.prepareStatement(sql);
            rs = prep.executeQuery();
            list = new ArrayList<>();

            while (rs.next()) {
                account = new Account();
                Integer id1 = rs.getInt("id");
                Integer deal_type = rs.getInt("deal_type");
                Integer pet_id = rs.getInt("pet_id");
                Integer seller_id = rs.getInt("seller_id");
                Integer buyer_id = rs.getInt("buyer_id");
                Integer price = rs.getInt("price");
                Date deal_time = rs.getDate("deal_time");

                account.setId(id1);
                account.setDeal_type(deal_type);
                account.setSeller_id(seller_id);
                account.setDeal_time(deal_time);
                account.setPet_id(pet_id);
                account.setPrice(price);
                account.setBuyer_id(buyer_id);

                list.add(account);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            closeAll(rs,prep,conn);
        }

        return list;

    }
}
