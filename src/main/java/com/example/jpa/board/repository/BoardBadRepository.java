package com.example.jpa.board.repository;

import com.example.jpa.board.entity.BoardBadReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardBadRepository extends JpaRepository<BoardBadReport, Long> {
}
