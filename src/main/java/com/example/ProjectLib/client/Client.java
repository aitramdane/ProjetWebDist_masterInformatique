package com.example.ProjectLib.client;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import  com.example.ProjectLib.reservation.Reservation;

@Entity
@Table(name = "CLIENT")
public class Client {

        private Integer id;

        private String firstName;

        private String lastName;

        private String job;

        private String address;

        private String email;

        private String phone;

        private String city;

        private String country;

        private LocalDate creationDate;

        Set<Reservation> reservations = new HashSet<Reservation>();




        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "CLIENT_ID")
        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }


        @Column(name = "FIRST_NAME", nullable = false)
        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }


        @Column(name = "LAST_NAME", nullable = false)
        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }


        @Column(name = "JOB")
        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }


        @Column(name = "ADDRESS")
        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }


        @Column(name = "EMAIL", nullable = false, unique = true)
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }


        @Column(name = "CREATION_DATE", nullable = false)
        public LocalDate getCreationDate() {
            return creationDate;
        }

        public void setCreationDate(LocalDate creationDate) {
            this.creationDate = creationDate;
        }


        @Column(name="PHONE")
        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }


        @Column(name="CITY")
        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }


        @Column(name="COUNTRY")
        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
        this.country = country;
         }


        // La relation est chargée à la demande
        @OneToMany(fetch = FetchType.LAZY, mappedBy = "client", cascade = CascadeType.PERSIST)

        public Set<Reservation> getReservations() {
            return reservations;
        }

        public void setReservations(Set<Reservation> reservations) {
            this.reservations = reservations;
        }


    }
