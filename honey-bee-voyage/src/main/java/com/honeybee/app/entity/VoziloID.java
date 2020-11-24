package com.honeybee.app.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class VoziloID implements Serializable{

	PrevoznikID prevoznikId; //pk-fk iz tabele Prevoznik
	int id_vozila; //id iz tabele Dostupno vozilo
}
