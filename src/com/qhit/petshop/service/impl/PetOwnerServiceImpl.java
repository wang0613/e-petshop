package com.qhit.petshop.service.impl;

import com.qhit.petshop.dao.PetOwnerDao;
import com.qhit.petshop.dao.impl.PetOwnerDaoImpl;
import com.qhit.petshop.pojo.Account;
import com.qhit.petshop.pojo.Pet;
import com.qhit.petshop.pojo.PetOwner;
import com.qhit.petshop.pojo.PetStore;
import com.qhit.petshop.service.AccountService;
import com.qhit.petshop.service.PetOwnerService;
import com.qhit.petshop.service.PetService;
import com.qhit.petshop.service.PetStoreService;

import java.util.List;
/**
 * @author 王海龙
 */
public class PetOwnerServiceImpl implements PetOwnerService {

    private PetOwnerDao petOwnerDao = new PetOwnerDaoImpl();
    private PetStoreService petStoreService = new PetStoreServiceImpl();
    private PetService petService = new PetServiceImpl();
    private AccountService accountService = new AccountServiceImpl();

    /**
     * 查询所有的主人
     *
     * @return
     */
    @Override
    public List<PetOwner> findAllPetOwner() {
        List<PetOwner> owners = petOwnerDao.findPetOwnerList();
        return owners;
    }

    /**
     * 主人的登录方法
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public PetOwner login(String username, String password) {
        PetOwner petOwner = petOwnerDao.login(username, password);
        return petOwner;
    }

    /**
     * 买宠物
     *
     * @param pet_id
     * @param owner_id
     */
    @Override
    public void buyer(Integer pet_id, Integer owner_id) {
        //1.通过pet_id获取当前宠物
        Pet pet = petService.findByPetId(pet_id);
        //通过pet中的owner_id获取主人实体
        PetOwner petOwner = petOwnerDao.findPetOwner(owner_id);
        //通过pet中的store_id获取商店实体
        PetStore petStore = petStoreService.findPetStore(pet.getStore_id());

        //1.添加账目 Account
        Account account = new Account();
        account.setDeal_type(1);
        account.setPet_id(pet_id);
        account.setSeller_id(owner_id);  //设置买家为主人id
        account.setBuyer_id(pet.getStore_id()); //设置卖家为商店的id
        account.setPrice(50);

        //2.执行添加账户操作
        accountService.addAccount(account);

        //2.修改宠物的owner_id = 参数owner_id   store_id = null;
        pet.setOwner_id(owner_id);
        pet.setStore_id(null);
        //3.提交更新宠物
        petService.update(pet);

        //1.执行主人的扣钱 执行更新操作
        petOwner.setMoney(petOwner.getMoney() - 50);
        petOwnerDao.updatePetOwner(petOwner);

        //2.执行商店的加钱 执行更新操作
        petStore.setBalance(petStore.getBalance() + 50);
        petStoreService.updateStore(petStore);
    }

    /**
     * 麦宠物
     * @param pet_id
     * @param store_id
     * @param owner_id
     */
    @Override
    public void seller(Integer pet_id, Integer store_id, Integer owner_id) {

        //1.根据pet_id获取pet实体
        Pet pet = petService.findByPetId(pet_id);
        //根据owner_id获取主人实体
        PetOwner petOwner = petOwnerDao.findPetOwner(owner_id);
        //根据store_id获取商店实体
        PetStore petStore = petStoreService.findPetStore(store_id);

        //2.修改pet中的属性，owner_id = null; store_id =store_id
        pet.setStore_id(store_id);
        pet.setOwner_id(null);
        //3.执行更新pet操作
        petService.update(pet);

        //1.创建Account 设置数据 执行添加操作
        Account account  = new Account();
        account.setPet_id(pet_id);
        account.setDeal_type(2);  //2 主人卖个商店
        account.setSeller_id(store_id);  //设置买家为商店
        account.setBuyer_id(owner_id); //卖家为主人
        account.setPrice(80);

        accountService.addAccount(account);

        //2.  执行商店-money操作
        petStore.setBalance(petStore.getBalance()-80);
        petStoreService.updateStore(petStore);

        //3.  执行主人+money操作
        petOwner.setMoney(petOwner.getMoney()+80);
        petOwnerDao.updatePetOwner(petOwner);

    }
}
