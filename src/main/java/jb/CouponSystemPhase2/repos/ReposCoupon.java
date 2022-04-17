package jb.CouponSystemPhase2.repos;


import jb.CouponSystemPhase2.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface ReposCoupon extends JpaRepository<Coupon, Integer> {

    boolean existsByDescription(String Name);

    List<Coupon> findByCompanyId(int CompanyId);

    List<Coupon> findByIdAndPriceLessThanEqual(int CompanyId, double maxPrice);

    @Query(value = "SELECT * FROM coupons WHERE Category= category AND company_id = ? ", nativeQuery = true)
    ArrayList<Coupon> IdAndCategory(int companyId, String category);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM coupons WHERE EndDate< now()", nativeQuery = true)
    void DeleteInvalidCoupons();

    @Transactional
    @Modifying
    @Query(value = "delete from customers_coupons\n" +
            "where customerCoupons_id in ( select id from coupons where EndDate<now()" +
            ")", nativeQuery = true)
    void DeleteInvalidCouponsHistory();


    @Transactional
    @Modifying
    @Query(value = "DELETE FROM customers_coupons WHERE customers_id = ?", nativeQuery = true)
    void DeleteCustomerPurchaseHistory(int customers_id);


    @Transactional
    @Modifying
    @Query(value = "UPDATE coupons SET Amount = (select amount - 1) WHERE id=?", nativeQuery = true)
    void purchasedCouponAmountMinusOne(int couponsId);
}


