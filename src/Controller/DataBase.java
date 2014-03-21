package Controller;
import java.sql.*;
public class DataBase {

    public ResultSet find_vehicle(Statement stmt,String v_in) throws SQLException {
        String Query="SELECT serial_no FROM vehicle v WHERE"+v_in+"=v.serial_no";
        ResultSet re;
        re = stmt.executeQuery(Query);
        return re;
    }
    public void add_vehicle(Statement stmt,String serial_no, String maker, String model, String year,String color, String type_id) throws SQLException{
        String stetment="INSERT INTO vehicle values("+serial_no+",'"+maker+"','"+model+"',"+year+",'"+color+"',"+type_id+")";
        stmt.executeUpdate(stetment);
    }   
    public ResultSet find_people(Statement stmt,String s_in) throws SQLException{
        String Query="SELECT name FROM people p WHERE"+s_in+"=p.sin";
        ResultSet re = stmt.executeQuery(Query);
        return re;
    }
    public ResultSet get_transaction_id(Statement stmt) throws SQLException{
        String Query="select max(transaction_id) from auto_sale+1";
        ResultSet re = stmt.executeQuery(Query);
        return re;
    }
   
    public void add_auto_sale(Statement stmt,String transaction_id,String seller_id, String buyer_id,String vehicle_id,String s_date, String price) throws SQLException{
        String stetment="INSERT INTO auto_sale values("+transaction_id+","+seller_id+","+buyer_id+","+vehicle_id+","+s_date+","+price+")";
        stmt.executeUpdate(stetment);
    }
    public void add_people(Statement stmt,String sin,String name, String height, String weight, String eyecolor, String haircolor, String addr, String gender, String birthday) throws SQLException{
        String stetment="INSERT INTO people values("+sin+","+name+","+height+","+weight+","+weight+","+weight+","+weight+","+weight+","+weight+")";
        stmt.executeUpdate(stetment);
    }
    public void delect_owner(Statement stmt,String buyer,String seller) throws SQLException{
        String stetment="DELETE from owner o,where "+seller+"=o.owner_idand o.is_primary_owner=1 ";
        stmt.executeUpdate(stetment);
    }
    public void add_owner(Statement stmt, String owner_id,String vehicle_id,String in_primary_owner) throws SQLException{
        String stetment="insert into owner values("+owner_id+","+vehicle_id+"y)";
        stmt.executeUpdate(stetment);
    }
 /*   public void add_diver_licence (Statement stmt, String licence_no,String sin, String class_no, ,String issuing_date, String expiring_date){
        String stetment="insert into diver_licence values("+licence_no+","+sin+","+class_no+","++","+issuing_date+","+expiring_date+")";
        stmt.executeUpdate(stetment);       
    }*/
   
    public void add_ticket(Statement stmt,String ticket_no,String violator_no,String vehicle_no,String office_no,String vtype,String vdata,String place,String descriptions) throws SQLException{
        String stetment="insert into ticket values("+ticket_no+","+violator_no+","+vehicle_no+","+office_no+","+vtype+","+vdata+","+place+","+descriptions+")";
        stmt.executeUpdate(stetment);       
    }   
    public ResultSet find_owner(Statement stmt, String vehicel_no) throws SQLException{
        String Query="select p.sin from owner o, vehicle v, people p where v.serial_no="+vehicel_no+" and o.vehicle_id=v.serial_no and p.sin = o.owner_id and o.is_primary_owner='y'";
        ResultSet re = stmt.executeQuery(Query);   
        return re;
    }
    public ResultSet search_people(Statement stmt, String driver_licence_no, String name) throws SQLException{
        String Query="(select name, licence_no, addr, birthday, d.class, dc.description, d.expiring_date from drive_licence d, people p, driving_condition dc where "+driver_licence_no+" = d.licence_noand p.sin= d.sin) union"
+"(select name, licence_no, addr, birthday, d1.class, dc1.description, d1.expiring_date from people p1, drive_licence d1, driving_condition dc1 where p1.name="+name+"and d1.sin =p1.sin)";
        ResultSet re = stmt.executeQuery(Query);
        return re;
    }
    public ResultSet serch_description(Statement stmt, String sin, String driver_licence_no) throws SQLException{
        String Query="(select descriptions from ticket k, people p, drive_licence d, owner o where  "+sin+" = p.sin and p.sin =o.owner_id and o.vehicle_id = d.serial_no)"
+"union (select descriptions from ticket k, people p, drive_licence d, owner o where "+driver_licence_no+"=d.licence_no and d.sin=o.owner_id and o.vehicle_id=d.serial_no)";
        ResultSet re = stmt.executeQuery(Query);
        return re;
    }
    public ResultSet search_history(Statement stmt) throws SQLException{
        String Query="CREATE view vehicle_history (vehicle_no, number_sales, average_price, total_tickets) as SELECT  h.serial_no, count(distinct seller_id || buyer_id), "
                +"avg(price), count(distinct t.ticket_no) FROM vehicle h, auto_sale au, ticket t WHERE   t.VEHICLE_ID (+) = h.serial_no AND"
                +"au.vehicle_id (+) = h.serial_no GROUP BY h.serial_no";
        ResultSet re = stmt.executeQuery(Query);
        return re;
    }
}
