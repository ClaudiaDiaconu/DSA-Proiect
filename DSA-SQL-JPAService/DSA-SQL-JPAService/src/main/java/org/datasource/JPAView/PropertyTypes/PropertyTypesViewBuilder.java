package org.datasource.JPAView.PropertyTypes;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Component
public class PropertyTypesViewBuilder {
    @PersistenceContext
    private EntityManager entityManager;

    public List<PropertyTypesView> build() {
        return entityManager
                .createQuery("SELECT p FROM PropertyTypesView p", PropertyTypesView.class)
                .getResultList();
    }
}
