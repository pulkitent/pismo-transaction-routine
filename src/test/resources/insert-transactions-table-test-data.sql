BEGIN;

DELETE FROM transactions;
DELETE FROM accounts;
DELETE FROM operations_type;

INSERT INTO operations_type(id, description)
VALUES (99999999, 'Normal Purchase'), (88888888, 'Purchase with installments'), (77777777, 'Withdrawal'), (66666666, 'Credit Voucher');

INSERT INTO accounts(id, document_number)
VALUES (55555555, '12345678900'), (6666666, '12345678901');

INSERT INTO transactions(id, account_id, operation_type_id, amount)
VALUES (44444444, 55555555, 66666666, 23.5), (55555555, 6666666, 88888888, -23.5);

COMMIT;