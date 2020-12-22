package kr.co.bluezine.bookstore.sql;

import java.util.List;

/**
 * Paging Entity
 * 
 * @author Kisig Ian Seo
 *
 */
public class PageEntity {
    /**
     * Total Count
     */
    private long totalCount;

    /**
     * Total Array
     */
    private List<? extends SuperEntity> arrays;

    public long getTotalCount() {
	return totalCount;
    }

    public void setTotalCount(long totalCount) {
	this.totalCount = totalCount;
    }

    public List<? extends SuperEntity> getArrays() {
	return arrays;
    }

    public void setArrays(List<? extends SuperEntity> arrays) {
	this.arrays = arrays;
    }
}
