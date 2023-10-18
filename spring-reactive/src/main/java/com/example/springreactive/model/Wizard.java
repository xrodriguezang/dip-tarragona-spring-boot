package com.example.springreactive.model;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wizard {
	private UUID id;
	private String name;
	private List<String> alternateNames;
	private String species;
	private String gender;
	private String house;
	private String dateOfBirth;
	private Integer yearOfBirth;
	private Boolean wizard;
	private String ancestry;
	private String eyeColour;
	private String hairColour;
	private Object wand;
	private String patronus;
	private Boolean hogwartsStudent;
	private Boolean hogwartsStaff;
	private String actor;
	private List<String> alternate_actors;
	private Boolean alive;
	private String image;
}
