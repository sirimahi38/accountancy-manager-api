create table WF_HISTORY
(
wfh_id bigserial  NOT NULL PRIMARY KEY,
wfh_instant TIMESTAMP  NOT NULL,
wfh_au_code varchar,
wfh_g_id int8 NOT NULL,
wfh_n_id int8,
wfh_e_id int8 NOT NULL,
wfh_o_id int8 ,
wfh_class varchar NOT NULL,
wfh_value text NOT NULL,
wfh_on_behalf_of varchar
);