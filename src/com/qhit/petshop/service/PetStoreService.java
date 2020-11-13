package com.qhit.petshop.service;

import com.qhit.petshop.pojo.PetStore;

import java.util.List;
/**
 * @author 王海龙
 */
public interface PetStoreService {

    //1.查询所有店铺信息
    List<PetStore> findPetStoreList();

    //2.登录的方法
    PetStore login(String username,String password);

    //查询出对应的商店
    PetStore findPetStore(Integer petStore_id);

    //修改
    void updateStore(PetStore petStore);
}
