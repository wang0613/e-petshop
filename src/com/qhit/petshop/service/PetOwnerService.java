package com.qhit.petshop.service;

import com.qhit.petshop.pojo.PetOwner;

import java.util.List;
/**
 * @author 王海龙
 */
public interface PetOwnerService {

    //1.查询所有的主人信息
    List<PetOwner> findAllPetOwner();

    //2.登录
    PetOwner login(String username,String password);

    //3.购买
    void buyer(Integer pet_id,Integer owner_id);

    //4.卖出
    void seller(Integer pet_id,Integer store_id,Integer owner_id);

}
