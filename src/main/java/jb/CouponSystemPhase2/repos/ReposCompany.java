package jb.CouponSystemPhase2.repos;

import jb.CouponSystemPhase2.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReposCompany extends JpaRepository<Company, Integer> {

    boolean existsByEmailAndPassword(String email, String password);

    Company findByEmail(String email);
}