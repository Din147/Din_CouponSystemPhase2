package jb.CouponSystemPhase2.clr;

import jb.CouponSystemPhase2.util.Art;
import jb.CouponSystemPhase2.beans.Category;
import jb.CouponSystemPhase2.beans.Company;
import jb.CouponSystemPhase2.beans.Coupon;
import jb.CouponSystemPhase2.beans.Customer;
import jb.CouponSystemPhase2.repos.ReposCompany;
import jb.CouponSystemPhase2.repos.ReposCoupon;
import jb.CouponSystemPhase2.repos.ReposCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;

@Component
@Order(1)

public class TestRepos implements CommandLineRunner {

    @Autowired
    private ReposCompany reposCompany;

    @Autowired
    private ReposCustomer reposCustomer;

    @Autowired
    private ReposCoupon reposCoupon;


    @Override
    public void run(String... args) throws Exception {

        System.out.println(Art.TESTING_REPOS);

        Company company1 = Company.builder()
                .email("Company1@gmail.com")
                .password("1 Company")
                .name("Comapny 1")
                .build();

        Company company2 = Company.builder()
                .email("Company2@gmail.com")
                .password("2 Company")
                .name("Comapny 2")
                .build();

        Company company3 = Company.builder()
                .email("Company3@gmail.com")
                .password("3 Company")
                .name("Comapny 3")
                .build();

        Customer customer1 = Customer.builder()
                .firstname("Avi 1")
                .lastname("lavi 1")
                .email("Avi1@gmail.com")
                .password("1 Avi")
                .build();

        Customer customer2 = Customer.builder()
                .firstname("Avi 2")
                .lastname("lavi 2")
                .email("Avi2@gmail.com")
                .password("2 Avi")
                .build();

        Customer customer3 = Customer.builder()
                .firstname("Avi 3")
                .lastname("lavi 3")
                .email("Avi3@gmail.com")
                .password("3 Avi")
                .build();


        Coupon coupon1 = Coupon.builder()
                .amount(100)
                .price(100.5)
                .image("cat")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate((Date.valueOf(LocalDate.now().plusDays(7))))
                .title("Copuon1 title")
                .description("coupon 1 description")
                .category(Category.Food)
                .company(reposCompany.getById(1))
                .build();

        Coupon coupon2 = Coupon.builder()
                .amount(200)
                .price(200.5)
                .image("TV")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate((Date.valueOf(LocalDate.now().plusDays(7))))
                .title("Copuon2 title")
                .description("coupon 2 description")
                .category(Category.Electricity)
                .company(reposCompany.getById(2))
                .build();


        Coupon coupon3 = Coupon.builder()
                .amount(300)
                .price(300.5)
                .image("ASAF GRANIT")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate((Date.valueOf(LocalDate.now().plusDays(7))))
                .title("Coupon3 title")
                .description("coupon 3 description")
                .category(Category.Restaurant)
                .company(reposCompany.getById(3))
                .build();


        Customer customer4 = Customer.builder()
                .firstname("Avi 4")
                .lastname("lavi 4")
                .email("Avi4@gmail.com")
                .password("4 Avi")
                .build();


        reposCompany.saveAll(Arrays.asList(company1, company2, company3));
        reposCustomer.saveAll((Arrays.asList(customer1, customer2, customer3, customer4)));
        reposCoupon.saveAll((Arrays.asList(coupon1, coupon2, coupon3)));


    }
}
