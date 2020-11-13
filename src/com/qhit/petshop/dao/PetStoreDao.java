package com.qhit.petshop.dao;


import com.qhit.petshop.pojo.PetStore;

import java.util.List;
/**
 * @author 王海龙
 */
public interface PetStoreDao {

    void addPetStore(PetStore petStore);
    void updatePetStore(PetStore petStore);
    void deletePetStore(Integer id);
    PetStore findPetStore(Integer id);
    List<PetStore> findPetStoreList();
    PetStore login(String username,String password);
}
