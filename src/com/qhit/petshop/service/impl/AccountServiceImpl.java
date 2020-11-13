package com.qhit.petshop.service.impl;

import com.qhit.petshop.dao.AccountDao;
import com.qhit.petshop.dao.impl.AccountDaoImpl;
import com.qhit.petshop.pojo.Account;
import com.qhit.petshop.service.AccountService;
/**
 * @author 王海龙
 */
public class AccountServiceImpl implements AccountService {

    AccountDao accountDao = new AccountDaoImpl();

    @Override
    public void addAccount(Account account) {
        accountDao.addAccount(account);
    }
}
