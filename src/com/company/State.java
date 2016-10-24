package com.company;

public class State {
    private boolean realTimeInterpolation = false;
    State(){
        System.out.println("State of the app is initialized:");
    }

    public void printState(){
        System.out.println("State of the app:");
        if(realTimeInterpolation)
            System.out.println("    Real time interpolation is on");
        else
            System.out.println("    Real time interpolation is off");
    }

    // getters and setters for realTimeInterpolation

    public boolean getValueOfRealTimeInterpolation(){
        return  realTimeInterpolation;
    }
    public void setValueOfRealTimeInterpolation(boolean newValue){
        realTimeInterpolation = newValue;
    }

}
