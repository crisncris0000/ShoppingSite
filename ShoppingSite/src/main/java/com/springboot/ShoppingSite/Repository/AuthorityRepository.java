package com.springboot.ShoppingSite.Repository;

import com.springboot.ShoppingSite.Entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

}
