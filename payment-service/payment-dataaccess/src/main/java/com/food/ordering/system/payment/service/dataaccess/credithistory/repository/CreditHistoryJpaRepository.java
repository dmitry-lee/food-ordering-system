package com.food.ordering.system.payment.service.dataaccess.credithistory.repository;

import com.food.ordering.system.payment.service.dataaccess.credithistory.entity.CreditHistoryEntity;
import com.food.ordering.system.payment.service.domain.entity.CreditHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CreditHistoryJpaRepository extends JpaRepository<CreditHistoryEntity, UUID> {

    CreditHistory save(CreditHistory creditHistory);

    Optional<List<CreditHistoryEntity>> findByCustomerId(UUID customerId);
}
