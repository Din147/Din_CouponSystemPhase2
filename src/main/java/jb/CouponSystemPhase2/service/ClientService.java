package jb.CouponSystemPhase2.service;

import jb.CouponSystemPhase2.exception.CouponSystemException;
import jb.CouponSystemPhase2.repos.ReposCompany;
import jb.CouponSystemPhase2.repos.ReposCoupon;
import jb.CouponSystemPhase2.repos.ReposCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("Prototype")
public abstract class ClientService {

    @Autowired
    protected ReposCompany reposCompany;

    @Autowired
    protected ReposCustomer reposCustomer;

    @Autowired
    protected ReposCoupon reposCoupon;

    public abstract boolean login(String email, String password) throws CouponSystemException;


}