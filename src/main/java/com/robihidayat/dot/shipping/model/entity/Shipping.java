package com.robihidayat.dot.shipping.model.entity;


import java.io.Serializable;
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
@Table(name = "shipping")
@EqualsAndHashCode
@Getter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Shipping implements Serializable {
  @Id
  private Long id;
  private Long amount;
  private Long destination;
  private Long source;
  private boolean status;

}
