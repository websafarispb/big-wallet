package ru.stepev.bigwallet.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Wallet {
	
	private Integer id;
	private Integer ownerId; 
	private Integer currencyId;
	
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate createDate;
    
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalTime createTime;
	private List<Expense> expenses;
}
