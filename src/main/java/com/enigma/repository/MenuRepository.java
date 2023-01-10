package com.enigma.repository;

import com.enigma.model.BMenu;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<BMenu, String>{

}
