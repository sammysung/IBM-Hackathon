import java.sql.*;
import java.util.Scanner;

public class test{

    public static void main(String[] args){
        //Student students = new Student();
        //students.;
        String[] students = {"0253457", "4829728", "st83434"};

	    int sel = (int) (Math.random() * 3);
	    String sel_stu = students[sel];
	    System.out.println("Welcome, user: "+sel_stu+"! \nLogging you in...\n");
        String url = "jdbc:mysql://127.0.0.1:3306/stu_table?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String userName = "sam";
        String password = "password";
        int enroll = 1;
        int credits = 0;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, userName, password);
            Statement st = con.createStatement();
            String sql = "select first, last from student where id = '"+sel_stu+"'";
            ResultSet rs = st.executeQuery(sql);
            //PreparedStatement prep = null;
            while(rs.next())
                 System.out.println(String.format("Welcome back, %s %s!\n", rs.getString(1), rs.getString(2)));
            st = con.createStatement();
            sql = "select credits from course where id = (select course_id from offering, enroll where enroll.sem_id = offering.id and enroll.student_id = '"+sel_stu+"')";
            rs = st.executeQuery(sql);
            while(rs.next())
                credits+=rs.getInt(1);
            while(rs.next())
                 System.out.println(String.format("Welcome back, %s %s!\n", rs.getString(1), rs.getString(2)));
            int menu = 0;
            int step = 0;
            int dept = 0;
            String course = "";
            String list = "";
            String section = "";
            Scanner input = new Scanner(System.in);
            while (menu < 6){
                System.out.println("Main Menu.\nSelect an Option by Number.");
                System.out.println("1. Select by Department.");
                System.out.println("2. Direct Enroll (must know exact course id!).");
                System.out.println("Enter 6 or higher to exit.\n");
                step = input.nextInt();
                System.out.println();
                if (step == 1){
                    System.out.println("Please input the department number you wish to search for.");
                    System.out.println("1. Liberal Arts\n2. Mathematics\n3. Natural Sciences\n");
                    dept = input.nextInt();
                    input.nextLine();
                    st = con.createStatement();
                    sql = "select id from course where dept_id = "+dept;
                    rs = st.executeQuery(sql);
                    System.out.println("\nNext, select the course you would like to register for from this list.\n\n");
                    System.out.println("Courses");
                    System.out.println("---------");
                    while (rs.next()) {
                        System.out.println(String.format("%-20s", rs.getString(1)));
                    }
                    System.out.println("\nSelect the course you would like here:\n");
                    course = input.nextLine();
                    st = con.createStatement();
                    sql = "select course_id, id, day, start, end, max_student, num_registered from offering where course_id = '"+course+"'";
                    rs = st.executeQuery(sql);
                    System.out.println("\n Course       Section    Semester     Days      Start       End       Maximum     Registered");
                    System.out.println("--------     ---------   ---------   ------    -------   ----------   ---------  ------------");

                    while (rs.next()) {
                        list = String.format(" %-14s %-8s Fall 2019   %-9s %-10s %-14s %-10s %-10s", rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                        System.out.println(list+"\n");
                    }
                    System.out.println("Select the section number you would like to register for:\n");
                    section = input.nextLine();
                    st = con.createStatement();
                    sql = "select sem_id from enroll where student_id = '"+sel_stu+"'";
                    rs = st.executeQuery(sql);
                    boolean present = false;

                    while(rs.next()){
                        String check = rs.getString(1);
                        if (check.equals(section)){
                             present = true;
                             break;
                        }
                    }

                    if (present == true){
                        System.out.println("Already enrolled in this course!");
                        continue;
                    }

                    st = con.createStatement();
                    sql = "select credits from course where id = (select course_id from offering where id = '"+section+"')";
                    rs = st.executeQuery(sql);

                    while(rs.next()){
                        if((credits + rs.getInt(1)) > 12){
                            present = true;
                            break;
                        }
                        credits+=rs.getInt(1);
                    }


                    if (present == true){
                        System.out.println("Enrolled in too many credits already! You are currently enrolled in "+credits+" credits!");
                        continue;
                    }

                    //credits += rs.getInt(1);
                    //st = con.createStatement();

                    sql = "insert into enroll (id, student_id, sem_id) values (?, ?, ?)";
                    PreparedStatement prep = con.prepareStatement(sql);
                    prep.setInt(1, enroll);
                    prep.setString(2, sel_stu);
                    prep.setString(3, section);
                    int row = prep.executeUpdate();
                    prep.close();

                    st = con.createStatement();
                    sql = "select first, last, student.id from student, enroll where enroll.id = "+enroll;
                    rs = st.executeQuery(sql);
                    while(rs.next()){
                         if(sel_stu.equals(rs.getString(3))){
                             System.out.println(String.format("%s %s has registered for section "+section+"!",         rs.getString(1), rs.getString(2)));
                         }
                    }
                    enroll++;
                }
                else if (step == 2){
                    System.out.println("Input the course you would like to register for; must be exact course id match!\n");
                    course=input.nextLine();
                    st = con.createStatement();
                    sql = "select course_id, id, day, start, end, max_student, num_registered from offering where course_id = '"+course+"'";
                    rs = st.executeQuery(sql);
                    System.out.println("\n Course       Section     Days      Start       End       Maximum     Registered");
                    System.out.println("--------     ---------   ------    -------   ----------   ---------  ------------");

                    while (rs.next()) {
                        list = String.format(" %-14s %-8s %-9s %-10s %-14s %-10s %-10s", rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                        System.out.println(list+"\n");
                    }
                    System.out.println("Select the section number you would like to register for:\n");
                    section = input.nextLine();
                    st = con.createStatement();
                    sql = "select sem_id from enroll where student_id = '"+sel_stu+"'";
                    rs = st.executeQuery(sql);
                    boolean present = false;

                    while(rs.next()){
                        String check = rs.getString(1);
                        if (check.equals(section)){
                             present = true;
                             break;
                        }
                    }

                    if (present == true){
                        System.out.println("Already enrolled in this course!");
                        continue;
                    }

                    st = con.createStatement();
                    sql = "select credits from course where id = (select course_id from offering where id = '"+section+"')";
                    rs = st.executeQuery(sql);

                    while(rs.next()){
                        if((credits + rs.getInt(1)) > 12){
                            present = true;
                            break;
                        }
                        credits+=rs.getInt(1);
                    }


                    if (present == true){
                        System.out.println("Enrolled in too many credits already! You are currently enrolled in "+credits+" credits!");
                        continue;
                    }

                    //credits += rs.getInt(1);
                    //st = con.createStatement();

                    sql = "insert into enroll (id, student_id, sem_id) values (?, ?, ?)";
                    PreparedStatement prep = con.prepareStatement(sql);
                    prep.setInt(1, enroll);
                    prep.setString(2, sel_stu);
                    prep.setString(3, section);
                    int row = prep.executeUpdate();
                    prep.close();

                    st = con.createStatement();
                    sql = "select first, last, student.id from student, enroll where enroll.id = "+enroll;
                    rs = st.executeQuery(sql);
                    while(rs.next()){
                         if(sel_stu.equals(rs.getString(3))){
                             System.out.println(String.format("%s %s has registered for section "+section+"!",         rs.getString(1), rs.getString(2)));
                         }
                    }
                    enroll++;
                }
            }
        }
        catch(ClassNotFoundException cnfe){
            cnfe.printStackTrace();
            System.err.println(cnfe);
        }
        catch (SQLException e){
            System.err.println(e);
        }
    }
}
