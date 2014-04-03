package Controller;
import java.sql.*;
import java.util.Date;

public class DataBase {

public boolean find_vehicle(Statement stmt,String v_in) throws SQLException {
String Query="SELECT serial_no FROM vehicle v WHERE '"+v_in+"'=v.serial_no";
boolean re;
re = stmt.executeQuery(Query).next();
return re;
}
public void add_vehicle(Connection con,String serial_no, String maker, String model, Integer year,String color, Integer type_id) throws SQLException{
PreparedStatement stet= con.prepareStatement("insert into vehicle" + "(serial_no,maker,model,year,color,type_id) values" + "(?,?,?,?,?,?)");
stet.setString(1, serial_no);
stet.setString(2, maker);
stet.setString(3, model);
stet.setInt(4, year);
stet.setString(5, color);
stet.setInt(6, type_id);
stet.executeUpdate();
}
public boolean find_people(Statement stmt,String s_in) throws SQLException{
String Query="SELECT name FROM people p WHERE '"+s_in+"'=p.sin";
boolean re = stmt.executeQuery(Query).next();
return re;
}
public ResultSet find_people_by_name(Statement stmt,String name) throws SQLException{
String Query="SELECT name FROM people p WHERE '"+name+"'=lower(p.name)";
ResultSet re = stmt.executeQuery(Query);
return re;
}
public ResultSet get_transaction_id(Statement stmt) throws SQLException{
String Query="select max(transaction_id) from auto_sale+1";
ResultSet re = stmt.executeQuery(Query);
return re;
}

public void delect_secondary_owner(Connection con,String seller) throws SQLException{
String delete = "DELETE from owner where owner_id = ? and is_primary_owner = 'n'";
PreparedStatement stet = con.prepareStatement(delete);
stet.setString(1, seller);
stet.executeUpdate();
}

public void add_auto_sale(Connection con,Integer transaction_id,String seller_id, String buyer_id,String vehicle_id,java.sql.Date s_date, Integer price) throws SQLException{
PreparedStatement stet= con.prepareStatement("insert into auto_sale" + "(transaction_id,seller_id,buyer_id,vehicle_id,s_date,price) values" + "(?,?,?,?,?,?)");
stet.setInt(1, transaction_id);
stet.setString(2, seller_id);
stet.setString(3, buyer_id);
stet.setString(4, vehicle_id);
stet.setDate(5,s_date);
stet.setInt(6, price);
stet.executeUpdate();
}
public void add_people(Connection con,String sin,String name, int height, int weight, String eyecolor, String haircolor, String addr, String gender,  java.sql.Date bir) throws SQLException{
PreparedStatement stet= con.prepareStatement("insert into people" + "(sin,name,height,weight,eyecolor,haircolor,addr,gender,birthday) values" + "(?,?,?,?,?,?,?,?,?)");
stet.setString(1, sin);
stet.setString(2, name);
stet.setInt(3, height);
stet.setInt(4, weight);
stet.setString(5, eyecolor);
stet.setString(6, haircolor);
stet.setString(7, addr);
stet.setString(8, gender);
stet.setDate(9, bir);

stet.execute();
}
public void delect_owner(Connection con,String seller) throws SQLException{
String delete = "DELETE from owner where owner_id = ? and is_primary_owner = 'y'";
PreparedStatement stet = con.prepareStatement(delete);
stet.setString(1, seller);
stet.executeUpdate();
}
public void add_owner(Connection con, String owner_id,String vehicle_id) throws SQLException{
PreparedStatement stet= con.prepareStatement("insert into owner" + "(owner_id,vehicle_id,is_primary_owner) values" + "(?,?,?)");
stet.setString(1, owner_id);
stet.setString(2, vehicle_id);
stet.setString(3, "y");
stet.executeUpdate();
}
public void add_secondary_owner(Connection con, String owner_id,String vehicle_id) throws SQLException{
    PreparedStatement stet= con.prepareStatement("insert into owner" + "(owner_id,vehicle_id,is_primary_owner) values" + "(?,?,?)");
    stet.setString(1, owner_id);
    stet.setString(2, vehicle_id);
    stet.setString(3, "n");
stet.executeUpdate();
}
public <Blog> void add_diver_licence (Connection con, String licence_no,String sin, String class_no,Blob photo,Date issuing_date, Date expiring_date) throws SQLException{
PreparedStatement stet= con.prepareStatement("insert into diver_licence" + "(serial_no,maker,model,year,color,type_id) values" + "(?,?,?,?,?,?)");
stet.setString(1, licence_no);
stet.setString(2, sin);
stet.setString(3, class_no);
stet.setBlob(4, (Blob) photo);
stet.setDate(5,(java.sql.Date) issuing_date);
stet.setDate(6,(java.sql.Date) expiring_date);
stet.executeUpdate();
}

public void add_ticket(Connection con,Integer ticket_no,String violator_no,String vehicle_no,String office_no,String vtype,java.sql.Date vdata,String place,String descriptions) throws SQLException{
PreparedStatement stet= con.prepareStatement("insert into ticket" + "(ticket_no,violator_no,vehicle_no,office_no,vtype,vdata,place,descriptions) values" + "(?,?,?,?,?,?,?,?)");
stet.setInt(1,ticket_no);
stet.setString(2, violator_no);
stet.setString(3, vehicle_no);
stet.setString(4, office_no);
stet.setString(5, vtype);
stet.setDate(6,vdata);
stet.setString(7, place);
stet.setString(8, descriptions);
stet.execute();
}
public boolean find_owner(Statement stmt, String vehicel_no) throws SQLException{
String Query="select p.sin from owner o, vehicle v, people p where v.serial_no='"+vehicel_no+"' and o.vehicle_id=v.serial_no and p.sin = o.owner_id and o.is_primary_owner='y'";
boolean re = stmt.executeQuery(Query).next();
return re;
}
public ResultSet search_people(Statement stmt, String driver_licence_no, String name) throws SQLException{
String Query="(select name, licence_no, addr, birthday, d.class, dc.description, d.expiring_date from drive_licence d, people p, driving_condition dc where '"+driver_licence_no+"' = d.licence_no and p.sin= d.sin) union"
+"(select name, licence_no, addr, birthday, d1.class, dc1.description, d1.expiring_date from people p1, drive_licence d1, driving_condition dc1 where lower(p1.name)='"+name+"' and d1.sin =p1.sin)";
ResultSet re = stmt.executeQuery(Query);
return re;
}

public ResultSet serch_description(Statement stmt, String sin, String driver_licence_no) throws SQLException{
String Query="(select descriptions from ticket k, people p, drive_licence d, owner o where '"+sin+"' = p.sin and p.sin =o.owner_id and o.vehicle_id = d.serial_no)"
+"union (select descriptions from ticket k, people p, drive_licence d, owner o where '"+driver_licence_no+"' =d.licence_no and d.sin=o.owner_id and o.vehicle_id=d.serial_no)";
ResultSet re = stmt.executeQuery(Query);
return re;
}
public ResultSet search_history(Statement stmt,String serial_no) throws SQLException{
String Query="SELECT h.serial_no, count(distinct seller_id || buyer_id), "
+"avg(price), count(distinct t.ticket_no) FROM vehicle h, auto_sale au, ticket t WHERE '"+ serial_no+"' = h.serial_no And t.VEHICLE_ID (+) = h.serial_no AND"
+"au.vehicle_id (+) = h.serial_no GROUP BY h.serial_no";
ResultSet re = stmt.executeQuery(Query);
return re;
}
public boolean find_owner_a(Statement stmt, String seller) throws SQLException{
String Query="select owner_id from owner o where "+seller+" = o.owner_id and o.is_primary_owner='y'";
boolean re = stmt.executeQuery(Query).next();
return re;
}
public boolean find_owner_b(Statement stmt, String seller) throws SQLException{
String Query="select owner_id from owner o where "+seller+" = o.owner_id and o.is_primary_owner='n'";
boolean re = stmt.executeQuery(Query).next();
return re;
}
public ResultSet give_vehicleno(Statement stmt,String seller) throws SQLException{
	String query = "select vehicle_id from owner o where o.owner_id= "+seller;
	ResultSet re = stmt.executeQuery(query);
	return re;
}
}