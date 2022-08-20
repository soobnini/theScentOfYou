package com.scentofyou.scentofyou.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Entity
@Table(name="Perfume_Likes")
@Data
@AllArgsConstructor 
@NoArgsConstructor
public class perfumeLikes implements Serializable{
	@Id
	@ManyToOne(targetEntity = Perfume.class)
	@JoinColumn(name="Perfume_perfume_id")
	private int perfume;
	@Id
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name="User_id")
	private String user;
	
	@Column(name="star")
	private double star;
}
