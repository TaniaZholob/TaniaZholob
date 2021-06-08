package com.example.TestProject.constants;

public class SQLquary {
    /*MASTER*/
    public static final String SQL__FIND_MASTER_BY_ID = "SELECT * FROM masters WHERE id=?";
    public static final String SQL__FIND_MASTERS_BY_PROCEDURE = "select * from masters inner JOIN "+
            "(SELECT * FROM procedures_has_masters as l where procedure_id=(SELECT id from procedures as k where title=?))as h ON id=master_id";
    public static final String SQL__COUNT_MASTERS = "select count(*) as id from masters";
    public static final String SQL__FIND_MASTERS = "SELECT * FROM masters";
    public static final String SQL__FIND_MASTER_BY_NAME_SURNAME = "SELECT * FROM masters WHERE name=? and surname=?";
    public static final String SQL__UPDATE_MASTER_RATING ="UPDATE masters set rating=(SELECT AVG(rating) from reviews where master_id=?) where id=?";
    /*Procedure*/
    public static final String SQL__FIND_PROCEDURE_BY_ID = "SELECT * FROM procedures WHERE id=?";
    public static final String SQL__FIND_PROCEDURE_BY_TITLE = "SELECT * FROM procedures WHERE title=?";
    public static final String SQL__FIND_PROCEDURES = "SELECT * FROM procedures";

    /*USER*/
    public static final String SQL__FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login=? and password=?";
    public static final String SQL__FIND_USER_BY_ID = "SELECT * FROM users WHERE id=?";
    public static final String SQL__UPDATE_USER = "UPDATE users SET password=?, firstName=?, lastName=? WHERE id=?";
    public static final String SQL__INSERT_USER = "INSERT INTO users VALUES(null, ?,?,?,?,?)";
    /*ORDER*/
    public static final String SQL__FIND_TIMESLOT_BY_ID = "SELECT * FROM time_slots WHERE id=?";
    public static final String SQL__INSERT_TIME_SLOT = "INSERT INTO time_slots VALUES(null, ?,?)";
    public static final String SQL__FIND_TIMESLOT_BY_DATES = "SELECT id FROM time_slots WHERE master_id=? and date_time=?";
    public static final String SQL__COUNT_RECORDS = "select count(*) as id from orders o where  o.payment_status_id<>3";
    public static final String SQL__UPDATE_PAYMENT_STATUS ="Update beauty_salon.orders set payment_status_id=? where id=?;";
    public static final String SQL__FIND_ALL_RECORDS_BY_USER = "SELECT o.id, u.login, u.firstName, u.lastName, p.title as service, mt.dateTime, mt.nameOfMaster, mt.surnameOfMaster, ps.name as paymentStatus, pefS.name as performStatus"+
            " from orders as o inner join users as u on o.user_id=u.id inner join procedures as p on o.procedure_id=p.id "+
            " inner join (select ts.id as idOfTimeslot,  ts.date_time as dateTime, name as nameOfMaster,  surname as surnameOfMaster from masters m inner join time_slots ts on m.id=ts.master_id)as mt on o.time_slot_id=idOfTimeslot "+
            "inner join payment_statuses ps on o.payment_status_id=ps.id inner join performance_statuses pefS on o.performance_statuses_id=pefS.id where o.user_id=? "+/*and o.payment_status_id<>3 and (o.performance_statuses_id<>2 or 1)*/" order by id";
    public static final String SQL__UPDATE_TIME_IN_TIMESLOT = "UPDATE beauty_salon.time_slots set date_time=? where id=?";
    public static final String SQL__SELECT_ID_OF_TIME_SLOT_BY_ID_OF_ORDER = "SELECT time_slot_id from beauty_salon.orders WHERE id=?";
    public static final String SQL__DELETE_RECORD = "DELETE from beauty_salon.orders where id=?";
    public static final String SQL__CHANGE_PERFORM_STATUS = "update beauty_salon.orders set performance_statuses_id=2 where id=?;";
    public static final String SQL__GET_ORDER_BY_ID = "SELECT * from orders where id=?;";
    /*REWIEV*/


    public static final String SQL__INSERT_REVIEW = "INSERT INTO reviews VALUES(null, ?,?,?,?)";
    public static final String SQL__FIND_REVIEWS_BY_MASTER_ID ="SELECT * FROM beauty_salon.reviews where master_id=?;" ;


    public static String SQL__INSERT_Record = "INSERT INTO orders VALUES(null, 1,?,?,0,0,?)";


    public static String SQL__FIND_ALL_RECORDS = "SELECT o.id, u.login, u.firstName, u.lastName, p.title as service, mt.dateTime, mt.nameOfMaster, mt.surnameOfMaster, ps.name as paymentStatus, pefS.name as performStatus"+
            " from orders as o inner join users as u on o.user_id=u.id inner join procedures as p on o.procedure_id=p.id "+
            " inner join (select ts.id as idOfTimeslot,  ts.date_time as dateTime, name as nameOfMaster,  surname as surnameOfMaster from masters m inner join time_slots ts on m.id=ts.master_id)as mt on o.time_slot_id=idOfTimeslot "+
            "inner join payment_statuses ps on o.payment_status_id=ps.id inner join performance_statuses pefS on o.performance_statuses_id=pefS.id where o.payment_status_id<>3 order by id  LIMIT ?, ?";


    public static String SQL__FIND_ALL_MASTER_RECORDS = "SELECT o.id, u.login, u.firstName, u.lastName, p.title as service, mt.dateTime, mt.nameOfMaster, mt.surnameOfMaster, ps.name as paymentStatus, pefS.name as performStatus "+
    "from orders as o inner join users as u on o.user_id=u.id inner join procedures as p on o.procedure_id=p.id "+
    "inner join (select ts.id as idOfTimeslot,  ts.date_time as dateTime, name as nameOfMaster,  surname as surnameOfMaster from masters m  inner join time_slots ts on m.id=ts.master_id where m.name=? and m.surname=?)as mt on o.time_slot_id=idOfTimeslot "+
    "inner join payment_statuses ps on o.payment_status_id=ps.id inner join performance_statuses pefS on o.performance_statuses_id=pefS.id where  o.payment_status_id<>3 and (o.performance_statuses_id<>2 or 1) order by id";
}
