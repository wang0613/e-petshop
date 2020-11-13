package com.qhit.petshop.service;

import com.qhit.petshop.pojo.Pet;

import java.util.List;
/**
 * @author 王海龙
 */
public interface PetService {
    //1.获取所有的宠物
    List<Pet> getPetList();

    //2.获取商店的宠物
    List<Pet> getStorePetList();

    //3.根据主人id查询当前主人的宠物有哪些
    List<Pet> getPetListByOwnerId(Integer ownerId);

    //4.根据id获取当前宠物对象
    Pet findByPetId(Integer pet_id);

    //5.更新当前的宠物信息
    void update(Pet pet);


}
