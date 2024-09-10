package com.ca.account.manager.common.repos;

import com.ca.account.manager.common.repos.domain.IndexDatabase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndexDatabaseRepository extends JpaRepository<IndexDatabase, String> {
}
