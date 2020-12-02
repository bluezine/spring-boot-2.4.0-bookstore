package kr.co.bluezine.bookstore.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Book Service
 * 
 * @author Kisig Ian Seo
 *
 */
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    /**
     * Load book item by id
     * 
     * @param id
     * @return
     */
    public Book load(Long id) {
	return bookRepository.findById(id).get();
    }

    /**
     * Load book item by item
     * 
     * @param item
     * @return
     */
    public Book load(Book item) {
	return bookRepository.findById(item.getId()).get();
    }

    /**
     * Save book item
     * 
     * @param item
     * @return
     */
    public Book save(Book item) {
	bookRepository.save(item);
	return item;
    }
}
