package org.datasource.JPAView.Owner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "owners_view")
public class OwnerView {

    @Id
    private Integer id;
    private String fullName;
    private String email;
    private String phone;
    private Date createdAt;

    public Integer getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public Date getCreatedAt() { return createdAt; }

    // constructori, toString(), etc. – opțional
}


