package com.robihidayat.dot.shipping.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "cities")
@EqualsAndHashCode
@Getter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Cities {

  @Id
  private Long id;
  private String name;
  private Long latitude;
  private Long longitude;

}
