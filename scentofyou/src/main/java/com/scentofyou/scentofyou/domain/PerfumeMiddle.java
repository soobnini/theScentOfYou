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
@Table(name = "Perfume_Middle")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerfumeMiddle implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="Perfume_scent_id")
	private PerfumeScent perfumeScent;
	
	@ManyToOne
	@JoinColumn(name="Perfume_perfume_id")
	private Perfume perfume;
}
