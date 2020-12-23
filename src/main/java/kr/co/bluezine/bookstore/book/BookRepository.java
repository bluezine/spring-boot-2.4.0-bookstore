package kr.co.bluezine.bookstore.book;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Book Repository
 * 
 * @author Kisig Ian Seo
 *
 */
@Repository(value = "bookRepository")
public interface BookRepository extends CrudRepository<Book, Long> {

}
