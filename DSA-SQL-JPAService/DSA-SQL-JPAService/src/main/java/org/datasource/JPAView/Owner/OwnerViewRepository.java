package org.datasource.JPAView.Owner;


import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerViewRepository extends JpaRepository<OwnerView, Integer> {
    // optional: metode personalizate
}