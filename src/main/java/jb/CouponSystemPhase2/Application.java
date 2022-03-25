package jb.CouponSystemPhase2;

import jb.CouponSystemPhase2.job.DailyJob;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {


    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(Application.class, args);
//        DailyJob dailyJob = new DailyJob();
        System.out.println("Run successful");

    }

}
