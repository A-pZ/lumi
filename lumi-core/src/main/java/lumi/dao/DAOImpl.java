package lumi.dao;

import java.util.List;

import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

/**
 * <pre>
 * DAO
 * データベースにアクセスする実装。
 * </pre>
 *
 * @author A-pZ ( Serendipity 3 ./ as sundome goes by. )
 * @version 1.0.0
 */
public class DAOImpl extends SqlSessionDaoSupport implements DAO {

	@Override
	public int insert(String queryString, Object object) {
		return getSqlSession().insert(queryString, object);
	}

	@Override
	public int update(String queryString, Object object) {
		int count = getSqlSession().update(queryString, object);

		return count;
	}

	@Override
	public int delete(String queryString, Object object) {
		return getSqlSession().delete(queryString, object);
	}

	/**
	 * カウント用SQL_ID変換。 Mybatisから実行されるSELECT COUNT(*)を用いるSQLIDには必ず、_countの接尾辞をつける。
	 *
	 * @param sqlid
	 *            SQL_ID
	 * @return [SQLID]_count
	 */
	private String getCountSqlId(String sqlid) {
		return sqlid + "_count";
	}

	@Override
	public void handledResult(String queryString, Object object,
			RowBounds rowBounds, ResultHandler resultHandler) throws Exception {
		getSqlSession().select(queryString, object, rowBounds, resultHandler);
	}

	@Override
	public <T> List<T> select(String queryString, Object object) {

		return getSqlSession().selectList(queryString, object);
	}

	@Override
	public int selectCount(String sqlid, Object param) {
		Integer count = (Integer) selectObject(getCountSqlId(sqlid), param);
		return count.intValue();
	}

	@Override
	public <T> Object selectObject(String queryString, Object object) {
		return getSqlSession().selectOne(queryString, object);
	}

	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
}
