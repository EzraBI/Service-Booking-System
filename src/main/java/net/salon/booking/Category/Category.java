package net.salon.booking.Category;


import net.salon.booking.User.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false, length = 45)
    private int category_id;
    @Column(name = "category_name",nullable = false, length = 45, unique = true)
    private String category_name;


    @OneToMany(mappedBy = "category")
    List<User> users = new ArrayList<>();

//    @OneToMany(mappedBy = "category")
//    List<Boardroom> boardrooms = new ArrayList<>();


    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "category{" +
                "category_id=" + category_id +
                ", category_name='" + category_name + '\'' +
                '}';
    }
}
