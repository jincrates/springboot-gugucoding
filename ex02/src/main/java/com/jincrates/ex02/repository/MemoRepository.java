package com.jincrates.ex02.repository;

import com.jincrates.ex02.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoRepository extends JpaRepository<Memo, Long> {
}
