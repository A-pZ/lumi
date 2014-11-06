package lumi.dao;

import java.util.List;

import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

/**
 * データベースを操作するDAOインタフェース。
 * Mybatis実装に合わせている。
 * @author A-pZ ( Serendipity 3 ./ as sundome goes by. )
 *
 */
public interface DAO {

	/**
	 * insertを実行します。
	 *
	 * @param queryString
	 *            SQLID
	 * @param object
	 *            条件モデル
	 * @return 追加件数
	 */
	int insert(String queryString, Object object);

	/**
	 * updateを実行します。
	 *
	 * @param queryString
	 *            SQLID
	 * @param object
	 *            条件モデル
	 * @return 更新件数
	 */
	int update(String queryString, Object object);

	/**
	 * deleteを実行します。
	 *
	 * @param queryString
	 *            SQLID
	 * @param object
	 *            条件モデル
	 * @return 更新件数
	 */
	int delete(String queryString, Object object);

	/**
	 * MybatisのResultHandlerを利用した検索クエリーの実行を行う。
	 * 行フェッチはこのメソッドを呼び出した先で、ResultHandlerを使うところで行う。
	 */
	void handledResult(String queryString, Object object,
			RowBounds rowBounds, ResultHandler resultHandler) throws Exception;

	/**
	 * selectを実行します。
	 *
	 * @param queryString
	 *            SQLID
	 * @param object
	 *            条件モデル
	 * @return 結果オブジェクトのリスト
	 */
	<T> List<T> select(String queryString, Object object);

	/**
	 * 最大件数を取得します。select count()用のメソッドです。
	 * selectCountは自動的にページング用SQLIDを使います。ページング用SQLIDは、接尾語として_countがつくルールにしています。
	 *
	 * @param sqlid
	 *            SQLID _countがついているSQLID（指定するときは_countをつけない！）
	 * @param param
	 *            パラメータ
	 * @return 検索結果
	 */
	int selectCount(String sqlid, Object param);

	/**
	 * selectを実行します。
	 *
	 * @param queryString
	 *            SQLID
	 * @param object
	 *            条件モデル
	 * @return 結果オブジェクト(単一)
	 */
	<T> Object selectObject(String queryString, Object object);

}