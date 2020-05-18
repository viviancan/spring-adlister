package com.edevs.springadlister.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="categories")
public class AdCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false)
    private String name;

//    @ManyToMany(mappedBy = "categories")
//    private List<Ad> ads;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "ads_categories",
            joinColumns = {@JoinColumn(name="category_id")},
            inverseJoinColumns = {@JoinColumn(name="ad_id")}
    )
    private List<Ad> ads;

}