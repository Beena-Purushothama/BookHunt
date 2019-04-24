package com.beena.books.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

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
	private String id;
	
    @Column(unique = true)
    @NotNull
	private String title;
	//private List<String> authors; 
	private String imageLinks;
	
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
            		CascadeType.ALL
            },
            mappedBy = "books")
	@JsonIgnore
    private Set<SearchKey> searchKeys = new HashSet<>();
}
