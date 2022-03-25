package jb.CouponSystemPhase2.service.customer;

import jb.CouponSystemPhase2.beans.Category;
import jb.CouponSystemPhase2.beans.Coupon;
import jb.CouponSystemPhase2.beans.Customer;
import jb.CouponSystemPhase2.exception.CouponSystemException;

import java.util.ArrayList;
import java.util.List;

public interface CustomerService {

    void purchaseCoupon(Coupon coupon) throws CouponSystemException;

    List<Coupon> getCustomersCoupons(int costumerId);

    ArrayList<Coupon> getCustomersCoupons(Category category, int costumerId);

    ArrayList<Coupon> getCustomersCoupons(double maxPrice, int costumerId);

    Customer getCustomerDetails();

    void customerVSCoupon(int customerId, int couponId);

}
