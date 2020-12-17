package kr.co.bluezine.bookstore.book;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import kr.co.bluezine.bookstore.sql.SuperEntity;

/**
 * Book Entity
 * 
 * @author Kisig Ian Seo
 *
 *         <pre>
CREATE TABLE `book` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`title` VARCHAR(100) NOT NULL COLLATE 'utf8mb4_general_ci',
	`status` INT(6) NOT NULL DEFAULT '0',
	`auth_name` VARCHAR(100) NOT NULL COLLATE 'utf8mb4_general_ci',
	`pub_cmpy` VARCHAR(100) NOT NULL COLLATE 'utf8mb4_general_ci',
	`pub_year` INT(4) NOT NULL,
	`pub_date` DATETIME NOT NULL,
	`isbn` VARCHAR(13) NOT NULL COLLATE 'utf8mb4_general_ci',
	`desc` LONGTEXT NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
	PRIMARY KEY (`id`) USING BTREE
)
COMMENT='book item'
COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
;
 *         </pre>
 */
@Entity
public class Book extends SuperEntity {

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
     * Publish date
     */
    @Column(name = "pub_date")
    private Date pubDate;

    /**
     * ISBN code
     */
    @Column(name = "isbn")
    private String isbn;

    /**
     * Description
     */
    @Column(name = "desc")
    private String desc;

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

    public Date getPubDate() {
	return pubDate;
    }

    public void setPubDate(Date pubDate) {
	this.pubDate = pubDate;
    }

    public String getIsbn() {
	return isbn;
    }

    public void setIsbn(String isbn) {
	this.isbn = isbn;
    }

    public String getDesc() {
	return desc;
    }

    public void setDesc(String desc) {
	this.desc = desc;
    }
}
