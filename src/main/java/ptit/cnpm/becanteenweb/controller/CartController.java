package ptit.cnpm.becanteenweb.controller;

import org.springframework.web.bind.annotation.*;
import ptit.cnpm.becanteenweb.helper.DatabaseHelper;
import ptit.cnpm.becanteenweb.model.Cart;
import ptit.cnpm.becanteenweb.model.Orders;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class CartController {
    @GetMapping("/carts/{id}")
    public List<Cart> getAllCartItem(@PathVariable int id) {
        List<Cart> result = new ArrayList<>();
        try {
            Connection con = DatabaseHelper.openConnection();
            CallableStatement stmt = con.prepareCall("{call SP_CART_INFO(?)}");
            stmt.setInt(1, id);
            Boolean hasResult = stmt.execute();
            if (hasResult) {
                ResultSet rs = stmt.getResultSet();

                while (rs.next()) {
                    Cart item = new Cart(rs.getInt("ORDER_ID"), rs.getInt("PRODUCT_ID"), rs.getInt("QUANTITY"),
                            rs.getString("STATUS"), rs.getNString("NAME"), rs.getDouble("PRICE"),
                            rs.getInt("IN_STOCK"), rs.getNString("DESCRIPTION"), rs.getString("IMAGE"),
                            rs.getInt("DELIVERY_ID"), rs.getInt("PAYMENT_ID"));
                    result.add(item);
                }
            }
            stmt.close();
            con.close();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return result;
    }
}
