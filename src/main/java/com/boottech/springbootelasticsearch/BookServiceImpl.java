package com.boottech.springbootelasticsearch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class BookServiceImpl implements BookService{
  private final BookRepository repository;

  public BookServiceImpl(BookRepository repository){
    this.repository = repository;
  }

  @Override
  public List<Book> getAll(){
    return StreamSupport.stream(
        Spliterators.spliteratorUnknownSize(repository.findAll().iterator(), 0), false)
      .collect(Collectors.toList());
  }

  @Override
  public Book addBook(Book book){
    log.info("addBook : {}", book);
    return repository.save(book);
  }

  @Override
  public Book getById(String id) throws Exception{
    return repository.findById(id).orElseThrow(()->new Exception("Book id not Found"));
  }

  @Override
  public Book update(Book book, String id) throws Exception{
    repository.findById(id)
      .ifPresentOrElse(book1 -> {
        book1.setTitle(book.getTitle());
        book1.setIsbn(book.getIsbn());
        book1.setDescription(book.getDescription());
        book1.setLanguage(book.getLanguage());
        book1.setPage(book.getPage());
        book1.setPrice(book.getPrice());
        repository.save(book1);
      },() -> {
        try {
          throw new Exception("Book id not found");
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      });

    return book;
  }

  @Override
  public void deleteById(String id) {
    repository.deleteById(id);
  }


}
