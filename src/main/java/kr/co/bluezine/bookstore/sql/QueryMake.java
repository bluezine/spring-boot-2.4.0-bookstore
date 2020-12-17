package kr.co.bluezine.bookstore.sql;

/**
 * Query Make
 * 
 * @author Kisig Ian Seo
 *
 */
public class QueryMake {
    private StringBuffer selectQuery;
    private StringBuffer fromQuery;
    private StringBuffer whereQuery;
    private StringBuffer orderQuery;

    /**
     * Select Fragement
     * 
     * @param select
     */
    public void select(String select) {
	if (selectQuery != null) {
	    selectQuery.append(", " + select);
	} else {
	    selectQuery = new StringBuffer();
	    selectQuery.append(select);
	}
    }

    /**
     * From Fragement
     * 
     * @param from
     */
    public void from(String from) {
	if (fromQuery != null) {
	    fromQuery.append(", " + from);
	} else {
	    fromQuery = new StringBuffer();
	    fromQuery.append(from);
	}
    }

    /**
     * Where Fragement
     * 
     * @param where
     */
    public void where(String where) {
	if (whereQuery != null) {
	    whereQuery.append(" AND " + where);
	} else {
	    whereQuery = new StringBuffer();
	    whereQuery.append(where);
	}
    }

    /**
     * Order Fragement
     * 
     * @param order
     */
    public void order(String order) {
	if (orderQuery != null) {
	    orderQuery.append(", " + order);
	} else {
	    orderQuery = new StringBuffer();
	    orderQuery.append(order);
	}
    }

    /**
     * Get Count Query
     * 
     * @return
     */
    public String countQuery() {
	String newQuery = "";
	newQuery += " SELECT COUNT(*) AS cnt FROM ( ";
	newQuery += makeQuery();
	return newQuery;
    }

    /**
     * Get All Query
     * 
     * @return
     */
    public String query() {
	String newQuery = "";
	newQuery += " SELECT i.* FROM ( ";
	newQuery += makeQuery();
	if (orderQuery != null) {
	    newQuery += " ORDER BY ";
	    newQuery += orderQuery.toString();
	}
	return newQuery;
    }

    /**
     * Get Page Query
     * 
     * @param page
     * @param count
     * @return
     */
    public String pageQuery(int page, int count) {
	String newQuery = "";
	newQuery += query();
	newQuery += " LIMIT " + ((page - 1) * count) + ", " + count;
	return newQuery;
    }

    /**
     * Make Query
     * 
     * @return
     */
    private String makeQuery() {
	String newQuery = "";
	if (selectQuery != null) {
	    newQuery += " SELECT ";
	    newQuery += selectQuery.toString();
	}
	if (fromQuery != null) {
	    newQuery += " FROM ";
	    newQuery += fromQuery.toString();
	}
	if (whereQuery != null) {
	    newQuery += " WHERE ";
	    newQuery += whereQuery.toString();
	}
	newQuery += " ) i ";
	return newQuery;
    }
}
