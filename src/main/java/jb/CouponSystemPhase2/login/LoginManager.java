package jb.CouponSystemPhase2.login;

import jb.CouponSystemPhase2.exception.CouponSystemException;
import jb.CouponSystemPhase2.service.ClientService;
import jb.CouponSystemPhase2.service.admin.AdminService;
import jb.CouponSystemPhase2.service.admin.AdminServiceImpl;
import jb.CouponSystemPhase2.service.company.CompanyServiceImpl;
import jb.CouponSystemPhase2.service.customer.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Scope("prototype")
@Service
public class LoginManager {
    @Autowired
    private ApplicationContext ctx;

    public ClientService login(String email, String password, ClientType clientType) throws CouponSystemException {
        switch (clientType) {
            case Administrator:
                AdminServiceImpl adminServiceImpl = ctx.getBean(AdminServiceImpl.class);
                if (adminServiceImpl.login(email, password)) return adminServiceImpl;
            case Company:
                CompanyServiceImpl companyServiceImpl = ctx.getBean(CompanyServiceImpl.class);
                if (companyServiceImpl.login(email, password)) return companyServiceImpl;
            case Customer:
                CustomerServiceImpl customerServiceImpl = ctx.getBean(CustomerServiceImpl.class);
                if (customerServiceImpl.login(email, password)) return customerServiceImpl;
        }
        return null;
    }
}




