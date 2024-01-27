package com.boottech.springbootelasticsearch;

import java.util.List;

public interface BookService {

  public List<Book> getAll();

  public Book addBook(Book book);

  public Book getById(String id) throws Exception;

  public Book update(Book book, String id) throws Exception;

  public void deleteById(String id);
}
