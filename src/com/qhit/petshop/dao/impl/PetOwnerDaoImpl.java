package com.qhit.petshop.dao.impl;

import com.qhit.petshop.dao.BaseDao;
import com.qhit.petshop.dao.PetOwnerDao;
import com.qhit.petshop.pojo.PetOwner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 * @author 王海龙
 */
public class PetOwnerDaoImpl extends BaseDao implements PetOwnerDao {


    @Override
    public void addPetOwner(PetOwner petOwner) {
        String sql = "insert into petowner(name,password,money) values(?,?,?) ";
        Object[] objs = {petOwner.getName(), petOwner.getPassword(), petOwner.getMoney()};
        update(sql, objs);

    }

    @Override
    public void updatePetOwner(PetOwner petOwner) {
        String sql = "update petowner set name = ?,password = ?,money = ? where id = ? ";
        Object[] objs = {petOwner.getName(), petOwner.getPassword(), petOwner.getMoney(), petOwner.getId()};
        update(sql, objs);
    }

    @Override
    public void deletePetOwner(Integer id) {
        String sql = "delete from petowner where id = ? ";
        Object[] objs = {id};
        update(sql, objs);
    }

    @Override
    public PetOwner findPetOwner(Integer id) {
        String sql = "select * from petowner where id = ?";
        Connection connection =getConnection();
        PreparedStatement prep = null;
        ResultSet rs = null;
        PetOwner petOwner = null;
        try {
            prep = connection.prepareStatement(sql);
            prep.setInt(1, id);
            rs = prep.executeQuery();

            while (rs.next()) {
                petOwner = new PetOwner();
                Integer id1 = rs.getInt("id");
                String name = rs.getString("name");
                String password = rs.getString("password");
                Integer money = rs.getInt("money");

                petOwner.setId(id1);
                petOwner.setName(name);
                petOwner.setPassword(password);
                petOwner.setMoney(money);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeAll(rs, prep, connection);
        }

        return petOwner;
    }

    @Override
    public List<PetOwner> findPetOwnerList() {

        String sql = "select * from petowner";
        Connection connection = getConnection();
        PreparedStatement prep = null;
        ResultSet rs = null;
        PetOwner petOwner = null;
        List<PetOwner> list = new ArrayList<>();
        try {
            prep = connection.prepareStatement(sql);
            rs = prep.executeQuery();

            while (rs.next()) {
                petOwner = new PetOwner();
                Integer id1 = rs.getInt("id");
                String name = rs.getString("name");
                String password = rs.getString("password");
                Integer money = rs.getInt("money");

                petOwner.setId(id1);
                petOwner.setName(name);
                petOwner.setPassword(password);
                petOwner.setMoney(money);

                list.add(petOwner);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeAll(rs, prep, connection);
        }

        return list;
    }

    @Override
    public PetOwner login(String username, String password) {

        String sql = "select * from petowner where name = ? and password = ?";
        Connection conn = getConnection();
        PreparedStatement prep = null;
        ResultSet rs = null;
        PetOwner petOwner = null;
        try {
            prep = conn.prepareStatement(sql);
            prep.setString(1, username);
            prep.setString(2, password);
            rs = prep.executeQuery();

            if (rs.next()) {
                petOwner = new PetOwner();
                Integer id1 = rs.getInt("id");
                String name = rs.getString("name");
                String pwd = rs.getString("password");
                Integer money = rs.getInt("money");

                petOwner.setId(id1);
                petOwner.setName(name);
                petOwner.setPassword(pwd);
                petOwner.setMoney(money);
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeAll(rs, prep, conn);
        }
        return petOwner;
    }
}
