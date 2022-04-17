package jb.CouponSystemPhase2.beans;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "FirstName", nullable = false)
    private String firstname;

    @Column(name = "LastName", nullable = false)
    private String lastname;

    @Column(name = "Email", updatable = false, nullable = false)
    private String email;

    @Column(name = "Password", updatable = false, nullable = false)
    private String password;


    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.ALL)
    @Singular
    private List<Coupon> customerCoupons = new ArrayList<>();


}


