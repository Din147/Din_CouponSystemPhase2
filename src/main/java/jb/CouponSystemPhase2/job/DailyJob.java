package jb.CouponSystemPhase2.job;
import jb.CouponSystemPhase2.exception.CouponSystemException;
import jb.CouponSystemPhase2.repos.ReposCoupon;
import jb.CouponSystemPhase2.service.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


@Configuration
@EnableScheduling
public class DailyJob {
    @Autowired
    private CompanyService CompanyService;

    @Autowired
    private ReposCoupon reposCoupon;

    //@Scheduled(initialDelay = 20 * 1000, fixedDelay = 10 * 1000)   //for test
    @Scheduled(cron = "58 59 23 * * *")
    public void someJob() throws InterruptedException, CouponSystemException {

        reposCoupon.DeleteInvalidCouponsHistory();
        reposCoupon.DeleteInvalidCoupons();
        System.out.println("invalid coupons were deleted");

    }
}








