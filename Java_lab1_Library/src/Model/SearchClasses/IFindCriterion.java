package Model.SearchClasses;

import Model.Book;

public interface IFindCriterion {
    Object getTag(Object obj, Book b);
}
