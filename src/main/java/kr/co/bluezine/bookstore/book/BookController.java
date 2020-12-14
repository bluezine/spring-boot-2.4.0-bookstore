package kr.co.bluezine.bookstore.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "list/{page}/{count}", method = RequestMethod.GET)
    public PageEntity list(@PathVariable int page, @PathVariable int count) {
	QueryMake make = new QueryMake();
	make.select("*");
	make.from("book b");
	make.order("id");

	PageEntity entity = new PageEntity();
	entity.setTotalCount((Long) jdbcTemplate.query(make.countQuery(), new ColumnMapRowMapper()).get(0).get("cnt"));
	entity.setArrays(jdbcTemplate.query(make.pageQuery(page, count), new BeanPropertyRowMapper<Book>(Book.class)));
	return entity;
    }

    @RequestMapping(value = "read/{id}", method = RequestMethod.GET)
    public Book read(@PathVariable Long id) {
	return bookService.load(id);
    }

    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public Book insert(@RequestBody Book item) {
	return bookService.save(item);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Book update(@RequestBody Book item) {
	return bookService.save(item);
    }
}
