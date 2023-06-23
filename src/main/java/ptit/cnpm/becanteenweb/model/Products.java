package ptit.cnpm.becanteenweb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PRODUCTS")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    private String name;
    private Double price;
    private int quantity;
    private String description;
    private String image;
    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    Collection<OrderDetails> orderDetails;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "PRODUCT_DETAILS",
            joinColumns = {@JoinColumn(name = "productId")},
            inverseJoinColumns = {@JoinColumn(name = "categoryId")})
    @JsonIgnore
    private Collection<Category> categories;
}
