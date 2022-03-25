package jb.CouponSystemPhase2.service.admin;

import jb.CouponSystemPhase2.beans.Company;
import jb.CouponSystemPhase2.beans.Customer;
import jb.CouponSystemPhase2.exception.CouponSystemException;


import java.util.ArrayList;
import java.util.List;

public interface AdminService {
    void addCompany(Company company) throws CouponSystemException;

    void updateCompany(Company company) throws CouponSystemException;

    void deleteCompany(int companyId) throws CouponSystemException;

    List<Company> getAllCompanies();

    Company getOneCompany(int companyId) throws CouponSystemException;

    void addCustomer(Customer customer) throws CouponSystemException;

    void updateCustomer(Customer Customer) throws CouponSystemException;

    void deleteCustomer(int customerId) throws CouponSystemException;

    ArrayList<Customer> getAllCustomers();

    Customer getOneCustomer(int customerId) throws CouponSystemException;


}
