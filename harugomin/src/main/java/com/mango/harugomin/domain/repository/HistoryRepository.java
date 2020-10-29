package com.mango.harugomin.domain.repository;

import com.mango.harugomin.domain.entity.History;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface HistoryRepository extends JpaRepository<History, Long>, HistoryRepositoryCustom{
    Page<History> findAllByUserUserId(Long userId, Pageable pageable);
}
