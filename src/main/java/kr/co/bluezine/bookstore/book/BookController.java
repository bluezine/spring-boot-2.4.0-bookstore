package kr.co.bluezine.bookstore.book;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.bluezine.bookstore.sql.PageEntity;
import kr.co.bluezine.bookstore.sql.QueryMake;

/**
 * Book Controller
 * 
 * @author Kisig Ian Seo
 *
 */
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Get book list
     * 
     * @param page
     * @param count
     * @param search
     * @return
     * @throws NumberFormatException
     * @throws SQLException
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public PageEntity list(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int count,
	    @RequestParam(defaultValue = "") String search) throws NumberFormatException, SQLException {
	QueryMake make = new QueryMake();
	make.select("*");
	make.from("book b");
	make.appendSearch(search, Book.SEARCH);
	make.order("id");

	PageEntity entity = new PageEntity();
	entity.setTotalCount((Long) jdbcTemplate
		.query(make.countQuery(), make.preparedStatementSetter, new ColumnMapRowMapper()).get(0).get("cnt"));
	make.resetIndex();
	entity.setArrays(jdbcTemplate.query(make.pageQuery(page, count), make.preparedStatementSetter,
		new BeanPropertyRowMapper<Book>(Book.class)));
	return entity;
    }

    /**
     * Read book
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "read/{id}", method = RequestMethod.GET)
    public Book read(@PathVariable Long id) {
	return bookService.load(id);
    }

    /**
     * Insert book
     * 
     * @param item
     * @return
     */
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public Book insert(@RequestBody Book item) {
	return bookService.save(item);
    }

    /**
     * Update book
     * 
     * @param item
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Book update(@RequestBody Book item) {
	return bookService.save(item);
    }
}
