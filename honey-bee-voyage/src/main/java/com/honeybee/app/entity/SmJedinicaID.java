package com.honeybee.app.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class SmJedinicaID implements Serializable{

	SmestajID smestajId;
	int id_smestajne_jedinice;
}
