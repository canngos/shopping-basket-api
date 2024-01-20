package com.canngos.shoppingbasketservice.repository;

import com.canngos.shoppingbasketservice.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, String> {
}
