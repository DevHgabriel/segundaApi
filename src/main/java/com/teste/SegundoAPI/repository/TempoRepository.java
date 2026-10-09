package com.teste.SegundoAPI.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;


import com.teste.SegundoAPI.model.RegintroTempo;

import java.time.OffsetDateTime;


public interface TempoRepository extends JpaRepository<RegintroTempo,Long> {

    List<RegintroTempo> findByTempo(OffsetDateTime tempo);


}
