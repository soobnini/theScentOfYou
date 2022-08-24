package com.scentofyou.scentofyou.controller;

import java.io.Serializable;

import com.scentofyou.scentofyou.domain.Perfume;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor 
@NoArgsConstructor
@SuppressWarnings("serial")
public class PerfumeCommand implements Serializable {
	private String perfumeName;
	private String perfumeGender;
	private String perfumeGroup; // 큰 단위
	private String perfumeCategory; // 작은 단위
}
