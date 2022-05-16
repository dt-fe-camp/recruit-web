package recruit;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlTool {
  public SqlSession createSqlSession() {
    InputStream iStream;
    try {
      iStream = Resources.getResourceAsStream("mybatisConfig.xml");
    } catch (IOException e) {
      System.out.println("load error: " + e.toString());
      return null;
    }

    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(iStream, "development");
    SqlSession sqlSession = sqlSessionFactory.openSession();

    return sqlSession;
  }
}
