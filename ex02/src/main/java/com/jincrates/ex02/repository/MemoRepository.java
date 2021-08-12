package com.jincrates.ex02.repository;

import com.jincrates.ex02.entity.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {

    List<Memo> findByMnoBetweenOrderByMnoDesc(Long from, Long to);

    Page<Memo> findByMnoBetween(Long from, Long to, Pageable pageable);

    void deleteMemoByMnoLessThan(Long num);

    //간단한 쿼리들은 위와 같은 쿼리 메서드를 사용할 수 있지만, join이나 복잡한 조건을 처리해야할 때는
    //@Query 어노테이션을 이용하는 경우가 더 많다고 한다.
    @Query("select m from Memo m order by m.mno desc")
    List<Memo> getListDesc();

    @Transactional
    @Modifying
    @Query("update Memo m set m.memoText = :memoText where m.mno = :mno")  //파라미터 바인딩
    int updateMemoText(@Param("mno") Long mno, @Param("memoText") String memoText);
}
