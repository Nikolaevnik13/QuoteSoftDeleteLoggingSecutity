package com.quote.model;


import java.time.LocalDateTime;

import javax.annotation.ManagedBean;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.web.context.annotation.RequestScope;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@ToString
@ManagedBean
@RequestScope
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "quote_log")
@EqualsAndHashCode(of = "id")
public class Quote_log {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Integer id;
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime created_date;
	
	private String quote_id;
	@Enumerated(EnumType.STRING)
	private Operation operation;
	private Integer error_code;
	private String message;
	
	
	
}
