package com.mphasis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mphasis.domain.Book;
import com.mphasis.proxy.BookServiceProxy;

@RestController
@Scope("request")
public class BookClientController {

	@Autowired
	private BookServiceProxy bookserviceproxy;
	
	@GetMapping("/get-book/{id}")
	public Book getBookById(@PathVariable("id") long id) {
		
		Book book= bookserviceproxy.getBookById(id);
		return book;
		}
	@GetMapping("/get-books")
	public List<Book> getAllBook() {
		
		List<Book> book= bookserviceproxy.getAllBooks();
		return book;
		}
	
}
