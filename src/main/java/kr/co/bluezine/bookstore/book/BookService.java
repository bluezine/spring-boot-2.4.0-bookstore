package kr.co.bluezine.bookstore.book;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import kr.co.bluezine.bookstore.sql.PageEntity;
import kr.co.bluezine.bookstore.sql.QueryMake;

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

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Load Book Item By Id
     * 
     * @param id
     * @return
     */
    public Book load(Long id) {
	return bookRepository.findById(id).get();
    }

    /**
     * Load Book Item By Item
     * 
     * @param item
     * @return
     */
    public Book load(Book item) {
	return bookRepository.findById(item.getId()).get();
    }

    /**
     * Save Book Item
     * 
     * @param item
     * @return
     */
    public Book save(Book item) {
	bookRepository.save(item);
	return item;
    }

    /**
     * Return Book List
     * 
     * @param page
     * @param count
     * @param search
     * @return
     * @throws NumberFormatException
     * @throws SQLException
     */
    public PageEntity list(int page, int count, String search, String order)
	    throws NumberFormatException, SQLException {
	QueryMake make = new QueryMake();
	make.select("b.id, b.title, b.status, b.auth_name");
	make.from("book b");
	make.appendSearch(search, Book.SEARCH);
	make.order(order);

	PageEntity entity = new PageEntity();
	entity.setTotalCount((Long) jdbcTemplate
		.query(make.countQuery(), make.preparedStatementSetter, new ColumnMapRowMapper()).get(0).get("cnt"));
	make.resetIndex();
	entity.setArrays(jdbcTemplate.query(make.pageQuery(page, count), make.preparedStatementSetter,
		new BeanPropertyRowMapper<Book>(Book.class)));
	return entity;
    }
}
