package kr.co.bluezine.bookstore.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

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
	categoryRepository.save(item);
	if (item.getPid() == null) {
	    item.setRootId(item.getId());
	} else {
	    Category parentItem = categoryRepository.findById(item.getPid()).get();
	    item.setRootId(parentItem.getRootId());
	    parentItem.setLeaf(false);
	    categoryRepository.save(parentItem);
	}
	item.setLeaf(true);
	item.setSort(item.getId());
	categoryRepository.save(item);
	return item;
    }

    /**
     * Delete Category Item
     * 
     * @param item
     * @return
     */
    public Category delete(Category item) {
	item.setStatus(CategoryCode.CATEGORY_STATUS_DELETE);
	return item;
    }

    /**
     * Recover Category Item
     * 
     * @param item
     * @return
     */
    public Category restore(Category item) {
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
}
