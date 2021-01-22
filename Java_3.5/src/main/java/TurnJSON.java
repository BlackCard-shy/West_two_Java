import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.sql.*;

public class TurnJSON {
    public static void turnData(int flag) {
        String response = APIGet.GetData("https://covid-api.mmediagroup.fr/v1/cases");
        String[] Countries = {"China", "US", "United Kingdom", "Japan"};
        JSONObject text = JSONObject.parseObject(response);
        Connection conn_0=null;
        try {
            conn_0 = ConnectSQL.getConnection();
            conn_0.setAutoCommit(false);

            String sql_0=" TRUNCATE TABLE Country";
            PreparedStatement st_0=conn_0.prepareStatement(sql_0, Statement.RETURN_GENERATED_KEYS);
            st_0.executeUpdate();

            String sql_00=" TRUNCATE TABLE City";
            PreparedStatement st_00=conn_0.prepareStatement(sql_00, Statement.RETURN_GENERATED_KEYS);
            st_00.executeUpdate();

            //提交事务
            conn_0.commit();
            if(flag==1)
            System.out.println("Initialization successful.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        for(String e:Countries){
            JSONObject  CountryObject = text.getJSONObject(e);
            JSONObject  ALLObject=CountryObject.getJSONObject("All");
            Connection conn=null;
            PreparedStatement st =null,stt=null;
            try {
                conn = ConnectSQL.getConnection();
                conn.setAutoCommit(false);
                String sql="INSERT INTO Country(Confirmed,Recovered,Deaths,Country,Population," +
                        "Sq_km_area,Life_expectancy,Elevation_in_meters,Continent,Abbreviation,Location," +
                        "Iso,Capital_city,Country_Lat,Country_Long,Country_Updated) " +
                        "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) " ;
                st=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                st.setInt(1,ALLObject.getIntValue("confirmed"));
                st.setInt(2,ALLObject.getIntValue("recovered"));
                st.setInt(3,ALLObject.getIntValue("deaths"));
                st.setString(4,ALLObject.getString("country"));
                st.setInt(5,ALLObject.getIntValue("population"));
                st.setInt(6,ALLObject.getIntValue("sq_km_area"));
                st.setString(7,ALLObject.getString("life_expectancy"));
                st.setInt(8,ALLObject.getIntValue("elevation_in_meters"));
                st.setString(9,ALLObject.getString("continent"));
                st.setString(10,ALLObject.getString("abbreviation"));
                st.setString(11,ALLObject.getString("location"));
                st.setInt(12,ALLObject.getIntValue("iso"));
                st.setString(13,ALLObject.getString("capital_city"));
                st.setString(14,ALLObject.getString("lat"));
                st.setString(15,ALLObject.getString("long"));
                st.setString(16,ALLObject.getString("updated"));
                //st.executeUpdate();

                int i = -1;
                if(st.executeUpdate() > 0 ) {
                    /**
                     * 获取刚刚插入进去的记录中关注的那几列的值
                     */
                    ResultSet rs = st.getGeneratedKeys();
                    if (rs.next()) {
                        //获取deptno的值(int)--现在只关注此列
                        i = rs.getInt(1);//此处建议使用数字,getInt("deptno")对于不同的数据库版本可能不支持
                    }
                    rs.close();
                }
                CountryObject.remove("All");
                for (String key : CountryObject.keySet()) {
                    //System.out.println(key);
                    JSONObject  CityObject=CountryObject.getJSONObject(key);
                    //saveDataProvince(countryData.getJSONObject(key), key, countryId);// 解析其他部分数据
                    String sqll="INSERT INTO City(Country_Id,city_name,city_lat,city_long,city_confirmed," +
                            "city_recovered,city_deaths,city_updated) VALUES (?,?,?,?,?,?,?,?)";
                    stt=conn.prepareStatement(sqll);
                    stt.setInt(1,i);
                    stt.setString(2,key);
                    stt.setString(3,CityObject.getString("lat"));
                    stt.setString(4,CityObject.getString("long"));
                    stt.setInt(5,CityObject.getIntValue("confirmed"));
                    stt.setInt(6,CityObject.getIntValue("recovered"));
                    stt.setInt(7,CityObject.getIntValue("deaths"));
                    stt.setString(8,CityObject.getString("updated"));
                    stt.executeUpdate();
                    //System.out.println("1");
                }
                //System.out.println("zhujian"+i);
                //提交事务
                conn.commit();
                if(flag==1)
                System.out.println("Update Successful.");

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                ConnectSQL.endConnection(conn);
            }
        }
    }
    public static void queryData(String City_name){
        String response = APIGet.GetData("https://covid-api.mmediagroup.fr/v1/cases");
        JSONObject message = JSONObject.parseObject(response);
        Connection connect=null;
        try {
            connect = ConnectSQL.getConnection();
            connect.setAutoCommit(false);
            String sql="SELECT * from City WHERE city_name=?";

            PreparedStatement st=connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1,City_name);
            ResultSet rs=st.executeQuery();
            while (rs.next()) {
                int city_id = rs.getInt(1);
                int country_id = rs.getInt(2);
                String city_name = rs.getString(3);
                String city_lat = rs.getString(4);
                String city_long = rs.getString(5);
                int city_confirmed = rs.getInt(6);
                int city_recovered = rs.getInt(7);
                int city_deaths = rs.getInt(8);
                String city_updated = rs.getString(9);
                System.out.println("city_name:" + city_name);
                System.out.println("lat:" + city_lat + "    long:" + city_long);
                System.out.println("confirmed:" + city_confirmed + "    recovered:" + city_recovered + "    deaths:" + city_deaths);
                System.out.println("updated:" + city_updated);
            }
           // st.executeUpdate();

            //提交事务
            //connect.commit();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
