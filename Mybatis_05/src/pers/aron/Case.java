package pers.aron;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import pers.aron.bean.ConditionUser;
import pers.aron.bean.User;

//��̬SQL��ģ����ѯ
public class Case {
	
	private SqlSession session;
	
	@Before
	public void setUp() throws IOException {
		String resource = "config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
		//Ĭ�����ֶ��ύ��
		session = factory.openSession();
		//�Զ��ύ
		//session = factory.openSession(true);
	}
	

	@Test
	public void select(){
		String statement = "pers.aron.userMapper.getUser";
		ConditionUser parameter = new ConditionUser("%o%",13,18);
		List<User> list = session.selectList(statement, parameter);
		session.commit();
		System.out.println(list);
		session.close();
	}
	

}
