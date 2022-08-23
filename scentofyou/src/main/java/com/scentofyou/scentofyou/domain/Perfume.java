package com.scentofyou.scentofyou.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

	@Column(name = "perfume_img")
	private String perfumeImg;

	@Column(name = "perfume_gender")
	private String gender;
	
	@Column(name = "perfume_category")
	private String category;
	
	@Column(name = "perfume_day")
	private String day;
	
	@Column(name = "perfume_persistence")
	private String persistence;
	
	@Column(name = "perfume_weight")
	private String weight;
	
	@Column(name = "perfume_like")
	private int likes;
	
	@Column(name = "perfume_voters")
	private int voters;
	
	@OneToMany(mappedBy = "perfume")
	private List<PerfumeBase> baseNotes;
	
	@OneToMany(mappedBy = "perfume")
	private List<PerfumeMiddle> middleNotes;
	
	@OneToMany(mappedBy = "perfume")
	private List<PerfumeTop> topNotes;
	
	@OneToMany(mappedBy = "perfume")
	private List<PerfumeNone> noneNotes;

}
