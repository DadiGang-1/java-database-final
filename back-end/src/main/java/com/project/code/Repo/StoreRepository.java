package com.project.code.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.code.Model.Store;

public interface StoreRepository extends JpaRepository<Store, Long>{
    Optional<Store> findById(Long id);

    @Query("SELECT p FROM Store p WHERE LOWER(p.name) LIKE(CONCAT('%', :pname, '%'))")
    List<Store> findBySubName(String pname);

}
