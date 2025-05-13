package org.datasource.JPAView.Cities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cities_VIEW")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CitiesView {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "state")
    private String state;

    @Column(name = "zip_code")
    private String zipCode;

}
