package org.datasource.JPAView.Cities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitiesViewRepository extends JpaRepository<CitiesView, Integer> {
}