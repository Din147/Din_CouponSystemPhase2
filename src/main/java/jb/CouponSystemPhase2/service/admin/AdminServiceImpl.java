package jb.CouponSystemPhase2.service.admin;

import jb.CouponSystemPhase2.beans.Company;
import jb.CouponSystemPhase2.beans.Customer;
import jb.CouponSystemPhase2.exception.CouponSystemException;
import jb.CouponSystemPhase2.exception.ErrMsg;
import jb.CouponSystemPhase2.service.ClientService;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
public class AdminServiceImpl extends ClientService implements AdminService {

    @Override
    public boolean login(String email, String password) throws CouponSystemException {
        if (!email.equals("admin@admin.com") || !password.equals("admin"))
            throw new CouponSystemException(ErrMsg.INVALID_EMAIL_OR_PASSWORD);
        return true;
    }

    @Override
    public void addCompany(Company company) throws CouponSystemException {
        if (reposCompany.existsByEmailAndPassword(company.getEmail(), company.getPassword()))
            throw new CouponSystemException(ErrMsg.Already_Exist);
        reposCompany.save(company);
    }

    @Override
    public void updateCompany(Company company) throws CouponSystemException {
        if (!reposCompany.existsById(company.getId())) throw new CouponSystemException(ErrMsg.NOT_Exist);
        if ((!reposCompany.existsByEmailAndPassword(company.getEmail(), company.getPassword())))
            throw new CouponSystemException(ErrMsg.INVALID_NAME_OR_EMAIL);
        reposCompany.saveAndFlush(company);
    }

    @Override
    public void deleteCompany(int companyId) throws CouponSystemException {
        if (!reposCompany.existsById(companyId)) throw new CouponSystemException(ErrMsg.NOT_Exist);
        reposCompany.deleteById(companyId);
    }

    @Override
    public List getAllCompanies() {
        return reposCompany.findAll();
    }

    @Override
    public Company getOneCompany(int companyId) throws CouponSystemException {
        return reposCompany.findById(companyId).orElseThrow(() -> new CouponSystemException(ErrMsg.NOT_Exist));
    }

    @Override
    public void addCustomer(Customer customer) throws CouponSystemException {
        if (reposCustomer.existsByEmail(customer.getEmail())) throw new CouponSystemException(ErrMsg.Already_Exist);
        reposCustomer.save(customer);
    }

    @Override
    public void updateCustomer(Customer customer) throws CouponSystemException {
        if (!reposCustomer.existsById(customer.getId())) throw new CouponSystemException(ErrMsg.NOT_Exist);
        if (!reposCustomer.existsByEmail(customer.getEmail()))
            throw new CouponSystemException(ErrMsg.INVALID_NAME_OR_EMAIL);
        reposCustomer.saveAndFlush(customer);
    }

    @Override
    public void deleteCustomer(int customerId) throws CouponSystemException {
        if (!reposCustomer.existsById(customerId)) throw new CouponSystemException(ErrMsg.NOT_Exist);
        reposCustomer.deleteById(customerId);
        reposCoupon.DeleteCustomerPurchaseHistory(customerId);
    }

    @Override
    public ArrayList<Customer> getAllCustomers() {
        return (ArrayList<Customer>) reposCustomer.findAll();
    }

    @Override
    public Customer getOneCustomer(int customerId) throws CouponSystemException {
        if (!reposCustomer.existsById(customerId)) throw new CouponSystemException(ErrMsg.NOT_Exist);
        return reposCustomer.getById(customerId);
    }
}
