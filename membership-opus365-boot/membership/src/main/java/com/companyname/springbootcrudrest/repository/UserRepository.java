package com.companyname.springbootcrudrest.repository;

import com.companyname.springbootcrudrest.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserAccount, String>{

}
