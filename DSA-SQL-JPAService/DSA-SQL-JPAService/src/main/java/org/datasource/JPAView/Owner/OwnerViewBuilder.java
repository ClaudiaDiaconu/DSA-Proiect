package org.datasource.JPAView.Owner;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerViewBuilder {

        private final OwnerViewRepository repository;

        public OwnerViewBuilder(OwnerViewRepository repository) {
            this.repository = repository;
        }

        public List<OwnerView> findAll() {
            return repository.findAll();
        }

        public Optional<OwnerView> findById(Integer id) {
            return repository.findById(id);
        }
    }
