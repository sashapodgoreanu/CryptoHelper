/*
 * Copyright 2014 st116629.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package util.temp.ExperimentSaveObject;

/**
 *
 * @author st116629
 */


import cryptohelper.service.DBController;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.sql.PreparedStatement;


public class SaveObject {

    public Object javaObject=null;

    public Object getJavaObject() {
        return javaObject;
    }


    public void setJavaObject(Object javaObject) {
        this.javaObject = javaObject;
    }


    
/*

    public Object getObject() throws Exception
    {
        Object rmObj=null;
        Connection conn=/// get connection string;
        PreparedStatement ps=null;
        ResultSet rs=null;
        String sql=null;

        sql="select * from javaobject where id=1";

        ps=conn.prepareStatement(sql);

        rs=ps.executeQuery();

        if(rs.next())
        {
            ByteArrayInputStream bais;

            ObjectInputStream ins;

            try {

            bais = new ByteArrayInputStream(rs.getBytes("javaObject"));

            ins = new ObjectInputStream(bais);

            MyClass mc =(MyClass)ins.readObject();

            System.out.println("Object in value ::"+mc.getSName());
            ins.close();

            }
            catch (Exception e) {

            e.printStackTrace();
            }

        }

        return rmObj;
    }*/
}


