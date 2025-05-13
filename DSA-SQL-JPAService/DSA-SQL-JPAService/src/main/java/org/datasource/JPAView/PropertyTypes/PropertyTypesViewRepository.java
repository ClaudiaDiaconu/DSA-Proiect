package org.datasource.JPAView.PropertyTypes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyTypesViewRepository extends JpaRepository<PropertyTypesView, Integer> {
}
