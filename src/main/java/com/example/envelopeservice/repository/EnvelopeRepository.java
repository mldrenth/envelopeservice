package com.example.envelopeservice.repository;

import com.example.envelopeservice.models.Envelope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnvelopeRepository extends JpaRepository<Envelope, Long> {
}
