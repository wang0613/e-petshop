package com.qhit.petshop.dao.impl;

import com.qhit.petshop.dao.BaseDao;
import com.qhit.petshop.dao.PetDao;
import com.qhit.petshop.pojo.Pet;


import java.sql.Connection;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 王海龙
 */
public class PetDaoImpl extends BaseDao implements PetDao {


    @Override
    public void addPet(Pet pet) {
        String sql = "insert into pet(name,type_name,health,love,owner_id,store_id) values(?,?,?,?,?,?)";

        String name = pet.getName();
        String type_name = pet.getType_name();
        Integer health = pet.getHealth();
        Integer love = pet.getLove();
        Integer owner_id = pet.getOwner_id();
        Integer store_id = pet.getStore_id();

        Object[] objs = {name, type_name, health, love, owner_id, store_id};
       update(sql, objs);

    }

    @Override
    public void updatePet(Pet pet) {
        String sql = "update pet set name = ?,type_name = ?,health = ?,love = ?,owner_id = ?,store_id = ? where id = ?";

        String name = pet.getName();
        String type_name = pet.getType_name();
        Integer health = pet.getHealth();
        Integer love = pet.getLove();
        Integer owner_id = pet.getOwner_id();
        Integer store_id = pet.getStore_id();
        Integer id = pet.getId();

        Object[] objs = {name, type_name, health, love, owner_id, store_id, id};
        update(sql, objs);

    }

    @Override
    public void deletePet(Integer id) {
        String sql = "delete from pet  where id = ?";

        Object[] objs = {id};
        int line = update(sql, objs);
        if (line > 0) {
            System.out.println("删除成功!!");
        }
    }

    @Override
    public Pet findPet(Integer id) {
        Connection conn = getConnection();
        PreparedStatement prep = null;
        ResultSet rs = null;
        Pet pet = null;
        try {
            String sql = "select * from  pet  where id = ?";
            prep = conn.prepareStatement(sql);
            prep.setInt(1, id);
            rs = prep.executeQuery();

            while (rs.next()) {
                pet = new Pet();
                Integer id1 = rs.getInt("id");
                String name = rs.getString("name");
                String type_name = rs.getString("type_name");
                Integer health = rs.getInt("health");
                Integer love = rs.getInt("love");
                Date birthday = rs.getDate("birthday");
                Integer owner_id = rs.getInt("owner_id");
                Integer store_id = rs.getInt("store_id");

                pet.setId(id1);
                pet.setName(name);
                pet.setType_name(type_name);
                pet.setHealth(health);
                pet.setLove(love);
                pet.setBirthday(birthday);
                pet.setOwner_id(owner_id);
                pet.setStore_id(store_id);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }finally {
            closeAll(rs,prep,conn);
        }

        return pet;
    }

    @Override
    public List<Pet> findPetList() {
        Connection conn = getConnection();
        PreparedStatement prep = null;
        ResultSet rs = null;
        Pet pet = null;
        List<Pet> petList = new ArrayList<>();
        try {
            String sql = "select * from  pet";
            prep = conn.prepareStatement(sql);
            rs = prep.executeQuery();
            while (rs.next()) {
                pet = new Pet();
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                String type_name = rs.getString("type_name");
                Integer health = rs.getInt("health");
                Integer love = rs.getInt("love");
                Date birthday = rs.getDate("birthday");
                Integer owner_id = rs.getInt("owner_id");
                Integer store_id = rs.getInt("store_id");

                pet.setId(id);
                pet.setName(name);
                pet.setType_name(type_name);
                pet.setHealth(health);
                pet.setLove(love);
                pet.setBirthday(birthday);
                pet.setOwner_id(owner_id);
                pet.setStore_id(store_id);

                petList.add(pet);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }finally {
            closeAll(rs,prep,conn);
        }
        return petList;
    }

    /**
     * 获取商店的宠物信息
     * @return
     */
    @Override
    public List<Pet> findStorePetList() {
        String sql = "SELECT * FROM pet where store_id is not null;";
        Connection conn = getConnection();
        PreparedStatement prep = null;
        ResultSet rs = null;
        Pet pet = null;
        List<Pet> petList = new ArrayList<>();
        try {
            prep = conn.prepareStatement(sql);
            rs = prep.executeQuery();
            while (rs.next()) {
                pet = new Pet();
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                String type_name = rs.getString("type_name");
                Integer health = rs.getInt("health");
                Integer love = rs.getInt("love");
                Date birthday = rs.getDate("birthday");
                Integer owner_id = rs.getInt("owner_id");
                Integer store_id = rs.getInt("store_id");

                pet.setId(id);
                pet.setName(name);
                pet.setType_name(type_name);
                pet.setHealth(health);
                pet.setLove(love);
                pet.setBirthday(birthday);
                pet.setOwner_id(owner_id);
                pet.setStore_id(store_id);

                petList.add(pet);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }finally {
            closeAll(rs,prep,conn);
        }
        return petList;
    }

    /**
     * 获取当前主人所有的宠物
     * @param ownerId 主人Id
     * @return
     */
    @Override
    public List<Pet> findPetByOwnerId(Integer ownerId) {
        String SQL = "SELECT * FROM pet where owner_id =  ? ; ";
        Connection conn = getConnection();
        PreparedStatement prep = null;
        ResultSet rs = null;
        Pet pet = null;
        List<Pet> petList = new ArrayList<>();
        try {
            prep = conn.prepareStatement(SQL);
            //设置当前主人id
            prep.setInt(1,ownerId);
            rs = prep.executeQuery();
            while (rs.next()) {
                pet = new Pet();
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                String type_name = rs.getString("type_name");
                Integer health = rs.getInt("health");
                Integer love = rs.getInt("love");
                Date birthday = rs.getDate("birthday");
                Integer owner_id = rs.getInt("owner_id");
                Integer store_id = rs.getInt("store_id");

                pet.setId(id);
                pet.setName(name);
                pet.setType_name(type_name);
                pet.setHealth(health);
                pet.setLove(love);
                pet.setBirthday(birthday);
                pet.setOwner_id(owner_id);
                pet.setStore_id(store_id);

                petList.add(pet);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }finally {
            closeAll(rs,prep,conn);
        }
        return petList;
    }
}
