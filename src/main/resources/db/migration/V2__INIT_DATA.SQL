INSERT INTO category (id, title, parent_id, feature_table)
VALUES (nextval('category_id_seq'), 'Electronics', NULL, NULL),
       (nextval('category_id_seq'), 'TV', 1, 'tv_feature'),
       (nextval('category_id_seq'), 'Computers', 1, 'computer_feature'),
       (nextval('category_id_seq'), 'Auto', NULL, 'auto_feature'),
       (nextval('category_id_seq'), 'Other', NULL, NULL);