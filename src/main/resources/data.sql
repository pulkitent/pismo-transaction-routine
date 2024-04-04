BEGIN;

DELETE FROM operations_type;

INSERT INTO operations_type(id, description)
VALUES (1, 'Normal Purchase'),
(2, 'Purchase with installments'),
(3, 'Withdrawal'),
(4, 'Credit Voucher');

COMMIT;