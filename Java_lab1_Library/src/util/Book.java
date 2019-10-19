package util;

public class Book {

    private int Id;
    private String name;
    private String author;
    private int pages;
    private double price;

    public Book(int Id, String name, String author, int pages, double price){
        this.Id = Id;
        this.name = name;
        this.author = author;
        this.pages = pages;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getId() {
        return Id;
    }

    public int getPages() {
        return pages;
    }

    public double getPrice() {
        return price;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Book(){

    }
    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Book listDao = (Book)obj;

        if(pages != listDao.pages || price != listDao.price || Id != listDao.Id)
            return false;

        if(name == null && author == null)
            return (name == listDao.name) && (author == listDao.author);

        else if (!author.equals(listDao.author) || !name.equals(listDao.name))
            return false;
        return true;
    }

    public int hashCode(){
        return (int)(author.hashCode() + name.hashCode()+ 23*price + 58*pages);
    }

    public String toString(){
        return "Id"+ this.Id + "Name: " + this.name + " Autor: "+ this.author +
                " Pages: " + this.pages + " Price: " + this.price;
    }
}
