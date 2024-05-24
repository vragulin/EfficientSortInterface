
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author V. Ragulin
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }

    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
       
        for (int i=0; i< in.size(); i++) {
    
            if (checkInSortedOrder(in)) {
                System.out.printf("Number of passes: %d\n", i);
                break;
            }
            
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
    
    }
        
        public int getLargestDepth(ArrayList<QuakeEntry> quakes, int from) {
        int deepestIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            double deepestSoFar = quakes.get(deepestIdx).getDepth();
            double depth = quakes.get(i).getDepth();
            if (depth < deepestSoFar) deepestIdx = i;
        }
        return deepestIdx;
    }
    
    public void sortByLargestDepth(ArrayList<QuakeEntry> in){

        //for the quiz, set i<70
        //int limit = in.size();
        int limit = 70;
        for (int i=0; i<limit; i++){
            int indDeepest = getLargestDepth(in, i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qDeepest = in.get(indDeepest);
            in.set(i, qDeepest);
            in.set(indDeepest, qi);
        }
    }
    
    
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {
        
        for (int i=0; i<quakeData.size()-1-numSorted; i++){
            QuakeEntry q1 = quakeData.get(i);
            QuakeEntry q2 = quakeData.get(i+1);

            if(q1.getMagnitude()>q2.getMagnitude()) {
                quakeData.set(i, q2);
                quakeData.set(i+1,q1);
            }
        }
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in){
        for (int i=0; i<in.size(); i++)
            onePassBubbleSort(in, i);
    }
    
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes) {
        for (int i=0; i<quakes.size()-1; i++){
                if (quakes.get(i).getMagnitude() > quakes.get(i+1).getMagnitude()) 
                    return false;
        }
        return true;
    }
    
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in){
        
        for (int i=0; i<in.size(); i++){
            if (checkInSortedOrder(in)) {
                System.out.printf("Number of passes: %d\n", i);
                break;
            }
            onePassBubbleSort(in, i);
        }
    }
    
    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
        //String source = "data/earthquakeDataSampleSix2.atom";
        String source = "data/earthquakeDataWeekDec6sample2.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        sortByLargestDepth(list);
        //sortByMagnitudeWithBubbleSort(list);
        //sortByMagnitudeWithBubbleSortWithCheck(list);
        //sortByMagnitudeWithCheck(list);
        for (QuakeEntry qe: list) {
            System.out.println(qe);
        } 
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }
        
    }
}
