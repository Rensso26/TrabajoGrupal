package uce.grupal.history.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import uce.grupal.history.History;

public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findByUserId(Long userId);
}