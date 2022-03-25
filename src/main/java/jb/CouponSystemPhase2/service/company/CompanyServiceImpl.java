package jb.CouponSystemPhase2.service.company;

import jb.CouponSystemPhase2.beans.Company;
import jb.CouponSystemPhase2.beans.Coupon;
import jb.CouponSystemPhase2.exception.CouponSystemException;
import jb.CouponSystemPhase2.exception.ErrMsg;
import jb.CouponSystemPhase2.service.ClientService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Scope("prototype")
// TODO: 21-Mar-22 to delete prototype 
public class CompanyServiceImpl extends ClientService implements CompanyService {
    private int companyId;


    @Override
    public boolean login(String email, String password) throws CouponSystemException {
        if (!reposCompany.existsByEmailAndPassword(email, password))
            throw new CouponSystemException(ErrMsg.INVALID_EMAIL_OR_PASSWORD);
        companyId = reposCompany.findByEmail(email).getId();
        return true;
    }

    @Override
    public void addCoupon(Coupon coupon) throws CouponSystemException {
        if (reposCoupon.existsByDescription(coupon.getTitle()) && coupon.getCompany().getId() == companyId)
            throw new CouponSystemException(ErrMsg.Already_Exist);
        reposCoupon.save(coupon);
    }

    @Override
    public void updateCoupon(Coupon coupon) throws CouponSystemException {
        if (!reposCoupon.existsById(coupon.getId())) throw new CouponSystemException(ErrMsg.UPDATE_NOT_Exist_COUPONID);
        if (reposCoupon.getById(coupon.getId()).getCompany().getId() != companyId)
            throw new CouponSystemException(ErrMsg.UPDATE_NOT_Exist_COMANYID);
        reposCoupon.saveAndFlush(coupon);
    }

    @Override
    public void deleteCoupon(int couponId) throws CouponSystemException {
        if (!reposCoupon.existsById(couponId)) throw new CouponSystemException(ErrMsg.NOT_Exist);
        reposCoupon.deleteById(couponId); //!!need to delete from customer!!
    }

    @Override
    public ArrayList<Coupon> getCompanyCoupons(int companyId, String category) {
        return reposCoupon.IdAndCategory(companyId, category);
    }

    @Override
    public ArrayList<Coupon> getCompanyCoupons(int companyId, double maxPrice) {
        return (ArrayList<Coupon>) reposCoupon.findByIdAndPriceLessThanEqual(companyId, maxPrice);
    }

    @Override
    public Company getCompanyDetails() {
        return reposCompany.getById(companyId);
    }

    @Override
    public ArrayList<Coupon> getCompanyCoupons(int companyId) {
        return (ArrayList<Coupon>) reposCoupon.findByCompanyId(companyId);
    }

}
