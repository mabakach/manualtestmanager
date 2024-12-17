insert into public.role (sysid, sysinsertedby, sysupdatedby, sysinsertts, sysupdatets, sysversion, rolename) values (1, 'setup', 'setup', current_timestamp, current_timestamp, 1, 'ADMIN');
alter sequence public.role_seq restart with 2;

--Password: Password
insert into public.user (sysid, sysinsertedby, sysupdatedby, sysinsertts, sysupdatets, sysversion, username, password) values (1, 'setup', 'setup', current_timestamp, current_timestamp, 1, 'admin', '$2a$10$i1i9rzVJN08KMJNuOWpuo.tMe7cOSMtMyhF55m9plzKXtj/E6dhoS');
alter sequence public.user_seq restart with 2;

insert into public.user_role(users_sysid, roles_sysid) values (1,1);
