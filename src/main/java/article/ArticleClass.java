//package article;
//
//import java.sql.Blob;
//import java.sql.Timestamp;
//
//public class ArticleClass {
//    private int id;
//    private String title;
//    private String content;
//    private Timestamp createdAt;
//    private String category;
//    private String image; // Assuming the image is stored as a URL or file path
//
//    // Default constructor
//    public ArticleClass() {
//    }
//
//    // Parameterized constructor
//    public ArticleClass(int id, String title, String content, String category, String image) {
//        this.id = id;
//        this.title = title;
//        this.content = content;
//        this.category = category;
//        this.image = image;
//    }
//
//    // Getters and setters for each field
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getContent() {
//        return content;
//    }
//    public Timestamp getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public String getCategory() {
//        return category;
//    }
//
//    public void setCategory(String category) {
//        this.category = category;
//    }
//
//    public String getImage() {
//        return image;
//    }
//
//	public void setCreatedAt(Timestamp timestamp) {
//		this.createdAt = timestamp;
//	}
//
//	public void setImage(String image) {
//        this.image = image;
//		
//	}
//
//}


package article;

import java.sql.Timestamp;
import java.util.List;

public class ArticleClass {
    private int id;
    private String title;
    private String content;
    private Timestamp createdAt;
    private String category;
    private List<byte[]> images; // List of image BLOBs

    // Default constructor
    public ArticleClass() {
    }

    // Parameterized constructor
    public ArticleClass(int id, String title, String content, String category, List<byte[]> images, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.category = category;
        this.images = images;
        this.createdAt = createdAt;
    }

    // Getters and setters for each field
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<byte[]> getImages() {
        return images;
    }

    public void setImages(List<byte[]> images) {
        this.images = images;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}

