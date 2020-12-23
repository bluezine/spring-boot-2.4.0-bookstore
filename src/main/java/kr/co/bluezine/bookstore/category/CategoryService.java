package kr.co.bluezine.bookstore.category;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import kr.co.bluezine.bookstore.sql.PageEntity;
import kr.co.bluezine.bookstore.sql.QueryMake;

/**
 * Category Service
 * 
 * @author Kisig Ian Seo
 *
 */
@Service(value = "categoryService")
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Load Category Item By Id
     * 
     * @param id
     * @return
     */
    public Category load(Long id) {
	return categoryRepository.findById(id).get();
    }

    /**
     * Load Category Item By Item
     * 
     * @param item
     * @return
     */
    public Category load(Category item) {
	return categoryRepository.findById(item.getId()).get();
    }

    /**
     * Save Category Item
     * 
     * @param item
     * @return
     */
    public Category save(Category item) {
	item.setUpdateDate(new Date());
	categoryRepository.save(item);
	return item;
    }

    /**
     * Create Category Item
     * 
     * @param item
     * @return
     */
    public Category create(Category item) {
	save(item);
	if (item.getPid() == null) {
	    item.setRootId(item.getId());
	} else {
	    Category parentItem = categoryRepository.findById(item.getPid()).get();
	    item.setRootId(parentItem.getRootId());
	    parentItem.setLeaf(false);
	    save(parentItem);
	}
	item.setLeaf(true);
	item.setSort(item.getId());
	item.setRgstDate(new Date());
	save(item);
	return item;
    }

    /**
     * Delete Category Item
     * 
     * @param item
     * @return
     */
    public Category delete(Category item) {
	item.setUpdateDate(new Date());
	item.setStatus(CategoryCode.CATEGORY_STATUS_DELETE);
	save(item);
	return item;
    }

    /**
     * Recover Category Item
     * 
     * @param item
     * @return
     */
    public Category restore(Category item) {
	item.setUpdateDate(new Date());
	item.setStatus(CategoryCode.CATEGORY_STATUS_NORMAL);
	return item;
    }

    /**
     * Remove Category Item
     * 
     * @param item
     */
    public void remove(Category item) {
	categoryRepository.delete(item);
    }

    /**
     * Swap Category
     * 
     * @param upItem
     * @param downItem
     */
    public void swap(Long upItemId, Long downItemId) {
	Category upItem = categoryRepository.findById(upItemId).get();
	Category downItem = categoryRepository.findById(downItemId).get();

	Long sort = upItem.getSort();
	upItem.setSort(downItem.getSort());
	downItem.setSort(sort);
	save(upItem);
	save(downItem);
    }

    /**
     * Return Root List
     * 
     * @return
     */
    public PageEntity rootList() {
	QueryMake make = new QueryMake();
	make.select("i.id, i.pid, i.title, i.status, i.isleaf as leaf, i.sort");
	make.from("category i");
	make.where("i.pid is null");
	make.where("i.status = ?", CategoryCode.CATEGORY_STATUS_NORMAL);
	make.order("sort");

	PageEntity entity = new PageEntity();
	entity.setTotalCount((Long) jdbcTemplate
		.query(make.countQuery(), make.preparedStatementSetter, new ColumnMapRowMapper()).get(0).get("cnt"));
	make.resetIndex();
	entity.setArrays(jdbcTemplate.query(make.query(), make.preparedStatementSetter,
		new BeanPropertyRowMapper<Category>(Category.class)));
	return entity;
    }

    /**
     * Return Children List
     * 
     * @param id
     * @return
     */
    public PageEntity childrenList(Long id) {
	QueryMake make = new QueryMake();
	make.select("i.id, i.pid, i.title, i.status, i.isleaf as leaf, i.sort");
	make.from("category i");
	make.where("i.pid = ?", id);
	make.where("i.status = ?", CategoryCode.CATEGORY_STATUS_NORMAL);
	make.order("sort");

	PageEntity entity = new PageEntity();
	entity.setTotalCount((Long) jdbcTemplate
		.query(make.countQuery(), make.preparedStatementSetter, new ColumnMapRowMapper()).get(0).get("cnt"));
	make.resetIndex();
	entity.setArrays(jdbcTemplate.query(make.query(), make.preparedStatementSetter,
		new BeanPropertyRowMapper<Category>(Category.class)));
	return entity;
    }
}
