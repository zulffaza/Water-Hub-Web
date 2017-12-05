package com.waterhub.web.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "store")
@Entity
public class Store implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "store_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "store_name", nullable = false)
    private String name;

    @Column(name = "store_description")
    private String description;

    @Column(name = "store_phone", nullable = false)
    private String phone;

    @Column(name = "store_country", nullable = false)
    private String country;

    @Column(name = "store_city", nullable = false)
    private String city;

    @Column(name = "store_address", nullable = false)
    private String address;

    @Column(name = "store_latitude", nullable = false)
    private Double latitude;

    @Column(name = "store_longitude", nullable = false)
    private Double longitude;

    @Column(name = "store_status", nullable = false)
    private StoreStatus status;

    @Column(name = "store_picture")
    private String picture;

    @Column(name = "store_created_at", nullable = false)
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Store() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public StoreStatus getStatus() {
        return status;
    }

    public void setStatus(StoreStatus status) {
        this.status = status;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Store) {
            Store u = (Store) obj;

            return id.equals(u.getId());
        } else
            return false;
    }

    @Override
    public String toString() {
        return name;
    }
}
