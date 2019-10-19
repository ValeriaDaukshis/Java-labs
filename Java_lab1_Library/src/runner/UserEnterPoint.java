package runner;

import comparator.CompareByAuther;
import comparator.CompareByName;
import comparator.CompareByPrice;
import comparator.compareByPages;
import util.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

public class UserEnterPoint {

    private boolean isRunning;
    private BufferedReader reader;
    private HashMap<String, String> dictionary = new HashMap<String, String>();
    private BookListDao list;

    public UserEnterPoint(){
         list = new BookListDao();
    }
    private String[] helpMessages = new String[]
            {
                    "The 'help' command prints the help screen." ,
                    "The 'exit' command exits the application." ,
                    "The 'stat' command prints the record statistics." ,
                    "The 'add' command creates a new record." ,
                    "The 'get' command get all record." ,
                    "The 'edit' command edit record by id." ,
                    "The 'find' command find record by name/author/pages/price.",
                    "The 'remove' command remove book by id.",
                    "The 'sort' command sort books by name/author/pages/price.."
            };

    public void UserCommands() throws IOException
    {
        dictionary.put("help", "Help");
        dictionary.put("add", "AddBook");
        dictionary.put("get","Get");
        dictionary.put("edit", "Edit");
        dictionary.put("find", "Find");
        dictionary.put("exit", "Exit");
        dictionary.put("stat", "Stat");
        dictionary.put("remove", "Remove");
        dictionary.put("sort", "Sort");

        list.ReadBooksInfoFromFile("C:\\Users\\dauks\\Day8Daukshis\\books.xml");
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.isRunning = true;
        do
        {
            System.out.print("> ");
            String params = reader.readLine();
            String inputs = params;
            if(inputs.contains(" ")){
                inputs = inputs.split(" ")[0];
            }

            if (inputs == null || inputs.length() == 0)
            {
                System.out.println("Incorrect command");
                continue;
            }
            if (dictionary.containsKey(inputs))
            {
                Method method = null;
                try {
                    String methodName = dictionary.get(inputs);
                    method = this.getClass().getMethod(methodName, String.class);
                }
                catch (SecurityException e) {
                    System.out.println(e.getMessage());
                }
                catch (NoSuchMethodException e) {
                    System.out.println(e.getMessage());
                }

                try {
                    method.invoke(this, params);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                catch (IllegalAccessException e) {
                    System.out.println(e.getMessage());
                }
                catch (InvocationTargetException e) {
                    System.out.println(e.getMessage());
                }
            }
            else
            {
                System.out.println("Incorrect command");
            }
        }
        while (isRunning);
    }

    public void Help(String parameters){
        for(int i = 0 ; i < helpMessages.length; i++)
            System.out.println(helpMessages[i]);
    }

    public void Exit(String parameters) {
        System.out.println("Exiting an application...");
        this.isRunning = false;
    }

    public void Stat(String parameters) {
        System.out.println("Count of books: "+list.getStat());
    }

    public void AddBook(String parameters) throws IOException{
        System.out.print("Name: ");
        String name = reader.readLine();
        System.out.print("Author: ");
        String author = reader.readLine();
        System.out.print("Pages: ");
        int pages = CheckIntInput();
        System.out.print("Price: ");
        int price = CheckIntInput();
        int count = list.getStat();

        list.add(new Book(count+1, name, author, pages, price));
        System.out.println("New book is added");
    }

    private int CheckIntInput() throws IOException{
        boolean success = false;
        int number = 0;
        do{
            String value = reader.readLine();
            if(value.length() == 0 || value == null)
                continue;
            try {
                number = Integer.parseInt(value);
                success = true;
            }
            catch (NumberFormatException e)
            {
                continue;
            }

        }while(!success);

        return number;
    }
    public void Get(String parameters){
        ArrayList<Book> b = list.getBooks();
        PrintBooks(b);
    }

    public void Find(String parameters){
        String[] params = parameters.toLowerCase().split(" ",3);
        boolean isBookFound = false;
        if(params[1].equals("name")){
            ArrayList<Book> b = list.findByTag(params[2], new FindByName());
            PrintBooks(b);
            isBookFound = true;
        }
        else if(params[1].equals("author")){
            ArrayList<Book> b = list.findByTag(params[2], new FindByAuthor());
            PrintBooks(b);
            isBookFound = true;
        }
        else if(params[1].equals("pages")){
            ArrayList<Book> b = list.findByTag(params[2], new FindByPages());
            PrintBooks(b);
            isBookFound = true;
        }
        else if(params[1].equals("price")){
            ArrayList<Book> b = list.findByTag(params[2], new FindByPrice());
            PrintBooks(b);
            isBookFound = true;
        }
        else{
            System.out.println(parameters + " not fount");
            isBookFound = true;
        }

        if(!isBookFound){
            System.out.println("No books with parameter "+params[1]);
        }
    }

    public void Edit(String parameters) throws IOException{
        int Id = 0;
        Book book = null;
        if(parameters.contains(" ")){
            try {
                Id = Integer.parseInt(parameters.split(" ",2)[1]);
                book = list.getByIndex(Id-1);
            }
            catch (NumberFormatException e)
            {
                return;
            }

            if(book == null){
                System.out.println("No book with Id " + Id + " found");
                return;
            }
        }
        else{
            System.out.println("No book index found");
            return;
        }

        System.out.print("Pages: ");
        int pages = CheckIntInput();
        System.out.print("Price: ");
        int price = CheckIntInput();
        book.setPages(pages);
        book.setPrice(price);

        System.out.println("Book "+ Id + " is successfully updated");
    }

    public void Remove(String parameters){
        int Id = 0;
        Book book = null;
        if(parameters.contains(" ")){
            try {
                Id = Integer.parseInt(parameters.split(" ",2)[1]);
                book = list.getByIndex(Id-1);
            }
            catch (NumberFormatException e)
            {
                return;
            }
        }
        else{
            System.out.println("No book index found");
            return;
        }
        list.removeBook(book);
        System.out.println("Book with id" + Id + "was removed");
    }

    public void Sort(String parameters){
        String[] params = parameters.toLowerCase().split(" ",2);
        boolean isBookFound = false;
        Book[] b = new Book[list.getStat()];
        if(params[1].equals("name")){
            list.sortByTag(b,new CompareByName());
            PrintArrayOfBooks(b);
            isBookFound = true;
        }
        else if(params[1].equals("author")){
            list.sortByTag(b,new CompareByAuther());
            PrintArrayOfBooks(b);
            isBookFound = true;
        }
        else if(params[1].equals("pages")){
            list.sortByTag(b,new compareByPages());
            PrintArrayOfBooks(b);
            isBookFound = true;
        }
        else if(params[1].equals("price")){
            list.sortByTag(b,new CompareByPrice());
            PrintArrayOfBooks(b);
            isBookFound = true;
        }
        else{
            System.out.println(parameters + " not fount");
            isBookFound = true;
        }

        if(!isBookFound){
            System.out.println("No books with parameter "+params[1]);
        }
    }

    private void PrintBooks(ArrayList<Book> b){
        if(b == null){
            return;
        }
        for (Book a : b) {
            System.out.println("Id: "+ a.getId());
            System.out.println("\tName: "+ a.getName());
            System.out.println("\tAuthor: "+ a.getAuthor());
            System.out.println("\tPages: "+ a.getPages());
            System.out.println("\tPrice: "+ a.getPrice());
        }
    }

    private void PrintArrayOfBooks(Book[] b){
        if(b == null){
            return;
        }
        for (Book a : b) {
            System.out.println("Id: "+ a.getId());
            System.out.println("\tName: "+ a.getName());
            System.out.println("\tAuthor: "+ a.getAuthor());
            System.out.println("\tPages: "+ a.getPages());
            System.out.println("\tPrice: "+ a.getPrice());
        }
    }
}
