package kr.co.bluezine.bookstore.category;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Category Repository
 * 
 * @author Kisig Ian Seo
 *
 */
@Repository(value = "categoryRepository")
public interface CategoryRepository extends CrudRepository<Category, Long> {

}
