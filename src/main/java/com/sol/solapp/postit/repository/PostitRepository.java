package com.sol.solapp.postit.repository;

import com.sol.solapp.postit.entity.Postit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostitRepository extends JpaRepository<Postit, Long> {

    Page<Postit> findAllByOrderByUpdateDateDesc(Pageable pageable);

    Optional<Postit> findById(Long id);
   
}
