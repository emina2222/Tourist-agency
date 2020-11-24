package com.honeybee.app.dao;

import java.sql.CallableStatement;
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

import com.honeybee.app.entity.Kategorija;
import com.microsoft.sqlserver.jdbc.SQLServerException;

@Repository
public class KategorijaImpl implements KategorijaDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<Kategorija> getAllCategories() {
		
		Session session=sessionFactory.getCurrentSession();
		
		List<Kategorija> kategorije = session.createQuery("from GetAllCategories").getResultList();
		return kategorije;
	}

	@Override
	@Transactional
	public String getCategoryName(int id) {
		Session session = sessionFactory.getCurrentSession();

		String naziv=null;
		naziv = session.doReturningWork(new ReturningWork<String>() {

			@Override
			public String execute(Connection connection) throws SQLException {

				String naziv="";
				PreparedStatement stmt = null;
				try {
					String sqlString = "select * from prikaziNazivKategorije(?)";
					stmt = connection.prepareCall(sqlString);
					stmt.setInt(1, id);

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						naziv=rs.getString("naziv");
					}
				} finally {
					stmt.close();
				}
				return naziv;
			}

		});

		return naziv;
	}


	@Override
	@Transactional
	public List<Kategorija> sveKategorije() {
		Session session = sessionFactory.getCurrentSession();

		List<Kategorija> kat=null;
		kat = session.doReturningWork(new ReturningWork<List<Kategorija>>() {

			@Override
			public List<Kategorija> execute(Connection connection) throws SQLException {

				List<Kategorija> kat=new ArrayList<Kategorija>();
				PreparedStatement stmt = null;
				try {
					String sqlString = "select * from kategorija_aranzmana";
					stmt = connection.prepareCall(sqlString);

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						Kategorija a=new Kategorija(rs.getInt("ID_KATEGORIJE"),rs.getString("NAZIV")
								,rs.getString("OPIS"),rs.getInt("GRUPA"));
						kat.add(a);
					}
				} finally {
					stmt.close();
				}
				return kat;
			}

		});

		return kat;
	}

	@Override
	@Transactional
	public String addNewCategoryWithGroup(String naziv, String opis, int grupa) {
		Session session=sessionFactory.getCurrentSession();
		String rezultat=null;
		
		rezultat = session.doReturningWork(new ReturningWork<String>() {

			@Override
			public String execute(Connection connection) throws SQLException {

				String rez="";
				CallableStatement cstmt=null;
				try {
					String sqlString="{call unosKategorijeSaGrupom (?,?,?,?)}";
					cstmt = connection.prepareCall(sqlString);
					cstmt.setString(1, naziv);
					cstmt.setString(2, opis);
					cstmt.setInt(3, grupa);
					cstmt.registerOutParameter(4, java.sql.Types.NVARCHAR);

					cstmt.executeUpdate();
					rez=cstmt.getString(4);
				} 
				catch(SQLServerException ex) {
					//ex.printStackTrace();
					rez="Došlo je do greške sa bazom.";
				}
				finally {
					cstmt.close();
				}
				return rez;
			}

		});

		return rezultat;
	}

	@Override
	@Transactional
	public Integer getCategoryGroup(int id) {
		Session session = sessionFactory.getCurrentSession();

		Integer grupa=null;
		grupa = session.doReturningWork(new ReturningWork<Integer>() {

			@Override
			public Integer execute(Connection connection) throws SQLException {

				int grupa=0;
				PreparedStatement stmt = null;
				try {
					String sqlString = "select grupa from kategorija_aranzmana where id_kategorije=?";
					stmt = connection.prepareCall(sqlString);
					stmt.setInt(1, id);

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						grupa=rs.getInt("grupa");
					}
				} finally {
					stmt.close();
				}
				return grupa;
			}

		});

		return grupa;
	}

	@Override
	@Transactional
	public Integer getCategoryGroupForArrangement(int idAranzmana) {
		Session session = sessionFactory.getCurrentSession();

		Integer grupa=null;
		grupa = session.doReturningWork(new ReturningWork<Integer>() {

			@Override
			public Integer execute(Connection connection) throws SQLException {

				int grupa=0;
				PreparedStatement stmt = null;
				try {
					String sqlString = "select * from prikaziGrupuZaAranzman(?)";
					stmt = connection.prepareCall(sqlString);
					stmt.setInt(1, idAranzmana);

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						grupa=rs.getInt("grupa");
					}
				} finally {
					stmt.close();
				}
				return grupa;
			}

		});

		return grupa;
	}


}
