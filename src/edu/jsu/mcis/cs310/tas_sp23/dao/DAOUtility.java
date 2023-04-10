package edu.jsu.mcis.cs310.tas_sp23.dao;

import java.time.*;
import java.util.*;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
import com.github.cliftonlabs.json_simple.*;

import edu.jsu.mcis.cs310.tas_sp23.Punch;
import edu.jsu.mcis.cs310.tas_sp23.Shift;


public final class DAOUtility {

    public static String getPunchListPlusTotalsAsJSON(ArrayList<Punch> punchlist, Shift shift) {
        List<Map<String, String>> punchDataList = new ArrayList<>();
        
        for (Punch punch : punchlist) {
            Map<String, String> punchData = new HashMap<>();
            punchData.put("id", Integer.toString(punch.getId()));
            punchData.put("badgeid", punch.getBadge().getId());
            // Add other punch properties as needed
            
            punchDataList.add(punchData);
        }
        
        int totalMinutes = calculateTotalMinutes(punchlist, shift);
        double absenteeism = calculateAbsenteeism(punchlist, shift);
        
        Map<String, Object> resultData = new HashMap<>();
        resultData.put("punches", punchDataList);
        resultData.put("totalminutes", Integer.toString(totalMinutes));
        resultData.put("absenteeism", String.format("%.2f", absenteeism));
        
        String jsonString = Jsoner.serialize(resultData);
        
        return jsonString;
    }


}