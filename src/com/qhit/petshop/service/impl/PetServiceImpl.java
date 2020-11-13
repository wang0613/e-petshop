package com.qhit.petshop.service.impl;

import com.qhit.petshop.dao.PetDao;
import com.qhit.petshop.dao.impl.PetDaoImpl;
import com.qhit.petshop.pojo.Pet;
import com.qhit.petshop.service.PetService;

import java.util.List;
/**
 * @author 王海龙
 */
public class PetServiceImpl implements PetService {

    PetDao petDao = new PetDaoImpl();

    /**
     * 获取所有的宠物信息
     * @return
     */
    @Override
    public List<Pet> getPetList() {
        List<Pet> list = petDao.findPetList();
        return list;
    }

    /**
     * 获取商店宠物信息
     * @return
     */
    @Override
    public List<Pet> getStorePetList() {
        List<Pet> list = petDao.findStorePetList();
        return list;
    }

    /**
     * 查询当前主人有哪些宠物
     * @param ownerId
     * @return
     */
    @Override
    public List<Pet> getPetListByOwnerId(Integer ownerId) {
        List<Pet> pets = petDao.findPetByOwnerId(ownerId);

        return pets;
    }
    /**
     * 根据id获取当前宠物
     */
    @Override
    public Pet findByPetId(Integer pet_id) {

        return petDao.findPet(pet_id);
    }

    /*更新pet信息
     */
    @Override
    public void update(Pet pet) {
        petDao.updatePet(pet);
    }


}
