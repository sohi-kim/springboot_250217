
package org.iclass.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iclass.board.model.Todo2Entity;
import org.iclass.board.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class TodoService {

	private final TodoRepository repository;

	// entity 객체 검사
	private void validate(final Todo2Entity entity) {
		if(entity == null) {
			log.warn("Entity cannot be null.");
			throw new RuntimeException("Entity cannot be null.");
		}

		if(entity.getUserId() == null) {
			log.warn("Unknown user.");
			throw new RuntimeException("Unknown user.");
		}
	}


	public List<Todo2Entity> create(final Todo2Entity entity) {
		validate(entity);
		repository.save(entity);
		log.info("Entity Id : {} is saved.", entity.getId());
		return repository.findByUserId(entity.getUserId());
	}

	// 유저의 TodoEntity 목록 가져오기
	public List<Todo2Entity> retrieve(final String userId) {
		return repository.findByUserId(userId);
	}

	// entity 1개를 업데이트 하고 변경된 내용을 반영한 사용자의 TodoEntity 리스트를 리턴한다.
	public List<Todo2Entity> update(final Todo2Entity entity) {
		validate(entity);

		// 넘겨받은 엔티티 id를 이용해 TodoEntity 를 가져온다. 존재하지 않는 엔티티는 업데이트 할 수 없기 때문이다.
		final Optional<Todo2Entity> original = repository.findById(entity.getId());

		original.ifPresent(todo -> {
			todo.setTitle(entity.getTitle());
			todo.setDueDate(entity.getDueDate());
			todo.setDone(entity.isDone());
			repository.save(todo);
		});

		// retrieve 메소드를 실행하여 유저의 모든 TodoEntity 리스트를 리턴한다.
		return retrieve(entity.getUserId());
	}

	// entity 1개를 삭제 하고 삭제를 반영한 사용자의 TodoEntity 리스트를 리턴한다.
	public List<Todo2Entity> delete(final Todo2Entity entity) {
		validate(entity);

		try {
			repository.delete(entity);
		} catch(Exception e) {
			log.error("error deleting entity : {}, {}", entity.getId(), e.getMessage());
			throw new RuntimeException("error deleting entity :{}" + entity.getId());
		}
		// retrieve 메소드를 실행하여 유저의 모든 TodoEntity 리스트를 리턴한다.
		return retrieve(entity.getUserId());
	}

}
