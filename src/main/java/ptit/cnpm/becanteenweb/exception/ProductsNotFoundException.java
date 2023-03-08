package ptit.cnpm.becanteenweb.exception;

public class ProductsNotFoundException extends RuntimeException {
    public ProductsNotFoundException(String name) {
        super("Could not found the product " + name);
    }
}
