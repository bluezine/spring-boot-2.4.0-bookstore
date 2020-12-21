package kr.co.bluezine.bookstore.sql;

/**
 * SQL Search Query Maker
 * 
 * @author Kisig Ian Seo
 *
 */
public class SearchParam {

    /**
     * Column name
     */
    private String column;

    /**
     * Column type
     */
    private int type;

    /**
     * Column search type
     */
    private int searchType;

    public SearchParam(String column, int type) {
	this(column, type, SearchType.SEARCH_EXACT);
    }

    public SearchParam(String column, int type, int searchType) {
	this.column = column;
	this.type = type;
	this.searchType = searchType;
    }

    public String getColumn() {
	return column;
    }

    public void setColumn(String column) {
	this.column = column;
    }

    public int getType() {
	return type;
    }

    public void setType(int type) {
	this.type = type;
    }

    public int getSearchType() {
	return searchType;
    }

    public void setSearchType(int searchType) {
	this.searchType = searchType;
    }
}
