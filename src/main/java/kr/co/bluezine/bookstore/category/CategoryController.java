package kr.co.bluezine.bookstore.category;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Category Controller
 * 
 * @author Kisig Ian Seo
 *
 */
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
@RestController
@RequestMapping(value = "category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * Read Category
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "read/{id}", method = RequestMethod.GET)
    public Category read(@PathVariable Long id) {
	return categoryService.load(id);
    }

    /**
     * Insert Category
     * 
     * @param item
     * @return
     */
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public Category insert(@RequestBody Category item) {
	return categoryService.create(item);
    }

    /**
     * Update Category
     * 
     * @param item
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Category update(@RequestBody Category item) {
	return categoryService.save(item);
    }

    /**
     * Delete Category
     * 
     * @param item
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Category delete(@RequestBody Category item) {
	return categoryService.delete(item);
    }

    /**
     * Recover Category
     * 
     * @param item
     * @return
     */
    @RequestMapping(value = "restore", method = RequestMethod.POST)
    public Category recover(@RequestBody Category item) {
	return categoryService.restore(item);
    }

    /**
     * Swap Category
     * 
     * @param items
     * @return
     */
    @RequestMapping(value = "swap", method = RequestMethod.POST)
    public Map<String, Boolean> swap(@RequestBody List<Long> ids) {
	Map<String, Boolean> result = new HashMap<>();
	try {
	    categoryService.swap(ids.get(0), ids.get(1));
	    result.put("result", true);
	} catch (Exception ex) {
	    result.put("result", false);
	}
	return result;

    }
}
