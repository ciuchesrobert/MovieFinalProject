package com.sparta.moviefinalproject.DBObjects;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Geo {

    private ArrayList<Double> coordinates;

    private String type;
}
