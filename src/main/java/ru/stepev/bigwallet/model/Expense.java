package ru.stepev.bigwallet.model;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Expense {
	
	private Integer id;
	private Integer price;
    private Integer groupId;
    private Integer walletId;
    
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate date;
    
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalTime time;
}
