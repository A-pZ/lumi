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
	 * @param queryString SQLID
	 * @param object 条件モデル
	 * @param rowBounds {@link org.apache.ibatis.session.RowBounds}
	 * @param resultHandler {@link org.apache.ibatis.session.ResultHandler}
	 * @throws Exception SQL実行時例外やハンドラ内で発生した例外
	 */
	void handledResult(String queryString, Object object,
			RowBounds rowBounds, @SuppressWarnings("rawtypes") ResultHandler resultHandler) throws Exception;

	/**
	 * selectを実行します。
	 *
	 * @param queryString
	 *            SQLID
	 * @param object
	 *            条件モデル
	 * @param <T>
	 *            検索結果で返されるListの要素の型、クラス
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
	 * @return 検索結果（レコード数）
	 */
	int selectCount(String sqlid, Object param);

	/**
	 * selectを実行します。
	 *
	 * @param queryString
	 *            SQLID
	 * @param object
	 *            条件モデル
	 * @param <T>
	 *            検索結果で返される型、クラス
	 * @return 結果オブジェクト(単一)
	 */
	<T> Object selectObject(String queryString, Object object);

}