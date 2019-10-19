package util;

import util.Book;
import util.IFindCriterion;

public class FindByPrice implements IFindCriterion {
    public Object getTag(Object obj, Book b){
        return b.getPrice();
    }
}
