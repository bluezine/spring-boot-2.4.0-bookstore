package kr.co.bluezine.bookstore.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.util.StringUtils;

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
    private StringBuffer searchQuery;
    private StringBuffer orderQuery;
    public PreparedStatementSetter preparedStatementSetter;
    private final List<Object> param = new ArrayList<>();
    private int index = 1;

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
     * Where Fragement (append value)
     * 
     * @param where
     * @param value
     */
    public void where(String where, Object value) {
	where(where);
	param.add(value);
    }

    /**
     * Append Search Fragement
     * 
     * @param search
     * @param searchParam
     * @throws NumberFormatException
     * @throws SQLException
     */
    public void appendSearch(String search, Map<String, SearchParam> searchParam)
	    throws NumberFormatException, SQLException {
	if (StringUtils.hasLength(search)) {
	    searchQuery = new StringBuffer();
	    boolean isFirst = true;

	    StringTokenizer attributes = new StringTokenizer(search, ",");
	    while (attributes.hasMoreTokens()) {
		String attribute = attributes.nextToken();

		String name = attribute.split("_")[0];
		String value = attribute.split("_")[1];

		if (!isFirst) {
		    searchQuery.append(" AND ");
		}

		switch (searchParam.get(name).getType()) {
		case Types.NUMERIC:
		    searchQuery.append(searchParam.get(name).getColumn());
		    param.add(Long.parseLong(value));
		    break;
		case Types.VARCHAR:
		    searchQuery.append(searchParam.get(name).getColumn());
		    param.add(value);
		    break;
		case Types.DATE:
		    searchQuery.append(searchParam.get(name).getColumn());
		    param.add(new Timestamp(Long.parseLong(value)));
		    break;
		}
		switch (searchParam.get(name).getSearchType()) {
		case SearchType.SEARCH_EXACT:
		    searchQuery.append(" = ? ");
		    break;
		case SearchType.SEARCH_LIKE:
		    searchQuery.append(" like ? ");
		    break;
		}
		isFirst = false;
	    }
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
	if (searchQuery != null) {
	    if (whereQuery != null) {
		newQuery += " AND ( ";
	    } else {
		newQuery += " WHERE ( ";
	    }
	    newQuery += searchQuery.toString();
	    newQuery += " ) ";
	}
	newQuery += " ) i ";

	preparedStatementSetter = new PreparedStatementSetter() {
	    @Override
	    public void setValues(PreparedStatement ps) throws SQLException {
		for (Object obj : param) {
		    if (obj instanceof String) {
			ps.setNString(index++, "%" + obj.toString() + "%");
		    }
		}
	    }
	};
	return newQuery;
    }

    /**
     * Reset index
     */
    public void resetIndex() {
	this.index = 1;
    }

    @Override
    public String toString() {
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
	if (searchQuery != null) {
	    if (whereQuery != null) {
		newQuery += " AND ( ";
	    } else {
		newQuery += " WHERE ( ";
	    }
	    newQuery += searchQuery.toString();
	    newQuery += " ) ";
	}
	newQuery += " ) i ";
	return newQuery;
    }
}
