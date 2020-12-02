package kr.co.bluezine.bookstore.book;

import org.springframework.data.repository.CrudRepository;

/**
 * Book Repository
 * 
 * @author Kisig Ian Seo
 *
 */
public interface BookRepository extends CrudRepository<Book, Long> {

}
