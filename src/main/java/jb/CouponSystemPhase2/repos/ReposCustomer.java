package jb.CouponSystemPhase2.repos;


import jb.CouponSystemPhase2.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Repository
public interface ReposCustomer extends JpaRepository<Customer, Integer> {
    boolean existsByEmail(String email);

    boolean existsByEmailAndPassword(String email, String password);

    Customer findByEmail(String email);

    @Modifying
    @Query(value = "SELECT customerCoupons_id FROM customers_coupons\n" +
            "WHERE customers_id=?", nativeQuery = true)
    ArrayList<Integer> PullCouponsCustomer(int customerId);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO customers_coupons (customers_id, customerCoupons_id) VALUES (?,?)", nativeQuery = true)
    void CustomerVSCoupon(int customerId, int couponId);


}
