package com.beena.books.entity;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchKey {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
    @Size(max = 100)
	@Column(unique = true)
    private String key;	
	
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
            		CascadeType.ALL
            })
    @JoinTable(name = "book_search",
            joinColumns = { @JoinColumn(name = "search_id") },
            inverseJoinColumns = { @JoinColumn(name = "book_id") })
	@Builder.Default
	@OrderBy("title")
	@JsonIgnore
    private Set<Book> books = new LinkedHashSet<>();
	
	public void addBooks(Book b) {
		this.books.add(b);
	}
}
