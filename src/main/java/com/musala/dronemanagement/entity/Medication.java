
package com.musala.dronemanagement.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @Author Geeth_G
 * @CreatedTime on 22-10-2022 12:44:12 PM
 */


@Data
@Entity
@Table(name = "medication")
public class Medication implements Serializable {

    private static final long serialVersionUID = 5549416107753138439L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "weight")
    private BigDecimal weight;

    @Column(name = "code")
    private String code;

    @Column(name = "image_ref")
    private String imageRef;

    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;

}
