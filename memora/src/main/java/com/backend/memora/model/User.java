package com.backend.memora.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")  // ✅ Specifies the MongoDB collection
public class User {

    @Id
    private String id;  // ✅ MongoDB will generate this automatically

    private String email;
    private String password;
    private String role;

    // ✅ Default constructor (needed for Spring Data)
    public User() {}

    // ✅ Constructor with fields
    public User(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // ✅ Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
