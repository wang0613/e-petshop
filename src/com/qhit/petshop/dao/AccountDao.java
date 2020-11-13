package com.qhit.petshop.dao;

import com.qhit.petshop.pojo.Account;

import java.util.List;
/**
 * @author 王海龙
 */
public interface AccountDao {

    void addAccount(Account account);
    void updateAccount(Account account);
    void deleteAccount(Integer id);
    Account findAccount(Integer id);
    List<Account> findAccountList();

}
