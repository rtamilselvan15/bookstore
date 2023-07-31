package AseFirstApplication.com.acc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import AseFirstApplication.com.acc.entities.BookEntity;
public interface BookRepository extends JpaRepository<BookEntity, Long>{

}