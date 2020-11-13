package com.qhit.petshop.dao;


import com.qhit.petshop.pojo.PetOwner;

import java.util.List;
/**
 * @author 王海龙
 */
public interface PetOwnerDao {
    void addPetOwner(PetOwner petOwner);
    void updatePetOwner(PetOwner petOwner);
    void deletePetOwner(Integer id);
    PetOwner findPetOwner(Integer id);
    List<PetOwner> findPetOwnerList();
    PetOwner login(String username,String password);
}
