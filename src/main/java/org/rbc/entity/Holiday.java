package org.rbc.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "HOLIDAY")
@IdClass(HolidayID.class)
public class Holiday implements Comparable<Holiday>{

   /* @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;*/
    @Id
    @Column(nullable = false, length = 100,name = "holiday_name")
    String name;
    @Id
    @Column(nullable = false,name = "country")
    String country;

    @Column(nullable = false,name="holiday_date")
    String date;
    @Column(nullable = true,name = "state")
    String state;

    @Override
    public int compareTo(Holiday compHol) {
        String[] currHolSplit=this.getDate().split("-");
        String[] compHolSplit=compHol.getDate().split("-");
        if(Integer.valueOf(currHolSplit[0])>Integer.valueOf(compHolSplit[0])){
            return 1;
        }else if(Integer.valueOf(currHolSplit[0])<Integer.valueOf(compHolSplit[0])){
            return -1;
        }else{
            if(Integer.valueOf(currHolSplit[1])>Integer.valueOf(compHolSplit[1])){
                return 1;
            }else{
                return -1;
            }
        }

    }
}

