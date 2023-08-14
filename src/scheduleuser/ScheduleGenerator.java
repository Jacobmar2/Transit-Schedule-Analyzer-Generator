package scheduleuser;

import java.util.ArrayList;

public class ScheduleGenerator {

    //variables
    private boolean isPM;
    private ArrayList<Integer> sched = new ArrayList<Integer>(); //uses Integer
    private ArrayList<String> sched2 = new ArrayList<String>(); //uses String
    private int start;
    private int end;

	public int timeToMins(String time){ //converts given time (ex. 12:34 pm) into mins from midnight (ex. 754)
        //function assumes correct time format
        int mins = 0;
        char[] timeParts = time.toCharArray();
        if (timeParts[1] == ':'){   //hour is 1-9 with X:XX xm format
            mins = 60*(timeParts[0]-48) + 10*(timeParts[2]-48) + (timeParts[3]-48);
            if (timeParts[5] == 'p')
                mins += 720;
        }
        else{   //hour is 10-12 with XX:XX xm format
            mins = 600 + 60*(timeParts[1]-48) + 10*(timeParts[3]-48) + (timeParts[4]-48);
            if (timeParts[6] == 'p' && timeParts[1] != '2')
                mins += 720;
            if (timeParts[6] == 'a' && timeParts[1] == '2')
                mins += 720;
        }


        if (mins < 270)
            mins += 1440;
        else if (mins > 1709)
            mins -= 1440;

        //System.out.println(mins);
        return mins;
    }

    //default: new day starts from 4:30 am so min mins is 270, max mins is 1709
    public String minsToTime(int mins){ //converts mins from midnight to 12 hour time format
        //has built in valid mins detector
        String time = "";
        if (mins >= 600 && mins <= 779) {
            time += "1";
            time += (mins-600)/60;
        }
        else if (mins >= 1320 && mins <= 1499){
            time += "1";
            time += (mins-1320)/60;
        }
        else if (mins >= 270 && mins < 600){
            time += mins/60;
        }
        else if (mins > 779 && mins < 1320){
            time += (mins-720)/60;
        }
        else if (mins > 1499 && mins <= 1709){
            time += (mins-1440)/60;
        }
        else{
            System.out.println("not a valid time");
            return null;
        }
        time += ':';
        if (mins % 60 < 10)
            time += '0';
        time += mins % 60;
        time += " ";
        if (mins >= 720 && mins <= 1440)
            time += "p";
        else
            time += "a";
        time += 'm';

        //System.out.println(time);
        return time;
    }

    public ArrayList<Integer> minsToSchedule(int start, int end, int hw){
        ArrayList<Integer> subSched = new ArrayList<Integer>();
        for (int i = start; i <= end; i += hw){
            subSched.add(i);
        }
        this.start = start;
        this.end = end;

        return subSched;
    }

    public void addToSchedule(ArrayList<Integer> times){
        //for schedule overwriting
        int startInd = indexOf(start,true);
        int endInd = indexOf(end,false);

        System.out.println(startInd + " --- " + endInd);

        if (startInd >= 0 && startInd <= endInd){
            for (int i = startInd; i <= endInd; i++) {
                sched.remove(startInd);
                sched2.remove(startInd);
            }
        }


        //main part of function
        if (endInd == 0){
            int ind = 0;
            for (Integer time : times) {
                sched.add(ind,time);
                sched2.add(ind,minsToTime(time));
                ind++;
            }
        }
        else if (startInd == -1 || startInd == endInd) {
            for (Integer time : times) {
                sched.add(time);
                sched2.add(minsToTime(time));
            }
        }
        else
            for (Integer time : times) {
                sched.add(startInd, time);
                sched2.add(startInd, minsToTime(time));
                startInd++;
            }
    }

    public void printSchedule(){
        for (String s : sched2) System.out.println(s);
        //same as
        //for (int i = 0; i < sched2.size(); i++)
        //  System.out.println(sched2.get(i));
    }

    private boolean timesAreValid(int start, int end){ //checks if end time is ahead of start time
        return start < end;
    }

    private int indexOf(int time, boolean start){
        for(int i = 0; i < sched.size(); i++){
            if (sched.get(i) >= time)
                return i;
            else if (sched.get(i) > time){
                if (start)
                    return i;
                else
                    return i-1;
            }
        }
        return sched.size()-1;
    }

    private int indexOfList(int mins, boolean isAfter){
        int index = 0;
        for (int i = 0; i < sched.size(); i++){
            if (sched.get(i) > mins && index == 0)
                index = i;
        }
        if (!isAfter)
            index--;
        return index;
    }

    private boolean validTimeFormat(String time){
        //TODO
        //must be XX:XX xm format
        //no longer than 8 characters
        //first XX must be 1 <= XX <= 12
        //second XX must be 0 <= xx <= 59
        //third x must be a or p, can be capital or lowercase (lowercase is default)

        return true;
    }

}