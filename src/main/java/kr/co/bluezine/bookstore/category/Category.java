package kr.co.bluezine.bookstore.category;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import kr.co.bluezine.bookstore.sql.SuperEntity;

/**
 * Category Entity
 * 
 * @author Kisig Ian Seo
 * 
 *         <pre>
CREATE TABLE `category` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`pid` INT(11) NULL DEFAULT NULL,
	`rid` INT(11) NULL DEFAULT NULL,
	`title` VARCHAR(100) NOT NULL COLLATE 'utf8mb4_general_ci',
	`status` INT(6) NOT NULL DEFAULT '0',
	`rgst_date` DATETIME NOT NULL DEFAULT sysdate(),
	`updt_date` DATETIME NOT NULL DEFAULT sysdate(),
	`isleaf` INT(1) NULL DEFAULT NULL,
	PRIMARY KEY (`id`) USING BTREE,
	INDEX `KM_PID_KM_ID` (`pid`) USING BTREE,
	CONSTRAINT `KM_PID_KM_ID` FOREIGN KEY (`pid`) REFERENCES `bookstore`.`category` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
)
COMMENT='category'
COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
;
 *         </pre>
 */
public class Category extends SuperEntity {

    /**
     * Identify Key (PK)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Category Parent Id
     */
    @Column(name = "pid")
    private Long pid;

    /**
     * Category Root Id
     */
    @Column(name = "rid")
    private Long rootId;

    /**
     * Category Title
     */
    @Column(name = "title")
    private String title;

    /**
     * Category Status
     */
    @Column(name = "status")
    private int status;

    /**
     * Category Is Leaf
     */
    @Column(name = "isleaf")
    private boolean leaf;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Long getPid() {
	return pid;
    }

    public void setPid(Long pid) {
	this.pid = pid;
    }

    public Long getRootId() {
	return rootId;
    }

    public void setRootId(Long rootId) {
	this.rootId = rootId;
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

    public boolean isLeaf() {
	return leaf;
    }

    public void setLeaf(boolean leaf) {
	this.leaf = leaf;
    }
}
