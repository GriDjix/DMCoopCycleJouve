package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Order.
 */
@Entity
@Table(name = "jhi_order")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;

    @NotNull
    @Column(name = "order_date", nullable = false)
    private Instant orderDate;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    @NotNull
    @Size(min = 5, max = 100)
    @Column(name = "delivery_address", length = 100, nullable = false)
    private String deliveryAddress;

    @NotNull
    @Size(min = 3, max = 50)
    @Column(name = "delivery_city", length = 50, nullable = false)
    private String deliveryCity;

    @NotNull
    @Size(min = 3, max = 50)
    @Column(name = "delivery_country", length = 50, nullable = false)
    private String deliveryCountry;

    @NotNull
    @Column(name = "delivery_time", nullable = false)
    private ZonedDateTime deliveryTime;

    @OneToMany(mappedBy = "order")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "restaurant", "order" }, allowSetters = true)
    private Set<Meal> meals = new HashSet<>();

    @ManyToOne
    private User user;

    @ManyToOne
    @JsonIgnoreProperties(value = { "meals", "orders" }, allowSetters = true)
    private Restaurant restaurant;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public UUID getId() {
        return this.id;
    }

    public Order id(UUID id) {
        this.setId(id);
        return this;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Instant getOrderDate() {
        return this.orderDate;
    }

    public Order orderDate(Instant orderDate) {
        this.setOrderDate(orderDate);
        return this;
    }

    public void setOrderDate(Instant orderDate) {
        this.orderDate = orderDate;
    }

    public Double getTotalPrice() {
        return this.totalPrice;
    }

    public Order totalPrice(Double totalPrice) {
        this.setTotalPrice(totalPrice);
        return this;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDeliveryAddress() {
        return this.deliveryAddress;
    }

    public Order deliveryAddress(String deliveryAddress) {
        this.setDeliveryAddress(deliveryAddress);
        return this;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryCity() {
        return this.deliveryCity;
    }

    public Order deliveryCity(String deliveryCity) {
        this.setDeliveryCity(deliveryCity);
        return this;
    }

    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }

    public String getDeliveryCountry() {
        return this.deliveryCountry;
    }

    public Order deliveryCountry(String deliveryCountry) {
        this.setDeliveryCountry(deliveryCountry);
        return this;
    }

    public void setDeliveryCountry(String deliveryCountry) {
        this.deliveryCountry = deliveryCountry;
    }

    public ZonedDateTime getDeliveryTime() {
        return this.deliveryTime;
    }

    public Order deliveryTime(ZonedDateTime deliveryTime) {
        this.setDeliveryTime(deliveryTime);
        return this;
    }

    public void setDeliveryTime(ZonedDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Set<Meal> getMeals() {
        return this.meals;
    }

    public void setMeals(Set<Meal> meals) {
        if (this.meals != null) {
            this.meals.forEach(i -> i.setOrder(null));
        }
        if (meals != null) {
            meals.forEach(i -> i.setOrder(this));
        }
        this.meals = meals;
    }

    public Order meals(Set<Meal> meals) {
        this.setMeals(meals);
        return this;
    }

    public Order addMeal(Meal meal) {
        this.meals.add(meal);
        meal.setOrder(this);
        return this;
    }

    public Order removeMeal(Meal meal) {
        this.meals.remove(meal);
        meal.setOrder(null);
        return this;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order user(User user) {
        this.setUser(user);
        return this;
    }

    public Restaurant getRestaurant() {
        return this.restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Order restaurant(Restaurant restaurant) {
        this.setRestaurant(restaurant);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Order)) {
            return false;
        }
        return id != null && id.equals(((Order) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Order{" +
            "id=" + getId() +
            ", orderDate='" + getOrderDate() + "'" +
            ", totalPrice=" + getTotalPrice() +
            ", deliveryAddress='" + getDeliveryAddress() + "'" +
            ", deliveryCity='" + getDeliveryCity() + "'" +
            ", deliveryCountry='" + getDeliveryCountry() + "'" +
            ", deliveryTime='" + getDeliveryTime() + "'" +
            "}";
    }
}
