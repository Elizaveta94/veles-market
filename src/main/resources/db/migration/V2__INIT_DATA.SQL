INSERT INTO category (id, title, parent_id, feature_category)
VALUES (nextval('category_id_seq'), 'Electronics', NULL, NULL),
       (nextval('category_id_seq'), 'TV', 1, 'tv'),
       (nextval('category_id_seq'), 'Computers', 1, 'computer'),
       (nextval('category_id_seq'), 'Auto', NULL, 'auto'),
       (nextval('category_id_seq'), 'Other', NULL, NULL);