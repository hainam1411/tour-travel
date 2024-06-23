package org.example.tourtravel.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Pattern(regexp = "^[A-Z]{2}\\\\d{3}$", message = "hay viet dung dịnh dạng XX(A-Z)-xxx(0-9)!")
    private String code;
    @NotEmpty(message = "Tên không được để trống!")
    private String destination;
    @Min(value = 1000000 , message = "Giá thấp nhất phải trên 1000000")
    private double price;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;

    public Tour() {
    }

    public Tour(Long id, String code, String destination, double price, Type type) {
        this.id = id;
        this.code = code;
        this.destination = destination;
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

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
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
