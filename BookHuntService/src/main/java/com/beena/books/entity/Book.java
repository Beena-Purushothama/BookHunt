package com.beena.books.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
public class Book {
	@Id
	@Column(name="book_id")
	private String id;
	
    @NotNull
    @OrderBy
    @Column(length=512)
	private String title;
	private String authors; 
	private String imageLinks;

	@ManyToMany(fetch = FetchType.LAZY,
    cascade = {
    		CascadeType.MERGE,
    		CascadeType.PERSIST,
    		CascadeType.REMOVE
    })
	@JoinTable(name = "book_search",
    joinColumns = { @JoinColumn(name = "book_id") },
    inverseJoinColumns = { @JoinColumn(name = "search_id") })
	@JsonIgnore
	@Builder.Default
    private Set<SearchKey> searchKeys = new HashSet<>();
	
	public void addSearchKey(SearchKey k) {
		this.searchKeys.add(k);
	}
}
