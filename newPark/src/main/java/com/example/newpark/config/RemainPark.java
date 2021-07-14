package com.example.newpark.config;

import com.example.newpark.dto.Park;
import org.springframework.context.annotation.Configuration;

import java.io.*;

@Configuration
public class RemainPark {

    public Park readSetting() throws IOException {
        BufferedReader read = new BufferedReader(new FileReader("./setting.txt"));
        Park park = new Park();
        park.setRemain(Integer.parseInt(read.readLine()));
        park.setBase(Integer.parseInt(read.readLine()));
        park.setDay(Integer.parseInt(read.readLine()));
        park.setWeek(Integer.parseInt(read.readLine()));
        park.setMonth(Integer.parseInt(read.readLine()));
        park.setTreeMonth(Integer.parseInt(read.readLine()));
        read.close();

        return park;

    }

    public void writeSetting(Park park) throws Exception {
        BufferedWriter write = new BufferedWriter(new FileWriter("./setting.txt"));

        write.write(park.getRemain()+"\n");
        write.write(park.getBase()+"\n");
        write.write(park.getDay()+"\n");
        write.write(park.getWeek()+"\n");
        write.write(park.getMonth()+"\n");
        write.write(park.getTreeMonth()+"\n");

        write.flush();
        write.close();

    }

}
