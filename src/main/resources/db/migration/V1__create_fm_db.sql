create sequence client_field_sequence start 1 increment 50;
create sequence client_field_value_sequence start 1 increment 1;
create sequence client_sequence start 1 increment 50;
create sequence consultancy_sequence start 1 increment 50;
create sequence contract_sequence start 1 increment 50;
create sequence deal_queue_sequence start 1 increment 50;
create sequence deal_sequence start 1 increment 50;
create sequence employee_field_sequence start 1 increment 50;
create sequence employee_field_value_sequence start 1 increment 1;
create sequence employee_sequence start 1 increment 50;
create sequence hibernate_sequence start 1 increment 1;
create sequence invoice_sequence start 1 increment 50;
create sequence money_sequence start 1 increment 50;
create sequence payment_sequence start 1 increment 50;
create sequence personal_account_sequence start 1 increment 50;
create sequence personal_account_transfer_sequence start 1 increment 50;
create sequence salary_item_sequence start 1 increment 50;
create sequence salary_sequence start 1 increment 50;
create sequence user_role_sequence start 1 increment 50;
create table client (id int8 not null, created_by varchar(50) not null, created_date timestamp not null, last_modified_by varchar(50), last_modified_date timestamp, first_name varchar(255) not null, last_name varchar(255) not null, personal_account_id int8, primary key (id));
create table client_aud (id int8 not null, rev int4 not null, revtype int2, created_by varchar(50), created_date timestamp, last_modified_by varchar(50), last_modified_date timestamp, first_name varchar(255), last_name varchar(255), personal_account_id int8, primary key (id, rev));
create table client_field (id int8 not null, created_by varchar(50) not null, created_date timestamp not null, last_modified_by varchar(50), last_modified_date timestamp, name varchar(255) not null, primary key (id));
create table client_field_aud (id int8 not null, rev int4 not null, revtype int2, created_by varchar(50), created_date timestamp, last_modified_by varchar(50), last_modified_date timestamp, name varchar(255), primary key (id, rev));
create table client_field_value (id int8 not null, created_by varchar(50) not null, created_date timestamp not null, last_modified_by varchar(50), last_modified_date timestamp, value varchar(255), client_id int8, client_field_id int8 not null, primary key (id));
create table client_field_value_aud (id int8 not null, rev int4 not null, revtype int2, created_by varchar(50), created_date timestamp, last_modified_by varchar(50), last_modified_date timestamp, value varchar(255), client_id int8, client_field_id int8, primary key (id, rev));
create table consultancy (id int8 not null, created_by varchar(50) not null, created_date timestamp not null, last_modified_by varchar(50), last_modified_date timestamp, description varchar(255) not null, name varchar(50) not null, employee_rate_id int8 not null, primary key (id));
create table consultancy_aud (id int8 not null, rev int4 not null, revtype int2, created_by varchar(50), created_date timestamp, last_modified_by varchar(50), last_modified_date timestamp, description varchar(255), name varchar(255), employee_rate_id int8, primary key (id, rev));
create table consultancy_prices (consultancy_id int8 not null, prices_id int8 not null);
create table consultancy_prices_aud (rev int4 not null, consultancy_id int8 not null, prices_id int8 not null, revtype int2, primary key (rev, consultancy_id, prices_id));
create table contract (id int8 not null, created_by varchar(50) not null, created_date timestamp not null, last_modified_by varchar(50), last_modified_date timestamp, close_date date, close_type varchar(255), closing_description varchar(255), contract_date date, payment_date date, payment_type varchar(255) not null, deal_id int8 not null, employee_id int8 not null, employee_rate_id int8 not null, price_id int8 not null, primary key (id));
create table contract_aud (id int8 not null, rev int4 not null, revtype int2, created_by varchar(50), created_date timestamp, last_modified_by varchar(50), last_modified_date timestamp, close_date date, close_type varchar(255), closing_description varchar(255), contract_date date, payment_date date, payment_type varchar(255), deal_id int8, employee_id int8, employee_rate_id int8, price_id int8, primary key (id, rev));
create table deal (id int8 not null, created_by varchar(50) not null, created_date timestamp not null, last_modified_by varchar(50), last_modified_date timestamp, close_date date, open_date date, status varchar(255), client_id int8, consultancy_id int8 not null, primary key (id));
create table deal_aud (id int8 not null, rev int4 not null, revtype int2, created_by varchar(50), created_date timestamp, last_modified_by varchar(50), last_modified_date timestamp, close_date date, open_date date, status varchar(255), client_id int8, consultancy_id int8, primary key (id, rev));
create table deal_queue (id int8 not null, created_by varchar(50) not null, created_date timestamp not null, last_modified_by varchar(50), last_modified_date timestamp, priority varchar(255), queuing_date date, deal_id int8, primary key (id));
create table deal_queue_aud (id int8 not null, rev int4 not null, revtype int2, created_by varchar(50), created_date timestamp, last_modified_by varchar(50), last_modified_date timestamp, priority varchar(255), queuing_date date, deal_id int8, primary key (id, rev));
create table employee (id int8 not null, created_by varchar(50) not null, created_date timestamp not null, last_modified_by varchar(50), last_modified_date timestamp, first_name varchar(255), last_name varchar(255), max_clients int4, username varchar(30), primary key (id));
create table employee_aud (id int8 not null, rev int4 not null, revtype int2, created_by varchar(50), created_date timestamp, last_modified_by varchar(50), last_modified_date timestamp, first_name varchar(255), last_name varchar(255), max_clients int4, username varchar(30), primary key (id, rev));
create table employee_field (id int8 not null, created_by varchar(50) not null, created_date timestamp not null, last_modified_by varchar(50), last_modified_date timestamp, name varchar(255) not null, primary key (id));
create table employee_field_aud (id int8 not null, rev int4 not null, revtype int2, created_by varchar(50), created_date timestamp, last_modified_by varchar(50), last_modified_date timestamp, name varchar(255), primary key (id, rev));
create table employee_field_value (id int8 not null, created_by varchar(50) not null, created_date timestamp not null, last_modified_by varchar(50), last_modified_date timestamp, value varchar(255), employee_id int8, employee_field_id int8 not null, primary key (id));
create table employee_field_value_aud (id int8 not null, rev int4 not null, revtype int2, created_by varchar(50), created_date timestamp, last_modified_by varchar(50), last_modified_date timestamp, value varchar(255), employee_id int8, employee_field_id int8, primary key (id, rev));
create table fm_users (username varchar(30) not null, created_by varchar(50) not null, created_date timestamp not null, last_modified_by varchar(50), last_modified_date timestamp, email varchar(255) not null, enabled boolean default false not null, password varchar(60) not null, primary key (username));
create table fm_users_aud (username varchar(30) not null, rev int4 not null, revtype int2, created_by varchar(50), created_date timestamp, last_modified_by varchar(50), last_modified_date timestamp, email varchar(255), enabled boolean default false, password varchar(60), primary key (username, rev));
create table fm_users_user_role (username varchar(30) not null, role_id int4 not null, primary key (username, role_id));
create table fm_users_user_role_aud (rev int4 not null, username varchar(30) not null, role_id int4 not null, revtype int2, primary key (rev, username, role_id));
create table invoice (id int8 not null, created_by varchar(50) not null, created_date timestamp not null, last_modified_by varchar(50), last_modified_date timestamp, creation_date date, employee_paid boolean, period_from date, period_to date, contract_id int8, price_id int8, primary key (id));
create table invoice_aud (id int8 not null, rev int4 not null, revtype int2, created_by varchar(50), created_date timestamp, last_modified_by varchar(50), last_modified_date timestamp, creation_date date, employee_paid boolean, period_from date, period_to date, contract_id int8, price_id int8, primary key (id, rev));
create table money (id int8 not null, amount int8, currency varchar(255) not null, primary key (id));
create table payment (id int8 not null, created_by varchar(50) not null, created_date timestamp not null, last_modified_by varchar(50), last_modified_date timestamp, date_paid date, invoice_id int8, sum_id int8, primary key (id));
create table payment_aud (id int8 not null, rev int4 not null, revtype int2, created_by varchar(50), created_date timestamp, last_modified_by varchar(50), last_modified_date timestamp, sum_id int8, primary key (id, rev));
create table personal_account (id int8 not null, primary key (id));
create table personal_account_money (personal_account_id int8 not null, money_id int8 not null, primary key (personal_account_id, money_id));
create table personal_account_money_transfer (id int8 not null, created_by varchar(50) not null, created_date timestamp not null, last_modified_by varchar(50), last_modified_date timestamp, description varchar(255) not null, transfer_type varchar(255), money_id int8, personal_account_id int8, primary key (id));
create table personal_account_money_transfer_aud (id int8 not null, rev int4 not null, revtype int2, created_by varchar(50), created_date timestamp, last_modified_by varchar(50), last_modified_date timestamp, description varchar(255), transfer_type varchar(255), money_id int8, personal_account_id int8, primary key (id, rev));
create table revinfo (rev int4 not null, revtstmp int8, primary key (rev));
create table salary (id int8 not null, date_from date not null, date_to date not null, paid boolean, date_salary date not null, employee_id int8 not null, total_amount_id int8, primary key (id));
create table salary_aud (id int8 not null, rev int4 not null, revtype int2, total_amount_id int8, primary key (id, rev));
create table salary_item (id int8 not null, accounted boolean, date_from date not null, date_to date not null, employee_id int8 not null, employee_payment_id int8, invoice_id int8 not null, salary_id int8, primary key (id));
create table salary_item_aud (id int8 not null, rev int4 not null, revtype int2, employee_payment_id int8, primary key (id, rev));
create table user_role (id int4 not null, created_by varchar(50) not null, created_date timestamp not null, last_modified_by varchar(50), last_modified_date timestamp, role varchar(45) not null, primary key (id));
create table user_role_aud (id int4 not null, rev int4 not null, revtype int2, created_by varchar(50), created_date timestamp, last_modified_by varchar(50), last_modified_date timestamp, role varchar(45), primary key (id, rev));
alter table client_field add constraint UK_d8m2oueppn7wiwvq3avwuy1jw unique (name);
alter table consultancy_prices add constraint UK_2u2ure2angmp9ocfdct6843ve unique (prices_id);
alter table employee add constraint UK_im8flsuftl52etbhgnr62d6wh unique (username);
alter table personal_account_money add constraint UK_mtxwkox4nuob2fttvburpyq7c unique (money_id);
alter table user_role add constraint UK_s21d8k5lywjjc7inw14brj6ro unique (role);
alter table client add constraint FK82cva2q1iah7mjpflb0hna4cr foreign key (personal_account_id) references personal_account;
alter table client_aud add constraint FKq7rlntwn6l0k20fxnu2ro82h6 foreign key (rev) references revinfo;
alter table client_field_aud add constraint FKgotxqwwoa5icmub0vgjjftdcu foreign key (rev) references revinfo;
alter table client_field_value add constraint FK59491srurbv0r94j5vq7uf5ud foreign key (client_id) references client;
alter table client_field_value add constraint FK9tj8kwg25viagdwhuiij8bg8o foreign key (client_field_id) references client_field;
alter table client_field_value_aud add constraint FKdfgkkwb52302kf3dwtt02x795 foreign key (rev) references revinfo;
alter table consultancy add constraint FKmy4onqefgqwlefb8sswor1c8w foreign key (employee_rate_id) references money;
alter table consultancy_aud add constraint FKnb2f0pguiwp1ne9x29sjg3nmm foreign key (rev) references revinfo;
alter table consultancy_prices add constraint FK9v3qy8p4a4y1d8ftmj6qlisdx foreign key (prices_id) references money;
alter table consultancy_prices add constraint FK47u6y5k26shfhak5086o0dg63 foreign key (consultancy_id) references consultancy;
alter table consultancy_prices_aud add constraint FKey2rtev9ku4fbub1g6xwk9u1s foreign key (rev) references revinfo;
alter table contract add constraint FKlkqjwauus9felhfas3q5fbqi4 foreign key (deal_id) references deal;
alter table contract add constraint FKr2iw6grixlkbx43q2svdrhbb9 foreign key (employee_id) references employee;
alter table contract add constraint FKcyb9625she6jq8hjit1khowmr foreign key (employee_rate_id) references money;
alter table contract add constraint FKn6iquufmx3j3rmvh1p7opux5b foreign key (price_id) references money;
alter table contract_aud add constraint FKdwmknd8t7wjko72bg4ka0gtnb foreign key (rev) references revinfo;
alter table deal add constraint FKhvdhqqvs19c5y13vej3kv1byl foreign key (client_id) references client;
alter table deal add constraint FKq1r2s4rf3xq2d3cefp3ffwuw5 foreign key (consultancy_id) references consultancy;
alter table deal_aud add constraint FKicd7ybws9mrxonxnv0x0ksurj foreign key (rev) references revinfo;
alter table deal_queue add constraint FKip32k98e5brljf0r9c6krsq7r foreign key (deal_id) references deal;
alter table deal_queue_aud add constraint FKfgmu152mmr1wiwa40fs2fln0r foreign key (rev) references revinfo;
alter table employee add constraint FKp18svs86ndf3l9p610664b28o foreign key (username) references fm_users;
alter table employee_aud add constraint FK118cwnbfk1ny0ttu4bfqmeh8q foreign key (rev) references revinfo;
alter table employee_field_aud add constraint FKs0prhofwiaj6dcj39acvibg22 foreign key (rev) references revinfo;
alter table employee_field_value add constraint FK5hq1j88vrti7rj32hig5l1xfg foreign key (employee_id) references employee;
alter table employee_field_value add constraint FKe7nvcnw9hkb6nm0nh6h2rf7wy foreign key (employee_field_id) references employee_field;
alter table employee_field_value_aud add constraint FKady3ksynxybw3xxjkkskfkey4 foreign key (rev) references revinfo;
alter table fm_users_aud add constraint FK8erqg0etv657u526y7u6v9qvw foreign key (rev) references revinfo;
alter table fm_users_user_role add constraint FK55jwu3i3ryl5e8tk74jkx0lq6 foreign key (role_id) references user_role;
alter table fm_users_user_role add constraint FKmnlfe59hm04jw3v8fovc1l88i foreign key (username) references fm_users;
alter table fm_users_user_role_aud add constraint FKlksh2vnrerv8l9o10rq5pmvs8 foreign key (rev) references revinfo;
alter table invoice add constraint FKqh9ibaacfusht7an2afwkrq5 foreign key (contract_id) references contract;
alter table invoice add constraint FK2w9o8bijbcraupfqx7rhinepn foreign key (price_id) references money;
alter table invoice_aud add constraint FK57bloicw9h0k4c7xwn0i7u29p foreign key (rev) references revinfo;
alter table payment add constraint FKsb24p8f52refbb80qwp4gem9n foreign key (invoice_id) references invoice;
alter table payment add constraint FKq02a57mamoxryih8wbxrrh13c foreign key (sum_id) references money;
alter table payment_aud add constraint FK1uumpbl0vkiohnbo3v2pp4qsj foreign key (rev) references revinfo;
alter table personal_account_money add constraint FKk2tsss38w2m161bufubip1927 foreign key (money_id) references money;
alter table personal_account_money add constraint FKks6pay2ixwujt7jslkqu319aj foreign key (personal_account_id) references personal_account;
alter table personal_account_money_transfer add constraint FKa1mpr5ypfco8hbf5rn2je9oi6 foreign key (money_id) references money;
alter table personal_account_money_transfer add constraint FKexobeag7besmh86b6bdsco2q6 foreign key (personal_account_id) references personal_account;
alter table personal_account_money_transfer_aud add constraint FKp1ax0h2k0v3mpa37e8beix2ws foreign key (rev) references revinfo;
alter table salary add constraint FKnlnv3jbyvbiu8ci59r3btlk00 foreign key (employee_id) references employee;
alter table salary add constraint FKkag3v1liwmtvxgo64wb1026rf foreign key (total_amount_id) references money;
alter table salary_aud add constraint FKchi7yvayd79mnqnjifwejwjps foreign key (rev) references revinfo;
alter table salary_item add constraint FKfminbjrwpaucu6ewstpvln9vg foreign key (employee_id) references employee;
alter table salary_item add constraint FKihm4d8lj7nikpd279k1w04ai8 foreign key (employee_payment_id) references money;
alter table salary_item add constraint FK6gb320s1x010huttetoyvb7s5 foreign key (invoice_id) references invoice;
alter table salary_item add constraint FK26tnbqh5tcippll4b6w4hn82r foreign key (salary_id) references salary;
alter table salary_item_aud add constraint FKaf4i93cypbcyhs8lhxd91l7kd foreign key (rev) references revinfo;
alter table user_role_aud add constraint FK2ax4xks5sy1yh2a2gxdndkcmc foreign key (rev) references revinfo;
