package jb.CouponSystemPhase2.service.company;

import jb.CouponSystemPhase2.beans.Company;
import jb.CouponSystemPhase2.beans.Coupon;
import jb.CouponSystemPhase2.exception.CouponSystemException;

import java.util.ArrayList;

public interface CompanyService {
    void addCoupon(Coupon coupon) throws CouponSystemException;

    void updateCoupon(Coupon coupon) throws CouponSystemException;

    void deleteCoupon(int couponId) throws CouponSystemException;

    ArrayList<Coupon> getCompanyCoupons(int companyId, String category);

    ArrayList<Coupon> getCompanyCoupons(int companyId, double maxPrice);

    Company getCompanyDetails();

    ArrayList<Coupon> getCompanyCoupons(int companyId);


}
