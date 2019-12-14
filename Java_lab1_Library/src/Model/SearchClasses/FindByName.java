package Model.SearchClasses;

import Model.Book;
import Model.SearchClasses.IFindCriterion;

public class FindByName implements IFindCriterion {
    public Object getTag(Object obj, Book b) {
        return b.getName();
    }
}
