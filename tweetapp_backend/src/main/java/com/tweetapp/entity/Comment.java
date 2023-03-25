package com.tweetapp.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "comments")
@NoArgsConstructor
@Getter
@Setter
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank(message = "Comment Should have Atleat 1 Character")
	@Size(min=1,max=144,message="Comment Should not Exceed 144 Length Limit")
	@Column(nullable = false)
	private String content;
	@Size(max=50,message="Comment Should not Exceed 50 Length Limit")
	private String title;
	private Date createdAt;
	@ManyToOne
	private Post post;

	@ManyToOne
	private User user;
}
