package kr.co.bluezine.bookstore.book;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Book Entity
 * 
 * @author Kisig Ian Seo
 *
 */
@Entity
public class Book {

    /**
     * Identify Key (PK)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Book title
     */
    @Column(name = "title")
    private String title;

    /**
     * Book status
     */
    @Column(name = "status")
    private int status;

    /**
     * Author name
     */
    @Column(name = "auth_name")
    private String authorName;

    /**
     * Publish company name
     */
    @Column(name = "pub_cmpy")
    private String pubCompany;

    /**
     * Publish year
     */
    @Column(name = "pub_year")
    private String pubYear;

    /**
     * ISBN Code
     */
    @Column(name = "isbn")
    private String isbn;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public int getStatus() {
	return status;
    }

    public void setStatus(int status) {
	this.status = status;
    }

    public String getAuthorName() {
	return authorName;
    }

    public void setAuthorName(String authorName) {
	this.authorName = authorName;
    }

    public String getPubCompany() {
	return pubCompany;
    }

    public void setPubCompany(String pubCompany) {
	this.pubCompany = pubCompany;
    }

    public String getPubYear() {
	return pubYear;
    }

    public void setPubYear(String pubYear) {
	this.pubYear = pubYear;
    }

    public String getIsbn() {
	return isbn;
    }

    public void setIsbn(String isbn) {
	this.isbn = isbn;
    }
}
