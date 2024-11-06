package com.es.diecines.Repository;

import com.es.diecines.Model.Sesion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SesionRepository extends JpaRepository<Sesion, Long> {}
