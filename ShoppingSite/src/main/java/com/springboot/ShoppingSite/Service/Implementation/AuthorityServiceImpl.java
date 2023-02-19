package com.springboot.ShoppingSite.Service.Implementation;

import com.springboot.ShoppingSite.Entity.Authority;
import com.springboot.ShoppingSite.Repository.AuthorityRepository;
import com.springboot.ShoppingSite.Service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    AuthorityRepository authorityRepository;

    @Override
    public Authority findAuthorityById(int id) {

        Optional<Authority> authorities = authorityRepository.findById(id);

        Authority authority = authorities.get();

        if(authority == null) throw new NotFoundException("Authority not found");

        return authority;
    }
}
