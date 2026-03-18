DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS departments;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id BINARY(16) PRIMARY KEY,
  username VARCHAR(50) NOT NULL,
  password VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL,
  role VARCHAR(100) NOT NULL,
  created_at TIMESTAMP,
  updated_at TIMESTAMP,
  deleted_at TIMESTAMP
);

CREATE TABLE departments (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  created_at TIMESTAMP,
  updated_at TIMESTAMP,
  deleted_at TIMESTAMP
);

CREATE TABLE employees (
  id BINARY(16) PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  email VARCHAR(100) NOT NULL,
  department_id INT,
  created_at TIMESTAMP,
  updated_at TIMESTAMP,
  deleted_at TIMESTAMP,
  FOREIGN KEY (department_id) REFERENCES departments(id)
);
-- admin account: admin / admin
-- user account: user / user
INSERT INTO users (id, username, password, email, role) VALUES
(UUID_TO_BIN(UUID()), 'admin', '$2a$12$nR9OOi1pqo1UioO1tccA0ewRHQVe1oX/AeW9d/WRyImBr4WfAhJdK', 'admin@example.com', 'ADMIN'),
(UUID_TO_BIN(UUID()), 'user', '$2a$12$RiVK6vsf4p5qNnHqGCeV7uz8a1qSChYt5lunmsNf5lcZSg9g3rNNG', 'user@example.com', 'USER');

INSERT INTO departments (name) VALUES ('HR'), ('Engineering'), ('Sales');

INSERT INTO employees (id, name, email, department_id) VALUES 
(UUID_TO_BIN(UUID()), 'Alice', 'alice@example.com', 1),
(UUID_TO_BIN(UUID()), 'Bob', 'bob@example.com', 2),
(UUID_TO_BIN(UUID()), 'Bobby', 'bobby@example.com', 2),
(UUID_TO_BIN(UUID()), 'Charlie', 'charlie@example.com', 3),
(UUID_TO_BIN(UUID()), 'David', 'david@example.com', 1),
(UUID_TO_BIN(UUID()), 'Eve', 'eve@example.com', 2),
(UUID_TO_BIN(UUID()), 'Steve', 'steve@example.com', 2),
(UUID_TO_BIN(UUID()), 'Frank', 'frank@example.com', 3),
(UUID_TO_BIN(UUID()), 'Grace', 'grace@example.com', 1),
(UUID_TO_BIN(UUID()), 'Heidi', 'heidi@example.com', 2),
(UUID_TO_BIN(UUID()), 'Ivan', 'ivan@example.com', 3),
(UUID_TO_BIN(UUID()), 'Judy', 'judy@example.com', 2),
(UUID_TO_BIN(UUID()), 'Karl', 'karl@example.com', 2),
(UUID_TO_BIN(UUID()), 'Leo', 'leo@example.com', 2),
(UUID_TO_BIN(UUID()), 'Leopard', 'leopard@example.com', 2),
(UUID_TO_BIN(UUID()), 'Mallory', 'mallory@example.com', 2),
(UUID_TO_BIN(UUID()), 'Nina', 'nina@example.com', 2),
(UUID_TO_BIN(UUID()), 'Oscar', 'oscar@example.com', 2),
(UUID_TO_BIN(UUID()), 'Peggy', 'peggy@example.com', 2),
(UUID_TO_BIN(UUID()), 'Quentin', 'quentin@example.com', 2),
(UUID_TO_BIN(UUID()), 'Rupert', 'rupert@example.com', 2),
(UUID_TO_BIN(UUID()), 'Sybil', 'sybil@example.com', 3);


