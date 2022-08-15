package com.scentofyou.scentofyou.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Entity
@Table(name = "Perfume")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Perfume implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "perfume_id")
	private int id;
	
	@Column(name = "perfume_name")
	private String name;

	@Column(name = "perfume_brand")
	private String brand;

	@Column(name = "perfume_img")
	private String perfumeImg;

	@Column(name = "perfume_gender")
	private String gender;

}
