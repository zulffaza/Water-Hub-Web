package com.waterhub.web.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Table(name = "role")
@Entity
public class Role implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "role_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "role_name", nullable = false, unique = true)
    private String name;

    @ManyToMany()
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users = new ArrayList<>();

    public Role() {

    }

    public Role(String name) {
        this.name = name;
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User getUser(int index) {
        return users.get(index);
    }

    public void setUser(User user) {
        users.add(user);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Role) {
            Role r = (Role) obj;

            return id.equals(r.getId());
        } else
            return false;
    }

    @Override
    public String toString() {
        return name;
    }
}
