package org.example.tourtravel.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "tour")
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotEmpty(message="Nhập vào mã!")
    private String code;
    @NotEmpty(message = "Tên không được để trống!")
    private String name;
    @Min(value = 1000000 , message = "Giá thấp nhất là 1000000")
    private double price;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;

    public Tour() {
    }

    public Tour(Long id, String code, String name, double price, Type type) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
