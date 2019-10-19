package util;

import util.Book;
import util.IFindCriterion;

public class FindByName implements IFindCriterion {
    public Object getTag(Object obj, Book b){
        return b.getName();
    }
}
