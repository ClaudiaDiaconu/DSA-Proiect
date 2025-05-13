package org.datasource.jdbc.views.customers;
import java.util.Date;
import lombok.Value;

@Value
public class CustomerView {
    Integer id;
    String customerCode;
    String fullName;
    String email;
    String phone;
    Date registrationDate;
    String country;
    String city;
}
