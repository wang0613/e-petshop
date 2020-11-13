package com.qhit.petshop.service.impl;


import com.qhit.petshop.dao.PetStoreDao;
import com.qhit.petshop.dao.impl.PetStoreDaoImpl;
import com.qhit.petshop.pojo.PetStore;
import com.qhit.petshop.service.PetService;
import com.qhit.petshop.service.PetStoreService;

import java.util.List;

/**
 * @author 王海龙
 */
public class PetStoreServiceImpl implements PetStoreService {

    PetStoreDao dao = new PetStoreDaoImpl();

    /**
     * 查询所有的商店
     *
     * @return
     */
    @Override
    public List<PetStore> findPetStoreList() {
        return dao.findPetStoreList();
    }

    /**
     * 商店的登录方法
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public PetStore login(String username, String password) {
        return dao.login(username, password);
    }

    /**
     * 查询出对应的商店
     * @param petStore_id
     * @return
     */
    @Override
    public PetStore findPetStore(Integer petStore_id) {
        return dao.findPetStore(petStore_id);
    }

    @Override
    public void updateStore(PetStore petStore) {
        dao.updatePetStore(petStore);
    }
}
