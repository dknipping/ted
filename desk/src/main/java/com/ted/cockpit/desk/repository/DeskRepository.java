package com.ted.cockpit.desk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ted.cockpit.desk.entity.Desk;

public interface DeskRepository extends JpaRepository<Desk, Long> {

	Desk findByDeskNumber(Integer deskNumber);
}
