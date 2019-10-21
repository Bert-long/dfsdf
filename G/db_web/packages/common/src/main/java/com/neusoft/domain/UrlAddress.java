package com.neusoft.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "url_address")
@EqualsAndHashCode
public class UrlAddress implements Serializable {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "url")
    private String url;
}
