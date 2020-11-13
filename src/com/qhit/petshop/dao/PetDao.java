package com.qhit.petshop.dao;


import com.qhit.petshop.pojo.Pet;

import java.util.List;
/**
 * @author 王海龙
 */
public interface PetDao {
    void addPet(Pet pet);
    void updatePet(Pet pet);
    void deletePet(Integer id);
    Pet findPet(Integer id);
    List<Pet> findPetList(); //获取所有的宠物集合
    List<Pet> findStorePetList(); //获取商店的宠物集合
    List<Pet> findPetByOwnerId(Integer ownerId); //获取当前主人所有的宠物
}
