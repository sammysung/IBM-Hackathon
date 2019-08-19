import java.sql.*;

public class test{
    public static void main(String[] args){
        System.out.println("Hello World!");
        String url;
        url = "jdbc:db2://127.0.0.1:3306/test2?autoReconnect=true&useSSL=false";
        String userName = "sam";
        String password = "password";
        try{
            Class.forName("com.ibm.db2.jcc.DB2Driver");
            Connection con = DriverManager.getConnection(url, userName, password);
            Statement st = con.createStatement();

            String sql = "";

            ResultSet rs = st.executeQuery(sql);
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