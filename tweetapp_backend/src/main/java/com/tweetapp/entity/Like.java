package com.tweetapp.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "likes")
@NoArgsConstructor
@Getter
@Setter
public class Like {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int uId;
	@ManyToOne 
	private User user; 
	
	@ManyToOne
	private Post post;
}
