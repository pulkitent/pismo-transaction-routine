CREATE TABLE IF NOT EXISTS transactions(
    id SERIAL PRIMARY KEY,
    account_id BIGINT NOT NULL,
    operation_type_id BIGINT NOT NULL,
    amount NUMERIC(1000,2) NOT NULL,
    event_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_transaction_account_id FOREIGN KEY (account_id) REFERENCES accounts(id),
    CONSTRAINT fk_transaction_operation_id FOREIGN KEY (operation_type_id) REFERENCES operations_type(id)
);