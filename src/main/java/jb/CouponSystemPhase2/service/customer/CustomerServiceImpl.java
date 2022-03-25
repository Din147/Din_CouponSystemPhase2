package jb.CouponSystemPhase2.service.customer;

import jb.CouponSystemPhase2.beans.Category;
import jb.CouponSystemPhase2.beans.Coupon;
import jb.CouponSystemPhase2.beans.Customer;
import jb.CouponSystemPhase2.exception.CouponSystemException;
import jb.CouponSystemPhase2.exception.ErrMsg;
import jb.CouponSystemPhase2.service.ClientService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
// TODO: 18-Mar-22 to read more about prototype to delete prototype
@Scope("prototype")

public class CustomerServiceImpl extends ClientService implements CustomerService {
    private int costumerId;

    @Override
    public boolean login(String email, String password) throws CouponSystemException {
        if (!reposCustomer.existsByEmailAndPassword(email, password)) {
            throw new CouponSystemException(ErrMsg.INVALID_EMAIL_OR_PASSWORD);
        }
        costumerId = reposCustomer.findByEmail(email).getId();
        return true;
    }

    @Override
    public void purchaseCoupon(Coupon coupon) throws CouponSystemException {
        Coupon couponFromDB = reposCoupon.getById(coupon.getId());

        for (Coupon check : getCustomersCoupons(costumerId)) {
            if (check.getId() == coupon.getId()) throw new CouponSystemException(ErrMsg.Already_Exist);
            if (couponFromDB.getAmount() == 0) throw new CouponSystemException(ErrMsg.NOT_Exist);
            if (couponFromDB.getEndDate().getTime() < (Date.valueOf(LocalDate.now())).getTime())
                throw new CouponSystemException(ErrMsg.DATE_NOT_VALID);
        }

        reposCustomer.getById(costumerId).getCustomerCoupons().add(coupon);
        customerVSCoupon(costumerId, coupon.getId());
        int tempAmount = couponFromDB.getAmount();
        Coupon CouponTemp = couponFromDB;
        CouponTemp.setAmount(tempAmount - 1);
        reposCoupon.saveAndFlush(CouponTemp);
    }

    @Override
    public List<Coupon> getCustomersCoupons(int costumerId) {
        return reposCustomer.getById(costumerId).getCustomerCoupons();
    }

    @Override
    public ArrayList<Coupon> getCustomersCoupons(Category category, int costumerId) {
        List<Coupon> temp = new ArrayList<Coupon>();
        for (Coupon coupon : getCustomersCoupons(costumerId)) {
            if (coupon.getCategory().toString().equals(category.toString())) temp.add(coupon);
        }
        return (ArrayList<Coupon>) temp;
    }

    @Override
    public ArrayList<Coupon> getCustomersCoupons(double maxPrice, int costumerId) {
        List<Coupon> temp = new ArrayList<Coupon>();
        for (Coupon coupon : getCustomersCoupons(costumerId)) {
            if (coupon.getPrice() <= maxPrice) temp.add(coupon);
        }
        return (ArrayList<Coupon>) temp;
    }

    @Override
    public Customer getCustomerDetails() {
        return reposCustomer.getById(costumerId);
    }

    @Override
    public void customerVSCoupon(int customerId, int couponId) {
        reposCustomer.CustomerVSCoupon(customerId, couponId);
    }
}
