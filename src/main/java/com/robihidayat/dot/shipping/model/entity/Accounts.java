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
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "accounts")
@EqualsAndHashCode
@Getter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Accounts implements Serializable {

  @Id
  private Long id;
  @Column(nullable = false)
  private String username;
  @Column(nullable = false)
  private String address;
  @Column(nullable = false)
  private String password;
  @CreatedDate
  private Long createdOn;

}
