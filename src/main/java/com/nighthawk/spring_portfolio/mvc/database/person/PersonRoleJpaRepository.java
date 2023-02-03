package com.nighthawk.spring_portfolio.mvc.database.person;

import org.springframework.data.jpa.repository.JpaRepository;

public interface  PersonRoleJpaRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}

