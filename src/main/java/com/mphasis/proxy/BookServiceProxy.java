package com.mphasis.proxy;

import java.util.Arrays;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mphasis.domain.Book;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@FeignClient(name="book-service")
public interface BookServiceProxy {
	@Retry(name="book-service")
	@CircuitBreaker(name="book-service", fallbackMethod= "fallbackGetBookById")
	@GetMapping(value="/books/{id}")
	public Book getBookById(@PathVariable("id") Long id);
	
	@CircuitBreaker(name="book-service", fallbackMethod= "fallbackGetBookById")
	@GetMapping(value="/books")
	public List<Book> getAllBooks() ;
		
     default Book fallbackGetBookById(Long id, Throwable cause) {
		System.out.println("Exception raised with message" +cause.getMessage());
		return new Book(id, "title", "author", "isbn", 100, 2100, "publisher");
	}

     default List<Book> fallbackGetAllBooks(Throwable cause) {
    	 System.out.println("Exception raised with message" +cause.getMessage());
 		return Arrays.asList();
 	}
}
