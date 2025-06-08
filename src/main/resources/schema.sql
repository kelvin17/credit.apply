CREATE TABLE IF NOT EXISTS apply_order (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    apply_order_id VARCHAR(100) NOT NULL,
    user_name VARCHAR(100) NOT NULL,
    status VARCHAR(16) NOT NULL,
    certificate_type VARCHAR(16) NOT NULL,
    certificate_id VARCHAR(64) NOT NULL,
    phone_number VARCHAR(64),
    email VARCHAR(64),
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modify_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS user_credit_account (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(100) NOT NULL,
    certificate_type VARCHAR(16) NOT NULL,
    certificate_id VARCHAR(64) NOT NULL,
    has_credit_quota BOOLEAN NOT NULL,
    valid_date_begin TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    valid_date_end TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    quota_amount DECIMAL(15, 2),
    used_amount DECIMAL(15, 2),
    currency VARCHAR(10),
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modify_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY unique_user_cert (user_name, certificate_type, certificate_id)
);

CREATE TABLE IF NOT EXISTS data_collect_task (
    task_id VARCHAR(100) NOT NULL PRIMARY KEY,
    apply_order_id VARCHAR(100) NOT NULL,
    user_name VARCHAR(100) NOT NULL,
    data_item VARCHAR(100) NOT NULL,
    status VARCHAR(16) NOT NULL,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modify_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);