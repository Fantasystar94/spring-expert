package org.example.expert.domain.todo.repository;

import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;
/*
## v 3. N+1 문제 `필수`

        - `TodoController`와 `TodoService`를 통해 `Todo` 관련 데이터를 처리합니다.
- 여기서 N+1 문제가 발생할 수 있는 시나리오는 `getTodos` 메서드에서 모든 Todo를 조회할 때, 각 Todo와 연관된 데이터를 개별적으로 가져오는 경우입니다.
- 요구사항:
        - JPQL `fetch join`을 사용하여 N+1 문제를 해결하고 있는 `TodoRepository`가 있습니다. 이를 동일한 동작을 하는 `@EntityGraph` 기반의 구현으로 수정해주세요.
 */
public interface TodoRepository extends JpaRepository<Todo, Long> {

//    @Query("SELECT t FROM Todo t LEFT JOIN FETCH t.user u ORDER BY t.modifiedAt DESC")
    //todo 에서 fetch join 으로 todo.user 만 조회
    @EntityGraph(attributePaths = "user")
    Page<Todo> findAll(Pageable pageable);
//    Page<Todo> findAllByOrderByModifiedAtDesc(Pageable pageable);

    @Query("SELECT t FROM Todo t " +
            "LEFT JOIN FETCH t.user " +
            "WHERE t.id = :todoId")
    Optional<Todo> findByIdWithUser(@Param("todoId") Long todoId);

    int countById(Long todoId);
}
