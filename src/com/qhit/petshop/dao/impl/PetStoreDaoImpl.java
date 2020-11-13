package com.qhit.petshop.dao.impl;

import com.qhit.petshop.dao.BaseDao;
import com.qhit.petshop.dao.PetStoreDao;
import com.qhit.petshop.pojo.PetStore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 * @author 王海龙
 */
public class PetStoreDaoImpl extends BaseDao implements PetStoreDao {


    @Override
    public void addPetStore(PetStore petStore) {
        String sql = "insert into petstore(name,password,balance) values(?,?,?)";
        Object[] objs = {petStore.getName(), petStore.getPassword(), petStore.getBalance()};

        update(sql, objs);

    }

    @Override
    public void updatePetStore(PetStore petStore) {
        String sql = "update petstore set name = ?,password = ?,balance = ? where id = ?";
        Object[] objs = {petStore.getName(), petStore.getPassword(), petStore.getBalance(), petStore.getId()};
        update(sql, objs);

    }

    @Override
    public void deletePetStore(Integer id) {
        String sql = "delete from petstore where id = ?";
        Object[] objs = {id};
        update(sql, objs);

    }

    @Override
    public PetStore findPetStore(Integer id) {
        Connection conn = getConnection();
        PreparedStatement prep = null;
        ResultSet rs = null;
        PetStore ps = null;
        try {
            String sql = "select  * from petstore where id = ?";
            prep = conn.prepareStatement(sql);
            prep.setInt(1, id);
            rs = prep.executeQuery();

            while (rs.next()) {
                ps = new PetStore();
                Integer pid = rs.getInt("id");
                String name = rs.getString("name");
                String password = rs.getString("password");
                Integer balance = rs.getInt("balance");

                ps.setId(pid);
                ps.setName(name);
                ps.setBalance(balance);
                ps.setPassword(password);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeAll(rs, prep, conn);
        }

        return ps;
    }

    @Override
    public List<PetStore> findPetStoreList() {
        Connection conn = getConnection();
        PreparedStatement prep = null;
        ResultSet rs = null;
        PetStore ps = null;
        List<PetStore> list = new ArrayList<>();
        try {
            String sql = "select  * from petstore ";
            prep = conn.prepareStatement(sql);
            rs = prep.executeQuery();

            while (rs.next()) {
                ps = new PetStore();
                Integer pid = rs.getInt("id");
                String name = rs.getString("name");
                String password = rs.getString("password");
                Integer balance = rs.getInt("balance");

                ps.setId(pid);
                ps.setName(name);
                ps.setBalance(balance);
                ps.setPassword(password);

                list.add(ps);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeAll(rs, prep, conn);
        }

        return list;
    }

    @Override
    public PetStore login(String username, String password) {
        String sql = "select * from petstore where name = ? and password = ?";
        Connection conn = getConnection();
        PreparedStatement prep = null;
        ResultSet rs = null;
        PetStore petStore = null;
        try {
            prep = conn.prepareStatement(sql);
            prep.setString(1, username);
            prep.setString(2, password);

            rs = prep.executeQuery();
            while (rs.next()) {
                petStore = new PetStore();

                Integer pid = rs.getInt("id");
                String name = rs.getString("name");
                String pwd = rs.getString("password");
                Integer balance = rs.getInt("balance");

                petStore.setId(pid);
                petStore.setName(name);
                petStore.setBalance(balance);
                petStore.setPassword(pwd);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeAll(rs, prep, conn);
        }
        return petStore;
    }
}
