package com.honeybee.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.ReturningWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.honeybee.app.entity.Putnik;

@Repository
public class KlijentImpl implements KlijentDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public List<Putnik> showAllClients() {
		Session session = sessionFactory.getCurrentSession();

		List<Putnik> putnici=null;
		putnici = session.doReturningWork(new ReturningWork<List<Putnik>>() {

			@Override
			public List<Putnik> execute(Connection connection) throws SQLException {

				List<Putnik> putnici=new ArrayList<Putnik>();
				PreparedStatement stmt = null;
				try {
					String sqlString = "select * from putnik";
					stmt = connection.prepareCall(sqlString);

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						Putnik a=new Putnik(rs.getInt("ID_PUTNIKA"),rs.getInt("ID_KREDENCIJALA"),rs.getString("IME")
								,rs.getString("PREZIME"),rs.getString("BROJ_PASOSA"),rs.getString("BROJ_LICNE_KARTE")
								,rs.getString("BROJ_TELEFONA"),rs.getString("EMAIL"));
						putnici.add(a);
					}
				} finally {
					stmt.close();
				}
				return putnici;
			}

		});

		return putnici;
	}

}
