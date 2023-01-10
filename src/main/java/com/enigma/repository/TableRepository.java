package com.enigma.repository;

import com.enigma.model.BTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository<BTable, String> {
}
