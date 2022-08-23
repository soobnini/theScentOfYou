package com.scentofyou.scentofyou.domain;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@SuppressWarnings("serial")
@Data
@AllArgsConstructor 
@NoArgsConstructor
class LikesPK implements Serializable {
	int user;
	int perfume;
}
@SuppressWarnings("serial")
@Entity
@Table(name="Likes")
@Data
@AllArgsConstructor 
@NoArgsConstructor
@IdClass(LikesPK.class)
public class PerfumeLikes implements Serializable{
	@Id
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name="User_id")
	private User user;
	@Id
	@ManyToOne(targetEntity = Perfume.class)
	@JoinColumn(name="Perfume_perfume_id")
	private Perfume perfume;
}
