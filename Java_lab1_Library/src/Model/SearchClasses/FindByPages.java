package Model.SearchClasses;

import Model.Book;
import Model.SearchClasses.IFindCriterion;

public class FindByPages implements IFindCriterion {
    public Object getTag(Object obj, Book b) {
        return b.getPages();
    }
}
