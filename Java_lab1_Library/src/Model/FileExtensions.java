package Model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import Model.Book;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class FileExtensions {
    private static String path;
    private ArrayList<Book> bookList = new ArrayList<>();

    private int Id;
    private String name;
    private String author;
    private int pages;
    private int price;

    public FileExtensions(String path) {
        this.path = path;
    }

    public ArrayList<Book> ReadXmlFile() {
        try {

            File file = new File(path);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(file);

            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("book");
            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    this.Id = Integer.parseInt(eElement.getAttribute("id"));
                    this.name = eElement.getElementsByTagName("name").item(0).getTextContent();
                    this.author = eElement.getElementsByTagName("author").item(0).getTextContent();
                    this.pages = Integer.parseInt(eElement.getElementsByTagName("pages").item(0).getTextContent());
                    this.price = Integer.parseInt(eElement.getElementsByTagName("price").item(0).getTextContent());
                    bookList.add(new Book(this.Id, this.name, this.author, this.pages, this.price));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return bookList;
    }
}
