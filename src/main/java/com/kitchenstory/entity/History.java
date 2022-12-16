package com.kitchenstory.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "History")
@Data
public class History {

    public History(){}

    public History(String userEmail, String checkoutDate, String returnedDate, String shortName,
                   String cuisine, String description, String img) {
        this.userEmail = userEmail;
        this.checkoutDate = checkoutDate;
        this.returnedDate = returnedDate;
        this.shortName = shortName;
        this.cuisine = cuisine;
        this.description = description;
        this.img = img;
    }

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="user_email")
    private String userEmail;

    @Column(name="checkout_date")
    private String checkoutDate;

    @Column(name="returned_date")
    private String returnedDate;

    @Column(name="title")
    private String shortName;

    @Column(name="author")
    private String cuisine;

    @Column(name="description")
    private String description;

    @Column(name="img")
    private String img;
}
