CREATE DATABASE gear_rent_pro;
USE gear_rent_pro;

CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    role ENUM('ADMIN', 'MANAGER', 'STAFF') NOT NULL
);

CREATE TABLE branches (
    branch_id INT AUTO_INCREMENT PRIMARY KEY,
    branch_code VARCHAR(10) UNIQUE NOT NULL,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(255),
    contact VARCHAR(20)
);

CREATE TABLE memberships (
    membership_id INT AUTO_INCREMENT PRIMARY KEY,
    level ENUM('REGULAR', 'SILVER', 'GOLD') UNIQUE NOT NULL,
    discount_percentage DOUBLE NOT NULL
);

CREATE TABLE customers (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    nic_passport VARCHAR(50) UNIQUE NOT NULL,
    contact VARCHAR(20),
    email VARCHAR(100),
    address VARCHAR(255),
    membership_id INT,
    FOREIGN KEY (membership_id) REFERENCES memberships(membership_id)
);

INSERT INTO memberships (level, discount_percentage) VALUES
('REGULAR', 0),
('SILVER', 5),
('GOLD', 10);

INSERT INTO branches (branch_code, name, address, contact) VALUES
('PAN', 'Panadura Branch', 'Panadura', '0711111111'),
('GAL', 'Galle Branch', 'Galle', '0722222222'),
('COL', 'Colombo Branch', 'Colombo', '0733333333');


INSERT INTO users (username, password, role) VALUES
('admin', 'admin123', 'ADMIN'),
('manager1', 'manager123', 'MANAGER'),
('staff1', 'staff123', 'STAFF');

SELECT * FROM users;
SELECT * FROM branches;
SELECT * FROM memberships;
SELECT * FROM customers;

CREATE TABLE categories (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(255),
    base_price_factor DOUBLE NOT NULL,
    weekend_multiplier DOUBLE NOT NULL,
    late_fee_per_day DOUBLE
);

INSERT INTO categories (name, description, base_price_factor, weekend_multiplier, late_fee_per_day) VALUES
('Camera', 'Professional cameras', 1.0, 1.2, 1500),
('Lens', 'Camera lenses', 0.8, 1.1, 1000),
('Drone', 'Aerial drones', 1.5, 1.3, 3000),
('Lighting', 'Lighting kits', 0.9, 1.1, 1200),
('Audio', 'Audio equipment', 0.7, 1.1, 800);

SELECT * FROM categories;

CREATE TABLE equipment (
    equipment_id INT AUTO_INCREMENT PRIMARY KEY,
    category_id INT NOT NULL,
    branch_id INT NOT NULL,
    brand VARCHAR(50),
    model VARCHAR(50),
    purchase_year INT,
    base_daily_price DOUBLE NOT NULL,
    deposit_amount DOUBLE NOT NULL,
    status ENUM('AVAILABLE', 'RESERVED', 'RENTED', 'MAINTENANCE') DEFAULT 'AVAILABLE',
    FOREIGN KEY (category_id) REFERENCES categories(category_id),
    FOREIGN KEY (branch_id) REFERENCES branches(branch_id)
);

INSERT INTO equipment (category_id, branch_id, brand, model, purchase_year, base_daily_price, deposit_amount, status) VALUES
(1, 1, 'Canon', 'EOS R5', 2022, 12000, 100000, 'AVAILABLE'),
(1, 2, 'Nikon', 'Z6 II', 2021, 11000, 90000, 'AVAILABLE'),
(2, 1, 'Sony', '24-70mm', 2020, 6000, 50000, 'AVAILABLE'),
(3, 3, 'DJI', 'Mavic Air 2', 2023, 20000, 150000, 'AVAILABLE'),
(4, 2, 'Godox', 'SL60W', 2021, 5000, 40000, 'AVAILABLE'),
(5, 1, 'Rode', 'NTG4+', 2022, 4000, 30000, 'AVAILABLE');

SELECT * FROM equipment;

CREATE TABLE reservations (
    reservation_id INT AUTO_INCREMENT PRIMARY KEY,
    equipment_id INT NOT NULL,
    customer_id INT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    status ENUM('ACTIVE', 'CANCELLED') DEFAULT 'ACTIVE',
    FOREIGN KEY (equipment_id) REFERENCES equipment(equipment_id),
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

CREATE TABLE rentals (
    rental_id INT AUTO_INCREMENT PRIMARY KEY,
    equipment_id INT NOT NULL,
    customer_id INT NOT NULL,
    branch_id INT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    rental_amount DOUBLE NOT NULL,
    deposit_amount DOUBLE NOT NULL,
    membership_discount DOUBLE,
    long_rental_discount DOUBLE,
    final_amount DOUBLE NOT NULL,
    payment_status ENUM('PAID', 'UNPAID') DEFAULT 'UNPAID',
    rental_status ENUM('ACTIVE', 'RETURNED', 'OVERDUE', 'CANCELLED') DEFAULT 'ACTIVE',
    FOREIGN KEY (equipment_id) REFERENCES equipment(equipment_id),
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id),
    FOREIGN KEY (branch_id) REFERENCES branches(branch_id)
);

CREATE TABLE returns (
    return_id INT AUTO_INCREMENT PRIMARY KEY,
    rental_id INT NOT NULL,
    actual_return_date DATE NOT NULL,
    damage_description VARCHAR(255),
    damage_charge DOUBLE DEFAULT 0,
    late_fee DOUBLE DEFAULT 0,
    FOREIGN KEY (rental_id) REFERENCES rentals(rental_id)
);

INSERT INTO customers (name, nic_passport, contact, email, address, membership_id) VALUES
('Amaya Perera', 'NIC123456V', '0771234567', 'amaya@gmail.com', 'Colombo', 1),
('Nimal Silva', 'NIC234567V', '0712345678', 'nimal@gmail.com', 'Galle', 2),
('Sahan Fernando', 'NIC345678V', '0723456789', 'sahan@gmail.com', 'Panadura', 3),
('Kavindi Jayasuriya', 'NIC456789V', '0754567890', 'kavindi@gmail.com', 'Colombo', 1),
('Ravindu Perera', 'NIC567890V', '0765678901', 'ravindu@gmail.com', 'Galle', 2);

SELECT * FROM customers;

UPDATE equipment
SET status = 'RENTED'
WHERE equipment_id = 1;

INSERT INTO equipment (category_id, branch_id, brand, model, purchase_year, base_daily_price, deposit_amount, status) VALUES
-- Cameras
(1, 1, 'Sony', 'A7 III', 2021, 11500, 95000, 'AVAILABLE'),
(1, 2, 'Canon', 'EOS R6', 2022, 12500, 105000, 'AVAILABLE'),
(1, 3, 'Fujifilm', 'XT-4', 2020, 10000, 85000, 'AVAILABLE'),

-- Lenses
(2, 1, 'Canon', '50mm f/1.8', 2019, 4500, 30000, 'AVAILABLE'),
(2, 2, 'Nikon', '70-200mm', 2021, 7000, 60000, 'AVAILABLE'),
(2, 3, 'Sony', '85mm f/1.8', 2020, 6500, 55000, 'AVAILABLE'),

-- Drones
(3, 1, 'DJI', 'Mini 3 Pro', 2023, 18000, 140000, 'AVAILABLE'),
(3, 2, 'DJI', 'Air 2S', 2022, 22000, 160000, 'AVAILABLE'),
(3, 3, 'Autel', 'Evo Lite+', 2022, 21000, 155000, 'AVAILABLE'),

-- Lighting
(4, 1, 'Aputure', 'Light Storm 120D', 2021, 6000, 50000, 'AVAILABLE'),
(4, 2, 'Nanlite', 'Forza 60', 2022, 5500, 45000, 'AVAILABLE'),
(4, 3, 'Godox', 'VL150', 2020, 6500, 52000, 'AVAILABLE'),

-- Audio
(5, 1, 'Zoom', 'H6 Recorder', 2021, 5000, 40000, 'AVAILABLE'),
(5, 2, 'Sennheiser', 'MKH 416', 2020, 7000, 60000, 'AVAILABLE'),
(5, 3, 'Tascam', 'DR-40X', 2022, 4500, 35000, 'AVAILABLE');

SELECT COUNT(*) FROM equipment;

ALTER TABLE users ADD COLUMN branch_id INT;
