package com.honeybee.app.config;

import java.sql.Types;

import org.hibernate.dialect.SQLServer2012Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.ObjectType;

public class MyMssqlDialect extends SQLServer2012Dialect{

	public MyMssqlDialect() {
		super();
		registerHibernateType(Types.JAVA_OBJECT,"Putnik");
		//registerFunction("profil", new StandardSQLFunction("profil"));
		registerFunction("profil", new SQLFunctionTemplate(ObjectType.INSTANCE, "profil"));
	}
}
