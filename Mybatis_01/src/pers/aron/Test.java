package pers.aron;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Test {
	//��Ϥ�������ļ��� ӳ���ļ���ҵ��ģ�ͣ������Ĳ������
	public static void main(String[] args) throws IOException {
		String resource = "config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = factory.openSession();
		
		String statement = "pers.aron.userMapper.getUser";
		User user = session.selectOne(statement, 2);
		System.out.println(user);
	}
}
