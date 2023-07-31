package AseFirstApplication.com.acc.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class BookEntity {

    private long id;
    private String name;
    private String authorName;
    private double price;

    public BookEntity() {

    }

    public BookEntity(String name, String authorName, double price) {
        this.name = name;
        this.authorName = authorName;
        this.price = price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "book_name", nullable = false)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "author_name", nullable = false)
    public String getAuthorName() {
        return authorName;
    }
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Column(name = "price", nullable = false)
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}