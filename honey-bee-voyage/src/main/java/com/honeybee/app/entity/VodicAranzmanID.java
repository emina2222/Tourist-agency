package com.honeybee.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class VodicAranzmanID implements Serializable {

	@Column(name="ID_ARANZMANA")
	private int idAranzmana;
	
	@Column(name="ID_VODICA")
	private int idVodica;
}
