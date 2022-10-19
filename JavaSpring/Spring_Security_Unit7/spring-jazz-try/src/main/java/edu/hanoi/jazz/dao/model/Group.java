package edu.hanoi.jazz.dao.model;

import org.hibernate.annotations.SortComparator;
import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import java.util.List;
import java.util.SortedSet;

@javax.persistence.Entity
@Table(name = "HN_GROUP",
uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class Group {
    private String name;
    private int id;

    public Group() {
    }

    public Group(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Column(name = "name", nullable = false, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //private List<User> users;
    private SortedSet<User> users;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "groupId")
//    @SortNatural
    @SortComparator(UsernameComparator.class)
    public SortedSet<User> getUsers() {
        return users;
    }

    public void setUsers(SortedSet<User> users) {
        this.users = users;
    }

    //    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "groupId")
//    public List<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(List<User> users) {
//        this.users = users;
//    }

}
