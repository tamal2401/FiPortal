DROP TABLE IF EXISTS FI_SESSION_DATA;
  
CREATE TABLE FI_SESSION_DATA (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  sid VARCHAR(250) NOT NULL,
  user_name VARCHAR(250) NOT NULL,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  role VARCHAR(250) NOT NULL,
  email VARCHAR(250) NOT NULL
);

DROP TABLE IF EXISTS mdm_dqrule;

CREATE TABLE mdm_dqrule(
	rule_id serial PRIMARY KEY,
	obj_name VARCHAR(50) NOT NULL,
	entity_name VARCHAR(50) NOT NULL,
	hive_fld_nm VARCHAR(50) NOT NULL,
	rule_name TEXT,
	active_flag char(1),
	created_by VARCHAR(50) NOT NULL,
	created_on TIMESTAMP NOT NULL,
	updated_by VARCHAR(50) NOT NULL,
	last_updated TIMESTAMP NOT NULL
);

DROP TABLE IF EXISTS mdm_trustscoreconfig;

CREATE TABLE mdm_trustscoreconfig(
   rule_id serial PRIMARY KEY,
   table_nm VARCHAR (50) NOT NULL,
   attribute_nm_name VARCHAR (100) NOT NULL,
   src_sys_cd VARCHAR (100) NOT NULL,
   trust_score   VARCHAR (4) NOT NULL,
   active_flag char(1),
   created_on TIMESTAMP NOT NULL
);
