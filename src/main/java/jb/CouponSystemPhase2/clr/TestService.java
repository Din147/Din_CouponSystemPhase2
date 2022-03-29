package jb.CouponSystemPhase2.clr;

import jb.CouponSystemPhase2.beans.Category;
import jb.CouponSystemPhase2.beans.Company;
import jb.CouponSystemPhase2.beans.Coupon;
import jb.CouponSystemPhase2.beans.Customer;
import jb.CouponSystemPhase2.util.Art;
import jb.CouponSystemPhase2.util.TablePrinter;
import jb.CouponSystemPhase2.login.ClientType;
import jb.CouponSystemPhase2.login.LoginManager;
import jb.CouponSystemPhase2.service.admin.AdminService;
import jb.CouponSystemPhase2.service.company.CompanyService;
import jb.CouponSystemPhase2.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Component
@Order(2)
public class TestService implements CommandLineRunner {

    /**
     * to test the Daily Job, please go to DailyJob class and choose //        @Scheduled(initialDelay = 20*1000, fixedDelay = 10 * 1000)   //for test
     */
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RED = "\u001B[31m";

    @Autowired
    private LoginManager loginManager;

    @Override
    public void run(String... args) throws Exception {


        System.out.println(Art.TESTING_SERVICES);
        System.out.println("");

        Company company4 = Company.builder()
                .email("Company4@gmail.com")
                .password("4 Company")
                .name("Comapny 4")
                .build();

        Coupon coupon44 = Coupon.builder()
                .company(company4).amount(44)
                .category(Category.Food)
                .description("44 coupon")
                .image("44 image")
                .price(44)
                .title("44 title")
                .startDate(Date.valueOf(LocalDate.of(2022, 1, 1)))
                .endDate(Date.valueOf(LocalDate.of(2023, 4, 1)))
                .build();

        Coupon coupon55 = Coupon.builder()
                .company(company4)
                .amount(55)
                .category(Category.Restaurant)
                .description("55 coupon")
                .image("55 image")
                .price(55)
                .title("55 title")
                .startDate(Date.valueOf(LocalDate.of(2022, 1, 1)))
                .endDate(Date.valueOf(LocalDate.of(2023, 4, 1)))
                .build();

        Customer customer5 = Customer.builder()
                .email("Avi5@gmail.com")
                .firstname("Avi 5")
                .lastname("lavi 5")
                .password("5 Avi")
                .build();


        Customer customer6 = Customer.builder()
                .email("Avi6@gmail.com")
                .firstname("Avi 6")
                .lastname("lavi 6")
                .password("6 Avi")
                .build();

        Customer customer7 = Customer.builder()
                .email("Avi7@gmail.com")
                .firstname("Avi 7")
                .lastname("lavi 7")
                .password("7 Avi")
                .build();

        /**-------------------------------------------------------------TEST ADMIN -------------------------------------------------------------------------------------------------*/
        System.out.println(ANSI_YELLOW);
        System.out.println(Art.ADMIN_TEST);
        System.out.println(ANSI_WHITE);

        System.out.println(Art.T_LOGIN);

        System.out.println(loginManager.login("admin@admin.com", "admin", ClientType.Administrator)); //need to return address

        try {
            System.out.println(loginManager.login("aadmin@admin.com", "admin", ClientType.Administrator)); //wrong email
        } catch (Exception e) {
            System.out.println(ANSI_RED);
            System.out.println(e);
            System.out.println(ANSI_WHITE);

        }

        AdminService admin = (AdminService) loginManager.login("admin@admin.com", "admin", ClientType.Administrator);

        System.out.println(Art.T_ADD);
        admin.addCompany(company4);
        try {
            admin.addCompany(company4); //can't add the same company
        } catch (Exception e) {
            System.out.println(ANSI_RED);
            System.out.println(e);
            System.out.println(ANSI_WHITE);


        }

        System.out.println(Art.T_GET_ALL);
        TablePrinter.print(admin.getAllCompanies());

        System.out.println(Art.T_DELETE);

        admin.deleteCompany(2);
        try {
            admin.deleteCompany(6); // id not exist
        } catch (Exception e) {
            System.out.println(ANSI_RED);
            System.out.println(e);
            System.out.println(ANSI_WHITE);

        }

        TablePrinter.print(admin.getAllCompanies());

        System.out.println(Art.T_UPDATE);
        company4.setName("Company 44");
        admin.updateCompany(company4);


        try {
            company4.setEmail("test@test"); // it's not allowed to change email
            admin.updateCompany(company4);

        } catch (Exception e) {
            System.out.println(ANSI_RED);
            System.out.println(e);
            System.out.println(ANSI_WHITE);

        }

        TablePrinter.print(admin.getOneCompany(4));

        /**-------------------------------------------------------------TEST ADMIN -> Customer---------------------------------------------------------------------------------------------*/
        System.out.println(Art.T_ADD);
        admin.addCustomer(customer5);
        admin.addCustomer(customer6);

        try {
            admin.addCustomer(customer6); //can't add twice the same customer
        } catch (Exception e) {
            System.out.println(ANSI_RED);
            System.out.println(e);
            System.out.println(ANSI_WHITE);

        }

        System.out.println(Art.T_UPDATE);
        customer6.setLastname("LastNameUpdated");
        admin.updateCustomer(customer6);
        try {
            admin.updateCustomer(customer7); // customer not exist so you can't update it
        } catch (Exception e) {
            System.out.println(ANSI_RED);
            System.out.println(e);
            System.out.println(ANSI_WHITE);

        }
        System.out.println(Art.T_DELETE);
        admin.deleteCustomer(3);
        try {
            admin.deleteCustomer(8); //can't delete customer that not exist
        } catch (Exception e) {
            System.out.println(ANSI_RED);
            System.out.println(e);
            System.out.println(ANSI_WHITE);

        }

        System.out.println(Art.T_GET_ALL);
        TablePrinter.print(admin.getAllCustomers());

        System.out.println(Art.T_GET_ONE);
        TablePrinter.print(admin.getOneCustomer(1));
        try {
            System.out.println(admin.getOneCustomer(8)); //id not exist
        } catch (Exception e) {
            System.out.println(ANSI_RED);
            System.out.println(e);
            System.out.println(ANSI_WHITE);

        }
/**-------------------------------------------------------------TEST COMPANY---------------------------------------------------------------------------------------------*/
        System.out.println(ANSI_YELLOW);
        System.out.println(Art.Company_TEST);
        System.out.println(ANSI_WHITE);
        System.out.println(Art.T_LOGIN);

        System.out.println(loginManager.login("Company1@gmail.com", "1 Company", ClientType.Company));  //need to print address
        try {
            System.out.println(loginManager.login("cCompany1@gmail.com", "1 Company", ClientType.Company));  //error by email/password
        } catch (Exception e) {
            System.out.println(ANSI_RED);
            System.out.println(e);
            System.out.println(ANSI_WHITE);
        }

        CompanyService company = (CompanyService) loginManager.login("Company1@gmail.com", "1 Company", ClientType.Company);

        System.out.println(Art.T_ADD);
        company.addCoupon(coupon44);
        company.addCoupon(coupon55);
        TablePrinter.print(company.getCompanyCoupons(1));

        System.out.println(Art.T_GET_ALL);
        TablePrinter.print(company.getCompanyCoupons(4));
        System.out.println("------------by price----------------");
        TablePrinter.print(company.getCompanyCoupons(4, 50.0));
        System.out.println("------------by category----------------");
        TablePrinter.print(company.getCompanyCoupons(1, Category.Food.toString()));


        System.out.println(Art.T_DELETE);
        company.deleteCoupon(1);
        try {
            company.deleteCoupon(10); //can't delete not exist coupon   --
        } catch (Exception e) {
            System.out.println(ANSI_RED);
            System.out.println(e);
            System.out.println(ANSI_WHITE);

        }
        TablePrinter.print(company.getCompanyCoupons(1));
        System.out.println(Art.T_UPDATE);

        CompanyService company_4 = (CompanyService) loginManager.login("Company4@gmail.com", "4 Company", ClientType.Company);

        coupon55.setPrice(556);
        company_4.updateCoupon(coupon44);
        TablePrinter.print(company_4.getCompanyCoupons(company4.getId()));

        System.out.println(Art.T_GET_ONE);
        TablePrinter.print(company_4.getCompanyDetails());

        /**-------------------------------------------------------------TEST CUSTOMER---------------------------------------------------------------------------------------------*/
        System.out.println(ANSI_YELLOW);
        System.out.println(Art.Customer_TEST);
        System.out.println(ANSI_WHITE);
        System.out.println(Art.T_LOGIN);

        System.out.println(loginManager.login("Avi1@gmail.com", "1 Avi", ClientType.Customer)); //need to print address
        try {
            System.out.println(loginManager.login("aAvi1@gmail.com", "1 Avi", ClientType.Customer)); //wrong email/password
        } catch (Exception e) {
            System.out.println(ANSI_RED);
            System.out.println(e);
            System.out.println(ANSI_WHITE);

        }

        CustomerService customer = (CustomerService) loginManager.login("Avi1@gmail.com", "1 Avi", ClientType.Customer);

        System.out.println(Art.T_ADD);
        System.out.println("below suppose to be null/empty");
        TablePrinter.print(customer.getCustomersCoupons(1));
        customer.purchaseCoupon(coupon44);
        try {
            customer.purchaseCoupon(coupon44);   //failure to purchase same twice
        } catch (Exception e) {
            System.out.println(ANSI_RED);
            System.out.println(e);
            System.out.println(ANSI_WHITE);

        }

        customer.purchaseCoupon(coupon55);
        TablePrinter.print(customer.getCustomersCoupons(1));

        System.out.println(Art.T_GET_ALL);
        TablePrinter.print(customer.getCustomersCoupons(1));
        System.out.println("------------by price----------------");
        TablePrinter.print(customer.getCustomersCoupons(45, 1));
        System.out.println("------------by Category-------------");
        TablePrinter.print(customer.getCustomersCoupons(Category.Restaurant, 1));


        TablePrinter.print(customer.getCustomerDetails());


/**-------------------------------------------------------------TEST Thread---------------------------------------------------------------------------------------------*/


        Thread.sleep(5 * 1000);
        CompanyService company__4 = (CompanyService) loginManager.login("Company4@gmail.com", "4 Company", ClientType.Company);
        coupon55.setEndDate(Date.valueOf(LocalDate.of(2022, 3, 4)));
        company__4.updateCoupon(coupon55);
        List<Coupon> couponss = customer.getCustomersCoupons(1);
        couponss.get(0).setEndDate(Date.valueOf(LocalDate.of(2022, 3, 4)));
        company__4.updateCoupon(couponss.get(0));


    }


}

