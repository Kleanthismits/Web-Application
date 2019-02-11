package com.mitsioulis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mitsioulis.model.Attribute;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute, String> {

}
